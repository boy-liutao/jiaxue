package com.xuema.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xuema.bean.Banner;

@Repository(value = "bannerDao")
public interface IBannerDao {
    static String LIST = "select * from Banner";
    static String COUNT = "select count(*) from Banner";
    static String SELECT = "select * from Banner where id=#{id}";
    String DELETE = "delete from Banner where id=#{id}";
    static String UPDATE = "update Banner set img=#{img},url=#{url} where id=#{id}";
    static String INSERT = "insert into Banner ( img, url) values (#{img},#{url});";

    @Select(LIST)
    public List<Banner> list();

    @Select(COUNT)
    public int count();

    @Select(SELECT)
    public Banner select(int id);
    
    @Delete(DELETE)
    public void delete(int id);

    @Update(UPDATE)
    public void update(Banner banner);
    
    @Insert(INSERT)
    public void insert(Banner banner);
}
