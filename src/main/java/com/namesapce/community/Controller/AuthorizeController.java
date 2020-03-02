package com.namesapce.community.Controller;

import com.namesapce.community.DTO.AccessTokenDTO;
import com.namesapce.community.DTO.GitHubUser;
import com.namesapce.community.Mapper.UserMapper;
import com.namesapce.community.Model.User;
import com.namesapce.community.Provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Description: receive AnthorizeToken
 * @author: Anhlaidh
 * @date: 2020/2/29 0029 0:03
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        //github返回code state，封装到accessTokenDTO中
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.getState(state);
        //调用githubProvider 用accessTokenDTO取得accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //使用token 取得GitHubuser信息并封装成bean
        GitHubUser gitHubUser = githubProvider.getUser(accessToken);
        if (gitHubUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            request.getSession().setAttribute("gitHubUser",gitHubUser);
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }
}
