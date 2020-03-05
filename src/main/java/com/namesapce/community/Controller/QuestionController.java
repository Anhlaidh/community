package com.namesapce.community.Controller;

import com.namesapce.community.DTO.QuestionDTO;
import com.namesapce.community.Mapper.UserMapper;
import com.namesapce.community.Model.User;
import com.namesapce.community.Service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/3/4 0004 20:44
 */
@Controller
public class QuestionController {
    @Resource
    private QuestionService questionService;
    @Resource
    private UserMapper userMapper;
    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name = "id") Integer id,
            Model model
    ){
        QuestionDTO questionDTO = questionService.getId(id);
        User user = userMapper.findById(questionDTO.getCreator());
        questionDTO.setUser(user);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
