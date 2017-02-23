package com.xuema.bean;

import java.util.Date;

public class Orders {
    String id;//由yyyyMMdd+id组成
    int uid;
    int sid; //学校id
    int nurseryUnit; //缴费形式1学期交,2月交,3日交
    int nurseryCount; //支付数量,0表示没有
    int nurseryMoney; //托管费用
    int fruitCount;//水果数量,0表示没有
    int fruitMoney;//水果钱数
    int fruitRemainCount;//水果剩余次数
    int dinnerCount;//晚餐数量,0表示没有
    int dinnerMoney;//晚餐钱数
    int dinnerRemainCount;//晚餐剩余数量
    Date startDate;//托管开始时间
    Date endDate;//托管结束时间
    String briefInfo;//简明信息
    String note;//备注
     
    int money;//总价
    int payMoney;//支付金额 
    int payWay;//支付方式：0未知，1微信,2支付宝,3银联,4现金
    String billno;//商户订单号
    String payId;//支付id, 用于对接第三方帐单
    Date payTime;//支付时间
    int status;//支付状态，0未支付，1已支付，2退费中,3已退费,4已结束，5已取消
    
    Date returnApplyDate;
    Date returnDoneDate;
    int returnNurseryMoney;
    int returnFruitMoney;
    int returnDinnerMoney;
    
    Date createTime;
    String updateTime;
    
    User user;
    School school;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public int getNurseryUnit() {
        return nurseryUnit;
    }
    public void setNurseryUnit(int nurseryUnit) {
        this.nurseryUnit = nurseryUnit;
    }
    public int getNurseryCount() {
        return nurseryCount;
    }
    public void setNurseryCount(int nurseryCount) {
        this.nurseryCount = nurseryCount;
    }
    public int getFruitCount() {
        return fruitCount;
    }
    public int getNurseryMoney() {
        return nurseryMoney;
    }
    public void setNurseryMoney(int nurseryMoney) {
        this.nurseryMoney = nurseryMoney;
    }
    public void setFruitCount(int fruitCount) {
        this.fruitCount = fruitCount;
    }
    public int getFruitMoney() {
        return fruitMoney;
    }
    public void setFruitMoney(int fruitMoney) {
        this.fruitMoney = fruitMoney;
    }
    public int getFruitRemainCount() {
        return fruitRemainCount;
    }
    public void setFruitRemainCount(int fruitRemainCount) {
        this.fruitRemainCount = fruitRemainCount;
    }
    public int getDinnerCount() {
        return dinnerCount;
    }
    public void setDinnerCount(int dinnerCount) {
        this.dinnerCount = dinnerCount;
    }
    public int getDinnerMoney() {
        return dinnerMoney;
    }
    public void setDinnerMoney(int dinnerMoney) {
        this.dinnerMoney = dinnerMoney;
    }
    public int getDinnerRemainCount() {
        return dinnerRemainCount;
    }
    public void setDinnerRemainCount(int dinnerRemainCount) {
        this.dinnerRemainCount = dinnerRemainCount;
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
    public String getNote() {
        return note;
    }
    public String getBriefInfo() {
        return briefInfo;
    }
    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public int getPayMoney() {
        return payMoney;
    }
    public void setPayMoney(int payMoney) {
        this.payMoney = payMoney;
    }
    public String getPayId() {
        return payId;
    }
    public int getPayWay() {
        return payWay;
    }
    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }
    public void setPayId(String payId) {
        this.payId = payId;
    }
    public String getBillno() {
        return billno;
    }
    public void setBillno(String billno) {
        this.billno = billno;
    }
    public Date getPayTime() {
        return payTime;
    }
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getReturnApplyDate() {
        return returnApplyDate;
    }
    public void setReturnApplyDate(Date returnApplyDate) {
        this.returnApplyDate = returnApplyDate;
    }
    public Date getReturnDoneDate() {
        return returnDoneDate;
    }
    public void setReturnDoneDate(Date returnDoneDate) {
        this.returnDoneDate = returnDoneDate;
    }
    public int getReturnNurseryMoney() {
        return returnNurseryMoney;
    }
    public void setReturnNurseryMoney(int returnNurseryMoney) {
        this.returnNurseryMoney = returnNurseryMoney;
    }
    public int getReturnFruitMoney() {
        return returnFruitMoney;
    }
    public void setReturnFruitMoney(int returnFruitMoney) {
        this.returnFruitMoney = returnFruitMoney;
    }
    public int getReturnDinnerMoney() {
        return returnDinnerMoney;
    }
    public void setReturnDinnerMoney(int returnDinnerMoney) {
        this.returnDinnerMoney = returnDinnerMoney;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }
    
    
}
