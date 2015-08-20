var DropDownController = function(options) {}; // needed for detection

DropDownController.update = function(vars, data) {
	var targetId = '#'+vars.get('targetid'); 
	
	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars.get("template"),data);
    $(targetId).html(rendered);

    //event on change
    $(targetId+" > select").one('change', function() {
    	var options = {};
    	var arr = options["options"] = [];
    	$(targetId+" option:selected").each(function() {
    		var option = {};
    		option["value"] = $(this).val();
    		option["name"] = $(this).text();
    		arr.push(option);
    	});
    	eddie.sendEvent(vars.get('targetid'),"dropDownOptionSelected",options);
    });
};