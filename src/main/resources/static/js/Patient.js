/*{"id":1,"title":"Mr","name":"Guru","mobile":"(+91)1234567890","address1":null,"address2":null,"city":null,"pincode":null,
 * "profileId":null,"label":null,"landline":null,"bloodGroup":"BPositive","gender":null,"email":null,"dob":"1989-01-15",
 * "age":28,"medicalHistory":null,"medicalAlert":null,"allergies":null,"needWelcomeMessage":false,"birthdayWish":false,
 * "remainder":null,
 * "appointments":[{"id":3,"time":"2017-07-28T22:45:36","doctor":2,"patient":1},
 * {"id":1,"time":"2017-07-26T22:45:36","doctor":2,"patient":1},
 * {"id":2,"time":"2017-07-27T22:45:36","doctor":2,"patient":1}],"occupation":null}*/

var patientVo;

function initPatientVo() {
	patientVo = {
		id : ko.observable(""),
		name : ko.observable(""),
		title : ko.observable("0"),
		titleList:ko.observableArray([{"id":0,"name":"Mr."},{"id":1,"name":"Mrs."},{"id":2,"name":"Ms."},{"id":3,"name":"Master."},{"id":4,"name":"Baby."}]),
		email : ko.observable(""),
		mobile : ko.observable(""),
		landline : ko.observable(""),
		address1 : ko.observable(""),
		occupation:ko.observable(""),
		
		address2 : ko.observable(""),
		dob : ko.observable(""),
		city : ko.observable(""),
		pincode : ko.observable(""),
		profileId : ko.observable(""),
		
		label : ko.observable(""),
		landline : ko.observable(""),
		bloodGroup : ko.observable(""),
		bloodGroupList:ko.observableArray([{"id":0,"name":"A+"},{"id":1,"name":"A-"},{"id":2,"name":"B+"},{"id":3,"name":"B-"},{"id":4,"name":"O+"},{"id":5,"name":"O-"},{"id":6,"name":"AB+"},{"id":7,"name":"AB-"}]),
		gender : ko.observable(""),
		genderList:ko.observableArray([{"id":0,"name":"Male"},{"id":1,"name":"Female"}]),
		age : ko.observable(""),
		
		medicalHistory : ko.observable(""),
		medicalAlert : ko.observable(""),
		dentalHistory : ko.observable(""),
		allergies : ko.observable(""),
		needWelcomeMessage : ko.observable(""),
		birthdayWish : ko.observable(""),
		remainder : ko.observable(""),
		appointments : ko.observableArray[{appointments}]

	};
	
	
};
initPatientVo();
var appointments = {
		id : ko.observable(""),
		time : ko.observable(""),
		doctor :  ko.observable(""),
		patient: ko.observable("")
};

var Patient = function() {

	var self = this;
	
	self.loadPatientPage = function(data) {
		
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$(".content").html(responseObj);
				initPatientVo();
				if(data){
					var dob = data["dob"];
					if(dob){
						dateArr = dob.split("-");
						 data.dob = dateArr[2]+"-"+dateArr[1]+"-"+dateArr[0];
						 patientVo.dob(data.dob);
					}
					
					if(data["gender"] == "Male")
						patientVo.gender(0);
					else
						patientVo.gender(1);
						
					patientVo.id (data["id"]),
					patientVo.name ( data["name"]),
					patientVo.title ( data["titleId"]),
					patientVo.email ( data["email"]),
					patientVo.mobile ( data["mobile"]),
					patientVo.landline ( data["landline"]),
					patientVo.mobile ( data["mobile"]),
					patientVo.address1 ( data["address1"]),
					
					patientVo.address2 ( data["address2"]),
					
					// patientVo.dob ( data["dob"]),
					patientVo.city ( data["city"]),
					patientVo.pincode ( data["pincode"]),
					patientVo.profileId ( data["profileId"]),
					
					patientVo.label ( data["label"]),
					patientVo.landline ( data["landline"]),
					patientVo.bloodGroup ( data["bloodGroupId"]),
					
					patientVo.age ( data["age"]),
					patientVo.occupation(data["occupation"]);
					
					patientVo.medicalHistory ( data["medicalHistory"]),
					patientVo.dentalHistory ( data["dentalHistory"]),
					patientVo.medicalAlert ( data["medicalAlert"]),
					patientVo.allergies ( data["allergies"]),
					patientVo.needWelcomeMessage ( data["needWelcomeMessage"]),
					patientVo.birthdayWish ( data["birthdayWish"]),
					patientVo.remainder ( data["remainder"])
					
					// patientVo.appointments ( data["appointments"])
				}
				ko.cleanNode($("#patientForm")[0]);
				ko.applyBindings(patientVo, $("#patientForm")[0]);
								
				 $("#dob" ).datetimepicker({
					 timepicker:false,
					 format : 'd-m-Y',
				 });
			},
			requestUrl : "../pages/templates/patient_add.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	
	self.loadPatientsPageList = function(selectionMode) {
		
		$("#content").html(WsUtils.getGridFilterContainer("Patients List", "Add Patient"));
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				$("#comboDiv").html(WsUtils.getGridFilterContainer());
				var dataArr=resultGlobalClass.response;			
						
				$("#addNewBtn").bind("click",function(){
					self.loadPatientPage();
				});
				
				var dataArray =  dataArr;
				if(dataArray.length > 0 ){
					initDataGridModel();
					var dgm = $.extend(dataGridModel,{
							dataArray : dataArray ,
							gridHeaders : {"title":"Title","name":"Name","gender":"Gender","profileId":"Profile ID","mobile":"Mobile","email" : "Email","label":"Label/Grp"},
							hiddenColumns : ["id","titleId","dob","occupation","bloodGroup","age","address1","address2","city","pincode","landline","medicalAlert","medicalHistory","allergies","needWelcomeMessage","birthdayWish","remainder","appointments","dentalHistory","bloodGroupId"],
							isDeleteButton : true,
							deleteIconClass:"glyphicon glyphicon-remove-sign deleteIconImage",
							isViewButton : true,
							isSearchVisible:true,
							viewIconClass : "glyphicon glyphicon-eye-open viewIcon",
							isCustomPagination : false,
							callbackFunction : function(data,event,type){
								if(type == 'view'){
									new Profile().loadPatientProfilePage(data);
									//new Prescriptions().loadPrescriptionListGrid('content',data.id);
								}
								else if(type == 'delete')
								{	
									var requestUrl = "/intelhosp/patients/"+data.id;
									console.log(requestUrl)
									WsUtils.deleteOperation(function(){				
										resultGlobalObject = $.extend(resultGlobalClass, {
											callback : function(){
												WsUtils.showAlert("Delete Success");
												
												self.loadPatientsPageList();	
											},
											requestUrl : requestUrl,
											requestMethod:"DELETE",
											requestData : {
												"data" : data,
											},resultType : "json",
										});
										ServiceCalls.call();
									}, "");
									
								}
								else if(type = "rowSelect")
								{
									self.loadPatientPage(data);
									
								}	
							},
					});
					dataGridController.showDataGrid(dgm, "gridContainer", "doctorgrid",false);
				}
				else
				{
				   WsUtils.showAlert(Language.noData);
				}	
			},
			requestUrl : "/intelhosp/patients/",
			requestMethod: "GET",
			requestData : {
			},resultType : "json",
		});
		
		ServiceCalls.call();
	};
	
	self.savePatient = function(){
		
		if(WsUtils.validate("patientForm"))
			return;
		
		var methodType = "POST";
		if(patientVo.id()){
			methodType = "PUT";
		}
		
		 var data = ko.toJS(patientVo);
		
		 var bloodGroup = data.bloodGroup;
		 var date = data.dob;
		 var dH = data.dentalHistory;
		 var dateArr = date.split("-");
		 data.dob = dateArr[2]+"-"+dateArr[1]+"-"+dateArr[0];
		 
		 if(data.remainder == ""){
			 data.remainder = null;
		 }
		 data.needWelcomeMessage = false;
		 if($("#patientWelMsg").prop("checked")){
			 data.needWelcomeMessage = true;
		 }
		 console.log(data);
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				WsUtils.showAlert('Saved Successfully.');
				new Patient().loadPatientsPageList();
				
			},
			requestUrl : "/intelhosp/patients",
			requestMethod: methodType,
			requestData : {
				"data" : data ,
				"queryName" : "",
				"queryParamArray" : {}
			},resultType : "json",
		});
		ServiceCalls.call();
	},
	self.calculateAge = function(dob){
		//var dob = '15-02-1985';
		var curDt = new Date();

		var dobArr = dob.split("-");

		var dobYr = dobArr[2];
		var dobMon = dobArr[1];

		var curYr = curDt.getFullYear();
		var curMon = curDt.getMonth() + 1;


		if(curMon < dobMon ) {
			curYr = curYr - 1;
		}

		var age = curYr - dobYr;
		console.log(age);
		
		return age;
	}
}
