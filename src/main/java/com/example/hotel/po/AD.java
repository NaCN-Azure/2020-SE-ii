package com.example.hotel.po;

public class AD {
    private Integer id;
    private String uid;
    private String name;
    private String status;
    private String url;
    private String thumbUrl;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getStatus() {
        return status;
    }
}
