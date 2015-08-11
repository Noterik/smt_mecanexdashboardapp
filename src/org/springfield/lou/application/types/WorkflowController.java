package org.springfield.lou.application.types;

import org.json.simple.JSONObject;
import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;
import org.springfield.lou.controllers.FsListController;
import org.springfield.lou.controllers.Html5Controller;
import org.springfield.lou.screen.Screen;

public class WorkflowController extends Html5Controller {
	
	public WorkflowController() {
		
	}
	
	public void attach(String sel) {
		selector = sel;
		loadHtml();
       	screen.get("#workflowlist").attach(new FsListController()); 
       	screen.bind("#workflowlist","itemselected","itemselected", this);
       	
	}
	
    public void itemselected(Screen s,JSONObject data) {
    	FsNode node = Fs.getNode("/domain/mecanex/config/default/flowlist/"+data.get("itemid"));
    	if (node!=null) {
    		data = new JSONObject();	
    		data.put("command","switcharea");
    		data.put("name",node.getProperty("name"));
    		screen.get("#workarea").update(data);
    	}
    }
	
	private void loadHtml() {
		JSONObject data = new JSONObject();	
		data.put("language",screen.getLanguageCode());
		data.put("id",screen.getId());
		screen.get(selector).parsehtml(data);
	}
}
