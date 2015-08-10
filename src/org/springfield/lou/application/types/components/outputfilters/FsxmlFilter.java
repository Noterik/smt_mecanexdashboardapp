/* 
* FsxmlFilter.java
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
package org.springfield.lou.application.types.components.outputfilters;

import java.util.Iterator;
import java.util.List;

import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;

/**
 * FsxmlFilter.java
 *
 * @author Pieter van Leeuwen
 * @copyright Copyright: Noterik B.V. 2015
 * @package org.springfield.lou.application.types.components.outputfilters
 * 
 */
public class FsxmlFilter {

	//TODO: class filters only the fields that has been defined in the filter that is used
	
	String[] filter = { "summary", "topic", "extendedDescription" };
	
	public FsxmlFilter() {
		//constructor
	}
	
	public void test() {
		List<FsNode> videos = Fs.getNodes("/domain/espace/user/luce/video", 10, 0, 1);
		
		for (Iterator<FsNode> it = videos.iterator(); it.hasNext(); ) {
			FsNode video = it.next();
			
			//
			
		}
	}
}
