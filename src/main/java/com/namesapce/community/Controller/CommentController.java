package com.namesapce.community.Controller;

import com.namesapce.community.DTO.CommentDTO;
import com.namesapce.community.DTO.ResultDTO;
import com.namesapce.community.Exception.CustomizeErrorCode;
import com.namesapce.community.Mapper.CommentMapper;
import com.namesapce.community.Model.Comment;
import com.namesapce.community.Model.User;
import com.namesapce.community.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020-06-22 12:14
 */
@Controller
public class CommentController {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(1L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
