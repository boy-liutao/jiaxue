package com.xuema.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import com.xuema.bean.User;


public class BeanUtil {
	public static void mergeBean(Class clz, Object exist, Object latest){
		Method[] ms = clz.getMethods();
		for (Method m : ms){
			if (m.getName().startsWith("set")){
				String getM = m.getName().replaceFirst("set", "get");
				try {
					Method getMethod = clz.getMethod(getM);
					Object latestV = getMethod.invoke(latest);
					if (latestV != null){
						m.invoke(exist, latestV);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String arrayToString(String[] input){
		return arrayToString(input, XuemaUtil.SPLITTER);
	}
	
	public static String[] stringToArray(String input){
        return stringToArray(input, XuemaUtil.SPLITTER);
    }
    
    public static String[] stringToArray(String input, String splitter){
        if (!StringUtils.isEmpty(input))
            return input.split(splitter);
        return new String[0];
    }
	
	public static String arrayToString(String[] input, String delimiter){
		StringBuffer sb = new StringBuffer();
		String delim = "";
		for (String s : input){
			if (!StringUtils.isEmpty(s)){
				sb.append(delim).append(s);
				delim = delimiter;
			}
		}
		return sb.toString();
	}
	
	public static String arrayToStringKeepEmpty(String[] input, String delimiter){
		StringBuffer sb = new StringBuffer();
		String delim = "";
		for (String s : input){
			sb.append(delim).append(s);
			delim = delimiter;
		}
		return sb.toString();
	}
	
	public static Object initBean(String xml, String beanId){
		Resource r = new ByteArrayResource(xml.getBytes());
		BeanFactory f = new XmlBeanFactory(r);
		Object bean = f.getBean(beanId);
		return bean;
	}
	
	public static boolean isChineseChar(String str){
    	if (str == null)
    		return true;
        boolean temp = false;
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m=p.matcher(str);
        if(m.find()){
            temp =  true;
        }
        return temp;
    }
	
	public static String toChinese(String str) {
		try {
			return new String(str.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static boolean isNumber(String str){
		try {
			Integer.valueOf(str);
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	public static int parseInt(Integer i){
	    if (i == null)
	        return 0;
	    return i;
	}
	
	public static void main(String[] args) {
		System.out.println(isNumber("a123"));
	}

	public static boolean isBoolean(String str) {
		return "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str)
				|| "1".equals(str) || "0".equals(str);
	}
	
	public static String noFillUserInfo(User u){
	    if (u == null)
	        return "";
	    StringBuilder sb = new StringBuilder();
	    if (StringUtils.isEmpty(u.getParent())){
	        sb.append("家长姓名,");
	    }
	    if (StringUtils.isEmpty(u.getCardId())){
            sb.append("身份证号,");
        }
	    if (StringUtils.isEmpty(u.getEmail())){
            sb.append("邮箱,");
        }
	    if (StringUtils.isEmpty(u.getHomeAddress())){
            sb.append("家庭住址,");
        }
	    if (StringUtils.isEmpty(u.getName())){
            sb.append("孩子姓名,");
        }
	    if (u.getSex() == 0){
            sb.append("家长性别,");
        }
	    if (StringUtils.isEmpty(u.getInschool())){
            sb.append("所在学校,");
        }
	    if (StringUtils.isEmpty(u.getGrade())){
            sb.append("所在年级,");
        }
	    if (u.getBorn() == null){
            sb.append("生日,");
        }
	    if (StringUtils.isEmpty(u.getImg())){
            sb.append("头像,");
        }
	    return sb.toString();
	}
}

