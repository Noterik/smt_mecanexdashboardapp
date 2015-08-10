/* 
* Header.java
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

import org.json.simple.JSONObject;
import org.springfield.lou.screen.Screen;

/**
 * Header.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types.components
 * 
 */
public class Header extends ScreenComponent {
	
	public Header(Screen s) {
		super(s);
		render();
	}
	
	public void onLogin(Screen s, Object user) {
		JSONObject userInfo = new JSONObject();
		userInfo.put("username", (String) user);
		userInfo.put("firstname", (String) s.getProperty("firstname"));
		userInfo.put("lastname", (String) s.getProperty("lastname"));
		
		s.putMsg(target,"app","setUser("+userInfo+")"); 
		s.setDiv("logout", "bind:mousedown", "logout", Login.class);
	}
}
