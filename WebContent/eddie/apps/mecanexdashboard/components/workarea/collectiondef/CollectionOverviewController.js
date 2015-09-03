var CollectionOverviewController = function(options) {}; // needed for detection

CollectionOverviewController.update = function(vars, data) {
	var targetId = '#'+vars.get('targetid'); 

	console.log(data);
	
	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars.get("template"),data);
	$(targetId).html(rendered);   
	

};