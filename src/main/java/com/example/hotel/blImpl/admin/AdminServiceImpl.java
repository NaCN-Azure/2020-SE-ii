package com.example.hotel.blImpl.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    @Autowired
    AdminMapper adminMapper;
    @Override
    public ResponseVO addManager(UserForm userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUserName(userForm.getUserName());
        user.setHotelid(userForm.getHotelid());
        user.setUserType(UserType.HotelManager);
        
        try {
            adminMapper.addManager(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<User> getAllManagers() {
        return adminMapper.getAllManagers();
    }

    @Override
    public List<User> getClientList()
    {
        return adminMapper.getAllClient();

    }
    @Override
    public ResponseVO changeUser(UserVO user){
        int userid=user.getId();
        String email=user.getEmail();
        String password=user.getPassword();
        String username=user.getUserName();
        String phonenumber=user.getPhoneNumber();
        Double credit=user.getCredit();
        String birthday=user.getBirthday();



        adminMapper.updateUser(userid,email,password,username,phonenumber,credit,birthday);
        return ResponseVO.buildSuccess(true);


    }

    @Override
    public ResponseVO addUser(UserVO user) {
        System.out.println("chaomo");

        User user_ = new User();
        user_.setEmail(user.getEmail());
        user_.setPassword(user.getPassword());
        user_.setUserType(user.getUserType());
        user_.setCredit(user.getCredit());
        user_.setUserName(user.getUserName());
        user_.setPhoneNumber(user.getPhoneNumber());
        user_.setHotelid(user.getHotelid());
        try {
            adminMapper.addUser(user_);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO deleteUser(int userid) {
        adminMapper.deleteUser(userid);
        return ResponseVO.buildSuccess(true);
    }

    //测试代码

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






    }


}
