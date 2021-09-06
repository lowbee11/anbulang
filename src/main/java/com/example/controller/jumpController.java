package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class jumpController
{
    @RequestMapping("/register")
    public String register()
    {
        return "register";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/homepage")
    public String homepage()
    {
        return "homepage";
    }

    @RequestMapping("/share")
    public String share()
    {
        return "share";
    }

    @RequestMapping("/question")
    public String question()
    {
        return "question";
    }

    @RequestMapping("/userHome")
    public String userHome()
    {
        return "userHome";
    }

    @RequestMapping("/myQuestion")
    public String myQuestion()
    {
        return "myQuestion";
    }

    @RequestMapping("/test")
    public String test()
    {
        return "test";
    }
}
