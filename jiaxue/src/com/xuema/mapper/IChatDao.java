package com.xuema.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Chat;

@Repository(value = "chatDao")
public interface IChatDao { 
    static String LIST = "select manager, max(createTime) as t  from Chat where uid=#{uid} group by manager order by t desc";
    static String COUNT = "select count(*) from Chat";
    static String SELECT = "select * from Chat where id=#{id}";
    static String DELETE = "delete from Chat where id=#{id}";
    static String SELECTCONTENT = " SELECT content FROM Chat WHERE manager=#{manager} ORDER BY createTime DESC";
    static String INSERT = "insert into Chat (uid, manager, content, imgs,readStatus,toUser,createTime) values (#{uid},#{manager},#{content},#{imgs},#{readStatus},#{toUser},now());";
    static String LASTCHAT = "select * from Chat where uid=#{uid} and manager=#{manager} order by createTime desc limit 1";  
    static String UPDATE = "update Chat set readStatus=1 where id=#{id}";
    static String LISTOLDER = "select * from Chat where uid=#{uid} and manager=#{manager} and id < #{mark} order by createTime desc limit #{size}";
    static String LISTNEWER = "select * from Chat where uid=#{uid} and manager=#{manager} and id > #{mark} order by createTime";
    static String LISTNEARESTAFTERDATE = "select * from Chat where uid=#{uid} and manager=#{manager} and createTime>#{date} order by createTime";
    static String LISTNEAREST = "select * from Chat where uid=#{uid} and manager=#{manager} order by createTime desc limit #{size}";
    static String LISTDURINGDATE = "select * from Chat where uid=#{uid} and manager=#{manager} and createTime>=#{startDate} and createTime<#{endDate} order by createTime";
    static String USERMANAGERLASTCH = "SELECT * from Chat where uid=#{uid} and manager=#{username} ORDER BY createTime DESC LIMIT 1";
    static String USERLASTCH = "SELECT * from Chat where uid=#{uid} ORDER BY createTime DESC LIMIT 1";

    @Select(LIST)
    public List<Chat> list(int uid);

    @Select(COUNT)
    public int count();

    @Select(SELECT)
    public Chat select(int id);
    
    @Select(USERMANAGERLASTCH)
    public Chat userManagerLastCh(@Param("uid")int uid, @Param("username")String username);
    
    @Select(USERLASTCH)
    public Chat userLastCh(@Param("uid")int uid);
    
    @Delete(DELETE)
    public void delete(int id);

    @Insert(INSERT)
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statement = { "SELECT LAST_INSERT_ID()" })
    public void insert(Chat chat);

    @Select(LASTCHAT)
    public Chat selectLastChat(@Param("uid")int uid, @Param("manager")String manager);
    
    @Update(UPDATE)
    public void update(@Param("id")int id);

    @Select(LISTOLDER)
    public List<Chat> loadOlder(@Param("uid")int uid, @Param("manager")String manager, @Param("mark")int mark, @Param("size")int size);

    @Select(LISTNEWER)
    public List<Chat> loadNewer(@Param("uid")int uid, @Param("manager")String manager, @Param("mark")int mark);

    @Select(LISTNEAREST)
    public List<Chat> loadNearest(@Param("uid")int uid, @Param("manager")String manager, @Param("size")int size);

    @Select(LISTNEARESTAFTERDATE)
    public List<Chat> loadNewerAfterDate(@Param("uid")int uid, @Param("manager")String manager, @Param("date")Date date);
    
    @Select(LISTDURINGDATE)
    public List<Chat> loadDuringDate(@Param("uid")int uid, @Param("manager")String manager, @Param("startDate")Date startDate, @Param("endDate")Date endDate);


}
