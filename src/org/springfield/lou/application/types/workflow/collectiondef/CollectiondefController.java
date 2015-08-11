package org.springfield.lou.application.types.workflow.collectiondef;

import org.json.simple.JSONObject;
import org.springfield.lou.controllers.Html5Controller;

public class CollectiondefController extends Html5Controller {
	
	public CollectiondefController() {
		
	}
	
	public void attach(String sel) {
		selector = sel;
		loadHtml();
	}
	
	private void loadHtml() {
		JSONObject data = new JSONObject();	
		data.put("language",screen.getLanguageCode());
		data.put("id",screen.getId());
		screen.get(selector).parsehtml(data);
	}
}
