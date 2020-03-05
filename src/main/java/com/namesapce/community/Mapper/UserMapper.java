package com.namesapce.community.Mapper;

import com.namesapce.community.Model.User;
import org.apache.ibatis.annotations.*;


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
    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);
    @Update("update user set name = #{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id = #{id}")
    void update(User user);
}
