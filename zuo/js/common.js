function prevImg(sourceId, targetId) {
	var url = getFileUrl(sourceId);
	var imgPre = document.getElementById(targetId);
	imgPre.src = url;
}
function getFileUrl(sourceId) {
	var url;
	if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
	url = document.getElementById(sourceId).value;
	} else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox
	url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
	} else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome
	url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
	}
	return url;
}
function ajaxSubmitWithFunc(formId, succFunc, failFunc) {
    var hideForm = $(formId);
    var options = {
        dataType : "json",
        beforeSubmit : function() {
        },
        success : function(result) {
        	if (result.code > 0){
        		succFunc(result.response);
        	} else {
        		failFunc(result.code, result.response.reason);
        	}
        },
        error : function(result) {
        	if (failFunc)
        		failFunc(result.code, result.response.reason);
        }
    };
    hideForm.ajaxSubmit(options);
}

function printUtil(panel, requestdata, ajaxurl, printfunction){
	$.ajax({
		type: 'POST',
		url: ajaxurl,
		data: requestdata,
		cache:false,
		dataType:"json",
		async: false,
        success: function(response) {
        	if (response.code){
        		if (panel != null && panel.length > 0){
        			$(panel).html("");
        			if (printfunction != null)
        				$(panel).html(printfunction(response.response));
        		}
        		return true;
	        } else {
	        	//alert(response.reason);
	        }
        },
        error: function(x, e) {
        	alert("error", x);
        },
        complete: function(x) {
        	//alert("call complete");
        }
	});
	return false;
}

function ajaxUtil(requestdata, ajaxurl, succFunction, failFunction){
	$.ajax({
        url: ajaxurl,
        type: "POST",
        dataType: "json",
        cache:false,
        data: requestdata,
        async: false,
        success: function(response) {
        	if (response.code > 0){
        		if (succFunction != undefined && succFunction != null)
        			succFunction(response.response);
	        } else {
	        	if (failFunction != undefined && failFunction != null)
	        		failFunction(response);
	        }
        },
        error: function(x, e) {
        	//alert("error", x);
        },
        complete: function(x) {
        }
	});
	return false;
}
function reviseDisplay(content){
	if (content==null || content == undefined){
		return "";
	}
	content = content.replace(new RegExp("\r\n","gm"), "<br/>");
	content = content.replace(new RegExp("\n","gm"), "<br/>");
	content = content.replace(new RegExp("\r","gm"), "<br/>");
	return content;
}
function maxSize(content, max){
	content = reviseDisplay(content);
	if (content.length < max)
		return content;
	return content.substring(0,max) + "...";
}
function urlparameter(paras){
	var url = location.href;
	var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");
	var paraObj = {}
	for (i=0; j=paraString[i]; i++){
		paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);
	}
	var returnValue = paraObj[paras.toLowerCase()];
	if(typeof(returnValue)=="undefined"){
		return "";
	}else{
		return returnValue;
	}
}
String.prototype.endWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
	  return false;
	if(this.substring(this.length-str.length)==str)
	  return true;
	else
	  return false;
	return true;
}

String.prototype.startWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
	  return false;
	if(this.substr(0,str.length)==str)
	  return true;
	else
	  return false;
	return true;
}