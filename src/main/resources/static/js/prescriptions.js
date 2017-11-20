var medications = [];
var presModel = function(){
	var self = this;
	self.items = ko.observableArray([]),
	self.addNewItem = function(data){
		self.items.push(new item(data.medicationDto(),data.beforeFood(),data.morning(),data.noon(),data.night(),data.notes(),data.unit(),data.noOfDays()));
		console.log(self.items.length)
	}.bind(this),
	self.removeItem = function(data){
		self.items.remove(data);
	},
	self.getItems = function(){
		return self.items;
	},
	self.savePrescriptions = function(){
		var data = ko.toJS(self.items);
		new Prescriptions().savePrescriptions(data);
	}
	//To set default values
	self.items.push(new item(3,true,1,1,1,"Test Notes11","Number",3));
	
};



var item = function(id,beforeFood,morning,noon,night,notes,unit,noOfDays){
	
	var self = this;
	self.medications = ko.observableArray(medications),
	self.beforeAfter = ko.observableArray([{"id":true,"name":"Before"},{"id":false,"name":"After"}]),
	self.days = ko.observableArray([]);
	self.medicationDto = ko.observable(id);
	self.beforeFood =  ko.observable(beforeFood);
	self.morning = ko.observable(morning);
	self.noon = ko.observable(noon);
	self.night = ko.observable(night);
	self.notes = ko.observable(notes);
	self.unit = ko.observable(unit);
	self.noOfDays = ko.observable(noOfDays);
	
	var days = [];
	for(i=1; i<=31; i++){
		days[i-1] = {"id":i,"name":i};
	}
	self.days = days;
}

var priscriptionListModel = function(){
	
	var self = this;
	self.prescriptions = ko.observableArray([])
	self.addPrescriptions = function(data){
		self.prescriptions.push(data);
	}
};

var presciptionVO = function(data){

	var self = this;
	self.id = data.id;
	self.doctorName = ko.observable(data.doctorDto.name);
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
	
	self.loadPrescriptionForm = function(divToLoad) {		
		
		loadMedicationsAndInitPresVo();
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$("#"+divToLoad).html(responseObj);	
				
				new Appointment().showDoctorNames("doctor");
				new Appointment().showPatientNames("patient");

				
				ko.cleanNode($("#"+divToLoad)[0]);
				ko.applyBindings(new presModel(), $("#"+divToLoad)[0]);
				
			},
			requestUrl : "../pages/templates/prescriptions.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	self.savePrescriptions = function(data){

		var methodType = "POST";
		/*if(patientVo.id()){
			methodType = "PUT";
		}*/
		
		var dataToSave = {};
		for(i=0; i<data.length; i++){
			delete data[i]["medications"];
			delete data[i]["beforeAfter"];
			delete data[i]["days"];
			
			data[i].beforeFood = data[i]["beforeFood"]["id"];
			data[i].unit = "Number";
			
		}
		dataToSave.prescriptionEntries = data;
		dataToSave.date = "2017-11-10";  // Needs to change
		dataToSave.doctorId= 2; //Needs to change
		dataToSave.patientId = 1; //Needs to change
		
		console.log(dataToSave);
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				WsUtils.showAlert('Saved Successfully.');
				new Patient().loadPrescriptionForm();
				
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
	self.loadPrescriptionListGrid = function(divToLoad) {		
		
		initPresVo();
		//loadMedicationsAndInitPresVo();
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$("#"+divToLoad).html(responseObj);				
				
				self.loadPrescriptionsList();
				
			},
			requestUrl : "../pages/templates/prescriptions_list.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	self.loadPrescriptionsList = function(){

		var model = new priscriptionListModel();
		var methodType = "GET";	
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){				
				var result = resultGlobalClass.response;				
				for(i=0; i<result.length; i++){
					var presciptionVOObj = new presciptionVO(result[i]);
					model.addPrescriptions(presciptionVOObj);
				}
				console.log(result);
				ko.cleanNode($("#content")[0]);
				ko.applyBindings(model, $("#content")[0]);

			},
			requestUrl : "/intelhosp/prescription",
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

		alert(id);
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
	}
	
	
}