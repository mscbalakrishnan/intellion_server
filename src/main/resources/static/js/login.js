var login = function() {

	var self = this;
	self.doLogin = function() {

		if (WsUtils.validate("loginForm"))
			return;

		if ($("#userName").val() == "admin"
				&& $("#password").val() == "admin") {
			localStorage.setItem("user", $("#userName").val());
			window.location.href = "pages/calendar.html";

		} else {
			$("#invalidLoginError").text(
					"Invalid User name and password.");
			$("#userName").val("");
			$("#password").val("");
			$("#userName").focus();
		}

	}, self.doLogOff = function() {

		localStorage.removeItem("user");
		$.post({
			url : "/logout" ,
			type : "POST"});

	}
}