package com.namesapce.community.Service;

import com.namesapce.community.DTO.PaginationDTO;
import com.namesapce.community.DTO.QuestionDTO;
import com.namesapce.community.Exception.CustomizeErrorCode;
import com.namesapce.community.Exception.CustomizeException;
import com.namesapce.community.Mapper.QuestionMapper;
import com.namesapce.community.Mapper.UserMapper;
import com.namesapce.community.Model.Question;
import com.namesapce.community.Model.QuestionExample;
import com.namesapce.community.Model.User;
import org.apache.ibatis.session.RowBounds;
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

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question:questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        paginationDTO.setPage(page);
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }
    public PaginationDTO Profiles(Integer page, Integer size, User currentUser){
        Integer offset = size*(page-1);


        QuestionExample example = new QuestionExample();
        example.createCriteria().andIdEqualTo(currentUser.getId());
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question:questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);

        QuestionExample question = new QuestionExample();
        question.createCriteria().andIdEqualTo(currentUser.getId());
        Integer totalCount = (int)questionMapper.countByExample(question);
        paginationDTO.setPage(page);
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {

          Question question = questionMapper.selectByPrimaryKey(id);
          if (question==null){
              throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
          }
          QuestionDTO questionDTO = new QuestionDTO();
          BeanUtils.copyProperties(question,questionDTO);

        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            //更新
            question.setGmtModified(System.currentTimeMillis());
            int update = questionMapper.updateByPrimaryKeySelective(question);
            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

        }
    }
}
