package com.example.hotel.vo;

public class typeOrderVO {
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
