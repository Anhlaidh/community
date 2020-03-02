package com.namesapce.community.Model;

import lombok.Data;

/**
 * @Description: JavaBean
 * @author: Anhlaidh
 * @date: 2020/2/29 0029 21:54
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;


}
