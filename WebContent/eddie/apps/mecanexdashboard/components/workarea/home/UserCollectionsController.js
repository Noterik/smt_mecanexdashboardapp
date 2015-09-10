var UserCollectionsController = function(options) {}; // needed for detection

UserCollectionsController.update = function(vars, data) {
	var targetId = '#'+vars.get('targetid'); 
	
	console.log(data);
	
	// render the new html using mustache and the data from the server and show it
	var rendered = Mustache.render(vars.get("template"),data);
	$(targetId).html(rendered);   
	
	$(targetId+" a").on("click", function() {
		var obj = {};
		eddie.sendEvent(vars.get('targetid'),"addCollection",obj);
	});
	
	$(targetId +" .collection").on("click", function() {		
		var obj = {};
		obj['collection'] = $(this).attr("id");
		eddie.sendEvent(vars.get('targetid'), "collectionDetails", obj);
	});
}