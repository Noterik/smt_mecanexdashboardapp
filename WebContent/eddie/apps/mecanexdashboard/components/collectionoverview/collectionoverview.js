var Collectionoverview = function(options) {
	$("title").html("Mecanex Dashboard | Available collection");
	
	$('#luce_uri').click(function() {
		console.log("luce uri");
		window.open("http://a1.noterik.com:8081/smithers2/domain/espace/user/luce/video",'_blank');
	});
}

Collectionoverview.prototype = Object.create(Component.prototype);

Collectionoverview.prototype.setAvailableCollections = function(arg) {
	var obj = jQuery.parseJSON(arg);

	$.each(jQuery.parseJSON(arg), function() {
		$("#available_collections > ul").append(
				$('<li>').append(
						$('<a>').attr( {'href' : "javascript:void(0)", "title" : this.description , "data-id" : this.uri}).append(
						this.title+" ("+this.results+")"
		)));
	});
}

Collectionoverview.prototype.setUserCollections = function(arg) {
	var obj = jQuery.parseJSON(arg);
	
	$.each(jQuery.parseJSON(arg), function() {
		$("#user_collections > ul").append(
				$('<li>').append(
						$('<a>').attr( {'href' : "javascript:void(0)", "title" : this.description, "data-id" : this.uri }).append(
						this.title+" ("+this.results+")"
		)));
	});
}