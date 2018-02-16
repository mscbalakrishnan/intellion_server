var WsUtils = {
	
		isSuccess : function(jsonObj)
		{
			flag = false;
			
			if(jsonObj["serverResult"] != undefined && jsonObj["serverResult"]["mode"] == 'success')
				flag = true;
			
			return flag;
				
		},
		createButtons : function(buttonsArr)
		{
			$(".modal-footer").html("<div id='buttons'></div>")
			var button = "";
			var NumberOfButtons = buttonsArr.length;
			for (var i = 0; i < NumberOfButtons; i++)
			{
				name = buttonsArr[i]["name"];
				fun = buttonsArr[i]["fun"];
				if(name == "Yes" || name == "Ok" || name == "Approve" || name == "Allocate" || name == "Delete")
				{
					button += '<button type="button" class="btn btn-primary" onclick="'+fun+'" style="margin-right: 3%;">'+name+'</button>';
				}
				else if(name == "No" || name == "Cancel")
				{
					button += '<button type="button" class="btn btn-cancel" onclick="'+fun+'" style="margin-right: 3%;">'+name+'</button>';
				}
				else if(name == "Submit")
				{
					button += '<button type="button" class="btn btn-success" onclick="'+fun+'" style="margin-right: 3%;">'+name+'</button>';
				}
				else
				{
					button += '<button type="button" name="elseButton" class="btn btn-cancel" onclick="'+fun+'" style="margin-right: 3%;">'+name+'</button>';
				}
			}
			
			$("#myModalLabel").html("Confirmation");
			$("#buttons").html(button);
		},
		
		getServerResponseValue : function(jsonObj)
		{
			var serverResult = jsonObj["serverResult"]["response"];
			if(serverResult != undefined)
				return serverResult;
			else
				return "";
				
		},
		getGridFilterContainer : function(title,buttonName,gridContainerName, btnName)
		{
           if(!buttonName) buttonName = "Add New";
           if(!title) title = "";
           
           if(!gridContainerName) gridContainerName = 'gridContainer';
           
           if(!btnName) btnName = 'addNewBtn';
           
		   var gridContainerHtml = '<div class="row box-header" style="padding:4px;margin:0px;background:#3c8dbc"><div class="col-sm-6">' + 
										' <div class="dataTables_length" id="filter">' +
										'<h3 class="box-title" style="color:#FFF">'+title+'</h3>'+
										' </div>' +
									'</div>' + 
									'<div class="col-sm-6" id="buttonContainer" style="text-align:right"><button type="submit" class="btn btn-success" id="'+btnName+'"'+
									//' data-toggle="popover" data-content="Please search and select Medication." data-placement="left" title="Add/Update"' +
									'>'+buttonName+'</button></div> ' + 
									'</div>'+
									'<div class="table-responsive row" id="'+gridContainerName+'" style="padding:0px 10px 0px 10px"></div>';
			
			return gridContainerHtml;
		},
		getRPMappingGridFilterContainer : function()
		{
			var gridContainerHtml = '<div class="panel-body">' + 
										'<div class="dataTables_length1" id="filter"></div>' + 
										' <div class="buttonContainer col-sm-10" ><button type="submit" class="btn btn-success" id="searchBtn" >Search</button><button type="submit" class="btn btn-cancel" id="resetBtn" >Reset</button></div><div class="rightbuttonContainer col-sm-12" > '+
										'<button type="submit" class="btn btn-default" id="addNewBtn" >Add New</button> '+
										' <button type="button" class="btn btn-default" id="btnImport" onclick="WsUtils.showFileUploadRetailerScreen()" >Import Xls</button>'+
										' <button type="button" class="btn btn-default" id="btnDownload" onclick="new cardMaster().downloadProductIssueTemp()" style="margin-right: 4px;">Download Template</button>'+
										'<button type="button" class="btn btn-default" id="btnErrors" onclick="new master().loadProductSalesErrors();"> Show Errors</button> '+
										'</div>' +
										' <div class="col-sm-12 fileUploadContainer" id="retailerFileUpload"> File upload</div>' + 
									'</div>'+
									
									'<form action="fileUpload.aspx" method="post" enctype="multipart/form-data" id="cardSellingFileUpload" ' + 
									 ' style="display:none;border: 1px solid #EEEEEE;padding: 20px;margin: 10px;background-color: #CCCCCC;">'+
										'<input type="hidden" name="mode" id="mode" value="card_selling"/>' + 
										'<div class="form-group" >'+
										'<label for="inputCardNo" class="col-sm-4 control-label">Choose xls file to import Cards</label>'+
										'<div class="col-sm-4">'+
							 		 		'<input type="file" name="fileToUpload" label="User Image" />'+
							 		 	
										'</div>'+
										'<div class="col-sm-2">'+
											'<input type="submit" value="Upload" align="center" /> ' +
										'</div></div>'+
									'</form>' + 	
									
									'<div id="msgContainer"></div>'+
									'<div class="table-responsive row" id="gridContainer"></div>';
			
			return gridContainerHtml;
		},
		//Product Return
		getRPReturnFilterContainer : function()
		{
			var gridContainerHtml = '<div class="panel-body">' + 
										'<div class="dataTables_length" id="filter"></div>' + 
										' <div class="buttonContainer col-sm-10" ><button type="submit" class="btn btn-primary" id="searchBtn" >Search</button><button type="submit" class="btn btn-cancel" id="resetBtn" >Reset</button></div><div class="rightbuttonContainer col-sm-12" > '+
										' <button type="button" class="btn btn-default"  id="btnImport" style="display:block;float:right;margin:10px" onclick="WsUtils.showFileUploadProductReturnScreen()">Return Sold Card By Import</button>'+
										' <button type="button" class="btn btn-default" id="btnDownload" style="display:block;float:right;margin:10px;margin-right:0%" onclick="new cardMaster().downloadSoldCardsByExcel()" style="margin-right: 4px;">Download Sold Card</button>'+
										'<button type="button" class="btn btn-default" id="btnErrors" onclick="new master().loadProductSalesErrors();" style="margin-top: 1.1%;"> Show Errors</button> '+
										'</div>' +
										' <div class="col-sm-12 fileUploadContainer" id="retailerFileUpload"> File upload</div>' + 
									'</div>'+
									
									'<form action="fileUpload.aspx" method="post" enctype="multipart/form-data" id="cardReturnFileUpload" ' + 
									 ' style="display:none;border: 1px solid #EEEEEE;padding: 20px;margin: 10px;background-color: #CCCCCC;">'+
										'<input type="hidden" name="mode" id="mode" value="card_return"/>' + 
										'<div class="form-group" >'+
										'<label for="inputCardNo" class="col-sm-4 control-label">Choose xls file to Return Cards</label>'+
										'<div class="col-sm-4">'+
							 		 		'<input type="file" name="fileToUpload" label="User Image" />'+
							 		 	
										'</div>'+
										'<div class="col-sm-2">'+
											'<input type="submit" value="Upload" align="center" /> ' +
										'</div></div>'+
									'</form>' + 	
									
									'<div id="msgContainer"></div>'+
									'<div class="table-responsive row" id="gridContainer"></div>';
			
			return gridContainerHtml;
		},
		 
		showErrorMessage : function(jsonObj,containerToDisplay)
		{
			//alert("Error >>>" + jsonObj["serverResult"]["error"]);
			if($(".alert").html() != undefined)
			{
				$(".alert").remove();
			}	
			if(containerToDisplay == undefined)
				$("#mainContainer").prepend(WsUtils.str_alert_msg(jsonObj["serverResult"]["error"], "E"));
			else
				$("#"+containerToDisplay).prepend(WsUtils.str_alert_msg(jsonObj["serverResult"]["error"], "E"));
			
			WsUtils.gotoPageTop();
				
				
		},
		
		showWarningMessage : function(jsonObj)
		{
			if($(".alert").html() != undefined)
			{
				$(".alert").remove();
			}	
			$("#mainContainer").prepend(WsUtils.str_alert_msg(jsonObj["serverResult"]["error"], "W"));
			WsUtils.gotoPageTop();
				
		},
		showWarningMessage : function(msg)
		{
			if($(".alert").html() != undefined)
			{
				$(".alert").remove();
			}	
			$("#mainContainer").prepend(WsUtils.str_alert_msg(msg, "W"));
			WsUtils.gotoPageTop();
				
		},
		confirmSubmit : function(msg,okCallbackFunction,cancelCallbackFunction) {
			if(confirm(msg)){		
				if($.isFunction(okCallbackFunction))
				{
					okCallbackFunction.apply();
				}	
				else
				{
					eval(okCallbackFunction + "();");
				}
			}else{		
				if($.isFunction(cancelCallbackFunction))
				{
					cancelCallbackFunction.apply();
				}	
				else
				{
					eval(cancelCallbackFunction + "();");
				}
			}
		},

		deleteOperation : function(okCallbackFunction,cancelCallbackFunction) 
		{ 
			WsUtils.hidePopup();
			var button = "";
			button += '<button type="button" class="btn btn-primary" value="1" id="confirmBtn" style="margin-right: 3%;">Delete</button>';
			button += '<button type="button" class="btn btn-cancel" value="0"   id="cancelBtn"  style="margin-right: 3%;">Cancel</button>';
			
			WsUtils.showPopupWindowForDelete();
			$("#modelData").html(confirmDelete);
			$("#myModalLabel").html("Confirmation");
			$(".modal-footer").html(button);
			$("#confirmBtn").on("click",function()
			{
				var confirmation = $(this).val();
				if(confirmation == 1)
				{		
					if($.isFunction(okCallbackFunction))
					{
						okCallbackFunction.apply();
					}	
					else
					{
						if(okCallbackFunction){
							eval(okCallbackFunction + "();");
						}
						
					}
				}
					/*$("#modaldiv").remove();
					$(".modal-backdrop").remove();
					$(".modal-footer").remove();*/
					WsUtils.hidePopup();
			});
			$("#cancelBtn").on("click",function()
			{
				/*$("#modaldiv").remove();
				$(".modal-backdrop").remove();
				$(".modal-footer").remove();*/
				
				WsUtils.hidePopup();
				
				if($.isFunction(cancelCallbackFunction))
				{
					cancelCallbackFunction.apply();
				}	
				else
				{
					eval(cancelCallbackFunction + "();");
				}
			});
		},
		
		showAlert : function(msg)
		{
			
			if($(".alert").html() != undefined)
			{
				$(".alert").remove();
			}
			$("#pageErrorMessage").html(WsUtils.str_alert_msg(msg, "I"));
			WsUtils.gotoPageTop();
			$(".alert").delay(3000).fadeOut("slow");
		},
		showPopupAlert : function(msg)
		{
			
			$("#popupErrorMessage").html("");
			$("#popupErrorMessage").html(WsUtils.str_alert_msg(msg, "I"));
			//WsUtils.gotoPageTop();
			$("#popupErrorMessage").delay(3000).fadeOut("slow");
		},
		clearPopupAlert : function()
		{
			
			$("#popupErrorMessage").html("");
		},
		showSuccess : function(msg)
		{
			if($(".alert").html() != undefined)
			{
				$(".alert").remove();
			}
			$("#pageErrorMessage").html(WsUtils.str_alert_msg(msg, "S"));
			WsUtils.gotoPageTop();
		},
		
		gotoPageTop : function()
		{
			$("html, body").animate({ scrollTop: 0 }, "slow");
		},
		

		getDistrictOnStatechange : function(event)
		{
			var stateId ="";
			stateId = $(event.target).val();
			if(stateId =="")
			{
				return;
			}
			resultGlobalObject = $.extend(resultGlobalClass, {
				callback : function(){
					
						console.log(resultGlobalObject);
						var arr = WsUtils.getServerResponseValue(resultGlobalObject["response"]);
						usersViewModel.districtList(arr);
				},
				requestUrl : "records.wsdl",			
				requestData : {
					"data" : {} ,
					"queryName" : "districtOnState",
					"queryParamArray" : [stateId]
				},resultType : "json",
			});
			
			ServiceCalls.call();
			
		},

		/**To add combobox elements
		 */
		addComboboxElement : function(options,id_to_bind,columnid,columnname,selectedValue)
		{
			try 
			{ 
				
				$("#"+id_to_bind+" option").remove();
				if(id_to_bind=="zonealloc")
				{
				$("#"+id_to_bind).append('<option value="">PinCode</option>');
				}
				else if(id_to_bind=="centreNameFilter")
				{
				$("#"+id_to_bind).append('<option value="">Centre Name</option>');
				}
				else if(id_to_bind=="BrandFilter")
				{
				$("#"+id_to_bind).append('<option value="">Brand</option>');
				}
				else if(id_to_bind=="LocationFilter")
					{
					$("#"+id_to_bind).append('<option value="">Location</option>');
					}
				else if(id_to_bind=="roleFilter")
					{
					$("#"+id_to_bind).append('<option value="">Role</option>');
					}
				else{
				$("#"+id_to_bind).append('<option value="">Select</option>');}
				//alert(options);
				if(options != undefined){
					for (var i = 0; i < options.length; i++) {
						checked = "";
						if(selectedValue == options[i][columnid] )
							checked = "selected";
	
						$("#"+id_to_bind).append(
								'<option  '+ checked + ' value="' + options[i][columnid] + '">'
								+ options[i][columnname] + '</option>');
					}
				}
			} catch (e) {

				console.log(e.message);
			}
			
		},
		
		getValueFromObj : function(paramObj,fieldName)
		{
			var value = "";
			if(paramObj != undefined)
			{
				value = paramObj[fieldName];
				if(value == undefined || value == null)
					value = "";
			}	

			return value;
		},
		str_alert_msg :  function(msg, msgType)
		{
			var alertMsg	= "";
			var msgClass	= "alert-info";
			var msgrTitle	= "Information";
			switch(msgType)
			{
				case "E":
					msgClass	= "alert-danger";
					msgrTitle	= "Error";
					break;
				case "W":
					msgClass	= "alert-warning";
					msgrTitle	= "Alert";
					break;
				case "S":
					msgClass	= "alert-success";
					msgrTitle	= "Success";
					break;
				default:
					msgClass	= "alert-info";
					msgrTitle	= "Information";
					break;
			}
			alertMsg += '<div class="alert ' + msgClass + ' alert-dismissible" role="alert">';
			alertMsg += '  <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>';
			alertMsg += '  <strong>' + msgrTitle + '!</strong>&nbsp;&nbsp;&nbsp;&nbsp;' + msg;
			alertMsg += '</div>';
			return alertMsg;
		},

		showPopupWindow : function(callbackFunction,width)
		{
			$("body").append("<div id='modaldiv'></div>");
			var str = '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'+
			 ' <div class="modal-dialog">'+
			  '  <div class="modal-content" style="width:'+width+'px">'+
			   '   <div class="modal-header headerFooterColor" >'+
			   ' <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" style="font-size: 28px;">&times;</span><span class="sr-only">Close</span></button>' +
		        ' <h4 class="modal-title" id="myModalLabel" data-bind="text: obs_modal.title()"></h4> ' + 
			      '</div>'+
			      '<div id="popupErrorMessage"></div>'+
			      '<div class="modal-body" id="modelData">'+
			      //customerServiceStatusScreen.toString()+
			     ' </div>'+
			      '<div class="modal-footer headerFooterColor" >'+
			       // '<button type="button" class="btn btn-default" onclick=$("#myModal").remove();$(".modal-backdrop").remove(); data-dismiss="modal">Close</button>'+
			      '</div>'+
			    '</div>'+
			  '</div>'+
			'</div>';
			$("#modaldiv").html("");
			$("#modaldiv").append(str);
			
			//ko.cleanNode($("#modaldiv")[0]);
			//ko.applyBindings(obs_modal,	$("#modaldiv")[0]);	
		
			$("#myModal").modal('show');
			
			$('#myModal').unbind("hidden.bs.modal").bind('hidden.bs.modal',function()
			{
				if($.isFunction(callbackFunction))
				{
					callbackFunction.apply();
				}	
				else
				{
					if(callbackFunction){
						eval(callbackFunction + "();");
					}
					
				}	
			});
					
		},
		showPopupWindowForDelete : function(callbackFunction)
		{
			$("body").append("<div id='modaldiv'></div>");
			var str = '<div class="modal modal-info fade in" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'+
			 ' <div class="modal-dialog">'+
			  '  <div class="modal-content">'+
			   '   <div class="modal-header" >'+
			   ' <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" style="font-size: 28px;">&times;</span><span class="sr-only">Close</span></button>' +
		        ' <h4 class="modal-title" id="myModalLabel" data-bind="text: obs_modal.title()"></h4> ' + 
			      '</div>'+
			      '<div class="modal-body" id="modelData">'+

			     ' </div>'+
			      '<div class="modal-footer">'+
			      '</div>'+
			    '</div>'+
			  '</div>'+
			'</div>';
			
		
			
			$("#modaldiv").append(str);
			
			//ko.cleanNode($("#modaldiv")[0]);
			//ko.applyBindings(obs_modal,	$("#modaldiv")[0]);	
		
			$("#myModal").modal('show');
			
			$('#myModal').unbind("hidden.bs.modal").bind('hidden.bs.modal',function()
			{
				if($.isFunction(callbackFunction))
				{
					callbackFunction.apply();
				}	
				else
				{
					if(callbackFunction){
						eval(callbackFunction + "();");
					}
					
				}	
			});
					
		},
		hidePopup : function()
		{
			$("#myModal").modal('hide');
//			$("#modaldiv").remove();
//			$(".modal-backdrop").remove();
//			$(".modal-footer").remove();
		},
		getCookie : function(cname) {
			
			if(cname == 'userId')
			{
				return localStorage.getItem("userId");
			}	
		    var name = cname + "=";
		    var ca = document.cookie.split(';');
		    for(var i=0; i<ca.length; i++) {
		        var c = ca[i];
		        while (c.charAt(0)==' ') c = c.substring(1);
		        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
		    }
		    return "";
		},
		hideMobileMenu : function(mode)
		{
			if (navigator.userAgent.match(/(iPhone|iPod|iPad|Android|BlackBerry|IEMobile)/)) {
				if(mode != 'dashboard')
					$(".navbar-toggle").trigger("click");
			}
		},
		
		isDevice : function() {
			if (navigator.userAgent.match(/(iPhone|iPod|iPad|Android|BlackBerry|IEMobile)/) && constants.isAPKFile) {
				return true;
			}
			else
			{
				return false;
			}
		},
		getImeiNumber :  function() {
			var imeino = '';
			if (this.isDevice())
			{
				plugins.DeviceInfo.imeiNumber(function(imei){
					   console.log("imei "+ imei);   
					   imeino = imei;
					   return imeino;	
				});
			}
			else{
				return imeino;
			}
		},
		loadJs : function(src){
			 var jsLink = $("<script type='text/javascript' src='"+src+"'>");
	         $("head").prepend(jsLink); 
		},
		closePopup : function()
		{
			$(".close").trigger("click");
		},
		loadFaq : function()
		{
			$(".alert").remove();
			loader.showLoader();
			WsMenu.title(Language.faq);
			
			$("#mainContainer").load("../faq.html",function(responseTxt,statusTxt,xhr){
				 	loader.hideLoader();
			$(".faqQuestion").click(function(){
			$(this).next().next().slideToggle(500);
				 	  });
			});
			
		},
		isNumber : function(param)
		{
			return !isNaN(param);
		},
		showCustomPagination : function(divToShow,dataGridTableId)
		{
			
			var paginationStr = '<ul class="pagination" data=bind="visible:(totalPages == 0)?true:false">'+
				'<li class="paginate_button previous" dataObj="'+dataGridTableId+'" tabindex="0" id="'+dataGridTableId+'_previous">'+
					//Prev Button
					'<a href="#">Previous</a></li><li class="paginate_button active"'+' dataObj="'+dataGridTableId+'" tabindex="0">'+
					//Page No
					'<a href="#">'+'<div style="float: left;"><span data-bind="text:pageNoToDisplay"></span></div>  ' +
					//Total Page Num
					' <div class="totalPageDiv">/<span data-bind="text:totalPages"></span></div></a>'+
	  			'</li> '+
	  			//Next Button
	  			'<li class="paginate_button next" tabindex="0" dataObj="'+dataGridTableId+'" id="'+dataGridTableId+'_next"><a href="#">Next</a></li></ul>'

			$(divToShow).html(paginationStr);
			
			ko.cleanNode($(divToShow)[0]);
			ko.applyBindings(paginationObj,$(divToShow)[0]);
			
			$("#"+dataGridTableId+"_previous").bind("click",showPrevPage);
	      	
		    $("#"+dataGridTableId+"_next").bind("click",showNextPage);

		},
		configureAutoComplete : function(textbox,serverurl,callbackfunction){
			var options = {

					  url: function(phrase) {
						  return serverurl+"?name=" + phrase; //"../intelhosp/doctors/doctorname/find?name=";
					  },

					  getValue: function(element) {
					    return element.name;
					  },

					  ajaxSettings: {
					    dataType: "json",
					    method: "get",
					    data: {
					        dataType: "text"
					     }
					  },
					  
					  template: {
							type: "custom",
							method: function(value, item) {
								var html = '<p>'+item.name+'</p>';
								return html;
							}
						},

					  preparePostData: function(data) {
					    data.name = $("#"+textbox).val();
					    return "";
					  },
					  
					  adjustWidth:false,
					  list: {

						  onChooseEvent: function() {
								var item = $("#"+textbox).getSelectedItemData();
								//console.log(item.id);
								if($.isFunction(callbackfunction))
								{
									callbackfunction.apply(this,[item]);
								}else
								{
									alert('Callfunction problem');
								}
							}
						},

					  requestDelay: 400
					};

				 $("#"+textbox).easyAutocomplete(options);
		},
		validate:function(formName){
			//validation = "required"
			//errormsg = "error message we want to display"
			//validationtype = "email,number,text"
			
			$(".help-block").text("");
			$(".has-error").removeClass("has-error");
			var hasError = false;
			$("#"+formName).find("[validation='required']").each(function(ele){
				var validationType = "";
				if($(this).attr("type") && $(this).attr("type") != "text"){
					validationType = $(this).attr("type");
				}else{
					 validationType = "empty";
				}
					
				valArr = validationType.split(",");
				var element = $(this);
				var elementValue = element.val();
				$.each(valArr,function(i,val){
					if((validationType == 'empty' || validationType == 'password') && elementValue == ""){
						element.parent().addClass("has-error");
						var helpBlock = element.parent().find(".help-block");
						if(helpBlock){
							helpBlock.remove();
							element.parent().append('</i><span class="help-block"></span>');
							
						}
						
						if(element.attr("errormsg")){
							element.parent().find(".help-block").text(element.attr("errormsg"));
						}else{
							element.parent().find(".help-block").text("Invalid value.");
						}
						
						hasError = true;
					}if(validationType == 'dob' && elementValue == ""){
						element.parent().addClass("has-error");
						var helpBlock = element.parent().find(".help-block");
						if(helpBlock){
							helpBlock.remove();
							element.parent().append('</i><span class="help-block"></span>');
							
						}
						
						if(element.attr("errormsg")){
							element.parent().find(".help-block").text(element.attr("errormsg"));
						}else{
							element.parent().find(".help-block").text("Invalid value.");
						}
						
						hasError = true;
					}
					else if(validationType == 'email'){
						if(elementValue != ""){
					    var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
					    if(!pattern.test(elementValue)) {
					    	hasError = true;
					    	element.parent().addClass("has-error");
					    	if(helpBlock){
								helpBlock.remove();
								element.parent().append('</i><span class="help-block"></span>');
								
							}
					    	if(element.attr("errormsg")){
								element.parent().find(".help-block").text(element.attr("errormsg"));
							}else{
								element.parent().find(".help-block").text("Invalid Email Address.");
							}
					    }
						}
					}
					else if(validationType == 'number'){
					    if(elementValue == "" || isNaN(elementValue)) {
					    	hasError = true;
					    	element.parent().addClass("has-error");
					    	var helpBlock = element.parent().find(".help-block");
					    	console.log(helpBlock);
					    	if(helpBlock){
								helpBlock.remove();
								element.parent().append('</i><span class="help-block"></span>');
								
							}
					    	if(element.attr("errormsg")){
								element.parent().find(".help-block").text(element.attr("errormsg"));
							}else{
								element.parent().find(".help-block").text("Invalid Number.");
							}
					    }
						
					}
					else if(validationType == 'mobile'){
					    if(elementValue == "" || isNaN(elementValue) || elementValue.length != 10) {
					    	hasError = true;
					    	element.parent().addClass("has-error");
					    	var helpBlock = element.parent().find(".help-block");
							if(helpBlock){
								helpBlock.remove();
								element.parent().append('</i><span class="help-block"></span>');
								
							}
					    	if(element.attr("errormsg")){
								element.parent().find(".help-block").text(element.attr("errormsg"));
							}else{
								element.parent().find(".help-block").text("Invalid Phone Number.");
							}
					    }
						
					}
				
				});
				
			});
			
			return hasError;
			//;
		},
		addOnblurEvent:function(formName){
			$("#"+formName).find("[validation='required']").each(function(ele){
				console.log(ele)
				$(this).blur(function(){
					WsUtils.validate(formName);
				});
			});
		}		
		
};