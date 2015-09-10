var SubmitButtonController = function() {}; // needed for detection

SubmitButtonController.update = function(vars, data) {
	var targetId = '#'+vars.get('targetid'); 

	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars.get("template"),data);
    $(targetId).html(rendered);
    
    $(targetId).on('click', function() {    	
    	var fields = vars.get("controller/fieldsToSubmit").split(",");
    	var obj = {};
    	
    	fields.forEach(function (item, index, array) {
    		obj[item] = $("#"+item +" input").val();
    	});
    	eddie.sendEvent(vars.get('targetid'),"submitButtonClicked",obj);
    });
};