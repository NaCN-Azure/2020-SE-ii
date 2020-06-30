package com.example.hotel.controller.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.blImpl.admin.AdminServiceImpl;
import com.example.hotel.enums.UserType;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@RestController()
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/addManager")
    public ResponseVO addManager(@RequestBody UserForm userForm){

        return adminService.addManager(userForm);
    }

    @PostMapping("/getClientList")
    public ResponseVO getClientList(){
        return ResponseVO.buildSuccess(adminService.getClientList());
    }

    @PostMapping("/getAllManagers")
    public ResponseVO getAllManagers(){
        return ResponseVO.buildSuccess(adminService.getAllManagers());
    }


    @PostMapping("/updateUsers")
    public ResponseVO updateUsersAPI(@RequestBody typeUserVO typeuser)
    {
        System.out.println("Nacn");
        System.out.println(typeuser.updateType);
        System.out.println(typeuser.getUser().getId());
        if(typeuser.getUpdateType().equals("delete"))
        {
            return adminService.deleteUser(typeuser.getUser().getId());

        }
        else {
            return adminService.changeUser(typeuser.getUser());

        }




    }

    public static void main(String[] args)


    {

        UserVO user=new UserVO();
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
        AdminController adminController=new AdminController();
        adminController.adminService.addUser(user);





    }



}








