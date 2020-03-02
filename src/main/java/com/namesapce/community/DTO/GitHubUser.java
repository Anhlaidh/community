package com.namesapce.community.DTO;

import lombok.Data;

/**
 * @Description: github user bean
 * @author: Anhlaidh
 * @date: 2020/2/29 0029 12:19
 */
@Data
public class GitHubUser {
    private String name;
    private long id;
    private String bio;
    private String avatarUrl;


}
