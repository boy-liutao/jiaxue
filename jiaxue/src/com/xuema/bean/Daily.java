package com.xuema.bean;

import java.util.Date;

import com.xuema.util.DatetimeUtil;

public class Daily {
    int uid;
    Date date;
    boolean isLeave;
    boolean nurseryStatus;
    Date nurseryTime;
    String nurseryer;
    String nurseryInfo;
    String nurseryImg;
    int nurseryMoney;
    boolean fruitStatus;
    Date fruitTime;
    int fruitMoney;
    boolean dinnerStatus;
    int dinnerMoney;
    boolean commentStatus;
    Date dinnerTime;
    String comment;
    Date commentTime;
    String commenter;
    
    public Daily() {
        super();
    }
    public Daily(int uid, boolean fruitStatus, boolean dinnerStatus) {
        super();
        this.uid = uid;
        this.fruitStatus = fruitStatus;
        this.dinnerStatus = dinnerStatus;
    }

    //additional field
    String scheduleTime;//当天计划接送时间
    
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public boolean isNurseryStatus() {
        return nurseryStatus;
    }
    public boolean isLeave() {
        return isLeave;
    }
    public void setLeave(boolean isLeave) {
        this.isLeave = isLeave;
    }
    public void setNurseryStatus(boolean nurseryStatus) {
        this.nurseryStatus = nurseryStatus;
    }
    public Date getNurseryTime() {
        return nurseryTime;
    }
    public void setNurseryTime(Date nurseryTime) {
        this.nurseryTime = nurseryTime;
    }
    public String getNurseryImg() {
        return nurseryImg;
    }
    public void setNurseryImg(String nurseryImg) {
        this.nurseryImg = nurseryImg;
    }
    public String getNurseryer() {
        return nurseryer;
    }
    public void setNurseryer(String nurseryer) {
        this.nurseryer = nurseryer;
    }
    public String getNurseryInfo() {
        return nurseryInfo;
    }
    public void setNurseryInfo(String nurseryInfo) {
        this.nurseryInfo = nurseryInfo;
    }
    public boolean isFruitStatus() {
        return fruitStatus;
    }
    public void setFruitStatus(boolean fruitStatus) {
        this.fruitStatus = fruitStatus;
    }
    public Date getFruitTime() {
        return fruitTime;
    }
    public void setFruitTime(Date fruitTime) {
        this.fruitTime = fruitTime;
    }
    public boolean isDinnerStatus() {
        return dinnerStatus;
    }
    public void setDinnerStatus(boolean dinnerStatus) {
        this.dinnerStatus = dinnerStatus;
    }
    public Date getDinnerTime() {
        return dinnerTime;
    }
    public void setDinnerTime(Date dinnerTime) {
        this.dinnerTime = dinnerTime;
    }
    public boolean isCommentStatus() {
        return commentStatus;
    }
    public void setCommentStatus(boolean commentStatus) {
        this.commentStatus = commentStatus;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getCommentTime() {
        return commentTime;
    }
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
    public String getCommenter() {
        return commenter;
    }
    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
    public String getScheduleTime() {
        return scheduleTime;
    }
    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
    public String getNurseryTimeText() {
        if (nurseryTime != null){
            String dPart = DatetimeUtil.getWeekOfDate(nurseryTime);
            String tPart = DatetimeUtil.formatDate(nurseryTime, "HH:mm");
            return dPart + " " + tPart;
        }
        return "";
    }
    public int getNurseryMoney() {
        return nurseryMoney;
    }
    public void setNurseryMoney(int nurseryMoney) {
        this.nurseryMoney = nurseryMoney;
    }
    public int getFruitMoney() {
        return fruitMoney;
    }
    public void setFruitMoney(int fruitMoney) {
        this.fruitMoney = fruitMoney;
    }
    public int getDinnerMoney() {
        return dinnerMoney;
    }
    public void setDinnerMoney(int dinnerMoney) {
        this.dinnerMoney = dinnerMoney;
    }
    
    
}
