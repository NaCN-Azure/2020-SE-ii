package com.example.hotel.po;

public class HotelPic {
    private Integer id;
    private String Url;

    public void setUrl(String url) {
        Url = url;
    }

    public String getUrl() {
        return Url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
