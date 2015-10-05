var UserCollectionsController = function(options) {}; // needed for detection

UserCollectionsController.update = function(vars, data) {
	var targetId = '#'+data['targetid']; 
	
	console.log(data);
	
	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars["template"],data);
	$(targetId).html(rendered);   
	
	$(targetId+" a").on("click", function() {
		var obj = {};
		eddie.sendEvent(data['targetid'],"addCollection",obj);
	});
	
	$(targetId +" .collection").on("click", function() {		
		var obj = {};
		obj['collection'] = $(this).attr("id");
		eddie.sendEvent(data['targetid'], "collectionDetails", obj);
	});
}