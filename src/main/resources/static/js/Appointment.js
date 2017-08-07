var appointmentVo;
var events = [];
var eventCallbackMethod;
function initAppointmentVo() {
	appointmentVo = {
		id : ko.observable(""),
		time : ko.observable(""),
		doctorId : ko.observable(""),
		patientId : ko.observable(""),
		patientName : ko.observable(""),
		doctorName : ko.observable("")

	};
};
initAppointmentVo();
var Appointment = function() {

	var self = this;
	self.loadAppointmentDetails = function(callback) {

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				events = [];
				$.each(JSON.parse(responseObj), function(i, v) {
					self.displayAppointments(v);
				});
				eventCallbackMethod = callback;
				eventCallbackMethod(events);

			},
			requestUrl : "/intelhosp/appointments/",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.call();

	};
	
	self.loadAddAppointmentFormForUpdate = function(data){
		console.log(data);
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				WsUtils.showPopupWindow(function() {

				});
				$("#modelData").html(responseObj);
				initAppointmentVo();
				appointmentVo.id(data.id);
				appointmentVo.doctorId(data.doctorId);
				appointmentVo.patientId(data.patientId);
				appointmentVo.time(data.time);
				appointmentVo.doctorName(data.doctorName);
				appointmentVo.patientName(data.patientName);
				
				var datetime = data.time.split("T");
				var date = datetime[0];
				var time = datetime[1];
				var dateArr = date.split("-");
				var year = dateArr[0];
				var month = dateArr[1];
				var day = dateArr[2];
				var timeArr = time.split(":");
				var hour = timeArr[0];
				var min = timeArr[1];

				appointmentVo.time(day + "/" + month + "/" + year + " " + hour	+ ":" + min);
				
				var dt = new Date(date);
				$("#appointmentDateTime").val(day + "/" + month + "/" + year + " " + hour	+ ":" + min);
				$("#appointmentDateTime").datetimepicker(
						{
							format : 'd/m/Y H:i',
							formatTime : 'H:i',
							onShow : function(ct) {
								this.setOptions({
									minDate : jQuery('#appointmentDateTime')
											.val() ? jQuery('#appointmentDateTime').val()
											: false
								})
							},
						});

				new Appointment().showDoctorNames("doctor");
				new Appointment().showPatientNames("patient");
				
				ko.cleanNode($("#existingAppointment")[0]);
				ko.applyBindings(appointmentVo,	$("#existingAppointment")[0]);
				$(".active").hide();
				$("#existingAppointment").show();
				
				$("#yes").on("click",function(){
					self.deleteAppointment(data);
				});
				
				$("#no").on("click",function(){
					$(".active").hide();
					$("#existingAppointment").show();
				});
				
				$("#cancel").on("click",function(){
					$(".active").hide();
					$("#deleteDiv").show();
				})
				
				$("#update").on("click",function(){
					$(".active").hide();
					$("#addAppointment").show();
					
				})
			},
			requestUrl : "../pages/templates/add_appointment.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	},
	
	self.deleteAppointment = function(data){
		var requestUrl = "/intelhosp/appointments/"+data.id;
		console.log(requestUrl)
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				WsUtils.showAlert("Delete Success");
				$(".alert").delay(3000).fadeOut("slow");
				WsUtils.hidePopup();
			},
			requestUrl : requestUrl,
			requestMethod:"DELETE",
			requestData : {
				"data" : "",
			},resultType : "json",
		});
		ServiceCalls.call();
	}

	self.loadAddAppointmentForm = function(date) {

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				WsUtils.showPopupWindow(function() {

				});
				$("#modelData").html(responseObj);
				$(".active").hide();
				$("#addAppointment").show();
				var dt = new Date(date);
				$("#appointmentDateTime").val(
						dt.getDate() + "/" + (dt.getMonth() + 1) + "/"
								+ dt.getFullYear() + " " + dt.getUTCHours()
								+ ":" + dt.getUTCMinutes());
				$("#appointmentDateTime").datetimepicker(
						{
							format : 'd/m/Y H:i',
							formatTime : 'H:i',
							onShow : function(ct) {
								this.setOptions({
									minDate : jQuery('#appointmentDateTime')
											.val() ? jQuery(
											'#appointmentDateTime').val()
											: false
								})
							},
						});

				new Appointment().showDoctorNames("doctor");
				new Appointment().showPatientNames("patient");
			},
			requestUrl : "../pages/templates/add_appointment.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	};

	self.loadPatientFormInAppointmentPage = function(data) {
		resultGlobalObject = $
				.extend(
						resultGlobalClass,
						{
							callback : function() {
								var responseObj = resultGlobalClass.response;
								$("#patientForm").html(responseObj);

								initPatientVo();
								if (data) {
											patientVo.id(data["id"]),
											patientVo.name(data["name"]),
											patientVo.title(data["title"]),
											patientVo.email(data["email"]),
											patientVo.mobile(data["mobile"]),
											patientVo
													.landline(data["landline"]),
											patientVo.mobile(data["mobile"]),
											patientVo
													.address1(data["address1"]),

											patientVo
													.address2(data["address2"]),
											patientVo.dob(data["dob"]),
											patientVo.city(data["city"]),
											patientVo.pincode(data["pincode"]),
											patientVo
													.profileId(data["profileId"]),

											patientVo.label(data["label"]),
											patientVo
													.landline(data["landline"]),
											patientVo
													.bloodGroup(data["bloodGroup"]),
											patientVo.gender(data["gender"]),
											patientVo.age(data["age"]),

											patientVo
													.medicalHistory(data["medicalHistory"]),
											patientVo
													.medicalAlert(data["medicalAlert"]),
											patientVo
													.allergies(data["allergies"]),
											patientVo
													.needWelcomeMessage(data["needWelcomeMessage"]),
											patientVo
													.birthdayWish(data["birthdayWish"]),
											patientVo
													.remainder(data["remainder"])
									// patientVo.appointments (
									// data["appointments"])
								}
								ko.cleanNode($("#patientForm")[0]);
								ko.applyBindings(patientVo,
										$("#patientForm")[0]);

								$("#dob").datetimepicker({
									timepicker : false
								});

							},
							requestUrl : "../pages/templates/patient_add_appointment.html",
							requestData : {},
							resultType : "text",
						});
		ServiceCalls.loadHtmlPage();
	};

	self.saveAppointment = function() {

		var objToSave = {};
		var methodType = "POST";
		if (appointmentVo.id()) {
			methodType = "PUT";
			objToSave.id = appointmentVo.id();
		}

		objToSave.time = appointmentVo.time();
		objToSave.doctorId = appointmentVo.doctorId();
		objToSave.patientId = appointmentVo.patientId();
		var selectedDate = jQuery('#appointmentDateTime').val();

		var datetime = selectedDate.split(" ");
		var date = datetime[0];
		var time = datetime[1];

		var dateArr = date.split("/");
		var day = dateArr[0];
		var month = dateArr[1];
		var year = dateArr[2];
		var timeArr = time.split(":");
		var hour = timeArr[0];
		var min = timeArr[1];

		objToSave.time = year + "-" + month + "-" + day + "T" + hour + ":"
				+ min;

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				WsUtils.showAlert('Appointment Added Successfully.');
				$(".alert").delay(3000).fadeOut("slow");

				var data = resultGlobalClass.response;

				self.displayAppointments(data);
				
				if (!appointmentVo.id()) {
					$('#calendar').fullCalendar("removeEvents");        
			        $('#calendar').fullCalendar('addEventSource', events); 
				}else{
			        resultGlobalObject.requestMethod = "GET";
			        $('#calendar').fullCalendar('refetchEvents');
				}
				
				WsUtils.hidePopup();

			},
			requestUrl : "/intelhosp/appointments",
			requestMethod : methodType,
			requestData : {
				"data" : objToSave,
				"queryName" : "",
				"queryParamArray" : {}
			},
			resultType : "json",
		});
		ServiceCalls.call();
	};
	self.showDoctorNames = function(textbox) {

		var options = {

			url : function(phrase) {
				console.log(phrase);
				return "../intelhosp/doctors/doctorname/find?name=" + phrase;
			},

			getValue : function(element) {
				return element.name;
			},

			ajaxSettings : {
				dataType : "json",
				method : "get",
				data : {
					dataType : "text"
				}

			},

			template : {
				type : "custom",
				method : function(value, item) {
					var html = '<div class="user-panel" style="background:#000">'
							+ '<div class="pull-left image">'
							+ '<img src="../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">'
							+ '</div>'
							+ '<div class="pull-left info">'
							+ '<p>'
							+ item.name
							+ '</p>'
							+ '<a href="#"><i class="fa fa-circle text-success"></i> Online</a>'
							+ '</div>' + '</div>';
					return html;
				}
			},

			preparePostData : function(data) {
				data.name = $("#" + textbox).val();
				return "";
			},

			adjustWidth : false,
			list : {

				onChooseEvent : function() {
					var item = $("#" + textbox).getSelectedItemData();
					console.log(item.id);
					appointmentVo.doctorId(item.id);
				}
			},

			requestDelay : 400,
		};

		$("#" + textbox).easyAutocomplete(options);

	};

	self.showPatientNames = function(textbox) {

		var options = {

			url : function(phrase) {
				console.log(phrase);
				return "../intelhosp/patients/patientname/find?name=" + phrase;
			},

			getValue : function(element) {
				return element.name;
			},

			ajaxSettings : {
				dataType : "json",
				method : "get",
				data : {
					dataType : "text"
				}

			},

			template : {
				type : "custom",
				method : function(value, item) {
					var html = '<div class="user-panel" style="background:#000">'
							+ '<div class="pull-left image">'
							+ '<img src="../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">'
							+ '</div>'
							+ '<div class="pull-left info">'
							+ '<p>'
							+ item.name
							+ '</p>'
							+ '<a href="#"><i class="fa fa-circle text-success"></i> Online</a>'
							+ '</div>' + '</div>';
					return html;
				}
			},

			preparePostData : function(data) {
				data.name = $("#" + textbox).val();
				return "";
			},

			adjustWidth : false,
			list : {

				onChooseEvent : function() {
					var item = $("#" + textbox).getSelectedItemData();
					console.log(item.id);
					appointmentVo.patientId(item.id);
				}
			},

			requestDelay : 400,
		};

		$("#" + textbox).easyAutocomplete(options);

	};

	self.savePatientOnAppointment = function() {

		var methodType = "POST";
		if (patientVo.id()) {
			methodType = "PUT";
		}

		var data = ko.toJS(patientVo);
		var dob = data.dob;
		var date = dob.split(" ")[0];
		var dateArr = date.split("/");
		data.dob = dateArr[0] + "-" + dateArr[1] + "-" + dateArr[2];

		if (data.remainder == "") {
			data.remainder = null;
		}

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				alert('Saved Successfully.')
				$('#patientForm').html('');
			},
			requestUrl : "/intelhosp/patients",
			requestMethod : methodType,
			requestData : {
				"data" : data,
				"queryName" : "",
				"queryParamArray" : {}
			},
			resultType : "json",
		});
		ServiceCalls.call();
	};
	self.displayAppointments = function(v) {
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
			patientId:v.patient.id,
			doctorId:v.doctor.id,
			doctorName : v.doctor.name,
			patientName : v.patient.name,
			time : v.time,
			title : "Dr." + v.doctor.name + ">> " + v.patient.name,
			start : new Date(appointDate.getFullYear(),
					appointDate.getMonth() - 1, appointDate.getDate(), hour,
					min),
			allDay : false,
			backgroundColor : '#0073b7', // Blue
			borderColor : '#0073b7' // Blue
		});
	};
	self.initializeCalander = function(){
		  $(function () {

			    /*
				 * initialize the external events
				 * -----------------------------------------------------------------
				 */
			    function init_events(ele) {
			      ele.each(function () {

			        // create an Event Object
					// (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			        // it doesn't need to have a start or end
			        var eventObject = {
			          title: $.trim($(this).text()) // use the element's text as
													// the event title
			        }

			        // store the Event Object in the DOM element so we can get
					// to it later
			        $(this).data('eventObject', eventObject)

			        // make the event draggable using jQuery UI
			      /*
					 * $(this).draggable({ zIndex : 1070, revert : true, // will
					 * cause the event to go back to its revertDuration: 0 //
					 * original position after the drag })
					 */

			      })
			    }

			    init_events($('#external-events div.external-event'))

			    /*
				 * initialize the calendar
				 * -----------------------------------------------------------------
				 */
			    // Date for the calendar events (dummy data)
			    var date = new Date()
			    var d    = date.getDate(),
			        m    = date.getMonth(),
			        y    = date.getFullYear()
			    $('#calendar').fullCalendar({
			      header    : {
			        left  : 'prev,next today',
			        center: 'title',
			        right : 'month,agendaWeek,agendaDay,listMonth'
			      },
			      buttonText: {
			        today: 'today',
			        month: 'month',
			        week : 'week',
			        day  : 'day',
			        list : 'List'
			      },
			      // Random default events
			      events    : [
			        
			      ],
			      editable  : false,
			      droppable : false, // this allows things to be dropped onto
										// the calendar !!!
			      drop      : function (date, allDay) {

			      },dayClick: function (date, jsEvent, view) {
			    	  new Appointment().loadAddAppointmentForm(date);
				    },
				    events: function(start, end, timezone, callback) {
				    	new Appointment().loadAppointmentDetails(callback);
				      },
				      eventClick: function(calEvent, jsEvent, view) {

				          new Appointment().loadAddAppointmentFormForUpdate(calEvent);

				      },
				      eventMouseover: function(calEvent, jsEvent, view) {


				      },
				      eventMouseout : function(calEvent, jsEvent, view) {

				      },
			      
			    })

			    /* ADDING EVENTS */
			    var currColor = '#3c8dbc' // Red by default
			    // Color chooser button
			    var colorChooser = $('#color-chooser-btn')
			    $('#color-chooser > li > a').click(function (e) {
			      e.preventDefault()
			      // Save color
			      currColor = $(this).css('color')
			      // Add color effect to button
			      $('#add-new-event').css({ 'background-color': currColor, 'border-color': currColor })
			    })
			    $('#add-new-event').click(function (e) {
			      e.preventDefault()
			      // Get value and make sure it is not null
			      var val = $('#new-event').val()
			      if (val.length == 0) {
			        return
			      }

			      // Create events
			      var event = $('<div />')
			      event.css({
			        'background-color': currColor,
			        'border-color'    : currColor,
			        'color'           : '#fff'
			      }).addClass('external-event')
			      event.html(val)
			      $('#external-events').prepend(event)

			      // Add draggable funtionality
			     // init_events(event)

			      // Remove event from text input
			      $('#new-event').val('')
			    })
			  })
	}

}