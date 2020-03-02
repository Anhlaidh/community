package com.namesapce.community.Service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.namesapce.community.DTO.QuestionDTO;
import com.namesapce.community.Mapper.QuestionMapper;
import com.namesapce.community.Mapper.UserMapper;
import com.namesapce.community.Model.Question;
import com.namesapce.community.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: question
 * @author: Anhlaidh
 * @date: 2020/3/2 0002 20:35
 */
@Service
public class QuestionService {
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private UserMapper userMapper;
    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        for (Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
