package com.example.controller;

import com.example.entity.Answer;
import com.example.entity.Question;
import com.example.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerController
{
    @Autowired
    private AnswerService answerService;

    @RequestMapping("/answer/insert")
    public boolean answerInsert(@RequestBody Answer answer)
    {
        answerService.insertAnswer(answer);
        return true;
    }

    @RequestMapping("/answer/select")
    public List<Answer> answerSelect()
    {
        return answerService.selectAnswer();
    }
}
