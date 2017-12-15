var medicationVo;

function initMedicationVo() {
	medicationVo = {
		name : ko.observable("sds"),
		id : "",
		types : ko.observableArray([{"id":"Tablet","name":"Tablet"},{"id":"Syrup","name":"Syrup"},{"id":"Capsule","name":"Capsule"}]),
		type : ko.observable("Syrup"),
	}
}

initMedicationVo();

var Medications = function() {
	
	var self = this;

	self.loadMedicationsList = function(selectionMode) {
		
		initMedicationVo();
		
		$("#content").html(WsUtils.getGridFilterContainer("Medications", "Add Medication"));
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				$("#comboDiv").html(WsUtils.getGridFilterContainer());
				var dataArr=resultGlobalClass.response;			
						
				$("#addNewBtn").bind("click",function(){
					self.loadMedicationPage();
				});
				
				var dataArray =  dataArr;
				if(dataArray.length > 0 ){
					initDataGridModel();
					var dgm = $.extend(dataGridModel,{
							dataArray : dataArray ,
							gridHeaders : {"name":"Medication Name","type":"Type"},
							hiddenColumns : ["id"],
							isDeleteButton : true,
							isViewButton : false,
							isSearchVisible:true,
							isCustomPagination : false,
							callbackFunction : function(data,event,type){
								
								if(type == 'delete')
								{	
										WsUtils.deleteOperation(function(){
										
											var requestUrl = "/intelhosp/medications/"+data.id;
											console.log(requestUrl)
											
											resultGlobalObject = $.extend(resultGlobalClass, {
												callback : function(){
													WsUtils.showAlert("Delete Success");
													$(".alert").delay(3000).fadeOut("slow");
													self.loadMedicationsList();	
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
									self.loadMedicationPage(data);
									
								}	
							},
					});
					dataGridController.showDataGrid(dgm, "gridContainer", "medicationgrid",false);
				}
				else
				{
				   WsUtils.showAlert(Language.noData);
				}	
			},
			requestUrl : "/intelhosp/medications",
			requestMethod: "GET",
			requestData : {
			},resultType : "json",
		});
		
		ServiceCalls.call();
	};
	self.loadMedicationPage = function(data){
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				WsUtils.showPopupWindow(null,400);
				$("#modelData").html(responseObj);
				$(".modal-footer").html("");
				ko.cleanNode($("#medicationForm")[0]);
				ko.applyBindings(medicationVo, $("#medicationForm")[0]);
				
				if(data && data.id){
					medicationVo.id = data.id;
				}
				if(data){
					medicationVo.name(data.name);
					medicationVo.type(data.type);
				}
				
		
				
			},
			requestUrl : "../pages/templates/add_medication.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	};
	self.saveMedication = function(){
		
		if(WsUtils.validate("medicationForm"))
			return;
		
		var methodType = "POST";
		if(medicationVo.id){
			methodType = "PUT";
		}
		
		var data = {name : medicationVo.name(),id : medicationVo.id, type : medicationVo.type()};
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				WsUtils.showAlert('Saved Successfully.');
				WsUtils.hidePopup();
				
				if($("#addMedication")){
					self.loadMedications();
				}else{
					self.loadMedicationsList();
				}
				
			},
			requestUrl : "/intelhosp/medications",
			requestMethod: methodType,
			requestData : {
				"data" : data ,
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
	
}