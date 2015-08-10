var Login = function(options) { }

Login.prototype = Object.create(Component.prototype);

Login.prototype.init = function() {
	$("title").html("Mecanex Dashboard | Login");
	
	$("#login_submit").click(function() {
		eddie.putLou('', 'login_login('+$("#login_username").val()+','+$("#login_password").val()+')');
	});
}

Login.prototype.loginFailed = function(resp) {
	console.log("received"+resp);
	$("#login_error").text("Incorrect username or password");
}

Login.prototype.logout = function() {
	location.reload();
}
