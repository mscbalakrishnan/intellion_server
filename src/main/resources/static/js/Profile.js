
var Profile = function() {

	var self = this;
	self.loadDoctorProfilePage = function(data) {
		
		
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
	
	self.loadPatientProfilePage = function(data) {
		
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$(".content").html(responseObj);	
			},
			requestUrl : "../pages/templates/patient_profile.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	
}