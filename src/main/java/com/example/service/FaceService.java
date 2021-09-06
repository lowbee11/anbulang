package com.example.service;

import com.example.dao.FaceDao;
import com.example.entity.Face;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service("FaceService")
public class FaceService
{
    @Autowired
    private FaceDao faceDao;

    public boolean InsertFace(Face face)
    {
        faceDao.insertFace(face);
        return true;
    }

    public List<Face> selectFace()
    {
        return faceDao.selectFace();
    }

    public boolean updateFace(Face face)
    {
        faceDao.updateFace(face);
        return true;
    }

    public void login(User user, HttpServletRequest request, HttpServletResponse response)
    {
        for (Face f : faceDao.selectFace()
        )
        {
            if (f.getEmail().equals(user.getEmail()))
            {
                Cookie cookie = new Cookie("profile", f.getProfile());
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60);
                response.addCookie(cookie);
                return;
            }
        }

    }
}
