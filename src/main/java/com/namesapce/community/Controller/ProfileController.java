package com.namesapce.community.Controller;

import com.namesapce.community.DTO.PaginationDTO;
import com.namesapce.community.Mapper.UserMapper;
import com.namesapce.community.Model.User;
import com.namesapce.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/3/4 0004 14:20
 */
@Controller
public class ProfileController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action,
            @RequestParam(value = "page",defaultValue = "1")int page,
            @Value("${page.size}")int size,
            Model model,
            HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");


        if ("questions".equals(action)){
            model.addAttribute("section",action);
            model.addAttribute("title","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section",action);
            model.addAttribute("title","最新回复");
        }
        PaginationDTO pagination = questionService.Profiles(page,size,user);

        model.addAttribute("pagination",pagination);
        return "profile";

    }
}
