package com.namesapce.community.Controller;

import com.namesapce.community.DTO.PaginationDTO;
import com.namesapce.community.DTO.QuestionDTO;
import com.namesapce.community.Mapper.QuestionMapper;
import com.namesapce.community.Mapper.UserMapper;
import com.namesapce.community.Model.Question;
import com.namesapce.community.Model.User;
import com.namesapce.community.Service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/2/28 0028 21:15
 */
@Controller
public class IndexController {
//    @Autowired
    @Resource
    private UserMapper userMapper;
    @Resource
    private QuestionService questionService;

    @GetMapping("/")
    public String index(
            @RequestParam(value = "page",defaultValue = "1")int page,
            @Value("${page.size}")int size,
            HttpServletRequest request,
            Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);

        return "index";
    }
}