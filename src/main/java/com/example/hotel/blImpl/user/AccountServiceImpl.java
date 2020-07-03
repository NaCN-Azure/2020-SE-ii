package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {

        /*if (userVO==null) {
            System.out.println("Failed input");
            return ResponseVO.buildSuccess();
        }防御式编程*/

        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        User temp=accountMapper.getAccountByName(userVO.getEmail());//属性复制
        if(temp!=null){
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        try {
            user.setHotelid(-1);
            user.setUserName(userVO.getUserName());
            user.setUserType(UserType.Client);
            accountMapper.createNewAccount(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }


    @Override
    public ResponseVO registervip (UserVO userVO)
    {
        /*if (userVO==null) {
            System.out.println("Failed input");
            return ResponseVO.buildSuccess();
        }防御式编程*/

        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        user.setCorporationName(userVO.getCorporationName());
        user.setIsVIP(userVO.getIsVIP());
        int userid=user.getId();
        accountMapper.updateVIPII(user);
        return ResponseVO.buildSuccess();

    }

    @Override
    public User login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getEmail());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserInfo(int id) {
        User user = accountMapper.getAccountById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public ResponseVO updateUserInfo(int id, String password, String username, String phonenumber,String birthday) {
        try {
            accountMapper.updateAccount(id, password, username, phonenumber,birthday);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO addCredit(UserVO userVO)
    {
        /*if (userVO==null) {
            System.out.println("Failed input");
            return ResponseVO.buildSuccess();
        }防御式编程*/


        int id=userVO.getId();
        double credit=userVO.getCredit();
        accountMapper.addCredit(id,credit);
        System.out.println("chaomo3");


        return ResponseVO.buildSuccess(true);



    }

}
