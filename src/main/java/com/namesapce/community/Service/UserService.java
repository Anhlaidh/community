package com.namesapce.community.Service;

import com.namesapce.community.Mapper.UserMapper;
import com.namesapce.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/3/5 0005 15:19
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());


        if (dbUser==null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //插入
        }else {
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(System.currentTimeMillis());
            userMapper.update(dbUser);
            //更新
        }
    }
}
