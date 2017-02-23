package com.xuema.bean;

import java.util.List;

public class School {
    int id;
    String name;
    String address;
    String headmaster;
    String headmasterPhone;
    String phone;
    String createTime;
    List<GlobalSetting> settings;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getHeadmaster() {
        return headmaster;
    }
    public void setHeadmaster(String headmaster) {
        this.headmaster = headmaster;
    }
    public String getPhone() {
        return phone;
    }
    public String getHeadmasterPhone() {
        return headmasterPhone;
    }
    public void setHeadmasterPhone(String headmasterPhone) {
        this.headmasterPhone = headmasterPhone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public List<GlobalSetting> getSettings() {
        return settings;
    }
    public void setSettings(List<GlobalSetting> settings) {
        this.settings = settings;
    }
    
    
}
