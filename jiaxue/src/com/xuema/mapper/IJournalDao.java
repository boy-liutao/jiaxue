package com.xuema.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository(value = "journalDao")
public interface IJournalDao {

    String INSERT = "insert into Journal (oid, money, payWay, createTime) values (#{oid}, #{money}, #{payWay}, now())";

    @Insert(INSERT)
    void insert(@Param("oid")String oid, @Param("money")int money, @Param("payWay")int payWay);

}
