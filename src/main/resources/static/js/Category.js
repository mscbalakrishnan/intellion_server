var Category = function() {
	
	var self = this;
	self.loadCategoryList111 = function(){
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				var responseObj = resultGlobalClass.response;
				$(".content").html(responseObj);
				
				//ko.cleanNode($("#category")[0]);
				//ko.applyBindings(patientVo, $("#category")[0]);

			},
			requestUrl : "../pages/templates/category.html",			
			requestData : {},
			resultType : "text",
		});
		ServiceCalls.loadHtmlPage();
	};
	
	self.loadCategoryList = function(selectionMode) {
		
		$("#content").html(WsUtils.getGridFilterContainer("Category", "Add Category"));
		
		resultGlobalObject = $.extend(resultGlobalClass, {
			callback : function(){
				$("#comboDiv").html(WsUtils.getGridFilterContainer());
				var dataArr=resultGlobalClass.response;			
						
				$("#addNewBtn").bind("click",function(){
					
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
									
									
								}	
							},
					});
					dataGridController.showDataGrid(dgm, "gridContainer", "categorygrid",false);
				}
				else
				{
				   WsUtils.showAlert(Language.noData);
				}	
			},
			requestUrl : "/intelhosp/categories/categorydto",
			requestMethod: "GET",
			requestData : {
			},resultType : "json",
		});
		
		ServiceCalls.call();
	};
}