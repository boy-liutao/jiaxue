package com.xuema.mapper;

import java.util.Map;

import org.springframework.util.StringUtils;

public class UserSearchSQLProvider {
    public static String userSearchSQL(Map<String, Object> parameter){
        String wherePart = generateUserSearchWherePart(parameter);
        return "select u.* from Users u left join ClassesManager cm on u.cid=cm.cid left join Orders o on u.id=o.uid where "+wherePart+" group by u.id"; 
    }
    
    private static String generateUserSearchWherePart(Map<String, Object> parameter){
        String username = (String)parameter.get("username");
        int cid = (int)parameter.get("cid");
        int sid = (int)parameter.get("sid");
        String key = (String)parameter.get("key");
        String wherePart = " 1=1 ";
        wherePart += "and o.startDate<=curdate() and o.endDate>=curdate() and o.status=1 ";
        if (!StringUtils.isEmpty(username)){
            wherePart += "and cm.username=#{username} ";
        }
        if (!StringUtils.isEmpty(key)){
            wherePart += "and u.name like CONCAT('%',#{key},'%') ";
        }
        if (cid > 0){
            wherePart += "and u.cid = #{cid} ";
        }
        if (sid > 0){
            wherePart += "and u.sid = #{sid} ";
        }
        return wherePart; 
    }
    
    
}
