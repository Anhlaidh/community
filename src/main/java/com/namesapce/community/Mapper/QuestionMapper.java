package com.namesapce.community.Mapper;

import com.namesapce.community.DTO.QuestionDTO;
import com.namesapce.community.Model.Question;
import org.apache.ibatis.annotations.*;

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
    @Select("select * from question where id = #{id}")
    Question getById(Integer id);
    @Update("update question set title = #{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id = #{id}")
    void update(Question question);
}




