package com.example.service;

import com.example.dao.QuestionDao;
import com.example.entity.Question;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("QuestionService")
public class QuestionService
{
    @Autowired
    private QuestionDao questionDao;

    public boolean insertQuestion(Question question)
    {
        questionDao.insertQuestion(question);
        return true;
    }

    public List<Question> selectQuestion()
    {
        return questionDao.selectQuestion();
    }

    public List<Question> searchQuestion(String e)
    {
        return questionDao.searchQuestion(e);
    }
//    public List<Question> selectQuestion(User user)
//    {
//        return questionDao.selectQuestion(user);
//    }
}
