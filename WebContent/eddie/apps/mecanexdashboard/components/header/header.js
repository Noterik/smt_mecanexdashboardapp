var Header = function(options) { }

Header.prototype = Object.create(Component.prototype);

Header.prototype.setUser = function(arg) {
	var obj = jQuery.parseJSON(arg);

	$("#header_user > span").text(obj.firstname+" "+obj.lastname);
	$('#header_user').show();
	
	$('#logout').click(function() {
		eddie.putLou('', 'login_logout()');
	});
}