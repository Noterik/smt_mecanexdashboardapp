package org.springfield.lou.application.types.workflow.home;

import org.json.simple.JSONObject;
import org.springfield.lou.controllers.Html5Controller;

public class HomeController extends Html5Controller {
	
	public HomeController() {
		
	}
	
	public void attach(String sel) {
		selector = sel;		
		loadHtml();
		//Attach after loadHtml() to make sure these templates get loaded
		screen.get("#userCollections").attach(new UserCollectionsController());
		screen.get("#collectionDetails").attach(new CollectionDetailsController());
	}
	
	private void loadHtml() {
		JSONObject data = new JSONObject();	
		data.put("language",screen.getLanguageCode());
		data.put("id",screen.getId());
		screen.get(selector).parsehtml(data);
	}
}
