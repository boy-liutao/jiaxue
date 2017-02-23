package com.xuema.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.School;

@Repository(value = "schoolDao")
public interface ISchoolDao {

    static String INSERT = "insert into School (name, address, phone, headmaster, headmasterPhone, createTime) values (#{name}, #{address}, #{phone}, #{headmaster},#{headmasterPhone}, now())";
    static String UPDATE = "update School set name=#{name}, address=#{address}, phone=#{phone}, headmaster=#{headmaster},headmasterPhone=#{headmasterPhone} where id=#{id}";
    static String DELETE = "delete from School where id=#{id}";
    static String LIST = "select * from School order by id desc limit #{start}, #{size}";
    static String COUNT = "select count(*) from School";
    static String SELECT = "select * from School where id=#{id}";
    static String LISTALL = "select s.* from School s left join GlobalSetting gs on s.id=gs.sid where gs.sid is not null group by s.id order by s.id desc";//只列出设置了费用的学堂

    @Select(LIST)
    public List<School> list(@Param("start")int start, @Param("size")int size);
    
    @Select(COUNT)
    public int count();
    
    @Insert(INSERT)
    public void insert(School school);
    
    @Update(UPDATE)
    public void update(School school);
    
    @Delete(DELETE)
    public void delete(int id);
    
    @Select(SELECT)
    public School select(int id);

    @Select(LISTALL)
    public List<School> listAll();
}
