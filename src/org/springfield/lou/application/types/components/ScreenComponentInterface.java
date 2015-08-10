/* 
* ScreenComponentInterface.java
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

import org.springfield.lou.application.types.MecanexdashboardApplication;
import org.springfield.lou.screen.Screen;

/**
 * ScreenComponentInterface.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types.components
 * 
 */
public interface ScreenComponentInterface {
	public MecanexdashboardApplication getApplication(Screen s);
	public void sendMessage(String message);
	public void receiveMessage(Screen s, String function, String arguments, String[] args);
	public void render();
}
