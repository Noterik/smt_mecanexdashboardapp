/* 
* CollectionOverview.java
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
package org.springfield.lou.application.types.components;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;
import org.springfield.lou.screen.Screen;

/**
 * CollectionOverview.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types.components
 * 
 */
public class CollectionOverview extends ScreenComponent {
	
	public CollectionOverview(Screen s) {
		super(s);
	}
	
	public void receiveMessage(Screen s, String function, String arguments, String[] args) {
		if (function.equals("")) {
        	
        }
	}
	
	public void onLogin(Screen s, Object user) {
		JSONArray availableCollections = getAvailableCollectionsInfo(getAvailableCollections());
		JSONArray userCollections = getUserCollectionsInfo(getUserCollections(s));
		
		render();

		s.putMsg(target, "app", "setAvailableCollections("+availableCollections+")");
		s.putMsg(target, "app", "setUserCollections("+userCollections+")");
	}
	
	private List<FsNode> getAvailableCollections() {
		//TODO: not hardcoded users for collections;
		List<FsNode> collections = Fs.getNodes("/domain/mecanex/user/luce/collection", 1);
		
		return collections;
	}
	
	private JSONArray getAvailableCollectionsInfo(List<FsNode> collections) {
		JSONArray availableCollections = new JSONArray();
		
		for (FsNode collection : collections) {
			String title = collection.getProperty("title", "-");
			String description = collection.getProperty("description", "-");
			
			System.out.println("Title "+title+" description "+description);
			
			System.out.println("Collection path = "+collection.getPath());
			int results = Fs.getTotalResultsAvailable(collection.getPath()+"/video");
			System.out.println("results = "+results);
			
			JSONObject coll = new JSONObject();
			coll.put("title", title);
			coll.put("description", description);
			coll.put("uri", collection.getPath());
			coll.put("results", results);
			
			availableCollections.add(coll);
		}
		return availableCollections;
	}
	
	private List<FsNode> getUserCollections(Screen s) {
		List<FsNode> collections = Fs.getNodes("/domain/"+s.getApplication().getDomain()+"/user/"+s.getProperty("user")+"/collection", 1);
		return collections;
	}
	
	private JSONArray getUserCollectionsInfo(List<FsNode> collections) {
		JSONArray userCollections = new JSONArray();
		
		for (FsNode collection : collections) {
			String title = collection.getProperty("title", "-");
			String description = collection.getProperty("description", "-");
			
			int results = Fs.getTotalResultsAvailable(collection.getPath()+"/video");
			
			JSONObject coll = new JSONObject();
			coll.put("title", title);
			coll.put("description", description);
			coll.put("uri", collection.getPath());			
			coll.put("results", results);
			
			userCollections.add(coll);
		}		
		return userCollections;
	}
	
	private void getCollection(String uri, int start, int limit) {		
		int totalAvailableResults = Fs.getTotalResultsAvailable(uri);
		
		List<FsNode> videos = Fs.getNodes(uri, 0, start, limit);
	}
}
