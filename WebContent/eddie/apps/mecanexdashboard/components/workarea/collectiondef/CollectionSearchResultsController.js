var CollectionSearchResultsController = function(options) {}; // needed for detection

CollectionSearchResultsController.update = function(vars, data) {
	var targetId = '#'+data['targetid']; 

	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars["template"],data);
	$(targetId).html(rendered);   
	
	$(".add_result").on('click', function(event) {
		var itemId = event.target.id;
		var obj = {};
    	obj['itemId'] = itemId;
		eddie.sendEvent(data['targetid'],"addResult",obj);
	});
};