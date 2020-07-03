package com.example.hotel.data.admin;

import com.example.hotel.enums.UserType;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface AdminMapper {

    int addManager(User user);

    int addUser(User user);

    List<User> getAllManagers();

    List<User> getAllClient();

    int deleteUser(@Param("userid") int userid);

    int updateUser(@Param("id") Integer id,@Param("email") String email,@Param("password") String password,
                   @Param("username") String username, @Param("birthday") String birthday);


    public static void main(String[] args)
    {

        User user=new User();
        user.setBirthday("2000-01-16");
        user.setCorporationName("Baidu");
        user.setCredit(18);
        user.setEmail("893652269@qq.com");
        user.setHotelid(66);
        user.setId(66);
        user.setIsVIP(1);
        user.setPassword("123");
        user.setPhoneNumber("123");
        user.setUserName("chaomo");

        user.setUserType(UserType.Admin);










    }


}





