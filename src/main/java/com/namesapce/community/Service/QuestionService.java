package com.namesapce.community.Service;

import com.namesapce.community.DTO.PaginationDTO;
import com.namesapce.community.DTO.QuestionDTO;
import com.namesapce.community.Mapper.QuestionMapper;
import com.namesapce.community.Mapper.UserMapper;
import com.namesapce.community.Model.Question;
import com.namesapce.community.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public PaginationDTO list(Integer page,Integer size){
        Integer offset = size*(page-1);

        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPage(page);
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }
    public PaginationDTO Profiles(Integer page, Integer size, User currentUser){
        Integer offset = size*(page-1);

        List<Question> questions = questionMapper.listProfile(offset,size,currentUser.getId());
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);
        Integer totalCount = questionMapper.profileCount(currentUser.getId());
        paginationDTO.setPage(page);
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public QuestionDTO getId(Integer id) {
        Question question = questionMapper.getId(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }
}
