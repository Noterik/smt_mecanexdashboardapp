var InputFieldWithValidationController = function(options) {}; // needed for detection

InputFieldWithValidationController.update = function(vars, data) {
	var timer = 0;
	var targetId = '#'+data['targetid']; 
	
	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars["template"],data);
    $(targetId).html(rendered);
    
    //small timeout before sending input to server to make sure user has stopped typing
    var delay = (function() {
    	return function(callback, ms) {
    		timer = setTimeout(callback, ms);
    	};
    })();
    
    //validate input
    $(targetId+" > input").on('input', function() {
    	var input = $(this);
    	var regex = new RegExp(vars["controller/validRegex"]);
    	var valid = regex.test(input.val()) || !input.val();
    	
    	if (valid) {    		
    		clearTimeout(timer);
	    	delay(function() {
	    		var obj = {};
	    		obj['value'] = input.val();
	    		eddie.sendEvent(data['targetid'],"inputFieldEntered",obj);
	    	}, 500);
    	} else {
    		var regex = new RegExp(vars["controller/invalidRegex"]);
    		input.val(input.val().replace(regex, ''));
    	}    	
    });
};