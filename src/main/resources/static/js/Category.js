var categoryVo;

function initCategoryVo() {
	categoryVo = {
		name : ko.observable(""),
		id : ""
	}
}

initCategoryVo();

var Category = function() {
	
	var self = this;

	self.loadCategoryList = function(selectionMode) {
		
		initCategoryVo();
		
		$("#categoryContainer").html(WsUtils.getGridFilterContainer("Category", "Add Category","categoryGrid"));
		$("#categoryContainer").addClass("active");
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				$("#comboDiv").html(WsUtils.getGridFilterContainer());
				var dataArr=resultGlobalClass.response;			
						
				$("#addNewBtn").bind("click",function(){
					self.loadCategoryPage();
				});
				
				var dataArray =  dataArr;
				if(dataArray.length > 0 ){
					initDataGridModel();
					var dgm = $.extend(dataGridModel,{
							dataArray : dataArray ,
							gridHeaders : {"name":"Category Name"},
							hiddenColumns : ["doctors","id"],
							isDeleteButton : true,
							isViewButton : false,
							isSearchVisible:true,
							isCustomPagination : false,
							callbackFunction : function(data,event,type){
								
								if(type == 'delete')
								{	
										WsUtils.deleteOperation(function(){
										
											var requestUrl = "/intelhosp/categories/"+data.id;
											console.log(requestUrl)
											
											resultGlobalObject = $.extend(resultGlobalClass, {
												callback : function(){
													WsUtils.showAlert("Delete Success");
													$(".alert").delay(3000).fadeOut("slow");
													self.loadCategoryList();	
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
									self.loadCategoryPage(data);
									
								}	
							},
					});
					dataGridController.showDataGrid(dgm, "categoryGrid", "categorygrid",false);
				}
				else
				{
				   WsUtils.showAlert(Language.noData);
				}	
			},
			requestUrl : "/intelhosp/categories/categorydto",
			requestMethod: "GET",
			isAsyncCall:false,
			requestData : {
			},resultType : "json",
		});
		
		ServiceCalls.call();
	};
	self.loadCategoryPage = function(data){
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function() {
				var responseObj = resultGlobalClass.response;
				WsUtils.showPopupWindow(function() {

				});
				$("#modelData").html(responseObj);
				$(".modal-footer").html("");
				ko.cleanNode($("#categoryForm")[0]);
				ko.applyBindings(categoryVo, $("#categoryForm")[0]);
				
				if(data && data.id){
					categoryVo.id = data.id;					
				}
				if(data)
					categoryVo.name(data.name);
				else
					categoryVo.name("");
			},
			requestUrl : "../pages/templates/category.html",
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	};
	self.saveCategory = function(callingFrom){
		
		if(WsUtils.validate("categoryForm"))
			return;
		
		var methodType = "POST";
		if(categoryVo.id){
			methodType = "PUT";
		}
		
		var data = {name : categoryVo.name(),id : categoryVo.id};
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				WsUtils.showAlert('Saved Successfully.');
				WsUtils.hidePopup();
				
				if($("#categoryContainer").hasClass("active")){ //CAlling from doctor page
					self.loadCategoryList();
				
				}else{
					new Doctor().loadCategories();
					initCategoryVo();
				}
				
			},
			requestUrl : "/intelhosp/categories",
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