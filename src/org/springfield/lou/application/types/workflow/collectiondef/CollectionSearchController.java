package org.springfield.lou.application.types.workflow.collectiondef;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springfield.lou.application.types.workflow.element.form.DropDownController;
import org.springfield.lou.application.types.workflow.element.form.InputFieldWithValidationController;
import org.springfield.lou.controllers.Html5Controller;
import org.springfield.lou.screen.Screen;

/* 
 * CollectionSearchController.java
 * 
 * Copyright (c) 2015 Noterik B.V.
 * 
 * This file is part of smt_mecanexdashboardapp, related to the Noterik Springfield project.
 *
 * smt_mecanexdashboardapp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * smt_mecanexdashboardapp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with smt_mecanexdashboardapp.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * CollectionSearchController.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package 
 * 
 */
public class CollectionSearchController extends Html5Controller {
	
	public CollectionSearchController() {
		//constructor
	}
	
	public void attach(String sel) {
		selector = sel;
		loadHtml();
		screen.get("#maxPrice").attach(new InputFieldWithValidationController());
		screen.bind("#maxPrice", "client", "inputFieldEntered", this);
		screen.get("#decade").attach(new DropDownController());
		screen.bind("#decade", "client", "dropDownOptionSelected", this);
		screen.get("#license").attach(new DropDownController());
		screen.bind("#license","client","dropDownOptionSelected", this);
	}
	
	public void inputFieldEntered(Screen s, JSONObject data) {
		System.out.println(data.toJSONString());
	}
		
	public void dropDownOptionSelected(Screen s, JSONObject data) {
		System.out.println(data.toJSONString());
		
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
