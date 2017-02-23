package com.xuema.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Suggestion;

@Repository(value = "suggestionDao")
public interface ISuggestionDao {

    static String INSERT = "insert into Suggestion (content, submitter, phone, createTime) values (#{content}, #{submitter}, #{phone}, now())";
    static String UPDATE = "update Suggestion set content=#{content}, submitter=#{submitter}, phone=#{phone} where id=#{id}";
    static String DELETE = "delete from Suggestion where id=#{id}";
    static String LIST = "select * from Suggestion order by id desc limit #{start}, #{size}";
    static String COUNT = "select count(*) from Suggestion";
    static String SELECT = "select * from Suggestion where id=#{id}";

    @Select(LIST)
    public List<Suggestion> list(@Param("start")int start, @Param("size")int size);
    
    @Select(COUNT)
    public int count();
    
    @Insert(INSERT)
    public void insert(Suggestion suggestion);
    
    @Update(UPDATE)
    public void update(Suggestion suggestion);
    
    @Delete(DELETE)
    public void delete(int id);
    
    @Select(SELECT)
    public Suggestion select(int id);
}
