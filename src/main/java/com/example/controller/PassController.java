package com.example.controller;

import com.example.util.SendQQEmailUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class PassController
{

    @RequestMapping("/getPass")
    public boolean getPass(HttpServletRequest request, HttpServletResponse response) throws MessagingException
    {
        Integer pass = 1000 + (int) (Math.floor(Math.random() * 9999) * 0.9);
        HttpSession session = request.getSession();
        session.setAttribute(request.getParameter("email"), pass.toString());
        String sessionId = session.getId();
        Cookie cookie = new Cookie("JSESSIONID", sessionId);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        if (session.isNew())
        {
            System.out.println("session创建成功，id是" + sessionId);
        } else
        {
            System.out.println("服务器已存在该session，id是" + sessionId);
        }
//        cookie.setMaxAge(60*5);
//        response.addCookie(cookie);
        SendQQEmailUtil sendQQEmailUtil = new SendQQEmailUtil();
        sendQQEmailUtil.setMailAddress(request.getParameter("email"));
        sendQQEmailUtil.setPass(pass.toString());
        sendQQEmailUtil.send();
        return true;
    }
}
