package com.example.hotel.vo;

/**
 * @author huwen
 * @date 2019/3/23
 */
public class UserForm {
    /**
     * 用户邮箱，不可重复
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;
    private String userName;
    private int hotelid;


    public String getUserName(){return userName;}
    public void setUserName(String name){this.userName=name;}
    public int getHotelid(){return this.hotelid;}
    public void setHotelid(int hotelid){this.hotelid=hotelid;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
