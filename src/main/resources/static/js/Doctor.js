var doctorVo;
function initDoctorVo() {
	if(doctorVo){
		var categoryList = doctorVo.categoryList();
	}
	doctorVo = {
		id : ko.observable(""),
		name : ko.observable(""),
		title : ko.observable("5"),
		titleList:ko.observableArray([{"id":5,"name":"Dr."},{"id":6,"name":"Prof."}]),
		email : ko.observable(""),
		colorCode : ko.observable(""),
		qualification : ko.observable(""),
		fees : ko.observable(""),
		mobile : ko.observable(""),
		category : ko.observable(""),
		categoryId: ko.observable(""),
		categoryList:ko.observableArray([]),
		title : ko.observable(""),
		
		
	};
	
	doctorVo.categoryList(categoryList);
};
initDoctorVo();
var Doctor = function() {

	var self = this;
	self.loadDoctorPage = function(data) {
		
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$(".content").html(responseObj);	
				initDoctorVo();
				if(data){
					doctorVo.id(data["id"]);
					doctorVo.name(data["name"]);
					//doctorVo.title(data["title"]);
					doctorVo.title(data["titleId"]),
					doctorVo.email(data["email"]);
					doctorVo.qualification(data["qualification"]);
					doctorVo.colorCode(data["colorCode"]);
					doctorVo.fees(data["fees"]);
					doctorVo.mobile(data["mobile"]);
					doctorVo.category(data["categoryId"]);
				}
				ko.cleanNode($("#doctorForm")[0]);
				ko.applyBindings(doctorVo, $("#doctorForm")[0]);
			},
			requestUrl : "../pages/templates/doctor_add.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
		
	};
	self.loadDoctorPageList = function(selectionMode) {
		
		self.loadCategories();
		
		$("#content").html(WsUtils.getGridFilterContainer('Doctors List', 'Add New Doctor'));
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				$("#comboDiv").html(WsUtils.getGridFilterContainer());
				var dataArr=resultGlobalClass.response;			
						
				$("#addNewBtn").bind("click",function(){
					self.loadDoctorPage();
				});
				
				var dataArray = dataArr ; 
				
				if(dataArray.length > 0 ){
					initDataGridModel();
					var dgm = $.extend(dataGridModel,{
							dataArray : dataArray ,
							gridHeaders : {"title":"Title","name":"Name","qualification":"Qualification","email" : "Email","mobile":"Mobile"},
							hiddenColumns : ["id","categoryId","categories","appointments","fees","colorCode","titleId"],
							isDeleteButton : true,
							isCustomPagination : false,
							isSearchVisible:true,
							callbackFunction : function(data,event,type){
								if(type == 'delete')
								{	
									
									WsUtils.deleteOperation(function(){
										
										var requestUrl = "/intelhosp/doctors/"+data.id;
										console.log(requestUrl)
										
										resultGlobalObject = $.extend(resultGlobalClass, {
											callback : function(){
												WsUtils.showAlert("Delete Success");
												$(".alert").delay(3000).fadeOut("slow");
												self.loadDoctorPageList();	
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
									self.loadDoctorPage(data);
									
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
			requestUrl : "/intelhosp/doctors",
			requestMethod: "GET",
			requestData : {
				"data" : {},
				"queryName" : "",
				"queryParamArray" : {}
			},resultType : "json",
		});
		
		ServiceCalls.call();
	
	};
	self.saveDoctor = function(){
		var hasError = WsUtils.validate("doctorForm");
		console.log(hasError);
		if(hasError)
			return;
		
		var objToSave = {};
		if(doctorVo.id()){
			objToSave.id = doctorVo.id();
		}
		objToSave.name  = doctorVo.name();
		objToSave.title = doctorVo.title();
		objToSave.email = doctorVo.email();
		objToSave.qualification = doctorVo.qualification();
		objToSave.fees = doctorVo.fees()
		objToSave.mobile = doctorVo.mobile()
		objToSave.category = doctorVo.category();
		objToSave.categoryId = doctorVo.category();
		objToSave.colorCode = doctorVo.colorCode();
		
		var methodType = "POST";
		if(doctorVo.id()){
			methodType = "PUT";
		}
		
		console.log(objToSave);
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				WsUtils.showAlert('Saved Successfully.')
				new Doctor().loadDoctorPageList();
			},
			requestUrl : "/intelhosp/doctors",
			requestMethod: methodType,
			requestData : {
				"data" : objToSave ,
				"queryName" : "",
				"queryParamArray" : {}
			},resultType : "json",
		});
		ServiceCalls.call();
	};
	
	self.loadCategories = function(){
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var dataArr = resultGlobalClass.response;			
				console.log(dataArr);
				doctorVo.categoryList(dataArr);
			},
			requestUrl : "/intelhosp/categories/categorydto",
			requestMethod: "GET",
			isAsyncCall:false,
			requestData : {
			},resultType : "json",
		});
		
		ServiceCalls.call();
	};
	
}

