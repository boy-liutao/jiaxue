/* 
 * jeasyPro
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/
 * 2013-8-11 下午3:31:50
 */
package com.gson.bean;

/**
 * 多客服消息
 * 
 * @author ____′↘夏悸
 * 
 */
public class DKFOutMessage extends OutMessage {

	private String	MsgType	= "transfer_customer_service";
	
	public DKFOutMessage() {
	}

	public String getMsgType() {
		return MsgType;
	}

}
