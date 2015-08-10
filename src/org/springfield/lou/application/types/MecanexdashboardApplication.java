/* 
* MecanexdashboardApplication.java
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
package org.springfield.lou.application.types;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springfield.lou.application.Html5Application;
import org.springfield.lou.application.types.components.CollectionOverview;
import org.springfield.lou.application.types.components.Header;
import org.springfield.lou.application.types.components.Login;
import org.springfield.lou.application.types.components.ScreenComponent;
import org.springfield.lou.application.types.components.Tabs;
import org.springfield.lou.screen.Screen;

/**
 * MecanexdashboardApplication.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types
 * 
 */
public class MecanexdashboardApplication extends Html5Application {
	private static long AUTO_LOGOUT = 43200000l; //12 hours
	
	private LinkedHashMap<String, ScreenComponent> components;
	
	private Header header;
	private Login login;
	private Tabs tabs;
	private CollectionOverview collectionOverview;
	
	public MecanexdashboardApplication(String id) {
		super(id);
		this.setSessionRecovery(true);
		this.addToRecoveryList("user");
		this.addToRecoveryList("firstname");
		this.addToRecoveryList("lastname");
		//this.addToRecoveryList("lastlogin");
		components = new LinkedHashMap<String, ScreenComponent>();
	}
	
	/**
	 * Handling new screen
	 * 
	 * @param s - screen
	 */
	public void onNewScreen(Screen s) {	
		String sessionUser = (String) s.getProperty("user");
		//long lastLogin = (Long) s.getProperty("lastlogin");
		
		s.setRole("dashboard");
		
		loadStyleSheet(s, "generic");

		//define components
		header = new Header(s);	
		tabs = new Tabs(s);
		login = new Login(s);
		collectionOverview = new CollectionOverview(s);
		
		components.put("header", header);
		components.put("tabs", tabs);
		components.put("login", login);
		components.put("collectionOverview", collectionOverview);
		
		if (sessionUser != null && sessionUser != "") {
			//check if last login was recent enough, otherwise force login
			//if ((new Date().getTime() - lastLogin) < AUTO_LOGOUT) {			
			//	this.eventHandler(s, "onLogin", sessionUser);
			//} else {
			//	login.render();
			//	login.init();
			//}
			this.eventHandler(s, "onLogin", sessionUser);
		} else {
			login.render();
			login.init();
		}
	}
	
	public void putOnScreen(Screen s, String from, String msg) {
		System.out.println(msg);
		
		int position = msg.indexOf("(");
		
		if (position == -1) {
			return;
		}
		
		String function = msg.substring(0, position);
		String arguments = msg.substring(position+1, msg.length()-1);
		String[] args = arguments.split(",");
		
		if (function.indexOf("_") != -1) {
			String component = function.substring(0, function.indexOf("_"));
			function = function.substring(function.indexOf("_")+1);

			if (components.containsKey(component)) {
				try {
					components.get(component).receiveMessage(s, function, arguments, args);
				} catch (Exception e) {
					System.out.println("Component "+component+" throws exception ");
					System.out.println(e.toString());
					StackTraceElement[] elements = e.getStackTrace();
					
					for (int i = 0; i < elements.length; i++) {
						System.out.println(elements[i].toString());
					}					
				}				
			} else {
				System.out.println("Component "+component+" is not defined!");
			}
		} else {
			
		}
	}
	
	/** 
	 * Event handler to communicate events from and to components
	 * @param s
	 * @param method
	 */
	public void eventHandler(Screen s, String method, Object message) {
		for (Iterator<Map.Entry<String, ScreenComponent>> iterator = components.entrySet().iterator(); iterator.hasNext(); ) {
			Map.Entry<String, ScreenComponent> pair = (Map.Entry<String, ScreenComponent>) iterator.next();
			try {
				Method m = pair.getValue().getClass().getMethod(method, Screen.class, Object.class);
				
				if (m != null) {
					m.invoke(pair.getValue(), s, message);
				}
			} catch (Exception e) { }
		}
	}
}
