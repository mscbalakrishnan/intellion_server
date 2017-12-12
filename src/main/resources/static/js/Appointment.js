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
		doctorName : ko.observable(""),
		mobile: ko.observable(""),
		email: ko.observable("")

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
		
		/*var tour = new Tour({
			  steps: [
			  {
			    element: ".user-panel",
			    title: "Title of my step",
			    content: "Content of my step"
			  }
			]});

			// Initialize the tour
			tour.init();

			// Start the tour
			tour.start();*/

	};
	
	self.loadAddAppointmentFormForUpdate = function(data){
	// console.log(data);
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
				appointmentVo.mobile(data.appointmentObj.patient.mobile);
				appointmentVo.email(data.appointmentObj.patient.email);
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
				

				new Appointment().showDoctorNames("doctor");
				new Appointment().showPatientNames("patient");
				
				ko.cleanNode($("#existingAppointment")[0]);
				ko.applyBindings(appointmentVo,	$("#existingAppointment")[0]);				
			
				
				$(".activediv").hide();
				$("#existingAppointment").show();
				
				$("#yes").on("click",function(){
					self.deleteAppointment(data);
				});
				
				$("#no").on("click",function(){
					$(".activediv").hide();
					$("#existingAppointment").show();
				});
				
				$("#cancel").on("click",function(){
					$(".activediv").hide();
					$("#deleteDiv").show();
					// $(".content").css("min-height:0px !impartant");
					// $(".modal-header").remove();
				})
				
				$("#update").on("click",function(){
					$(".activediv").hide();
					$(".patient").hide();
					$(".doctor").removeClass("col-xs-6");
					$(".doctor").addClass("col-xs-12");
					$("#addAppointment").attr("isUpdate",true);
					$("#addAppointment").show();
					
					$("#appointmentDateTime").val(day + "/" + month + "/" + year + " " + hour	+ ":" + min);
					$("#doctor").val(data.doctorName);
					$("#doctor").change(function(){appointmentVo.doctorId("")});
					$("#appointmentDateTime").datetimepicker(
							{
								format : 'd/m/Y H:i',
								formatTime : 'H:i',
								onShow : function(ct) {
														this.setOptions({
														minDate:new Date()
														})
														 
								},
							});
					
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
				  resultGlobalObject.requestMethod = "GET";
			      $('#calendar').fullCalendar('refetchEvents');
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
				$(".activediv").hide();
				$("#addAppointment").show();
				var dt = new Date(date);
				var hours = dt.getHours();
				if(hours <= 9){
						hours = "0"+hours;
				}
				var minutes = dt.getMinutes();
				if(minutes <= 9){
					minutes = "0"+minutes;
				}
				
				var appointmentDate = $.datepicker.formatDate('dd/mm/yy',dt) + " " + hours	+ ":" + minutes;
				console.log("appointmentDate-->" + appointmentDate);
				$("#appointmentDateTime").val(appointmentDate);
				$("#appointmentDateTime").datetimepicker(
						{
							format : 'd/m/Y H:i',
							formatTime : 'H:i',
							onShow : function(ct) {
								this.setOptions({
									minDate:new Date()
								})
							},
						});
				new Appointment().showDoctorNames("doctor");
				new Appointment().showPatientNames("patient");
				$(".addPatient").show();
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
								$(".activediv").hide();
								$("#patientFormDiv").show();
								$("#patientFormDiv").html(responseObj);
								$("#myModalLabel").html("Add Patient");
								$(".content").css("min-height","450px")
								
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
								
								WsUtils.addOnblurEvent("patientForm");

								$("#dob").datetimepicker({
									timepicker : false,
									 format : 'd-m-Y'
								});

							},
							requestUrl : "../pages/templates/patient_add_appointment.html",
							requestData : {},
							resultType : "text",
						});
		ServiceCalls.loadHtmlPage();
	};

	self.saveAppointment = function() {
		
		if($("#addAppointment").attr("isUpdate")){
			$("#patient").removeAttr("validation");
		}
		
		if(WsUtils.validate("appointmentForm"))
				return;
	

		var objToSave = {};
		var methodType = "POST";
		if (appointmentVo.id()) {
			methodType = "PUT";
			objToSave.id = appointmentVo.id();
		}

		objToSave.time = appointmentVo.time();
		objToSave.doctorId = appointmentVo.doctorId();
		objToSave.patientId = appointmentVo.patientId();
		objToSave.smsToDoctor = $("#smsToDoctor").prop("checked");
		objToSave.smsToPatient = $("#smsToPatient").prop("checked");
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
		WsUtils.clearPopupAlert();
		if(!appointmentVo.doctorId()){
			WsUtils.showPopupAlert('Invalid Doctor Selection..');
			return;
		}
		if(!appointmentVo.patientId()){
			WsUtils.showPopupAlert('Invalid Patient Selection..');
			return;
		}

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				
				if($("#addAppointment").attr("isUpdate")){
					WsUtils.showAlert('Rescheduled Appointment Successfully.');
				}else{
					WsUtils.showAlert('Appointment Added Successfully.');
				}
				
				$(".alert").delay(3000).fadeOut("slow");

				var data = resultGlobalClass.response;

				//self.displayAppointments(data);
				location.reload();
				
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
					var html = '<div class="user-panel" style="background:#132d3b">'
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
					//console.log(item.id);
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
		
		if(WsUtils.validate("patientForm"))
			return;

		var methodType = "POST";
		if (patientVo.id()) {
			methodType = "PUT";
		}

		var data = ko.toJS(patientVo);
		//var dob = data.dob;
		 var date = data.dob;
		 var dateArr = date.split("-");
		 data.dob = dateArr[2]+"-"+dateArr[1]+"-"+dateArr[0];

		if (data.remainder == "") {
			data.remainder = null;
		}
		
		data.needWelcomeMessage = false;
		 if($("#patientWelMsg").prop("checked")){
			 data.needWelcomeMessage = true;
		 }

		 console.log(data);
		 
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				WsUtils.showPopupAlert('Saved Successfully.')
				self.cancelPatientOnAppointment();
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
	self.cancelPatientOnAppointment = function() {
		
		//$("#appointmentDateTime").val("02/08/2017 03:00");
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
		
		$(".activediv").hide();
		$("#addAppointment").show();
		$(".addPatient").show();
		$("#patientFormDiv").html("");
		$(".content").css("min-height","250px");
		
	},
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
			appointmentObj:v,
			title : "Dr." + v.doctor.name + ">> " + v.patient.name,
			start : new Date(appointDate.getFullYear(),
					appointDate.getMonth() - 1, appointDate.getDate(), hour,
					min,Math.random(900)),
			allDay : false,
			backgroundColor : '#00a65a', // Blue
			borderColor : '#00a65a' // Blue
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
			    	  var check = $.fullCalendar.formatDate(date,'Y/MM/DD HH:mm');
			    	  var today = moment().format('Y/MM/DD');
			    	  console.log("check-->" + check + '<' + "today-->" + today);
			    	
			    	  if(check < today)
			    	    {
			    		  	WsUtils.showAlert('Please choose the current or future date for appointment.');
			    	    }
			    	    else
			    	    {
			    	    	new Appointment().loadAddAppointmentForm(check);
			    	    }
			    	  
			    	  
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
			    var currColor = '#00a65a' // Red by default
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