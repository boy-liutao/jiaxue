package com.xuema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuema.bean.User;
import com.xuema.service.DBService;
import com.xuema.util.SessionUtil;
import com.xuema.util.WebUtil;
import com.xuema.util.XuemaUtil;

@Controller
@RequestMapping(value = "/redirect")
public class RedirectController {
    @Autowired
    DBService dbService;
    
    @RequestMapping(value="gonews")
    public void go(HttpServletRequest request, HttpServletResponse response) throws Exception{
        User user = SessionUtil.getUserInfo(request, dbService);
        if (gotoBindPhone(user)){
            if (WebUtil.isWeixin(request)){
                response.sendRedirect("http://open.weixin.qq.com/connect/oauth2/authorize?appid="+XuemaUtil.AppId+"&redirect_uri=" + XuemaUtil.domain_name + "/jiaxue/page/login&response_type=code&scope=snsapi_base&state=wx#wechat_redirect");
            } else {
                response.sendRedirect(XuemaUtil.domain_name + "/jiaxue/page/login");
            }
        } else {
            response.sendRedirect("http://mp.weixin.qq.com/mp/homepage?__biz=MzA3Mjc2NDU4Mw==&hid=1&sn=4d3a2f9818385f816c5a80f60dfcf39e#wechat_redirect");
        }
    } 
    
    public boolean gotoBindPhone(User user){
        if (user == null || StringUtils.isEmpty(user.getPhone())){
            return true;
        }
        return false;
    }
    
}
