package org.springfield.lou.application.types;

import org.springfield.lou.controllers.Html5Controller;


public class HeaderController extends Html5Controller {
	
	private String selector;
	
	public HeaderController() {
		
	}
	
	public void attach(String sel) {
		selector = sel;
		//screen.get("#login").attach(new LoginController());
	}
	

}
