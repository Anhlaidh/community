package com.namesapce.community.Mapper;

import com.namesapce.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/2/29 0029 21:49
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED,avatar_url) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where TOKEN=#{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from user where id=#{id}")
    User findById(@Param("id")int id);
}
