//To save properly, Form Id should be same as category name
//Each input field should have class name as 'field'
var settingDetailsArr = [];

function initSettingsVO(){
	
	var settingsVO = {
			id : null,
			category : "",
			type:"",
			settingsParamsDtos : []
	};
	return settingsVO;
}

var SettingsController = function(){
	
	var self = this;
	self.cancelSettings = function(category){
		category = category.toLowerCase();
		
		$("#"+category + " .box-footer").addClass("hide");4
		$("#"+category + " .edit").removeClass("hide");
		
		$("#"+category + " .field").each(function(ele){
			$(this).attr("disabled","disabled");
		});
		
		$(".help-block").text("");
		$(".has-error").removeClass("has-error");
		
	}
	
	self.editSettings = function(category,ele){
		
		category = category.toLowerCase();
		$("#"+category + " .box-footer").removeClass("hide");
		$("#"+category + " .edit").addClass("hide");
		
		$("#"+category + " .field").each(function(ele){
			$(this).removeAttr("disabled");
		});
	}
	
	self.addSettingsParam = function(id, paramName, paramValue){
		var settingsParam = {
				id :  id,
				paramName : paramName,
				paramValue : paramValue
		}
		return settingsParam;		
	};
		
	self.displaySettingsParam = function(category,settingsParamValues){
		category = category.toLowerCase();
		settingsParamValues.forEach(val => {
			var id = val.paramName;
			if(val.paramValue == 'true'){
				$("#"+id).attr("checked","checked");
			}
			
			var value = val.paramValue;
			$("#"+id).val(value);
			$("#"+id).attr("idValue",val.id);
			//$("#reminderdays_div").hide();
			if( val.paramName == 'sms_periodic_reminder' && val.paramValue == 'true'){
				$("#reminderdays_div").show();
			}

		});
		var category ;
		
		$("#"+category + " .field").each(function(ele){
			$(this).attr("disabled","disabled");
			
		});
	};
	
	self.saveSettingsParam = function(category){
		
		if(WsUtils.validate(category))
			return;
		var settingsVO = settingDetailsArr[category];
		console.log(settingsVO)
		var settingsParam = [];
		/*if(!settingsVO || !settingsVO.id){
			settingsVO = initSettingsVO();
		}*/
				
		
		settingsVO.category = category;
		settingsVO.settingsParamsDtos = settingsParam;
		
		$("#"+category + " .field").each(function(ele){
			var fieldValue = "";
			if($(this).attr("type") === 'checkbox'){
				fieldValue = $(this).is(":checked");
			}				
			else {
				fieldValue = $(this).val();
			}
			var idVal = $(this).attr("idValue");
			settingsParam.push(self.addSettingsParam(idVal, $(this).attr("id"), fieldValue));
		});
		
		console.log(settingsVO);
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				WsUtils.showAlert('Saved Successfully.');
				new SettingsController().cancelSettings(category)
				
			},
			requestUrl : "/intelhosp/settings",
			requestMethod: "PUT",
			requestData : {
				"data" : settingsVO ,
				"queryName" : "",
				"queryParamArray" : {}
			},resultType : "json",
		});
		ServiceCalls.call();
		
	}
}

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
	
	self.loadClinicDetailsPage = function(data) {

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$("#settingsContainer").html(responseObj);
				$(".tab-pane").removeClass("active");
				$("#settingsContainer").addClass("active");
				self.getAllSettings();
			},
			requestUrl : "../pages/templates/Clinic.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	self.getAllSettings = function(){		
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var dataArr = resultGlobalClass.response;
				var settingsController = new SettingsController();
				
				if(dataArr){
					dataArr.forEach(val => {
						settingDetailsArr[val.category] = val;
						settingsController.displaySettingsParam(val.category,val.settingsParamsDtos);
					});
				}
				console.log(settingDetailsArr);	
				
			},
			requestUrl : "/intelhosp/settings",
			requestMethod: "GET",
			requestData : {
				"data" : {} ,
				"queryName" : "",
				"queryParamArray" : {}
			},resultType : "json",
		});
		ServiceCalls.call();
		
	};
	self.showHideReminderDays = function() {
		$("#reminderdays_div").hide();
		if( $("#sms_periodic_reminder").is(":checked")) {
			$("#reminderdays_div").show();
		}
	}
}