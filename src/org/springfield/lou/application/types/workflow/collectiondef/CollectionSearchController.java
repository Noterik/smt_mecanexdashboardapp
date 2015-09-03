package org.springfield.lou.application.types.workflow.collectiondef;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springfield.fs.FSList;
import org.springfield.fs.FSListManager;
import org.springfield.fs.FsNode;
import org.springfield.lou.application.types.workflow.element.form.DropDownController;
import org.springfield.lou.application.types.workflow.element.form.InputFieldController;
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
	
	private HashMap<String, String> query = new HashMap<String, String>();
	
	public CollectionSearchController() {
		//constructor
	}
	
	public void attach(String sel) {
		selector = sel;
		loadHtml();
		screen.get("#query").attach(new InputFieldController());
		screen.bind("#query", "client", "queryEntered", this);
		screen.get("#maxPrice").attach(new InputFieldWithValidationController());
		screen.bind("#maxPrice", "client", "inputFieldEntered", this);
		screen.get("#decade").attach(new DropDownController());
		screen.bind("#decade", "client", "decadeSelected", this);
		screen.get("#license").attach(new DropDownController());
		screen.bind("#license","client","licenseSelected", this);
	}
	
	public void queryEntered(Screen s, JSONObject data) {
		String value = (String) data.get("value");
		
		query.put("query", value);
		updateSearch();
	}
	
	public void inputFieldEntered(Screen s, JSONObject data) {
		String value = (String) data.get("value");
		
		query.put("price", value);
		updateSearch();
	}
		
	public void decadeSelected(Screen s, JSONObject data) {
		String decade = "";
		
		if (data.containsKey("options")) {			
			JSONArray options = (JSONArray) data.get("options");
			if (options.size() > 0) {
				decade = (String) ((JSONObject) options.get(0)).get("value");
			}
		}
		
		query.put("decade", decade);
		updateSearch();
	}

	public void licenseSelected(Screen s, JSONObject data) {
		String license = "";
		
		if (data.containsKey("options")) {			
			JSONArray options = (JSONArray) data.get("options");
			if (options.size() > 0) {
				license = (String) ((JSONObject) options.get(0)).get("value");
			}
		}
		
		query.put("license", license);
		updateSearch();
	}
	
	private void updateSearch() {
		String q = query.get("query");
		
		String uri = "/domain/mecanex/user/*/*";
		FSList fslist = FSListManager.get(uri);
		//TODO: now only handling query, ignoring other fields
		List<FsNode> nodes = fslist.getNodesFiltered(q);
		
		JSONObject results = FSList.parseNodeList(uri, nodes).toJSONObject(screen.getLanguageCode(), "provider,TitleSet_TitleSetInEnglish_title");
		results.put("provider", "Luce");
		
		screen.get("#collectionSearchResults").update(results);		
	}
	
	private void loadHtml() {
		JSONObject data = new JSONObject();	
		data.put("language",screen.getLanguageCode());
		data.put("id",screen.getId());
		screen.get(selector).parsehtml(data);
	}
}
