package com.example.hotel.enums;

public enum OrderStates {
    UnfinishedOrder("未完成"),
    finishedOrder("已完成"),
    CancelledOrder("已撤销"),
    AbnormalOrder("异常");

    private String value;

    OrderStates(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
