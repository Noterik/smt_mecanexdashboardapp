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

import org.springfield.lou.application.*;
import org.springfield.lou.controllers.ScreenController;
import org.springfield.lou.screen.*;



/**
 * MecanexdashboardApplication.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types
 * 
 */
public class MecanexdashboardApplication extends Html5Application {
	//private static ActiveEvent ae = new ActiveEvent();
	
 	public MecanexdashboardApplication(String id) {
		super(id); 
		this.setSessionRecovery(true);
		this.addToRecoveryList("username");
		//this.addToRecoveryList("email");
	}
 	
	
    public void onNewScreen(Screen s) {
    		s.setLanguageCode("en");
			s.get("#screen").setViewProperty("template", "screen.mst");
			s.get("#screen").attach(new ScreenController());
			loadStyleSheet(s,"desktop");
				
			// attach all the controllers for this view from app.xml
			s.get("#header").attach(new HeaderController());
			s.get("#workflowbar").attach(new WorkflowController());
			s.get("#workarea").attach(new WorkAreaController()); 
     }
}
