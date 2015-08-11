package org.springfield.lou.application.types;

import org.json.simple.JSONObject;
import org.springfield.lou.application.types.workflow.annotations.AnnotationsController;
import org.springfield.lou.application.types.workflow.collectiondef.CollectiondefController;
import org.springfield.lou.application.types.workflow.editorial.EditorialController;
import org.springfield.lou.application.types.workflow.enrichments.EnrichmentsController;
import org.springfield.lou.application.types.workflow.home.HomeController;
import org.springfield.lou.application.types.workflow.ingest.IngestController;
import org.springfield.lou.controllers.Html5Controller;
import org.springfield.lou.screen.Screen;

public class WorkAreaController extends Html5Controller {
	
	
	public WorkAreaController() {
		
	}
	
	public void attach(String sel) {
		selector = sel;
		screen.get("#home").attach(new HomeController()); 
	}
	
	 public void update(JSONObject data) {
		 String cmd = (String)data.get("command");
		 if (cmd.equals("switcharea")) {
			 String name = (String)data.get("name");
			 screen.get(selector).html("<div id=\""+name+"\"></div>");
			 // ugly for now ?
			 if (name.equals("collectiondef")) screen.get("#collectiondef").attach(new CollectiondefController()); 
			 if (name.equals("home")) screen.get("#home").attach(new HomeController()); 
			 if (name.equals("ingest")) screen.get("#ingest").attach(new IngestController()); 
			 if (name.equals("annotations")) screen.get("#annotations").attach(new AnnotationsController()); 
			 if (name.equals("enrichments")) screen.get("#enrichments").attach(new EnrichmentsController()); 
			 if (name.equals("editorial")) screen.get("#editorial").attach(new EditorialController()); 
		 }
	 }
	
}
