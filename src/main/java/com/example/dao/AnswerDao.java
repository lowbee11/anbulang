package com.example.dao;

import com.example.entity.Answer;
import com.example.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.lang.annotation.Inherited;
import java.util.List;

@Mapper
public interface AnswerDao
{
    @Insert("insert into answer values(#{question_id},#{email},#{answer})")
    void insertAnswer(Answer answer);

    @Select("select * from answer")
    List<Answer> selectAnswer();
}
