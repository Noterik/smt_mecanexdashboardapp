package org.springfield.lou.application.types.workflow.annotations;

import org.json.simple.JSONObject;
import org.springfield.lou.controllers.Html5Controller;

public class AnnotationsController extends Html5Controller {
	
	public AnnotationsController() {
		
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
