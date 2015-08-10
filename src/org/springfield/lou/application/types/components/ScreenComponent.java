/* 
* ScreenComponent.java
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

import org.springfield.lou.application.Html5Application;
import org.springfield.lou.application.types.MecanexdashboardApplication;
import org.springfield.lou.screen.Screen;

/**
 * ScreenComponent.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types.components
 * 
 */
public class ScreenComponent implements ScreenComponentInterface {
	private Screen screen;
	protected String target;
	
	public ScreenComponent(Screen s) {
		this.screen = s;
		this.target = this.getClass().getSimpleName().toLowerCase();
	}
	
	public MecanexdashboardApplication getApplication(Screen s) {
		return (MecanexdashboardApplication) s.getApplication();
	}
	
	public void sendMessage(String message){
		screen.putMsg(target, "", message);
	}
	
	public void receiveMessage(Screen s, String function, String arguments, String[] args) {
		//override in screen components
	}
	
	public void render() {
		Html5Application app = (Html5Application) screen.getApplication();
		app.loadContent(screen, target, target);
	}
}
