var Settings = function() {
	var self = this;
	
	self.loadSettingsPage = function(data) {

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$(".content").html(responseObj);	
				new Category().loadCategoryList();
				
			},
			requestUrl : "../pages/templates/settings.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
}