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
		name : ko.observable("Bala"),
		title : ko.observable("1"),
		email : ko.observable("a@a.com"),
		mobile : ko.observable("12121"),
		landline : ko.observable("2323"),
		address1 : ko.observable("add1"),
		occupation:ko.observable("former"),
		
		address2 : ko.observable("add2"),
		dob : ko.observable("1985/06/04"),
		city : ko.observable(""),
		pincode : ko.observable(""),
		profileId : ko.observable(""),
		
		label : ko.observable(""),
		landline : ko.observable(""),
		bloodGroup : ko.observable(""),
		bloodGroupList:ko.observableArray([{"id":1,"name":"A1+"},{"id":2,"name":"O+"}]),
		gender : ko.observable(""),
		genderList:ko.observableArray([{"id":1,"name":"Male"},{"id":2,"name":"Female"}]),
		age : ko.observable(""),
		
		medicalHistory : ko.observable("medical hostory"),
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
					patientVo.id (data["id"]),
					patientVo.name ( data["name"]),
					patientVo.title ( data["title"]),
					patientVo.email ( data["email"]),
					patientVo.mobile ( data["mobile"]),
					patientVo.landline ( data["landline"]),
					patientVo.mobile ( data["mobile"]),
					patientVo.address1 ( data["address1"]),
					
					patientVo.address2 ( data["address2"]),
					patientVo.dob ( data["dob"]),
					patientVo.city ( data["city"]),
					patientVo.pincode ( data["pincode"]),
					patientVo.profileId ( data["profileId"]),
					
					patientVo.label ( data["label"]),
					patientVo.landline ( data["landline"]),
					patientVo.bloodGroup ( data["bloodGroup"]),
					patientVo.gender ( data["gender"]),
					patientVo.age ( data["age"]),
					
					patientVo.medicalHistory ( data["medicalHistory"]),
					patientVo.medicalAlert ( data["medicalAlert"]),
					patientVo.allergies ( data["allergies"]),
					patientVo.needWelcomeMessage ( data["needWelcomeMessage"]),
					patientVo.birthdayWish ( data["birthdayWish"]),
					patientVo.remainder ( data["remainder"])
					//patientVo.appointments ( data["appointments"])
				}
				ko.cleanNode($("#patientForm")[0]);
				ko.applyBindings(patientVo, $("#patientForm")[0]);
								
				 $("#dob" ).datetimepicker({
					 timepicker:false
				 });
			},
			requestUrl : "../pages/templates/patient_add.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	
	self.loadPatientsPageList = function(selectionMode) {
		
		$("#content").html(WsUtils.getGridFilterContainer());
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
							gridHeaders : {"title":"Title","name":"Name","mobile":"Mobile","email" : "Email","age":"Age"},
							hiddenColumns : ["id","label","profileId","medicalAlert","medicalHistory","allergies","needWelcomeMessage","birthdayWish","remainder","appointments"],
							isDeleteButton : true,
							isCustomPagination : false,
							callbackFunction : function(data,event,type){
								if(type == 'delete')
								{	
									var requestUrl = "/intelhosp/patients/"+data.id;
									console.log(requestUrl)
									WsUtils.deleteOperation(function(){				
										resultGlobalObject = $.extend(resultGlobalClass, {
											callback : function(){
												WsUtils.showAlert("Delete Success");
												$(".alert").delay(3000).fadeOut("slow");
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
		 var dob = data.dob;
		 var date = dob.split(" ")[0];
		 var dateArr = date.split("/");
		 data.dob = dateArr[0]+"-"+dateArr[1]+"-"+dateArr[2];
		 
		 if(data.remainder == ""){
			 data.remainder = null;
		 }
		
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
	}
}