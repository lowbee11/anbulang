package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service("UserService")
public class UserService
{
    @Autowired
    private UserDao userDao;

    public Integer register(User user)
    {
        List<User> users = userDao.selectUser();
        for (User u : users
        )
        {
            if (user.getEmail().equals(u.getEmail()))
            {
                System.out.println("账号已存在");
                return 102;
            }
        }
        userDao.insertUser(user);
        return 101;
    }

    public Integer login(User user, HttpServletRequest request, HttpServletResponse response)
    {
        List<User> users = userDao.selectUser();
        for (User u : users
        )
        {
            if (user.getEmail().equals(u.getEmail()))
            {
                if (user.getPassword().equals(u.getPassword()))
                {
                    Cookie email = new Cookie("email", u.getEmail());
                    Cookie username = new Cookie("username", u.getUsername());
                    Cookie password = new Cookie("password", u.getPassword());
                    email.setMaxAge(60 * 60);
                    username.setMaxAge(60 * 60);
                    password.setMaxAge(60 * 60);
                    email.setPath("/");
                    username.setPath("/");
                    password.setPath("/");
                    response.addCookie(email);
                    response.addCookie(username);
                    response.addCookie(password);
                    return 101;
                } else
                {
                    return 103;
                }
            }
        }
        return 102;
    }

    public List<User> selectUser()
    {
        return userDao.selectUser();
    }

    public List<User> usernameSelect()
    {
        return userDao.usernameSelect();
    }

    public boolean updateUsername(User user, HttpServletResponse response)
    {
        Cookie cookie = new Cookie("username", user.getUsername());
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        userDao.updateUsername(user);
        return true;
    }

    public boolean updateUserPassword(User user, HttpServletResponse response)
    {
        Cookie cookie = new Cookie("password", user.getPassword());
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        userDao.updateUserPassword(user);
        return true;
    }
}
