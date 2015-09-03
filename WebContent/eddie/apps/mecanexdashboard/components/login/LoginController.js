var LoginController = function(options) {}; // needed for detection

LoginController.update = function(vars, data) {

	if (data['function']) {		
		LoginController[data['function']](data['arguments']);
		return;
	}
	
	
	var targetId = '#'+vars.get('targetid'); 
	
	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars.get("template"),data);
	$(targetId).html(rendered);
	
	$(targetId+" > form > button").on('click', function() {
		var obj = {};
		obj['user'] = $(targetId +" input[type='text']").val();
		obj['pass'] = $(targetId +" input[type='password']").val();
		eddie.sendEvent(vars.get('targetid'),"loginSubmitted",obj);
	});
};

LoginController.redirect = function() {
	location.reload();
};