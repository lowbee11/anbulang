package com.example.dao;

import com.example.entity.Face;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FaceDao
{
    @Insert("insert into face values(#{email},#{profile})")
    void insertFace(Face face);

    @Select("select * from face")
    List<Face> selectFace();

    @Update("update face set profile = #{profile} where email = #{email}")
    void updateFace(Face face);
}
