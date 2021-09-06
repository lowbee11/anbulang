package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao
{
    @Insert("insert into user values(#{id},#{email},#{username},#{password})")
    void insertUser(User user);

    //    @Delete("delete from user where id = #{id}")
//    void deleteUser(User user);
    @Select("select * from user")
    List<User> selectUser();

    @Select("select email,username from user")
    List<User> usernameSelect();

    @Update("update user set username = #{username} where email = #{email}")
    void updateUsername(User user);

    @Update("update user set password = #{password} where email = #{email}")
    void updateUserPassword(User user);

}
