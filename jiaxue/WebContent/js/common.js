var mainpath = "/jiaxue";
var host = "jiaxue";
var jiaxuepath = "http://localhost:8080/jiaxue/";

var sexEnum = {1:"男",2:"女"}
var statusEnum = {0:"未支付",1:"已支付",2:"退费中",3:"已退费",4:"已结束",5:"已取消"};
var payWayEnum = {0:"未知",1:"微信",2:"支付宝",3:"银联",4:"现金"};
var nurseryUnit = {1:"学期交",2:"月交",3:"日交"};

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
function loadSelectionNV(panel, requestdata, ajaxurl, itemName, itemValue, afterFunc){
	ajaxUtil(requestdata, ajaxurl, function(response){
		var list = response.list;
		for (var i = 0;i<list.length;i++){
			$(panel).append("<option value='"+list[i][itemValue]+"'>"+list[i][itemName]+"</option>");
		}
		if (afterFunc != undefined){
			afterFunc();
		}
	}, null);
}
function loadGlobalSetting(panel,valuePanel, name,sid){
	ajaxUtil({name:name, id:sid}, mainpath+"/globalSetting/select.do", function(response){
		if (response == null){
			alert("请先设置学堂相关费用参数");
		} else {
			if (panel != null && panel != ""){
				$(panel).html(response.value);
			}
			if (valuePanel != null && valuePanel != ""){
				$(valuePanel).val(response.value);
			}
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
function preImg(sourceId, targetId) {
	var url = getFileUrl(sourceId);
	var imgPre = document.getElementById(targetId);
	imgPre.src = url;
}
	
function updateImg(resourceName, key) {
	var userImg = $("#userImg").val();
	if(userImg == "") {
		alert("请选择图片！");
		return;
	}
	var tmp = userImg.toLowerCase();
	var length = tmp.length - 4;
	if(tmp.lastIndexOf(".bmp") != length && tmp.lastIndexOf(".jpg") != length && tmp.lastIndexOf(".png") != length) {
		alert("只支持bmp、jpg和png格式的图片！");
		return;
	}
    var hideForm = $('#modifyUserInfoForm');
    var options = {
        dataType : "json",
        beforeSubmit : function() {
            //alert("正在上传");
        },
        success : function(result) {
        	if (result.result){
	            //update img
	            $("#userImgPic").attr("src", result.response);
	            savePicResource(resourceName, "#userImgPic", key);
        	} else {
        		alert("图片修改失败！");
        	}
        },
        error : function(result) {
        	alert("图片修改失败！");
        }
    };
    hideForm.ajaxSubmit(options);
}

function updateImgById(id, resourceName, key) {
	var userImg = $("#userImg"+id).val();
	if(userImg == "") {
		alert("请选择图片！");
		return;
	}
	var tmp = userImg.toLowerCase();
	var length = tmp.length - 4;
	if(tmp.lastIndexOf(".bmp") != length && tmp.lastIndexOf(".jpg") != length && tmp.lastIndexOf(".png") != length) {
		alert("只支持bmp、jpg和png格式的图片！");
		return;
	}
    var hideForm = $('#modifyUserInfoForm'+id);
    var options = {
        dataType : "json",
        beforeSubmit : function() {
            //alert("正在上传");
        },
        success : function(result) {
        	if (result.code){
	            //update img
	            $("#userImgPic").attr("src", result.response);
	            savePicResource(resourceName, "#userImgPic"+id, key);
        	} else {
        		alert("图片修改失败！");
        	}
        },
        error : function(result) {
        	alert("图片修改失败！");
        }
    };
    hideForm.ajaxSubmit(options);
}

function savePicResource(resourceName, panel, key){
	var picURL = $(panel).attr("src");
	var json = {"resourceName":resourceName,"key":key, "value": picURL};
	ajaxUtil(json, mainpath+"/module/updateResource.do", showMsg("修改成功"), null);
}
function saveResource(resourceName, panel, key){
	var value = $(panel).val();
	var json = {"resourceName":resourceName,"key":key, "value": value};
	ajaxUtil(json, mainpath+"/module/updateResource.do", showMsg("修改成功"), null);
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
        		if (failFunc != undefined){
        			failFunc(result.response.reason, result.code);
        		} else {
        			alert("提交失败！");
        		}
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
function isEmpty(str){
	if (str == undefined || str == "" || str == null || str == "null")
		return true;
	else
		return false;
}
function fileupload(ajaxUrl, fileElementId, succFunc){  
    $.ajaxFileUpload({  
            url:ajaxUrl,  
            secureuri:false,  
            fileElementId:fileElementId,  
            dataType: 'text/xml',             
            success: function (data) { 
            	if (data){
            		var url = data.substr(5, data.length-11);
            		succFunc(url);
            	} else {
            		alert("上传失败！");  
            	}
            },error: function (data, status, e){  
                alert("上传失败！");  
            }  
        }  
    );  
} 

function fileupload2(ajaxUrl, fileElementId, succFunc){  
    $.ajaxFileUpload({  
            url:ajaxUrl,  
            secureuri:false,  
            fileElementId:fileElementId,  
            dataType: 'text/html',             
            success: function (response) { 
            	if (response){
            		alert("上传成功！")
            	} else {
            		alert("上传失败！");  
            	}
            	$("#img-upload-btn").removeAttr("name");
            },error: function (data, status, e){  
                alert("上传失败！");
                $("#img-upload-btn").removeAttr("name");
            }  
        }  
    );  
} 
function addTab(tabtit,tabcon) {
    $(tabcon).hide();
    $(tabtit+" li:first").addClass("thistab").show();
    $(tabcon+":first").show();

    $(tabtit+" li").click(function() {
        $(tabtit+" li").removeClass("thistab");
        $(this).addClass("thistab");
        $(tabcon).hide();
        var activeTab = $(this).find("a").attr("tab");
        $("#"+activeTab).show();
    });
    
};
function hasAttr(id,attr){
	if(typeof($(id).attr(attr))=="undefined"){
		return false;
	} else{
		return true;
	}
}
function trimStr(str){
	if (str == undefined || str == null)
		return str;
	return str.trim();
}
function formatDate(dt){
	if (dt == null)
		return "";
	return new Date(dt).format("yyyy-MM-dd");
}
function formatDateTime(dt){
	if (dt == null)
		return "";
	return new Date(dt).format("yyyy-MM-dd hh:mm");
}
function formatShowTime(dt){
	if (dt == null)
		return "";
	return new Date(dt).format("MM-dd hh:mm");
}

function parseDate(str){
	if (str == null)
		return "";
	return new Date(str.replace(/-/g, "/")); 
}

// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
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

function reviseContent(str){
	if (str == null || str == "null")
		return "";
	return str;
}
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
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