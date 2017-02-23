package com.xuema.bean;

import java.util.Date;

public class User {
    int id;
    String cardId;
    String phone;
    String email;
    String openid;
    String name;
    int sex;
    Date born;
    String img;
    String inschool;//学生所在学校
    String grade;//年级
    int sid;//校区id
    String homeAddress;
    String emergencyPhone;
    String emergencyContacter;
    int cid;//班级id
    int payStatus;//是否已支付
    Date startDate;//入托日期
    Date endDate;//结束日期
    String parent;
    String monday;
    String tuesday;
    String wednesday;
    String thursday;
    String friday;
    String saturday;
    String sunday;
    
    School school;
    Classes classes;
    Daily daily;
    boolean todayComment;
    Chat lastChat;
    
    Date inGradeDate;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public Date getBorn() {
        return born;
    }
    public void setBorn(Date born) {
        this.born = born;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public int getSid() {
        return sid;
    }
    public String getInschool() {
        return inschool;
    }
    public void setInschool(String inschool) {
        this.inschool = inschool;
    }
    
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public String getHomeAddress() {
        return homeAddress;
    }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    public String getEmergencyPhone() {
        return emergencyPhone;
    }
    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
    public String getEmergencyContacter() {
        return emergencyContacter;
    }
    public void setEmergencyContacter(String emergencyContacter) {
        this.emergencyContacter = emergencyContacter;
    }
    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public int getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }
    public School getSchool() {
        return school;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public void setSchool(School school) {
        this.school = school;
    }
    public Classes getClasses() {
        return classes;
    }
    public void setClasses(Classes classes) {
        this.classes = classes;
    }
    public String getParent() {
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }
    public boolean isTodayComment() {
        return todayComment;
    }
    public void setTodayComment(boolean todayComment) {
        this.todayComment = todayComment;
    }
    public String getMonday() {
        return monday;
    }
    public void setMonday(String monday) {
        this.monday = monday;
    }
    public String getTuesday() {
        return tuesday;
    }
    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }
    public String getWednesday() {
        return wednesday;
    }
    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }
    public String getThursday() {
        return thursday;
    }
    public void setThursday(String thursday) {
        this.thursday = thursday;
    }
    public String getFriday() {
        return friday;
    }
    public void setFriday(String friday) {
        this.friday = friday;
    }
    public String getSaturday() {
        return saturday;
    }
    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }
    public String getSunday() {
        return sunday;
    }
    public void setSunday(String sunday) {
        this.sunday = sunday;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public Daily getDaily() {
        return daily;
    }
    public void setDaily(Daily daily) {
        this.daily = daily;
    }
	public Chat getLastChat() {
		return lastChat;
	}
	public void setLastChat(Chat lastChat) {
		this.lastChat = lastChat;
	}
    public Date getInGradeDate() {
        return inGradeDate;
    }
    public void setInGradeDate(Date inGradeDate) {
        this.inGradeDate = inGradeDate;
    }
	
}
