package com.example.dao;

import com.example.entity.Question;
import com.example.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionDao
{
    @Insert("insert into question values(#{id},#{email},#{question})")
    void insertQuestion(Question question);

    @Select("select * from question")
    List<Question> selectQuestion();

    @Select("select * from question where question like #{e}")
    List<Question> searchQuestion(String e);
//    @Select("select * from question where email = #{email}")
//    List<Question> selectQuestion(User user);
}
