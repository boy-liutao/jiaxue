package com.xuema.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Daily;

@Repository(value = "dailyDao")
public interface IDailyDao { 
    static String LIST = "SELECT * FROM Daily  WHERE nurseryTime IS NOT NULL and uid=#{uid} ";
    static String COUNT = "select count(*) from Daily";
    static String UPDATENURSERY = "update Daily set nurseryer=#{nurseryer}, nurseryInfo=#{nurseryInfo}, nurseryStatus=#{nurseryStatus}, nurseryImg=#{nurseryImg}, nurseryTime=now() where uid=#{uid} and date=#{date}";
    static String ADDNURSERY = "insert into Daily (uid, date, nurseryer, nurseryInfo, nurseryStatus, nurseryImg, nurseryTime) values (#{uid}, #{date}, #{nurseryer}, #{nurseryInfo}, #{nurseryStatus}, #{nurseryImg}, now())";
    static String SELECTLATESTDAILY = "select * from Daily where uid=#{uid} order by date desc limit 1";
    static String SELECTBEFOREDAILY = "select * from Daily where uid=#{uid} and date<#{date} order by date desc limit 1";
    static String UPDATECOMMENT = "update Daily set comment=#{comment}, commentStatus=1, commenter=#{commenter}, commentTime=now() where uid=#{uid} and date=#{date}";
    static String SELECTTODAY = "select * from Daily where uid=#{uid} and date=curDate()";
    static String ADDCOMMENT = "insert into Daily (uid, date, comment, commenter, commentStatus, commentTime) values (#{uid}, curDate(), #{comment}, #{commenter}, #{commentStatus}, now())";
    

    @Select(LIST)
    public List<Daily> list(int uid);

    @Select(COUNT)
    public int count();
    
    @Select(SELECTTODAY)
    public Daily today(int uid);

    @Insert(ADDNURSERY)
    public void addUserNursery(Daily d);
    
    @Update(UPDATENURSERY)
    public void updateUserNursery(Daily d);

    @Select(SELECTLATESTDAILY)
    public Daily selectLatestDaily(@Param("uid")int uid, @Param("date")Date date);

    @Select(SELECTBEFOREDAILY)
    public Daily selectBeforeDaily(@Param("uid")int uid, @Param("date")Date date);

    @Update(UPDATECOMMENT)
    public void updateComment(@Param("uid")int uid, @Param("date")Date date, @Param("comment")String comment, @Param("commenter")String commenter);

    @Insert(ADDCOMMENT)
    public void insertComment(Daily d);

    

}