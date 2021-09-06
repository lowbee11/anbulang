package com.example.util;

import com.example.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerifyPassUtil
{
    private String pass = null;

    public VerifyPassUtil(User user, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        this.pass = (String) session.getAttribute(user.getEmail());
    }

    public String getPass()
    {
        return pass;
    }
}
