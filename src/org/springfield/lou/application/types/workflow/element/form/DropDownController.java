/* 
* DropDownController.java
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
package org.springfield.lou.application.types.workflow.element.form;

import org.json.simple.JSONObject;
import org.springfield.fs.FSList;
import org.springfield.fs.FSListManager;
import org.springfield.fs.FsNode;
import org.springfield.lou.controllers.Html5Controller;

/**
 * DropDownController.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types.workflow.element.form
 * 
 */
public class DropDownController extends Html5Controller {
	
	private String nodepath;
	private String fields;
	private String template;
	
	public DropDownController() {
		//constructor
	}
	
	public DropDownController(String n) {
		nodepath = n;
	}
	
	public void attach(String s) {
		selector = s;
		if (screen!=null) {
			FsNode node = getControllerNode(selector);
			if (node!=null) {
				nodepath = node.getProperty("nodepath");
				fields = node.getProperty("fields");
				//template = node.getProperty("template");
				//System.out.println("WHOOOOLOG="+template);
				model.observeTree(this,nodepath);
				//screen.get(selector).template(template);
				loadHtml();
				//screen.get(selector).html("WHOOOFOFOOOOO");
			}
		}
	}
	
	private void loadHtml() {
		FSList fslist = FSListManager.get(nodepath,false);
		JSONObject data = fslist.toJSONObject(screen.getLanguageCode(),fields);
		data.put("language",screen.getLanguageCode());
		data.put("id",screen.getId());
		data.put("nodepath",nodepath);
		data.put("size", fslist.size());
		data.put("targetid",selector.substring(1));
		screen.get(selector).parsehtml(data);
	}
}
