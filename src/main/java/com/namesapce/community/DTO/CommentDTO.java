package com.namesapce.community.DTO;

import lombok.Data;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020-06-22 12:30
 */

@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
    
}
