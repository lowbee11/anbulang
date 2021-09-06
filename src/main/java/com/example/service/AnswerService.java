package com.example.service;

import com.example.dao.AnswerDao;
import com.example.entity.Answer;
import com.example.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AnswerService")
public class AnswerService
{
    @Autowired
    private AnswerDao answerDao;

    public boolean insertAnswer(Answer answer)
    {
        answerDao.insertAnswer(answer);
        return true;
    }

    public List<Answer> selectAnswer()
    {
        return answerDao.selectAnswer();
    }
}
