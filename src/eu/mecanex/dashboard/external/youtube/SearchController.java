/* 
* SearchController.java
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
package eu.mecanex.dashboard.external.youtube;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

/**
 * SearchController.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package eu.mecanex.dashboard.external.youtube
 * 
 */
public class SearchController {
	
	private long MAX_RESULTS = 50;
	private static String API_KEY_PATH = "/springfield/homer/youtube.api.key";
	
	private static String apiKey;
	
	private static YouTube youtube;
	
	private static SearchController instance = new SearchController();
	
	
	//constructor
	public SearchController() {
		instance = this;
		loadApiKey();
	}
	
	public static SearchController instance() {
		return instance;
	}
	
	public void doSearch(String query) {
		if (apiKey == null) {
			System.out.println("ERROR: Youtube API search key not loaded");
			return;
		}
		
		try {
			youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory() ,new HttpRequestInitializer() {
	        	public void initialize(HttpRequest request) throws IOException {
	            }
	        }).setApplicationName("mecanex-dashboard-youtube-search").build();
			
			//Define the API request for retrieving search results.
			YouTube.Search.List search = youtube.search().list("id,snippet");
			
			//API key
			search.setKey(apiKey);
			//Only search for videos
			search.setType("video");
			//Query
			search.setQ(query);
			//Only get fields needed
			search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
			//Set max results, limited by Google?
			search.setMaxResults(MAX_RESULTS);
			
			
			SearchListResponse searchResponse = search.execute();
			
		} catch (IOException e) {
			//TODO: handle
		}
		
		//TODO: add filter options
		// Filter options:
		// videoLicense
		// safeSearch 
		// relevanceLanguage
		// regioCode		
	}
	
	private void loadApiKey() {		
		File keyFile = new File(API_KEY_PATH);
		if (!keyFile.exists()) {
			System.out.println("ERROR: Youtube API search key not defined in "+API_KEY_PATH);
			return;
		}
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(keyFile));
			apiKey = reader.readLine();
			reader.close();
		} catch (IOException e) {
			System.out.println("ERROR: Could not read Youtube API key file "+API_KEY_PATH);
		} 
	}	
}
