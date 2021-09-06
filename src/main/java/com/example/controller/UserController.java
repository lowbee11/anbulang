package com.example.controller;

import com.example.entity.Face;
import com.example.entity.User;
import com.example.service.FaceService;
import com.example.service.UserService;
import com.example.util.VerifyPassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private FaceService faceService;

    @RequestMapping("/register/submit")
    public Integer registerSubmit(@RequestBody User user, HttpServletRequest request)
    {
        System.out.println(request.getParameter("pass"));
        System.out.println(new VerifyPassUtil(user, request).getPass());
        if (request.getParameter("pass").equals(new VerifyPassUtil(user, request).getPass()))
        {
            System.out.println("注册成功");
            Integer flag = userService.register(user);
            if (flag == 101)
            {
                Face face = new Face();
                face.setEmail(user.getEmail());
                face.setProfile("");
                faceService.InsertFace(face);
            }
            return flag;
        } else
        {
            System.out.println("验证码错误");
            return 103;
        }
    }

    @RequestMapping("/login/submit")
    public Integer loginSubmit(@RequestBody User user, HttpServletRequest request, HttpServletResponse response)
    {
        faceService.login(user, request, response);
        return userService.login(user, request, response);
    }

    @RequestMapping("/username/select")
    public List<User> usernameSelect()
    {
        return userService.usernameSelect();
    }

    @RequestMapping("/face/select")
    public List<Face> faceSelect()
    {
        return faceService.selectFace();
    }

    @RequestMapping("/username/update")
    public boolean usernameUpdate(@RequestBody User user, HttpServletResponse response)
    {
        return userService.updateUsername(user, response);
    }

    @RequestMapping("/userPassword/update")
    public boolean userPasswordUpdate(@RequestBody User user, HttpServletResponse response)
    {
        return userService.updateUserPassword(user, response);
    }
}
