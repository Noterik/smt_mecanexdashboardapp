var HeaderController = function(options) {}; // needed for detection

HeaderController.update = function(vars, data) {
	
	if (data['function']) {		
		HeaderController[data['function']](data['arguments']);
		return;
	}
	
	var targetId = '#'+vars.get('targetid'); 
	
	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars.get("template"),data);
	$(targetId).html(rendered);
	
	$(targetId+" a").on('click', function() {
		var obj = {};
		eddie.sendEvent(vars.get('targetid'),"logout",obj);
	});
};

HeaderController.logout = function() {
	location.reload();
};