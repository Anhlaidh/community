package com.namesapce.community.Controller;

import com.namesapce.community.Mapper.QuestionMapper;
import com.namesapce.community.Model.Question;
import com.namesapce.community.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description: PublishController
 * @author: Anhlaidh
 * @date: 2020/3/1 0001 17:12
 */
@Controller
public class PublishController {
    @Resource
    private QuestionMapper questionMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description")String description,
            @RequestParam("tag")String tag,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description==null||description=="") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }



        Question question = new Question();
//        System.out.println(request.getParameter("title"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null) {
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());


        questionMapper.create(question);

        return "redirect:/";
    }
}
