package com.xuema.bean;

import java.util.Date;


public class Coupon {
    String id;
    int value;
    int uid;
    String oid;
    int cond;
    int condType;
    Date deadline;
    int status;//0:"未使用",1:"已使用",2:"已过期",3:"作废",4:"冻结"
    String creator;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getOid() {
        return oid;
    }
    public void setOid(String oid) {
        this.oid = oid;
    }
    public int getCond() {
        return cond;
    }
    public void setCond(int cond) {
        this.cond = cond;
    }
    public int getCondType() {
        return condType;
    }
    public void setCondType(int condType) {
        this.condType = condType;
    }
    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
}
