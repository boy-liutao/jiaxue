package com.xuema.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.GlobalSetting;

@Repository(value = "globalSettingDao")
public interface IGlobalSettingDao {
    static String COUNT = "select count(*) from GlobalSetting";
    static String SELECT = "select * from GlobalSetting where sid=#{sid} and name=#{name}";
    static String UPDATE = "update GlobalSetting set value=#{value} where name=#{name} and sid=#{sid}";
    static String INSERT = "insert into GlobalSetting (name, value, type, sid, description) values (#{name},#{value},#{type},#{sid},#{description});";
    static String LISTBYSID = "select * from GlobalSetting where sid=#{sid}";


    @Select(COUNT)
    public int count();

    @Select(SELECT)
    public GlobalSetting select(@Param("sid")int id,@Param("name")String name);
    

    @Update(UPDATE)
    public void update(GlobalSetting globalSetting);
    
    @Insert(INSERT)
    public void insert(GlobalSetting globalSetting);

    @Select(LISTBYSID)
    public List<GlobalSetting> listBySid(int id);
}
