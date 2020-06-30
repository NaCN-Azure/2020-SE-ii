package com.example.hotel.data.user;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface AccountMapper {

    /**
     * 创建一个新的账号
     *
     * @return
     */
     int createNewAccount(User user);

     int registervip(User user);

     int deleteuser(@Param("userid") int userid);

    /**
     * 根据用户名查找账号
     * @param email
     * @return
     */
     User getAccountByName(@Param("email") String email);

     User getAccountById(@Param("id") int id);

     int punishCredit(@Param("id") int id,@Param("credit") double credit);

    /**
     * 更新用户信息
     * @param id
     * @param password
     * @param username
     * @param phonenumber
     * @return
     */
     int updateAccount(@Param("id") int id, @Param("password") String password,@Param("userName") String username, @Param("phoneNumber") String phonenumber,@Param("birthday") String birthday);

     int updateVIP(@Param("id") int id, @Param("password") String password,@Param("userName") String username, @Param("phoneNumber") String phonenumber,@Param("birthday") String birthday,@Param("isVIP") int isVIP,@Param("corporationName") String corpo );

     int updateVIPII(User user);

     int addCredit(@Param("id") int id,@Param("credit") double credit);

    int plusCredit(@Param("id") int id,@Param("credit") double credit);

}
