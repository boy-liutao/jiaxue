package com.xuema.bean;

import java.util.Date;

import com.xuema.util.DatetimeUtil;

public class Activity {
    int id;
    String title;
    String type;
    int sid;
    String img;
    String content;
    Date startTime;
    String updateTime;
    
    School school;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public String getDateLabel() {
        Date showTime = startTime;
        
        if (showTime == null)
            return "";
        if (showTime.equals(DatetimeUtil.todayBegin())){
            return "今天";
        }
        if (showTime.equals(DatetimeUtil.tomorrowBegin())){
            return "明天";
        }
        if (showTime.equals(DatetimeUtil.dayAfterTomorrowBegin())){
            return "后天";
        }
        if (showTime.equals(DatetimeUtil.yesterdayBegin())){
            return "昨天";
        }
        if (showTime.equals(DatetimeUtil.dayBeforeYesterdayBegin())){
            return "前天";
        }
        return DatetimeUtil.formatDate(showTime, "MM-dd hh:mm"  );
    }
    
}
