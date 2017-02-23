package com.xuema.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Classes;

@Repository(value = "classesDao")
public interface IClassesDao {

    static String LISTBYMANAGERSID = "select c.* from ClassesManager cm left join Classes c on cm.cid=c.id  where cm.username=#{username} and c.sid=#{sid}";
    static String SELECT = "select * from Classes where id=#{id}";

    @Select(LISTBYMANAGERSID)
    public List<Classes> listByManagerSid(@Param("username")String username, @Param("sid")int sid);

    @Select(SELECT)
    public Classes select(int cid);

}
