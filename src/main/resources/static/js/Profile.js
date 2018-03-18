var selectedPatientProfile;
var Profile = function() {

	var self = this;
	self.loadDoctorProfilePage = function(data) {
		
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$(".content").html(responseObj);	
			},
			requestUrl : "../pages/templates/doctor_profile.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	
	self.loadPatientProfilePage = function(data) {

		selectedPatientProfile = data;
		console.log(selectedPatientProfile);
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$(".content").html(responseObj);	
				self.loadPatientProfileInformation(data);
				
			},
			requestUrl : "../pages/templates/patient_profile.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	
	self.loadPatientProfileInformation = function(data){
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$("#profile").html(responseObj);
				
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
						
					$("#patDispName").html(data["name"]);
					$("#patDispPhName").html(data["mobile"]);
					patientVo.id (data["id"]),
					patientVo.name ( data["name"]),
					patientVo.title ( data["title"]),
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
					patientVo.bloodGroup ( data["bloodGroup"]),
					
					patientVo.age ( data["age"]),
					
					patientVo.medicalHistory ( data["medicalHistory"]),
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
}