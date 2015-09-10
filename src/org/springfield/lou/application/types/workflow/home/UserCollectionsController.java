/* 
* UserCollectionsController.java
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
package org.springfield.lou.application.types.workflow.home;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springfield.fs.FSList;
import org.springfield.fs.FSListManager;
import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;
import org.springfield.lou.controllers.Html5Controller;
import org.springfield.lou.screen.Screen;

/**
 * UserCollectionsController.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types.workflow.home
 * 
 */
public class UserCollectionsController extends Html5Controller {

	private String template;

	public UserCollectionsController() {
		//constructor
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
		
		//get user collections
		String user = (String) screen.getProperty("username");
		
		String uri = "/domain/mecanex/user/"+user+"/collection/";
		FSList fslist = FSListManager.get(uri);
		List<FsNode> nodes = fslist.getNodes();
		
		JSONObject results = FSList.parseNodeList(uri, nodes).toJSONObject(screen.getLanguageCode(), "title");
		
		screen.get("#userCollections").update(results);
		screen.bind("#userCollections","client","collectionHandler", this);
	}
	
	public void collectionHandler(Screen s,JSONObject data) {
		if (!data.containsKey("eventtype")) {
			return;
		}
		
		if (data.get("eventtype").equals("addCollection")) {
			
		}
		
		if (data.get("eventtype").equals("collectionDetails")) {
			if (!data.containsKey("collection")) {
				return;
			}
			
			String uri = (String) data.get("collection");
			//Workaround for uri that seems to contain double slash before the id from FsNode
			uri = uri.replace("//", "/");
			
			FsNode node = Fs.getNode(uri);
			//TODO:workaround to not disturb QANDR work on IBC, FsNode should have a direct toJSONObject function
			List<FsNode> nodes = new ArrayList<FsNode>();
			nodes.add(node);
			
			JSONObject results = FSList.parseNodeList(uri, nodes).toJSONObject(screen.getLanguageCode(), "title,description");		
			System.out.println(results.toJSONString());
			screen.get("#collectionDetails").update(results);
		}
	}
	
	private void loadHtml() {
		JSONObject data = new JSONObject();	
		data.put("language",screen.getLanguageCode());
		data.put("id",screen.getId());
		screen.get(selector).update(data);
	}
}
