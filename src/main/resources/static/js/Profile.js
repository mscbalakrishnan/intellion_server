
var Profile = function() {

	var self = this;
	self.loadProfilePage = function(data) {
		
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$(".content").html(responseObj);	
			},
			requestUrl : "../pages/templates/doctor_profile.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	
}