package com.xuema.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuema.service.DBService;


public class AuthorityAspect {
	
	@Autowired
	DBService dbService;
	
    public void doAfter(JoinPoint jp) {
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    	return pjp.proceed();
    }

    public void doBefore(JoinPoint jp) {
    	
    	
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
    	System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
    	ex.printStackTrace();
    }
}