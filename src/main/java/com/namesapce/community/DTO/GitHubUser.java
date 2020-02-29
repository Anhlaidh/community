package com.namesapce.community.DTO;

/**
 * @Description: github user bean
 * @author: Anhlaidh
 * @date: 2020/2/29 0029 12:19
 */
public class GitHubUser {
    private String name;
    private long id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
