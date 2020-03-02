package com.namesapce.community.Model;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: question bean
 * @author: Anhlaidh
 * @date: 2020/3/1 0001 21:43
 */
//@Configuration
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}
