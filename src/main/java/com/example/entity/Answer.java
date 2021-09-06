package com.example.entity;

public class Answer
{
    private Integer question_id = null;
    private String email = null;
    private String answer = null;

    public Integer getQuestion_id()
    {
        return question_id;
    }

    public void setQuestion_id(Integer question_id)
    {
        this.question_id = question_id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
}
