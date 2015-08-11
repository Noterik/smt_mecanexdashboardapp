package org.springfield.lou.application.types.workflow.editorial;

import org.json.simple.JSONObject;
import org.springfield.lou.controllers.Html5Controller;

public class EditorialController extends Html5Controller {
	
	public EditorialController() {
		
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
