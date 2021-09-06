package com.example.controller;

import com.example.entity.Question;
import com.example.entity.User;
import com.example.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class QuestionController
{
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/question/insert")
    public boolean questionInsert(@RequestBody Question question)
    {
        questionService.insertQuestion(question);
        return true;
    }

    @RequestMapping("/question/select")
    public List<Question> questionSelect()
    {
        return questionService.selectQuestion();
    }

    //    @RequestMapping("/myQuestion/select")
//    public List<Question> questionSelect(@RequestBody User user)
//    {
//        return questionService.selectQuestion(user);
//    }
    @RequestMapping("/question/search")
    public List<Question> questionSearch(@Param("e") String e)
    {
        System.out.println(e);
        for (Question q : questionService.searchQuestion(e)
        )
        {
            System.out.println(q.getQuestion());
        }
        return questionService.searchQuestion("%" + e + "%");
    }
}
