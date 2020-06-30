package com.example.hotel.data.admin;

import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserVO;

public class Tester {
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
