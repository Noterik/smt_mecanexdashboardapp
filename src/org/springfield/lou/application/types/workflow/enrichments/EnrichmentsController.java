package org.springfield.lou.application.types.workflow.enrichments;

import org.json.simple.JSONObject;
import org.springfield.lou.controllers.Html5Controller;

public class EnrichmentsController extends Html5Controller {
	
	public EnrichmentsController() {
		
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
