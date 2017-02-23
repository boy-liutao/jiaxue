package com.xuema.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Coupon;

@Repository(value = "couponDao")
public interface ICouponDao {
    String LIST = "select * from Coupon where uid=#{uid} and cond<=#{cond} and deadline >= curDate() order by createTime ";
    String FROZEN = "update Coupon set oid=#{oid}, status=4 where id=#{id}";
    String GET = "select * from Coupon where id=#{id}";
    String GETBYOID = "select * from Coupon where oid=#{oid}";
    String CONSUME = "update Coupon set status=1 where id=#{id}";

    @Select(LIST)
    public List<Coupon> list(@Param("uid")int uid, @Param("cond")int cond);

    @Update(FROZEN)
    public void frozen(@Param("id")String id, @Param("oid")String oid);

    @Select(GET)
    public Coupon get(String cid);

    @Select(GETBYOID)
    public Coupon getByOid(String oid);

    @Update(CONSUME)
    public void consume(String id);
}
