package com.namesapce.community.Mapper;

import com.namesapce.community.Model.Question;
import com.namesapce.community.Model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

}