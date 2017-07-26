/*{"id":1,"title":"Mr","name":"Guru","mobile":"(+91)1234567890","address1":null,"address2":null,"city":null,"pincode":null,"profileId":null,"label":null,"landline":null,"bloodGroup":"BPositive","gender":null,"email":null,"dob":"1989-01-15","age":28,"medicalHistory":null,"medicalAlert":null,"allergies":null,"needWelcomeMessage":false,"birthdayWish":false,"remainder":null,"appointments":[{"id":3,"time":"2017-07-28T22:45:36","doctor":2,"patient":1},{"id":1,"time":"2017-07-26T22:45:36","doctor":2,"patient":1},{"id":2,"time":"2017-07-27T22:45:36","doctor":2,"patient":1}],"occupation":null}*/

var Patient = function() {

	var self = this;
	
	self.loadPatientPage = function(selectionMode) {
		
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$(".content").html(responseObj);				
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
							gridHeaders : {"title":"Title","name":"Name","qualification":"Qualification","email" : "Email"},
							hiddenColumns : ["id","categoryid","categories","appointment"],
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
}