package com.xuema.exception;

public class XuemaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public XuemaException(String msg){
		super(msg);
	}
	
	public XuemaException(Throwable t){
		super(t);
	}
}
