var CollectionSearchResultsController = function(options) {}; // needed for detection

CollectionSearchResultsController.update = function(vars, data) {
	var targetId = '#'+vars.get('targetid'); 

	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars.get("template"),data);
	$(targetId).html(rendered);   
	
	$(".add_result").one('click', function() {
		console.log($(this).id);
	});
};