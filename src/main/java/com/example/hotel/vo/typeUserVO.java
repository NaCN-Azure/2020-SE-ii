package com.example.hotel.vo;

public class typeUserVO {

    //updateyType决定对订单执行何种类型的操作
    public String updateType;
    private UserVO  user;

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }


}
