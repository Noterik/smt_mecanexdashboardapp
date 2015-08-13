package org.springfield.lou.application.types.workflow.collectiondef;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springfield.lou.application.types.workflow.element.form.DropDownController;
import org.springfield.lou.controllers.Html5Controller;
import org.springfield.lou.screen.Screen;

public class CollectiondefController extends Html5Controller {
	
	public CollectiondefController() {
		
	}
	
	public void attach(String sel) {
		selector = sel;
		loadHtml();
		screen.get("#license").attach(new DropDownController());
		screen.bind("#license","client","dropDownOptionSelected", this);
	}
	
	public void dropDownOptionSelected(Screen s, JSONObject data) {
		if (data.containsKey("options")) {			
			JSONArray options = (JSONArray) data.get("options");
			for (int i = 0; i < options.size(); i++) {
				System.out.println(options.get(i));
			}
		}
	}
	
	private void loadHtml() {
		JSONObject data = new JSONObject();	
		data.put("language",screen.getLanguageCode());
		data.put("id",screen.getId());
		screen.get(selector).parsehtml(data);
	}
}
