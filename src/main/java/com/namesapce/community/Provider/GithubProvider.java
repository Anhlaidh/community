package com.namesapce.community.Provider;

import com.alibaba.fastjson.JSON;
import com.namesapce.community.DTO.AccessTokenDTO;
import com.namesapce.community.DTO.GitHubUser;
import okhttp3.*;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
//fdef8436bbbba8832810cd441448b097949f3172
/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/2/29 0029 0:09
 */
@Configuration
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        //OK Http 取得uri
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));//将accessTokenDTO bean转化为json
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //获得返回参数
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
//            System.out.println(token);分解字符串，提取token，并返回
            return token;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }
    public GitHubUser getUser(String accessToken){
        //传参访问github并返回json页面
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //将json页面转译为javaBean，返回githubuser
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            System.out.println(gitHubUser.getName());
            return  gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
