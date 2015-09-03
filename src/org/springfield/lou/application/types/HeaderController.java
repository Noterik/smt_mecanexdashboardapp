package org.springfield.lou.application.types;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springfield.fs.FsNode;
import org.springfield.lou.controllers.Html5Controller;
import org.springfield.lou.screen.Screen;


public class HeaderController extends Html5Controller {
	
	private String template;
	private String selector;
	
	public HeaderController() {
		
	}
	
	public void attach(String sel) {
		selector = sel;
		if (screen!=null) {
			FsNode node = getControllerNode(selector);
			if (node!=null) {
				template = node.getProperty("template");

				screen.get(selector).loadScript(this);
				screen.get(selector).template(template);
			}
		}		
		loadHtml();
	}
	
	public void logout(Screen s,JSONObject data) {
		s.setProperty("username", "");
		s.setProperty("lastlogin", "0");
		
		JSONObject logout = new JSONObject();
		logout.put("function", "logout");
		logout.put("arguments", "");

		screen.get(selector).update(logout);
	}
	
	private void loadHtml() {
		JSONObject data = new JSONObject();	
		data.put("language",screen.getLanguageCode());
		data.put("id",screen.getId());
		String username = (String) screen.getProperty("username");
		data.put("user", username);
		screen.get(selector).update(data);
	}
}
