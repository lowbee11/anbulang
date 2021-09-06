package com.example.controller;

import com.example.entity.Face;
import com.example.entity.MyFile;
import com.example.service.FaceService;
import com.example.util.GetFileListUtil;
import com.example.util.UploadUtil;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileController
{
    @Autowired
    private FaceService faceService;

    @RequestMapping(value = "/share/get")
    public List<MyFile> shareGet(@RequestBody MyFile myFile)
    {
        List<MyFile> myFiles = new ArrayList<>();
//        System.out.println(myFile.getDir());
        for (File file : new GetFileListUtil(myFile.getDir()).getFiles()
        )
        {
            myFiles.add(new MyFile(file));
        }
        return myFiles;
    }

    @RequestMapping(value = "/share/upload")
    public String shareUpload(HttpServletRequest request) throws IOException
    {
        new UploadUtil(request);
        return "<h1>上传成功</h1>\n" +
                "<span id=\"return_time\">3</span>秒后返回\n" +
                "<script>\n" +
                "    setTimeout(function () {\n" +
                "        window.open(\"/share\",\"_self\")\n" +
                "    },3000)\n" +
                "    var return_time = document.getElementById(\"return_time\");\n" +
                "    var time = 2;\n" +
                "    setInterval(function () {\n" +
                "        return_time.innerText = time;\n" +
                "        time--;\n" +
                "    },1000)\n" +
                "</script>";
    }

    @RequestMapping(value = "/userHome/upload")
    public String userHomeUpload(HttpServletRequest request, HttpServletResponse response, @CookieValue("email") String email) throws IOException, SQLException, ClassNotFoundException
    {
        UploadUtil upload = new UploadUtil(request);
        String[] fileNames = upload.getFileNames();
        Face face = new Face();
        face.setEmail(email);
        face.setProfile(fileNames[0]);
        faceService.updateFace(face);
        Cookie cookie = new Cookie("profile", fileNames[0]);
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "<h1>上传成功</h1>\n" +
                "<span id=\"return_time\">3</span>秒后返回\n" +
                "<script>\n" +
                "    setTimeout(function () {\n" +
                "        window.open(\"/userHome\",\"_self\")\n" +
                "    },3000)\n" +
                "    var return_time = document.getElementById(\"return_time\");\n" +
                "    var time = 2;\n" +
                "    setInterval(function () {\n" +
                "        return_time.innerText = time;\n" +
                "        time--;\n" +
                "    },1000)\n" +
                "</script>";
    }
}
