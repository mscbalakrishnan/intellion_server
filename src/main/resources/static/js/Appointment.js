var appointmentVo;
function initAppointmentVo() {
	appointmentVo = {
		id : ko.observable(""),
		time : ko.observable(""),
		doctor : ko.observable(""),
		patient : ko.observable("")

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
						start : new Date(appointDate.getFullYear(), appointDate
								.getMonth() - 1, appointDate.getDate(), hour,
								min),
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

	self.loadAddAppointmentForm = function(date) {
		alert(date);
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				WsUtils.showPopupWindow(function() {

				});
				$("#modelData").html(responseObj);
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

		var methodType = "POST";
		if (appointmentVo.id()) {
			methodType = "PUT";
		}

		var objToSave = {};
		objToSave.id = appointmentVo.id();
		objToSave.time = appointmentVo.time();
		objToSave.doctor = appointmentVo.doctor();
		objToSave.patient = appointmentVo.patient();

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				alert('Saved Successfully.')
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
	self.showDoctorNames = function() {

		try {
			// console.log($("#doctor").val());

			var searchString = $("#doctor").val();
			// alert("searchString "+searchString);
			searchString = encodeURI(searchString);
			// alert("searchString =====>"+searchString+",inputBoxId :
			// "+inputBoxId);
			$("#doctor")
					.autocomplete(
							{
								matchContains : true,
								minChars : 0,
								source : function(request, response) {

									$.ajax({
												url : "intelhosp/appointments/doctorname?name="
														+ searchString,
												dataType : "json",
												type : "post",
												data : {
													maxRows : 20,
													term : request.term
												},
												success : function(data) {

													if (data.returnList.length == 0) {
														$("#" + resultBoxId)
																.val("");
													}
													response($.map(
														data.returnList,
																function(item) {
																		return {
																			label : item.patientName,
																			value : item.patientName,
																			code : item.patientId
																					+ "~-~"
																					+ item.patientName
																					+ "~-~"
																					+ item.patientEmail
																					+ "~-~"
																					+ item.patientTelephone,
																			pkIds : item.patientId,
																			mail : item.patientEmail,
																			phone : item.patientTelephone
																		}
														}));

												}
											});
								},

								select : function(event, ui) {
									console.log(ui.item);
								},
								change : function(event, ui) {
									console.log(ui.item);
								}
							});
		} catch (e) {
			alert(e);
		}
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
	}

}