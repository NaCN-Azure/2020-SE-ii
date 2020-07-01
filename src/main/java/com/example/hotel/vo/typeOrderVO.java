package com.example.hotel.vo;

public class typeOrderVO {
    //updatetype决定对订单执行何种类型的操作
    public String updateType;

    private OrderVO  order;

    public OrderVO getOrder() {
        return order;
    }

    public void setOrder(OrderVO order) {
        this.order = order;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

}
