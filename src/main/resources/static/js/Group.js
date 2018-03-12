var groupVo;

function initGroupVo() {
	groupVo = {
		name : ko.observable(""),
		groupList : ko.observableArray([]),
		grpIdSMS : ko.observable(""),
		smsType : ko.observable(""),
		smsMsg : ko.observable(""),
		grpSelectedId : ko.observable(""),
		smsTypeList : ko.observableArray([ {
			"id" : "PROMO",
			"name" : "PROMOTIONAL"
		}, {
			"id" : "TXN",
			"name" : "TRANSACTIONAL"
		} ]),
		id : ""
	}
}

initGroupVo();

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

var Group = function() {

	var self = this;

	self.loadGroupList = function(selectionMode) {

		initGroupVo();

		$("#groupContainer").html(
				WsUtils.getGridFilterContainer("Group", "Add Group",
						"groupGrid", "addGroupBtn"));
		$(".tab-pane").removeClass("active");
		$("#groupContainer").addClass("active");

		resultGlobalObject = $
				.extend(
						resultGlobalClass,
						{
							callback : function() {
								$("#comboDiv").html(
										WsUtils.getGridFilterContainer());
								var dataArr = resultGlobalClass.response;

								$("#addGroupBtn").bind("click", function() {

									self.loadGroupPage();
								});

								var dataArray = dataArr;
								if (dataArray.length > 0) {
									initDataGridModel();

									for (var i = 0, len = dataArray.length; i < len; i++) {
										var idgrp = dataArray[i]["id"];
										var namegrp = dataArray[i]["name"];
										groupVo.groupList.push({
											name : namegrp,
											id : idgrp
										});

									}
									var dgm = $
											.extend(
													dataGridModel,
													{
														dataArray : dataArray,
														gridHeaders : {
															"name" : "Group Name"
														},
														hiddenColumns : [
																"id",
																"patientIdList",
																"patientDtoList" ],
														isDeleteButton : true,
														isViewButton : true,
														viewIconClass : "glyphicon glyphicon-envelope viewIcon",
														isSearchVisible : true,
														isCustomPagination : false,
														callbackFunction : function(
																data, event,
																type) {

															if (type == 'view') {
																self.loadSendSMSPage(data);
															} else if (type == 'delete') {
																WsUtils.deleteOperation(
																				function() {

																					var requestUrl = "/intelhosp/label/"+ data.id;
																					console.log(requestUrl)
																					resultGlobalObject = $
																							.extend(resultGlobalClass,{
																										callback : function() {
																											WsUtils.showAlert("Delete Success");
																											$(".alert").delay(3000).fadeOut("slow");
																											self.loadGroupList();
																										},
																										requestUrl : requestUrl,
																										requestMethod : "DELETE",
																										requestData : {
																											"data" : data,
																										},
																										resultType : "json",
																									});
																					ServiceCalls.call();

																				},"");

															} else if (type = "rowSelect") {
																self.loadGroupPage(data);

															}
														},
													});
									dataGridController.showDataGrid(dgm,"groupGrid", "groupgrid", false);
								} else {
									WsUtils.showAlert(Language.noData);
								}
							},
							requestUrl : "/intelhosp/label",
							requestMethod : "GET",
							isAsyncCall : false,
							requestData : {},
							resultType : "json",
						});

		ServiceCalls.call();
	};

	self.loadGroupPage = function(data) {
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				WsUtils.showPopupWindow(function() {

				});
				$("#modelData").html(responseObj);
				$(".modal-footer").html("");
				ko.cleanNode($("#groupForm")[0]);
				ko.applyBindings(groupVo, $("#groupForm")[0]);

				if (data && data.id) {
					groupVo.id = data.id;
				}
				if (data)
					groupVo.name(data.name);
				else
					groupVo.name("");
			},
			requestUrl : "../pages/templates/patient_group.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	};

	self.saveGroup = function(callingFrom) {
		if (WsUtils.validate("groupForm"))
			return;

		var methodType = "POST";
		if (groupVo.id) {
			methodType = "PUT";
		}

		var data = {
			name : groupVo.name(),
			id : groupVo.id
		};

		
		 if(methodType == "POST"){ data = {name : groupVo.name(),id :
		 groupVo.id,patientIdList: ["00010318","00010218"]}; }
		 

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				WsUtils.showAlert('Saved Successfully.');
				WsUtils.hidePopup();

				if ($("#groupContainer").hasClass("active")) {
					self.loadGroupList();

				}

			},
			requestUrl : "/intelhosp/label",
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

	self.loadSendSMSPage = function(data) {
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				WsUtils.showPopupWindow(function() {

				});
				$("#modelData").html(responseObj);
				$(".modal-footer").html("");
				ko.cleanNode($("#sendSMSForm")[0]);
				ko.applyBindings(groupVo, $("#sendSMSForm")[0]);
				if (data && data.id) {
					groupVo.grpIdSMS(data["id"]);
				}

			},
			requestUrl : "../pages/templates/send_sms.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	};

	self.sendsms = function(callingFrom) {
		if (WsUtils.validate("sendSMSForm"))
			return;

		var locGrpId = groupVo.grpIdSMS();
		var locSmsType = groupVo.smsType();
		var locSmsMsg = groupVo.smsMsg();

		var data = {
			id : locGrpId,
			smsContent : locSmsMsg,
			smsType : locSmsType
		};

		var requestUrl = "/intelhosp/label/grouppromo";

		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				WsUtils.showAlert("SMS Request Submitted Successfully");
				$(".alert").delay(3000).fadeOut("slow");
				WsUtils.hidePopup();
				self.loadGroupList();
			},
			requestUrl : requestUrl,
			requestMethod : "POST",
			requestData : {
				"data" : data,
			},
			resultType : "json",
		});
		ServiceCalls.call();

	};

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
							
							// doctorId = selectedItem.id;
						});

				self.loadGroupsData();
			},
			requestUrl : "../pages/templates/patient_group_add.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	};
	self.loadSelectedGroupsPatients = function(selectedGrpId) {
		
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
		alert("loadGroupsData");
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				alert("resp:"+responseObj);
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
		alert("delGroupPatients");
	}	

}