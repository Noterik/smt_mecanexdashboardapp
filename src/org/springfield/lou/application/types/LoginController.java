/* 
* LoginController.java
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

import java.util.Date;

import org.json.simple.JSONObject;
import org.springfield.fs.FsNode;
import org.springfield.lou.controllers.Html5Controller;
import org.springfield.lou.screen.Screen;
import org.springfield.mojo.interfaces.ServiceInterface;
import org.springfield.mojo.interfaces.ServiceManager;

/**
 * LoginController.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types
 * 
 */
public class LoginController extends Html5Controller {
	private String template;
	private JSONObject defaultResponse;
	private String errorText;
	
	public LoginController() {
		defaultResponse = new JSONObject();
	}
	
	public void attach(String s) {
		selector = s;
		if (screen!=null) {
			FsNode node = getControllerNode(selector);
			if (node!=null) {
				template = node.getProperty("template");
				
				defaultResponse.put("language",screen.getLanguageCode());
				defaultResponse.put("id",screen.getId());
				defaultResponse.put("topText", node.getProperty("topText"));
				defaultResponse.put("nameText", node.getProperty("nameText"));
				defaultResponse.put("passwordText", node.getProperty("passwordText"));
				defaultResponse.put("buttonText", node.getProperty("buttonText"));
				errorText = node.getProperty("errorText");
				
				screen.get(selector).loadScript(this);
				screen.get(selector).template(template);
			}
		}		
		loadHtml();
	}
	
	public void loginSubmitted(Screen s,JSONObject data) {
		String user = data.get("user") == null ? "" : (String) data.get("user");
		String pass = data.get("pass") == null ? "" : (String) data.get("pass");
		
		String ticket = getTicket(s, user, pass);
		
		if (ticket.equals("-1")) {
			JSONObject failedResponse = (JSONObject) defaultResponse.clone(); 
			failedResponse.put("errorText", errorText);

			screen.get(selector).update(failedResponse);
		} else {
			s.setProperty("username", user);
			s.setProperty("lastlogin", new Date().getTime());

			JSONObject redirect = new JSONObject();
			redirect.put("function", "redirect");
			redirect.put("arguments", "");
			
			System.out.println("Login successfull for "+user);
			screen.get(selector).update(redirect);
		}		
	}
	
	private String getTicket(Screen s, String account, String password) {
		ServiceInterface barney = ServiceManager.getService("barney");
		
		if (barney!=null) {
			String ticket = barney.get("login("+s.getApplication().getDomain()+","+account+","+password+")", null, null);
			if (ticket!=null && !ticket.equals("-1")) {
				return ticket;
			}
		}
		return "-1"; // backwards compatible		
	}
	
	private void loadHtml() {
		screen.get(selector).update(defaultResponse);
	}
}
