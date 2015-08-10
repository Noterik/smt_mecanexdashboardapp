/* 
* Login.java
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

import java.util.Date;

import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;
import org.springfield.lou.screen.Screen;
import org.springfield.lou.screen.ScreenManager;
import org.springfield.mojo.interfaces.ServiceInterface;
import org.springfield.mojo.interfaces.ServiceManager;

/**
 * Login.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types.components
 * 
 */
public class Login extends ScreenComponent {
	
	public Login(Screen s) {
		super(s);
	}
	
	public void init() {
		
		sendMessage("init()");
	}

	public void receiveMessage(Screen s, String function, String arguments, String[] args) {
		if (function.equals("login")) {
        	login(s, args);
        } else if (function.equals("logout")) {
        	logout(s);
        }
	}
	
	private void login(Screen s, String[] args) {
		String ticket = getTicket(s, args[0], args[1]);
		//Successful login
		if (!ticket.equals("-1")) {
        	//get account info
        	FsNode accountInfo = Fs.getNode("/domain/"+s.getApplication().getDomain()+"/user/"+args[0]+"/account/default");
        	String firstname = accountInfo.getProperty("firstname");
        	String lastname = accountInfo.getProperty("lastname");
        	
        	//store account info in session
        	s.setProperty("user", args[0]);
        	s.setProperty("firstname", firstname);
        	s.setProperty("lastname", lastname);
        	s.setProperty("lastlogin", new Date().getTime());
        	
        	//hide login box
        	s.removeContent(target);
        	//signal app that user logged in
        	getApplication(s).eventHandler(s, "onLogin", args[0]);
        } else {
        	//failed to login
        	s.putMsg(target, "app", "loginFailed()");
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
	
	public void logout(Screen s) {
		s.setProperty("user", "");
		s.setProperty("firstname", "");
		s.setProperty("lastname", "");
		s.setProperty("lastlogin", "");
		
		s.putMsg(target,"app","logout()");  
	}
}
