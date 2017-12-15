var medications = [];
var patientId = "";
var doctorId = "";
var presModel = function(){
	var self = this;
	self.items = ko.observableArray([]),
	self.addNewItem = function(data){
		self.items.push(new item(0,"",false,0,false,0,false,0,"Test Notes11","Number","Number","Number",3));
		
	}.bind(this),
	self.removeItem = function(data){
		console.log(self.items().length)
		if(self.items().length <= 1){
			alert("Atleast we need only one row. So, not able to delete it. Please reset form if you dont want information. ");
			return;
		}
		self.items.remove(data);
	},
	self.getItems = function(){
		return self.items;
	},
	self.savePrescriptions = function(){
		var data = ko.toJS(self);
		new Prescriptions().savePrescriptions(data);
	}
	//To set default values
	self.items.push(new item(0,"",false,0,false,0,false,0,"","Number","Number","Number",3));
	
};



var item = function(id,name,beforeFoodMorning,morning,beforeFoodNoon,noon,beforeFoodNight,night,notes,unit_morning,unit_noon,unit_night,noOfDays){
	
	var self = this;
	self.show = ko.observable(false);
	self.medicationDto = ko.observable(id);
	self.medicationName = ko.observable(name);
	self.beforeFood_morning =  ko.observable(beforeFoodMorning);
	self.morning = ko.observable(morning);
	self.beforeFood_morning =  ko.observable(beforeFoodMorning);
	self.noon = ko.observable(noon);
	self.beforeFood_noon =  ko.observable(beforeFoodNoon);
	self.night = ko.observable(night);
	self.beforeFood_night =  ko.observable(beforeFoodNight);
	self.notes = ko.observable(notes);
	self.unit_morning = ko.observable(unit_morning);
	self.unit_noon = ko.observable(unit_noon);
	self.unit_night = ko.observable(unit_night);
	self.noOfDays = ko.observable(noOfDays);
	
	
	self.medications = ko.observableArray(medications),
	self.beforeAfter = ko.observableArray([{"id":false,"name":"AF"},{"id":true,"name":"BF"}]),
	self.unitValues = ko.observableArray([{"id":"Number","name":"Number"},{"id":"spoon","name":"spoon"},{"id":"ML","name":"ML"}]),
	self.days = ko.observableArray([]);
	

	var days = [];
	for(i=1; i<=31; i++){
		days[i-1] = {"id":i,"name":i};
	}
	self.days = days;
	self.showHideUnit = function(selectedItem, event){
		
		for(i=0; i<self.medications().length; i++ ){
			var medications = self.medications()[i];
			console.log(medications.id + " >>> " + event.target.value + ">>>" + medications.type)
			selectedItem.show(false);
			if(medications.id == event.target.value && medications.type == "Syrup"){
				selectedItem.show(true);
			}
		}
			
	};
	self.showPopupForMediationSearch = function(rowItem){
		
		rowItem.show(false);
		
		 $('.searchBtn').popover({
			    container: 'body'
		 })
		 
		 //http://v4-alpha.getbootstrap.com/components/popovers/
		 $(".popover-content").html('<input id="prescription" type="text" width="150px" style="width: 150px" value="" autofocus />');
		 
		 $("#prescription").focus();
		 WsUtils.configureAutoComplete("prescription","../intelhosp/medications/find",function(selectedItem){
			
			if(selectedItem.type === "Syrup"){
				rowItem.show(true);
				rowItem.unit_morning("ML");
				rowItem.unit_noon("ML");
				rowItem.unit_night("ML");
			}else{
				rowItem.show(false);
			}
			
			rowItem.medicationDto(selectedItem.id);
			rowItem.medicationName(selectedItem.name);
			
			$('.searchBtn').popover('hide');
		});
	}

}
function morningTableData(self){
	if(self.morning == 0) return "";
	return self.morning + " " + self.unit_morning + " " + ((self.beforeFood_morning)?"Before Food":"After Food");
}
function noonTableData(self){
	if(self.noon == 0){
		return "";
	}else{
		return self.noon + " " + self.unit_noon + " " + ((self.beforeFood_noon)?"Before Food":"After Food");
	}
	
}
function nightTableData(self){
	//alert(self.night)
	if(self.night == 0) return "";
	return self.night + " " + self.unit_night + " " + ((self.beforeFood_night)?"Before Food":"After Food");
}

var priscriptionListModel = function(){
	
	var self = this;
	self.prescriptions = ko.observableArray([])
	self.addPrescriptions = function(data){
		self.prescriptions.push(data);
	};
	self.removeAllPrescriptions = function(){
		self.prescriptions([]);
	}
};

var presciptionVO = function(data){

	var self = this;
	self.id = data.id;
	self.doctorName = ko.observable(data.doctorDto.name);
	self.patientName = ko.observable(data.patientDto.name);
	self.doctorVo = ko.observable(data.doctorDto);
	self.patientVo = ko.observable(data.patientDto);
	self.date = data.date;
	self.prescriptionEntries = ko.observableArray(data.prescriptionEntries);
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
	
	self.loadPrescriptionForm = function(divToLoad,pageType) {		
		
		//loadMedicationsAndInitPresVo();
		
		$('.searchBtn').popover('hide');
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$("#"+divToLoad).html(responseObj);	
				
				ko.cleanNode($("#"+divToLoad)[0]);
				ko.applyBindings(new presModel(), $("#"+divToLoad)[0]);
				
				WsUtils.configureAutoComplete("doctor","../intelhosp/doctors/doctorname/find",function(selectedItem){
					//alert(selectedItem.id);
					doctorId = selectedItem.id;
				});
				WsUtils.configureAutoComplete("patient","../intelhosp/patients/patientname/find",function(selectedItem){
					//alert(selectedItem.id);
					patientId = selectedItem.id;
				});
				
				
				if(pageType == 'profile'){
					$('.tab-pane').removeClass('active');
					$('#prescriptions').addClass("active");
				}
				
			},
			requestUrl : "../pages/templates/prescriptions.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	self.savePrescriptions = function(fullData){

		if(!doctorId) {
			WsUtils.showAlert('Please select doctor.');
			return;
		}
		if(!patientId) {
			WsUtils.showAlert('Please select patient.');
			return;
		}
		var methodType = "POST";
		/*if(patientVo.id()){
			methodType = "PUT";
		}*/
		
		var data = fullData.items;
		var dataToSave = {};
		for(i=0; i<data.length; i++){
			
			if(!data[i]["medicationDto"]){
				WsUtils.showAlert('Medication selection is missing..');
				return;
			}
			delete data[i]["medications"];
			delete data[i]["beforeAfter"];
			delete data[i]["days"];
			
			data[i].beforeFood_morning = data[i]["beforeFood_morning"]["id"];
			data[i].beforeFood_noon = data[i]["beforeFood_noon"]["id"];
			data[i].beforeFood_night = data[i]["beforeFood_night"]["id"];
			
			data[i].unit_morning = data[i]["unit_morning"];
			data[i].unit_noon = data[i]["unit_noon"];
			data[i].unit_night = data[i]["unit_night"];
			
			
		}
		console.log(fullData)
		dataToSave.prescriptionEntries = data;
		var date = new Date();
		var day = date.getDate();
		if(day <= 9 ){
			day = "0"+day ;
		}
		
		var month = date.getMonth()+1;
		if(month <= 9 ){
			month = "0"+month ;
		}
		
		dataToSave.date = date.getFullYear()+ "-" + month+"-"+day; //"2017-11-10";  // Needs to change
		dataToSave.doctorId= doctorId; //Needs to change
		dataToSave.patientId = patientId; //Needs to change
		
		console.log(dataToSave);
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				WsUtils.showAlert('Saved Successfully.');
				new Prescriptions().loadPrescriptionForm('content');
				
			},
			requestUrl : "/intelhosp/prescription",
			requestMethod: methodType,
			requestData : {
				"data" : dataToSave ,
				"queryName" : "",
				"queryParamArray" : {}
			},resultType : "json",
		});
		ServiceCalls.call();
	};
	self.loadMedications = function(){

		var methodType = "GET";	
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				medications = resultGlobalClass.response;
				initPresVo();
			},
			requestUrl : "/intelhosp/medications",
			requestMethod: methodType,
			isAsyncCall:false,
			requestData : {
				"data" : {} ,
				"queryName" : "",
				"queryParamArray" : {}
			},resultType : "json",
		});
		ServiceCalls.call();
	};
	self.loadPrescriptionListGrid = function(divToLoad,patId,docId,pagetype) {		
		
		initPresVo();
		//loadMedicationsAndInitPresVo();
		patientId="";
		doctorId="";
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$("#"+divToLoad).html(responseObj);				
				
				if(pagetype == 'profile'){
					$(".tab-pane").removeClass("active");
					$("#prescriptionList").addClass("active");
					if(selectedPatientProfile) { patId = selectedPatientProfile.id; } // selectedPatientProfile declared in profile.js 
					self.loadPrescriptionsList(patId,docId,divToLoad);
					$(".prescriptionListFilter").html("");
				}else{
					self.loadPrescriptionsList(patId,docId,divToLoad);
				}
				
			},
			requestUrl : "../pages/templates/prescriptions_list.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	self.loadPrescriptionsList = function(patId,docId,divId){

		if(!divId){
			divId = "content";
		}
		
		var methodType = "GET";	
		var url = "/intelhosp/prescription";
		
		if(docId && patId){
			url = "/intelhosp/prescription/doctorandpatient/"+docId+"/"+patId;
		}
		else if(patId){
			url = "/intelhosp/prescription/patient/"+patId;					
		}
		else if(docId){
			url = "/intelhosp/prescription/doctor/"+docId;					
		}
		//alert(url);
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){	
				var model = new priscriptionListModel();
				model.removeAllPrescriptions();
				
				var result = resultGlobalClass.response;				
				for(i=0; i<result.length; i++){
					var presciptionVOObj = new presciptionVO(result[i]);
					model.addPrescriptions(presciptionVOObj);
				}
				//console.log(result);
				ko.cleanNode($("#"+divId)[0]);
				ko.applyBindings(model, $("#"+divId)[0]);
				WsUtils.configureAutoComplete("doctor","../intelhosp/doctors/doctorname/find",function(selectedItem){
					//alert(selectedItem.id);
					doctorId = selectedItem.id;
				});
				WsUtils.configureAutoComplete("patient","../intelhosp/patients/patientname/find",function(selectedItem){
					//alert(selectedItem.id);
					patientId = selectedItem.id;
				});

			},
			requestUrl : url,
			requestMethod: methodType,
			isAsyncCall:false,
			requestData : {
				"data" : {} ,
				"queryName" : "",
				"queryParamArray" : {}
			},resultType : "json",
		});
		ServiceCalls.call();
	};
	self.deletePrescription = function(id){

		//alert(id);
		
		WsUtils.deleteOperation(function(){
			var model = new priscriptionListModel();
			var methodType = "DELETE";	
			var url = "/intelhosp/prescription/"+id;
			resultGlobalObject = $.extend(resultGlobalClass, {
				callback : function(){				
					WsUtils.showAlert("Delete Success");
					self.loadPrescriptionListGrid("content");
				},
				requestUrl : url,
				requestMethod: methodType,
				isAsyncCall:false,
				requestData : {
					"data" : {} ,
					"queryName" : "",
					"queryParamArray" : {}
				},resultType : "json",
			});
			ServiceCalls.call();
		},"");
		
	},
	self.filterPrescription = function(){
		//alert(patientId + " >> " + doctorId)
		self.loadPrescriptionListGrid("content",patientId,doctorId);
	}
	
	
}