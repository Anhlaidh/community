package com.namesapce.community.DTO;

import com.namesapce.community.Model.User;
import lombok.Data;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/3/2 0002 20:34
 */
@Data
public class QuestionDTO {
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
    private User user;

}
