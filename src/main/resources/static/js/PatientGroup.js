var patientGroupVo;

function initPatientGroupVo() {
	patientGroupVo = {
		groupIdPat : ko.observable(""),
		groupNamePat : ko.observable(""),
		patients : ko.observableArray(""),
		groupsList : ko.observableArray("")
	}
}
initPatientGroupVo();


var PatientGroup = function() {

	var self = this;
	self.loadPatientGroupPage = function() {
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				
				$("#patientGroupAddContainer").html(responseObj);
				$(".tab-pane").removeClass("active");
				$("#patientGroupAddContainer").addClass("active");

				ko.cleanNode($("#patientGroupDiv")[0]);
				ko.applyBindings(patientGroupVo, $("#patientGroupDiv")[0]);

				WsUtils.configureAutoComplete("patientName",
						"../intelhosp/patients/patientname/find", function(
								selectedItem) {
							alert('you have choosen ' + selectedItem.name);
							patientGroupVo.patients.push({
								id : selectedItem.id,
								name : selectedItem.name
								
							});
							$("patientName").val("");
							self.saveGroupPatients(selectedItem.id);
							

						});

				self.loadGroupsData();
			},
			requestUrl : "../pages/templates/patient_group_add.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	};
	
	self.loadSelectedGroupsPatients = function() {
		
		
		var selectedGrpId = patientGroupVo.groupIdPat();
		alert("grpIdPatLoc:"+selectedGrpId);
		
		var url = "/intelhosp/label/"+selectedGrpId;

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				
				var responseObj = resultGlobalClass.response;
				patientGroupVo.patients.removeAll();
				if(responseObj.length > 0){
					var patList =responseObj;
					patList.forEach(val => {
						var patId = val.id;
						var patName = val.name;
						patientGroupVo.patients.push({id:patId,name:patName});
					});				
				}else{
					patientGroupVo.patients.removeAll();
				}
			},
			requestUrl :url,
			requestMethod : "GET",
			requestData : {},
			resultType : "json",
		});
		ServiceCalls.call();
	}
	self.saveGroupPatients = function(selectedPatId) {
		var grpIdPatLoc = patientGroupVo.groupIdPat();
		alert(grpIdPatLoc);
		alert(selectedPatId);
		var URL = "/intelhosp/label/"+grpIdPatLoc+"/"+selectedPatId;
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				alert('saved')

			},
			requestUrl : URL,
			requestMethod : "PUT",
			requestData : {},
			resultType : "json",
		});

		ServiceCalls.call();
	}

	self.loadGroupsData = function() {
		//alert("loadGroupsData");
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				//alert("resp:"+responseObj);
				patientGroupVo.groupsList(responseObj);

			},
			requestUrl : "/intelhosp/label",
			requestMethod : "GET",
			requestData : {},
			resultType : "json",
		});

		ServiceCalls.call();
	}
	
	self.delGroupPatients = function(selectedPatId) {

		var grpIdPatLoc = patientGroupVo.groupIdPat();
		var selectedPatIdLoc = selectedPatId;

		alert(selectedPatIdLoc);
		alert(grpIdPatLoc);
		var URL = "/intelhosp/label/"+grpIdPatLoc+"/"+selectedPatIdLoc;
		alert(URL);
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				alert('Deleted Successfully');
				self.loadSelectedGroupsPatients();

			},
			requestUrl : URL,
			requestMethod : "DELETE",
			requestData : {},
			resultType : "json",
		});

		ServiceCalls.call();
	}	
}	