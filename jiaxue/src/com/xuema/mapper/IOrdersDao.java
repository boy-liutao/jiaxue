package com.xuema.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Orders;

@Repository(value = "ordersDao")
public interface IOrdersDao {
    static String SELECT = "select * from Orders where id=#{id}";
    static String INSERT = "insert into Orders (id, uid, sid, nurseryUnit, nurseryCount, nurseryMoney, fruitCount, fruitMoney, fruitRemainCount, dinnerCount, dinnerMoney, dinnerRemainCount, startDate, briefInfo, note, money, billno, payId, payWay, payMoney, status, createTime, updateTime) "
            + "values (#{id}, #{uid}, #{sid}, #{nurseryUnit}, #{nurseryCount}, #{nurseryMoney}, #{fruitCount}, #{fruitMoney}, #{fruitRemainCount}, #{dinnerCount}, #{dinnerMoney}, #{dinnerRemainCount}, #{startDate}, #{briefInfo}, #{note}, #{money}, #{billno}, #{payId}, #{payWay}, #{payMoney}, #{status}, now(), now());";
    static String UPDATESTATUS = "update Orders set status=#{toStatus}, updateTime=now() where id=#{id}";
    static String UPDATEBYPAYID = "update Orders set status=1, payTime=now(), updateTime=now() where payId=#{payId}";
    static String SELECTBYPAYID = "select * from Orders where payId=#{payId}";
    static String LIST = "select * from Orders where uid=#{id} ORDER BY createTime DESC";
    static String FRUITREMAIN = "select sum(fruitRemainCount) from Orders where uid=#{uid} and status=1";
    static String DINNERREMAIN = "select sum(dinnerRemainCount) from Orders where uid=#{uid} and status=1";
    static String UPDATE = "update Orders set sid=#{sid}, nurseryUnit=#{nurseryUnit}, nurseryCount=#{nurseryCount}, nurseryMoney=#{nurseryMoney}, fruitCount=#{fruitCount}, fruitMoney=#{fruitMoney}, fruitRemainCount=#{fruitRemainCount}, dinnerCount=#{dinnerCount}, dinnerMoney=#{dinnerMoney}, dinnerRemainCount=#{dinnerRemainCount}, startDate=#{startDate}, briefInfo=#{briefInfo}, note=#{note}, money=#{money}, billno=#{billno}, payId=#{payId}, payWay=#{payWay}, payMoney=#{payMoney}, status=#{status}, updateTime=now() where id=#{id}";
    
    @Select(SELECT)
    public Orders select(String id);
    
    @Select(LIST)
    public List<Orders> list(int id);
    
    @Insert(INSERT)
    public void insert(Orders orders);

    @Update(UPDATESTATUS)
    public void updateStatus(@Param("id")String id, @Param("toStatus")int toStatus);

    @Update(UPDATEBYPAYID)
    public int payOrder(@Param("payId")String payId);

    @Select(SELECTBYPAYID)
    public Orders selectByPayId(String prepayId);

    @Select(FRUITREMAIN)
    public Integer computeFruitRemain(int uid);
    
    @Select(DINNERREMAIN)
    public Integer computeDinnerRemain(int uid);

    @Update(UPDATE)
    public void update(Orders order);
}
