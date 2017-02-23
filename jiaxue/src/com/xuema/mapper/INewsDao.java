package com.xuema.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Activity;
import com.xuema.bean.News;

@Repository(value = "newsDao")
public interface INewsDao { 
    static String LIST = "select a.id, a.title, a.type, a.sid, a.img, a.content, a.startTime, a.updateTime from Activity a where forNews=true union select n.id, n.title, '资讯' as type, 0 as sid, n.img, n.content, null as startTime, n.updateTime from News n order by updateTime desc";
    static String COUNT = "select count(*) from News";
    static String SELECT = "select * from News where id=#{id}";
    static String DELETE = "delete from News where id=#{id}";
    static String UPDATE = "update News set title=#{title},img=#{img},content=#{content},updateTime=now() where id=#{id}";
    static String INSERT = "insert into News (title, img, content, updateTime) values (#{title},#{img},#{content},now());";
    static String SELECTACTIVITY = "select * from Activity where id=#{id}";

    @Select(LIST)
    public List<News> list();

    @Select(COUNT)
    public int count();

    @Select(SELECT)
    public News select(int id);
    
    @Delete(DELETE)
    public void delete(int id);

    @Update(UPDATE)
    public void update(News news);
    
    @Insert(INSERT)
    public void insert(News news);

    @Select(SELECTACTIVITY)
    public Activity selectActivity(int id);
}
