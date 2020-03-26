package com.namesapce.community.Service;

import com.namesapce.community.Mapper.UserMapper;
import com.namesapce.community.Model.User;
import com.namesapce.community.Model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/3/5 0005 15:19
 */
@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //插入
        }else {
            User dbUser = users.get(0);
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(System.currentTimeMillis());

            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(dbUser, example);
            //更新
        }
    }
}
