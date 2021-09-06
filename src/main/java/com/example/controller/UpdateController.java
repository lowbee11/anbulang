package com.example.controller;

import com.example.entity.Face;
import com.example.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController
{
    @Autowired
    private FaceService faceService;

    @RequestMapping("/profile/update")
    public boolean profileUpdate(Face face)
    {
        return faceService.updateFace(face);
    }
}
