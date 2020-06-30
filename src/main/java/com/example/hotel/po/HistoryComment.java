package com.example.hotel.po;

public class HistoryComment {
    private int id;
    private String userName;
    private int rate;
    private String color;
    private int credit;
    private String content;
    private int userId;
    private int hotelId;
    private int orderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getRate() {
        return rate;
    }

    public String getColor() {
        return color;
    }

    public int getCredit() {
        return credit;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
