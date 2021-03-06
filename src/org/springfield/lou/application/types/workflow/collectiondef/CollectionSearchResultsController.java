/* 
* CollectionSearchResultsController.java
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
package org.springfield.lou.application.types.workflow.collectiondef;

import org.json.simple.JSONObject;
import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;
import org.springfield.lou.application.types.MecanexCollection;
import org.springfield.lou.controllers.Html5Controller;
import org.springfield.lou.screen.Screen;

/**
 * CollectionSearchResultsController.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types.workflow.collectiondef
 * 
 */
public class CollectionSearchResultsController extends Html5Controller {
	
	private String template;
	private MecanexCollection collection;
	
	public CollectionSearchResultsController() {
		collection = new MecanexCollection();
	}
	
	public void attach(String sel) {
		selector = sel;
		if (screen!=null) {
			FsNode node = getControllerNode(selector);
			if (node!=null) {
				template = node.getProperty("template");

				screen.get(selector).loadScript(this);
				screen.get(selector).template(template);
			}
		}		
		loadHtml();
		screen.bind(selector,"client","addResult",this);
		bindOverride("addResult");
	}
	
	public void addResult(Screen s,JSONObject data) {
		//add node 
		collection.add((String) data.get("itemId"), Fs.getNode("/domain/mecanex/user/luce/video/"+data.get("itemId")));
		
		JSONObject json = new JSONObject();
		json.put("items", collection.getSize());
		json.put("price", collection.getSize()*10);
		screen.get("#collectionOverview").update(json);	
	}
	
	private void loadHtml() {
		JSONObject data = new JSONObject();	
		data.put("language",screen.getLanguageCode());
		data.put("id",screen.getId());
		screen.get(selector).update(data);
	}
}
