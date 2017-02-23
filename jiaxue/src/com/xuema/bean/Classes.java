package com.xuema.bean;

import java.beans.Transient;
import java.util.List;

public class Classes {
    int id;
    int sid;
    String name;
    String address;
    String createTime;
    private String username;
    private String phone;
   
    School school;
    List<ClassesManager> classman;
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
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
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
	
	@Transient
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Transient
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public School getSchool() {
	        return school;
	}
	public void setSchool(School school) {
	        this.school = school;
	}
	public List<ClassesManager> getClassman() {
		return classman;
	}
	public void setClassman(List<ClassesManager> classman) {
		this.classman = classman;
	}
	
    
    
}
