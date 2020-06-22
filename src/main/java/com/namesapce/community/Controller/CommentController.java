package com.namesapce.community.Controller;

import com.namesapce.community.DTO.CommentDTO;
import com.namesapce.community.Mapper.CommentMapper;
import com.namesapce.community.Model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020-06-22 12:14
 */
@Controller
public class CommentController {
    @Resource
    private CommentMapper commentMapper;
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(1L);
        commentMapper.insert(comment);
        return null;
    }
}
