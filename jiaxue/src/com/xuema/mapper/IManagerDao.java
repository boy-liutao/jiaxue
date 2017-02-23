package com.xuema.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Manager;




@Repository(value = "managerDao")
public interface IManagerDao {

    static String SELECT = "select * from Manager where username=#{username} ORDER BY username";
    static String UPDATEPW = "update Manager set password=#{password} where username=#{username}";
    static String SELECTROLES = "select role from ManagerRole where username=#{username}";
    static String LIST = "select * from Manager order by createTime desc limit #{start}, #{size}";
    static String COUNT = "select count(*) from Manager";
    static String DELETE = "delete from Manager where username=#{username}";
    static String UPDATE = "update Manager set nickname=#{nickname},password=#{password},sid=#{sid},phone=#{phone}, img=#{img} where username=#{username}";
    static String INSERT = "insert into Manager (username, nickname, password, img, sid, phone, createTime) values (#{username}, #{nickname}, #{password}, #{img}, #{sid}, #{phone}, now());";
    static String INSERTROLE = "insert into ManagerRole (username, role) values (#{username}, #{role})";
    static String DELETEMANAGERROLE = "delete from ManagerRole where username=#{username}";
    static String SELECTBYOPENID = "select * from Manager where openid=#{openid}";
    static String SELECTBYPHONE = "select * from Manager where phone=#{phone}";
    static String UPDATEOPENID = "update Manager set openid=#{openid} where phone=#{phone}";
    static String UPDATEIMG = "update Manager set img=#{img} where username=#{username}";
    @Select(SELECT)
    Manager select(String username);

    @Update(UPDATEPW) 
    void updatePW(@Param("username")String username, @Param("password")String password);

    @Select(SELECTROLES)
    List<String> selectRoles(String username);

    @Select(LIST)
    public List<Manager> list(@Param("start")int start, @Param("size")int size);

    @Select(COUNT)
    public int count();
    
    @Delete(DELETE)
    public void delete(String username);

    @Update(UPDATE)
    public void update(Manager manager);
    
    @Insert(INSERT)
    public void insert(Manager manager);

    @Insert(INSERTROLE)
    void addRole(@Param("username")String username, @Param("role")String role);

    @Delete(DELETEMANAGERROLE)
    void deleteRole(String username);

    @Select(SELECTBYOPENID)
    Manager selectByOpenId(String openId);

    @Select(SELECTBYPHONE)
    Manager selectByPhone(String phone);

    @Update(UPDATEOPENID)
    void updateOpenid(@Param("phone")String phone, @Param("openid")String openid);

    @Update(UPDATEIMG)
    void updateManagerImg(@Param("username")String username, @Param("img")String img);
    
}
