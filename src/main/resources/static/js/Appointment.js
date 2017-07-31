var appointmentVo;
function initAppointmentVo() {
	appointmentVo = {
		id : ko.observable(""),
		time : ko.observable(""),
		doctor :  ko.observable(""),
		patient: ko.observable("")
		
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