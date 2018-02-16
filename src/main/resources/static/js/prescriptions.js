var medications = [];
var patientId = "";
var doctorId = "";
var currentPrescriptionPage = "";
var clinicDetails;

var presModel = function() {
	var self = this;
			self.id,
			self.items = ko.observableArray([]),
			self.addNewItem = function(data) {
				console.log(data);
				self.items.push(new item(0, "", false, 0, false, 0, false, 0,
						"", "Number", "Number", "Number", 0));

			}.bind(this),
			
			self.decideButtonVisible = function(index){
				if(index === self.items().length)
					return true;
				else
					return false;
			}.bind(this),

			self.addNewItemToUpdate = function(data) {
				console.log(data);

				console.log('data is available>>' + data.name);
				
				var newItem = new item(data.medicationDto.id,
						data.medicationDto.name, data.beforeFood_morning,
						data.morning, data.beforeFood_noon, data.noon,
						data.beforeFood_night, data.night, data.notes,
						data.unit_morning, data.unit_noon, data.unit_night,
						data.noOfDays, data.id);
				//alert(data.medicationDto.type);
				if(data.medicationDto.type === "Syrup") {
					newItem.showfoodmode(true);
				}else {
					newItem.showfoodmode(false);
				}
				self.items.push(newItem);

			}.bind(this),
			self.removeItem = function(data) {
				console.log(self.items().length)
				if (self.items().length <= 1) {
					alert("Atleast we need only one row. So, not able to delete it. Please reset form if you dont want information. ");
					return;
				}
				self.items.remove(data);
			}, self.getItems = function() {
				return self.items;
			}, self.savePrescriptions = function() {
				var data = ko.toJS(self);
				new Prescriptions().savePrescriptions(data);
			},
			// To set default values
			self.addDefaultValues = function() {

				self.items.push(new item(0, "", false, 0, false, 0, false, 0,
						"", "Number", "Number", "Number", 0));

			}

};

var item = function(id, name, beforeFoodMorning, morning, beforeFoodNoon, noon,
		beforeFoodNight, night, notes, unit_morning, unit_noon, unit_night,
		noOfDays, rowId) {

	var self = this;
	self.id = rowId;
	self.showfoodmode = ko.observable(false);
	self.medicationDto = ko.observable(id);
	self.medicationName = ko.observable(name);
	self.beforeFood_morning = ko.observable(beforeFoodMorning);
	self.morning = ko.observable(morning);
	self.beforeFood_morning = ko.observable(beforeFoodMorning);
	self.noon = ko.observable(noon);
	self.beforeFood_noon = ko.observable(beforeFoodNoon);
	self.night = ko.observable(night);
	self.beforeFood_night = ko.observable(beforeFoodNight);
	self.notes = ko.observable(notes);
	self.unit_morning = ko.observable(unit_morning);
	self.unit_noon = ko.observable(unit_noon);
	self.unit_night = ko.observable(unit_night);
	self.noOfDays = ko.observable(noOfDays);

	self.medications = ko.observableArray(medications), self.beforeAfter = ko
			.observableArray([ {
				"id" : false,
				"name" : "AF"
			}, {
				"id" : true,
				"name" : "BF"
			} ]), self.unitValues = ko.observableArray([ {
		"id" : "Number",
		"name" : "Number"
	}, {
		"id" : "spoon",
		"name" : "spoon"
	}, {
		"id" : "ML",
		"name" : "ML"
	} ]), self.days = ko.observableArray([]);

	var days = [];
	for (i = 1; i <= 31; i++) {
		days[i - 1] = {
			"id" : i,
			"name" : i
		};
	}
	self.days = days;
	
	self.showPopupForMediationSearch = function(rowItem) {

		//rowItem.showfoodmode(false);

		$('.searchBtn').popover({
			container : 'body'
		})

		// http://v4-alpha.getbootstrap.com/components/popovers/
		$(".popover-content")
				.html(
						'<input id="prescription" type="text" width="150px" style="width: 150px" value="" autofocus />');

		$("#prescription").focus();
		WsUtils.configureAutoComplete("prescription",
				"../intelhosp/medications/find", function(selectedItem) {

					if (selectedItem.type === "Syrup") {
						rowItem.showfoodmode(true);
						rowItem.unit_morning("ML");
						rowItem.unit_noon("ML");
						rowItem.unit_night("ML");
					} else {
						rowItem.showfoodmode(false);
					}

					rowItem.medicationDto(selectedItem.id);
					rowItem.medicationName(selectedItem.name);

					$('.searchBtn').popover('hide');
				});
	}

}
function morningTableData(self) {
	if (self.morning == 0)
		return "";
	return self.morning + " " + self.unit_morning + " "
			+ ((self.beforeFood_morning) ? "Before Food" : "After Food");
}
function noonTableData(self) {
	if (self.noon == 0) {
		return "";
	} else {
		return self.noon + " " + self.unit_noon + " "
				+ ((self.beforeFood_noon) ? "Before Food" : "After Food");
	}

}
function nightTableData(self) {
	// alert(self.night)
	if (self.night == 0)
		return "";
	return self.night + " " + self.unit_night + " "
			+ ((self.beforeFood_night) ? "Before Food" : "After Food");
}

var priscriptionListModel = function() {

	var self = this;
	self.prescriptions = ko.observableArray([])
	self.addPrescriptions = function(data) {
		self.prescriptions.push(data);
	};
	self.removeAllPrescriptions = function() {
		self.prescriptions([]);
	}
};

var presciptionVO = function(data) {

	var self = this;
	self.id = data.id;
	self.doctorName = ko.observable(data.doctorDto && data.doctorDto.name);
	self.patientName = ko.observable(data.patientDto && data.patientDto.name);
	self.doctorVo = ko.observable(data.doctorDto);
	self.patientVo = ko.observable(data.patientDto);
	self.date = data.date;
	self.prescriptionEntries = ko.observableArray(data.prescriptionEntries);
	self.clinicObject = ko.observable();
}

var presVO;
function initPresVo() {

	presVO = new presModel();

}

function loadMedicationsAndInitPresVo() {

	new Prescriptions().loadMedications();

}

var Prescriptions = function() {

	var self = this;

	self.loadPrescriptionForm = function(divToLoad, pageType) {

		// loadMedicationsAndInitPresVo();
		
		if(currentPrescriptionPage == 'edit'){
			$("#popupcontent").html("");
			$("#wholepagepopup").hide();
			currentPrescriptionPage = "";
			return;
		}

		$('.searchBtn').popover('hide');

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				$("#" + divToLoad).html(responseObj);

				var presModelTemp = new presModel();
				presModelTemp.addDefaultValues();

				ko.cleanNode($("#" + divToLoad)[0]);
				ko.applyBindings(presModelTemp, $("#" + divToLoad)[0]);

				WsUtils.configureAutoComplete("doctor",
						"../intelhosp/doctors/doctorname/find", function(
								selectedItem) {
							// alert(selectedItem.id);
							doctorId = selectedItem.id;
						});
				WsUtils.configureAutoComplete("patient",
						"../intelhosp/patients/patientname/find", function(
								selectedItem) {
							// alert(selectedItem.id);
							patientId = selectedItem.id;
						});
				

				if (pageType == 'profile') {
					$('.tab-pane').removeClass('active');
					$('#prescriptions').addClass("active");
					$('#presProfMenu').addClass("active");
					
					$('#presProfListMenu').removeClass("active");
					patientId = selectedPatientProfile && selectedPatientProfile.id;
					$("#patient").val(selectedPatientProfile.name);
					$("#patient").attr("disabled","disabled");
					$("#cancelPresForm").click(function(){
						
						new Prescriptions().loadPrescriptionForm('prescriptions','profile');
					})
				} else {
					
					$("#cancelPresForm").click(function(){
						
						new Prescriptions().loadPrescriptionForm('content');
					});
					
					
				}
				
				
				

			},
			requestUrl : "../pages/templates/prescriptions.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();

	};
	self.savePrescriptions = function(fullData) {

		if (!doctorId) {
			WsUtils.showAlert('Please select doctor.');
			return;
		}
		if (!patientId) {
			WsUtils.showAlert('Please select patient.');
			return;
		}
		var methodType = "POST";

		if (fullData.id) {
			methodType = "PUT";
		}

		//alert(methodType);

		var data = fullData.items;
		var dataToSave = {};
		for (i = 0; i < data.length; i++) {

			if (!data[i]["medicationDto"]) {
				WsUtils.showAlert('Medication selection is missing..');
				return;
			}
			delete data[i]["medications"];
			delete data[i]["beforeAfter"];
			delete data[i]["days"];

			data[i].beforeFood_morning = data[i]["beforeFood_morning"];
			data[i].beforeFood_noon = data[i]["beforeFood_noon"];
			data[i].beforeFood_night = data[i]["beforeFood_night"];

			data[i].unit_morning = data[i]["unit_morning"];
			data[i].unit_noon = data[i]["unit_noon"];
			data[i].unit_night = data[i]["unit_night"];
			
			console.log(">>>>>>");
			console.log(data)

		}
		console.log(fullData)
		dataToSave.prescriptionEntries = data;
		var date = new Date();
		var day = date.getDate();
		if (day <= 9) {
			day = "0" + day;
		}

		var month = date.getMonth() + 1;
		if (month <= 9) {
			month = "0" + month;
		}

		dataToSave.date = date.getFullYear() + "-" + month + "-" + day; // "2017-11-10";
		// //
		// Needs
		// to
		// change
		dataToSave.doctorId = doctorId; // Needs to change
		dataToSave.patientId = patientId; // Needs to change
		dataToSave.id = fullData.id;

		console.log(dataToSave);

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				WsUtils.showAlert('Saved Successfully.');
				
				if($("#wholepagepopup")){
					$("#popupcontent").html("");
					$("#wholepagepopup").hide();
					
				}
				if(currentPrescriptionPage != 'edit'){
					new Prescriptions().loadPrescriptionForm('content');
					
				}	
				if(currentPrescriptionPage === 'edit'){
					new Prescriptions().loadPrescriptionListGrid('content');					
				}
				
				currentPrescriptionPage = "";
			},
			requestUrl : "/intelhosp/prescription",
			requestMethod : methodType,
			requestData : {
				"data" : dataToSave,
				"queryName" : "",
				"queryParamArray" : {}
			},
			resultType : "json",
		});
		ServiceCalls.call();
	};
	self.loadMedications = function() {

		var methodType = "GET";
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				medications = resultGlobalClass.response;
				initPresVo();
			},
			requestUrl : "/intelhosp/medications",
			requestMethod : methodType,
			isAsyncCall : false,
			requestData : {
				"data" : {},
				"queryName" : "",
				"queryParamArray" : {}
			},
			resultType : "json",
		});
		ServiceCalls.call();
	};
	self.loadPrescriptionListGrid = function(divToLoad, patId, docId, pagetype) {

		initPresVo();
		// loadMedicationsAndInitPresVo();
		patientId = "";
		doctorId = "";
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				$("#" + divToLoad).html(responseObj);

				if (pagetype == 'profile') {
					$(".tab-pane").removeClass("active");
					$("#prescriptionList").addClass("active");
					if (selectedPatientProfile) {
						patId = selectedPatientProfile.id;
					} // selectedPatientProfile declared in profile.js
					self.loadPrescriptionsList(patId, docId, divToLoad);
					$(".prescriptionListFilter").html("");
					$("#addPres").click(function(){
						new Prescriptions().loadPrescriptionForm('prescriptions','profile');
					});
				} else {
					self.loadPrescriptionsList(patId, docId, divToLoad);
					$("#addPres").click(function(){
						new Prescriptions().loadPrescriptionForm('content');
					});
				}

			},
			requestUrl : "../pages/templates/prescriptions_list.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();

	};
	self.loadPrescriptionsList = function(patId, docId, divId) {

		if (!divId) {
			divId = "content";
		}

		var methodType = "GET";
		var url = "/intelhosp/prescription";

		if (docId && patId) {
			url = "/intelhosp/prescription/doctorandpatient/" + docId + "/"
					+ patId;
		} else if (patId) {
			url = "/intelhosp/prescription/patient/" + patId;
		} else if (docId) {
			url = "/intelhosp/prescription/doctor/" + docId;
		}
		 //alert(url);
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var model = new priscriptionListModel();
				model.removeAllPrescriptions();

				var result = resultGlobalClass.response;
				for (i = 0; i < result.length; i++) {
					var presciptionVOObj = new presciptionVO(result[i]);
					model.addPrescriptions(presciptionVOObj);
				}
				// console.log(result);
				ko.cleanNode($("#" + divId)[0]);
				ko.applyBindings(model, $("#" + divId)[0]);
				WsUtils.configureAutoComplete("doctorId",
						"../intelhosp/doctors/doctorname/find", function(
								selectedItem) {
							// alert(selectedItem.id);
							doctorId = selectedItem.id;
						});
				WsUtils.configureAutoComplete("patientId",
						"../intelhosp/patients/patientname/find", function(
								selectedItem) {
							// alert(selectedItem.id);
							patientId = selectedItem.id;
						});

			},
			requestUrl : url,
			requestMethod : methodType,
			isAsyncCall : false,
			requestData : {
				"data" : {},
				"queryName" : "",
				"queryParamArray" : {}
			},
			resultType : "json",
		});
		ServiceCalls.call();
	};
	self.deletePrescription = function(id) {

		// alert(id);

		WsUtils.deleteOperation(function() {
			var model = new priscriptionListModel();
			var methodType = "DELETE";
			var url = "/intelhosp/prescription/" + id;
			resultGlobalObject = $.extend(resultGlobalClass, {
				callback : function() {
					WsUtils.showAlert("Delete Success");
					self.loadPrescriptionListGrid("content");
				},
				requestUrl : url,
				requestMethod : methodType,
				isAsyncCall : false,
				requestData : {
					"data" : {},
					"queryName" : "",
					"queryParamArray" : {}
				},
				resultType : "json",
			});
			ServiceCalls.call();
		}, "");

	}, self.filterPrescription = function() {
		// alert(patientId + " >> " + doctorId)
		self.loadPrescriptionListGrid("content", patientId, doctorId);
	};
	self.editPrescription = function(prescriptionDetails, divToLoad, pageType) {

		console.log(prescriptionDetails);
		$('.searchBtn').popover('hide');
		currentPrescriptionPage = "edit";

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				$("#" + divToLoad).html(responseObj);
				if(!pageType){
					$("#wholepagepopup").removeClass("hide");
					$("#wholepagepopup").show();
					window.location.href = "#pagetop";
				}

				initPresVo();
				var presEntries = prescriptionDetails.prescriptionEntries();
				console.log(presEntries.length);

				for (var i = 0; i < presEntries.length; i++) {
					console.log(i)
					presVO.addNewItemToUpdate(presEntries[i]);
				}

				patientId = prescriptionDetails.patientVo().id;
				doctorId = prescriptionDetails.doctorVo().id;

				$("#doctor").val(prescriptionDetails.doctorVo().name);
				$("#patient").val(prescriptionDetails.patientVo().name);
				presVO.id = prescriptionDetails.id;

				ko.cleanNode($("#" + divToLoad)[0]);
				ko.applyBindings(presVO, $("#" + divToLoad)[0]);

				WsUtils.configureAutoComplete("doctor",
						"../intelhosp/doctors/doctorname/find", function(
								selectedItem) {
							doctorId = selectedItem.id;
						});
				WsUtils.configureAutoComplete("patient",
						"../intelhosp/patients/patientname/find", function(
								selectedItem) {
							patientId = selectedItem.id;
						});

				if (pageType == 'profile') {
					$('.tab-pane').removeClass('active');
					$('#prescriptions').addClass("active");
					
				}
				
				$("#cancelPresForm").click(function(){					
					try{
						closeWholePagePopup();
					}catch(e){}
				})

			},
			requestUrl : "../pages/templates/prescriptions.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	}
	
	self.printPrescription = function(prescriptionDetails, divToLoad, pageType) {

		
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				$("#" + divToLoad).html(responseObj);
				if(!pageType){
					$("#wholepagepopup").removeClass("hide");
					$("#wholepagepopup").show();
					window.location.href = "#pagetop";
				}
				
				self.getClinicDetails(prescriptionDetails,divToLoad);
				
				
			},
			requestUrl : "../pages/templates/print_prescription.html",
			requestData : {},
			resultType : "text",
			isAsyncCall:true,
		});
		ServiceCalls.loadHtmlPage();
	}
	
	self.getClinicDetails = function(prescriptionDetails,divToLoad){		
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var dataArr = resultGlobalClass.response;
				var settingsController = new SettingsController();
				
				if(dataArr){
					dataArr.forEach(val => {
						settingDetailsArr[val.category] = val;
						if(val.category === 'clinic'){
							clinicDetails = val;
						}
					});
				}
				
				prescriptionDetails.clinicObject(clinicDetails.clinicObject);

				ko.cleanNode($("#" + divToLoad)[0]);
				ko.applyBindings(prescriptionDetails, $("#" + divToLoad)[0]);
				$("#content").hide();
				$(".main-footer").hide();
				$(".popupHeader").hide();
				$("#closeBtn").hide();
				
				console.log(window.print());
				closeWholePagePopup();
				$("#content").show();
				$(".main-footer").show();
				$(".popupHeader").show();
				$("#closeBtn").show();
				
				
			},
			requestUrl : "/intelhosp/settings",
			requestMethod: "GET",
			isAsyncCall:true,
			requestData : {
				"data" : {} ,
				"queryName" : "",
				"queryParamArray" : {}
			},resultType : "json",
		});
		ServiceCalls.call();
		
	}

}