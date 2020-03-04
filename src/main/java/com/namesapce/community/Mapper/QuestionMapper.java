package com.namesapce.community.Mapper;

import com.namesapce.community.Model.Question;
import com.namesapce.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION (title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("Select * from Question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);
    @Select("select count(1) from question")
    Integer count();
    @Select("select count(1) from question where creator=#{user}")
    Integer profileCount(@Param("user") Integer user);
    @Select("Select * from Question where creator=#{user} limit #{offset},#{size} ")
    List<Question> listProfile(@Param("offset") Integer offset,@Param("size") Integer size,@Param("user") Integer user);
}




