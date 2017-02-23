package com.xuema.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Daily;
import com.xuema.bean.User;

@Repository(value = "userDao")
public interface IUserDao {
	static String SELECT = "select * from Users where id=#{id}";
	static String INSERT = "insert into Users (cardId, phone, email, openid, name, sex, born, img, inschool, grade, homeAddress, parent, emergencyPhone, emergencyContacter, sid, cid, payStatus, createTime, updateTime,monday,tuesday,wednesday,thursday,friday,saturday,sunday,startDate,endDate) values (#{cardId}, #{phone}, #{email}, #{openid}, #{name}, #{sex}, #{born}, #{img}, #{inschool}, #{grade}, #{homeAddress}, #{parent}, #{emergencyPhone},#{emergencyContacter},#{sid},#{cid},#{payStatus}, now(), now(),#{monday},#{tuesday},#{wednesday},#{thursday},#{friday},#{saturday},#{sunday},#{startDate},#{endDate})";
    static String UPDATE = "update Users set cardId=#{cardId}, email=#{email}, name=#{name}, sex=#{sex}, born=#{born}, img=#{img}, inschool=#{inschool}, grade=#{grade}, homeAddress=#{homeAddress}, parent=#{parent}, updateTime=now() where id=#{id}";
    static String LISTBYFROMID = "select * from Users where fromid=#{fromid} and result>=0 order by updateTime desc";
    static String SELECTRAND = "select * from Users where result>=0 and wx=1 order by updateTime desc limit #{size} ";
    static String SELECTBYOPENID = "select * from Users where openId=#{openId}";
    static String UPDATEPHONE = "update Users set phone=#{phone}, img=#{img} where id=#{id}";
    static String SELECTDAILY = "select * from Daily where uid=#{uid} and date=#{date}";
    static String LISTBYMANAGERKEY = "select u.* from Users u left join ClassesManager cm on u.cid=cm.cid left join Orders o on u.id=o.uid where cm.username=#{username} and u.name like concat('%',#{key},'%') and o.startDate<=curdate() and o.endDate>=curdate() and o.status=1  group by u.id";
    static String UPDATESCHOOL = "update Users set sid=#{sid} where id=#{id}";
    static String SELECTBYPHONE = "select * from Users where phone=#{phone}";
    static String UPDATEOPENID = "update Users set openId=#{openId}, img=#{img} where id=#{id}";
    static String DELETE = "delete from Users where id=#{id}";
    static String UPDATEHISPHONE = "update Users set hisPhone=phone where id=#{id}";
    static String UPDATESID = "update Users set sid=#{sid} where id=#{id}";
    
    @Select(SELECT)
    public User select(int id);

    @Insert(INSERT)
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statement = { "SELECT LAST_INSERT_ID()" })
    public void insert(User u);
    
    @Update(UPDATE)
    public void update(User u);

    @Select(LISTBYFROMID)
    public List<User> listNext(String fromid);

    @Select(SELECTRAND)
    public List<User> selectRand(int size);

    @Select(SELECTBYOPENID)
    public User selectByOpenId(String openId);

    @Update(UPDATEPHONE)
    public void updatePhone(@Param("id")int id, @Param("phone")String phone, @Param("img")String img);
    
    @Update(UPDATEOPENID)
    public void updateOpenId(@Param("id")int id, @Param("openId")String openId, @Param("img")String img);
    
    @SelectProvider(type=UserSearchSQLProvider.class, method="userSearchSQL")
    public List<User> selectByManager(@Param("username")String username, @Param("sid")int sid, @Param("cid")int cid, @Param("key")String key);

    @Select(SELECTDAILY)
    public Daily selectDaily(@Param("uid")int uid, @Param("date")Date date);

    @Select(LISTBYMANAGERKEY)
    public List<User> selectByManagerKey(@Param("username")String username, @Param("key")String key);

    @Update(UPDATESCHOOL)
    public void updateSchool(@Param("id")int id, @Param("sid")int sid);

    @Select(SELECTBYPHONE)
    public User getUserByPhone(String phone);

    @Delete(DELETE)
    public void delete(int id);

    @Update(UPDATEHISPHONE)
    public void updateHisPhone(int id);

    @Update(UPDATESID)
    public void updateSid(@Param("id")int id, @Param("sid")int sid);

    
   
}
