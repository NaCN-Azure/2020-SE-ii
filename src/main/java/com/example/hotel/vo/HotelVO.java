package com.example.hotel.vo;

import java.util.List;

public class HotelVO {
    private Integer id;
    private String name;
    private String address;
    private String bizRegion;
    private int hotelStar;
    private Double rate;
    private String description;
    private String phoneNum;
    private Integer managerId;
    private List<HistoryCommentVO> historyCommentVOs;

    private List<RoomVO> rooms;

    public List<HistoryCommentVO> getHistoryCommentVOs() {
        return historyCommentVOs;
    }

    public void setHistoryCommentVOs(List<HistoryCommentVO> historyCommentVOs) {
        this.historyCommentVOs = historyCommentVOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBizRegion() {
        return bizRegion;
    }

    public void setBizRegion(String bizRegion) {
        this.bizRegion = bizRegion;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(int hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<RoomVO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomVO> rooms) {
        this.rooms = rooms;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }



}
