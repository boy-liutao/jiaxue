package com.xuema.service;

public class InitService {
	
	public static void init(){
		org.apache.ibatis.logging.LogFactory.useLog4JLogging(); 
	}
}
