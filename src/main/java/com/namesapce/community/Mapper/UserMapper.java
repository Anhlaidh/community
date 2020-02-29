package com.namesapce.community.Mapper;

import com.namesapce.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/2/29 0029 21:49
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    public void insert(User user);
}
