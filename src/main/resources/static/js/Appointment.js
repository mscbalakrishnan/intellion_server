var appointmentVo;
function initAppointmentVo() {
	doctorVo = {
		id : ko.observable(""),
		name : ko.observable(""),
		title : ko.observable(""),
		email : ko.observable(""),
		qualification : ko.observable(""),
		fees : ko.observable(""),
		mobile : ko.observable(""),
		category : ko.observable(""),
		categoryList : ko.observableArray([ {
			"id" : 1,
			"name" : "dentist"
		}, {
			"id" : 2,
			"name" : "Root Canel Splst"
		} ]),
		title : ko.observable(""),
		titleList : ko.observableArray([ {
			"id" : 1,
			"name" : "Mr"
		}, {
			"id" : 2,
			"name" : "Ms/Mrs"
		} ])
	};
};
initAppointmentVo();
var Appointment = function() {

	var self = this;
	self.loadAppointmentDetails = function(callback) {

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				var events = [];
				$.each(JSON.parse(responseObj), function(i, v) {
					var datetime = v.time.split("T");
					var date = datetime[0];
					var time = datetime[1];
					var dateArr = date.split("-");
					var year = dateArr[0];
					var month = dateArr[1];
					var day = dateArr[2];
					var timeArr = time.split(":");
					var hour = timeArr[0];
					var min = timeArr[1];

					var appointDate = new Date(year, month, day);

					events.push({
						id : v.id,
						title : 'Meeting',
						start : new Date(appointDate.getFullYear(),appointDate.getMonth()-1,appointDate.getDate(),hour,min),
						allDay : false,
						backgroundColor : '#0073b7', // Blue
						borderColor : '#0073b7' // Blue
					});
				});
				callback(events);

			},
			requestUrl : "/intelhosp/appointments/",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.call();

	};
	
	self.loadAddAppointmentForm = function(){
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				WsUtils.showPopupWindow(function(){
					
				});
				$("#modelData").html(responseObj);
			},
			requestUrl : "../pages/templates/add_appointment.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	}

}