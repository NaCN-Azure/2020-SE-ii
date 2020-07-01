package com.example.hotel.vo;

public class HotelSearchVO {
    private String address;
    private int[] hotelStar;
    private int[] money;
    private String name;
    private int[] rate;

    public int[] getRate() {
        return rate;
    }

    public void setRate(int[] rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public int[] getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(int[] hotelStar) {
        this.hotelStar = hotelStar;
    }

    public int[] getMoney() {
        return money;
    }

    public void setMoney(int[] money) {
        this.money = money;
    }



}
