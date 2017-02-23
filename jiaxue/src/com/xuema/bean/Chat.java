package com.xuema.bean;

import java.util.Date;

import com.xuema.util.DatetimeUtil;


public class Chat {
    int id;
    int uid;
    String manager;
    String content;
    String imgs;
    int readStatus;
    Date createTime;
    boolean toUser;
   
    String managerNickname;
    String managerImg;
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public int getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getManagerNickname() {
        return managerNickname;
    }
    public void setManagerNickname(String managerNickname) {
        this.managerNickname = managerNickname;
    }
    public String getManagerImg() {
        return managerImg;
    }
    public void setManagerImg(String managerImg) {
        this.managerImg = managerImg;
    }
    public String getShowTime() {
        return DatetimeUtil.getShowTime(createTime);
    }
    public boolean isToUser() {
        return toUser;
    }
    public void setToUser(boolean toUser) {
        this.toUser = toUser;
    }
	
	
}
