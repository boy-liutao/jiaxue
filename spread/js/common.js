function printUtil(panel, requestdata, ajaxurl, printfunction){
	$.ajax({
		type: 'POST',
		url: ajaxurl,
		data: requestdata,
		cache:false,
		dataType:"json",
		async: false,
        success: function(response) {
        	if (response.code > 0){
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
	        		failFunction(response.reason, response.code);
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
function loadSelection(panel, requestdata, ajaxurl, itemName){
	ajaxUtil(requestdata, ajaxurl, function(response){
		var list = response.list;
		for (var i = 0;i<list.length;i++){
			$(panel).append("<option value='"+list[i][itemName]+"'>"+list[i][itemName]+"</option>");
		}
	}, null);
}
function loadSelectionNV(panel, requestdata, ajaxurl, itemName, itemValue){
	ajaxUtil(requestdata, ajaxurl, function(response){
		var list = response.list;
		for (var i = 0;i<list.length;i++){
			$(panel).append("<option value='"+list[i][itemValue]+"'>"+list[i][itemName]+"</option>");
		}
	}, null);
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

	/**
	* 将本地图片 显示到浏览器上
	*/
function prevImg(sourceId, targetId) {
	var url = getFileUrl(sourceId);
	var imgPre = document.getElementById(targetId);
	imgPre.src = url;
}
function refresh(){
	window.location.href = window.location.href;
}
function ajaxSubmit(formId) {
    var hideForm = $(formId);
    var options = {
        dataType : "json",
        beforeSubmit : function() {
        },
        success : function(result) {
        	if (result.code){
        		showMsg("提交成功");
        	} else {
        		alert("提交失败！");
        	}
        },
        error : function(result) {
        	alert("提交失败！");
        }
    };
    hideForm.ajaxSubmit(options);
}
function ajaxSubmitRefresh(formId) {
    var hideForm = $(formId);
    var options = {
        dataType : "json",
        beforeSubmit : function() {
        },
        success : function(result) {
        	if (result.code){
        		showMsg("提交成功");
        	} else {
        		alert("提交失败！");
        	}
        },
        error : function(result) {
        	alert("提交失败！");
        }
    };
    hideForm.ajaxSubmit(options);
}
function ajaxSubmitWithFunc(formId, succFunc) {
    var hideForm = $(formId);
    var options = {
        dataType : "json",
        beforeSubmit : function() {
        },
        success : function(result) {
        	if (result.code){
        		succFunc(result.response);
        	} else {
        		alert("提交失败！");
        	}
        },
        error : function(result) {
        	alert("提交失败！");
        }
    };
    hideForm.ajaxSubmit(options);
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

String.prototype.startWith=function(str){    
  var reg=new RegExp("^"+str);    
  return reg.test(this);       
} 

String.prototype.endWith=function(str){    
  var reg=new RegExp(str+"$");    
  return reg.test(this);       
}

function parseDate(dt){
	return new Date(dt).format("yyyy-MM-dd");
}
function parseDateTime(dt){
	return new Date(dt).format("yyyy-MM-dd hh:mm:ss");
}
// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  



/**
 * 
 * @param panel 用于显示列表的容器
 * @param container 用于显示分页按钮的容器
 * @param count 共有多少分页
 * @param pageNo 当前第几页
 * @param pageSize 每页显示多少条
 * @param funcName 点击分页后的函数
 */

function setPage(panel, container, count, url, data, pageNo, pageSize, funcName) {
	var container = container;
	var pageindex = pageNo;
	var pageLink = "javascript:"+funcName+"('"+panel+"','"+url+"','"+data+"', #pageNo, "+pageSize+")";
	var a = [];
	  //总页数少于10 全部显示,大于10 显示前3 后3 中间3 其余....
	  if (pageindex == 1) {
	    a[a.length] = "<a href=\"#\" class=\"prev unclick\">前一页</a>";
	  } else {
	    a[a.length] = "<a href=\""+pageLink.replace("#pageNo", pageNo-1)+"\" class=\"prev\">前一页</a>";
	  }
	  function setPageList() {
	    if (pageindex == i) {
	      a[a.length] = "<a href=\""+pageLink.replace("#pageNo", i)+"\" class=\"current\">" + i + "</a>";
	    } else {
	      a[a.length] = "<a href=\""+pageLink.replace("#pageNo", i)+"\">" + i + "</a>";
	    }
	  }
	  //总页数小于10
	  if (count <= 10) {
	    for (var i = 1; i <= count; i++) {
	      setPageList();
	    }
	  }
	  //总页数大于10页
	  else {
	    if (pageindex <= 4) {
	      for (var i = 1; i <= 5; i++) {
	        setPageList();
	      }
	      a[a.length] = "...<a href=\""+pageLink.replace("#pageNo", count)+"\">" + count + "</a>";
	    } else if (pageindex >= count - 3) {
	      a[a.length] = "<a href=\""+pageLink.replace("#pageNo", 1)+"\">1</a>...";
	      for (var i = count - 4; i <= count; i++) {
	        setPageList();
	      }
	    }
	    else { //当前页在中间部分
	      a[a.length] = "<a href=\""+pageLink.replace("#pageNo", 1)+"\">1</a>...";
	      for (var i = pageindex - 2; i <= pageindex + 2; i++) {
	        setPageList();
	      }
	      a[a.length] = "...<a href=\""+pageLink.replace("#pageNo", count)+"\">" + count + "</a>";
	    }
	  }
	  if (pageindex == count) {
	    a[a.length] = "<a href=\"#\" class=\"next unclick\">后一页</a>";
	  } else {
	    a[a.length] = "<a href=\""+pageLink.replace("#pageNo", parseInt(pageNo)+1)+"\" class=\"next\">后一页</a>";
	  }
	  container.innerHTML = a.join("");
	  //事件点击
	}