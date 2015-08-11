package org.springfield.lou.application.types.workflow.ingest;

import org.json.simple.JSONObject;
import org.springfield.lou.controllers.Html5Controller;

public class IngestController extends Html5Controller {
	
	public IngestController() {
		
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
