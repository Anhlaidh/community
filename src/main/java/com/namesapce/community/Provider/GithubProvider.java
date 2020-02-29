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
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }
    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            System.out.println(gitHubUser.getName());
            return  gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
