package com.example.hotel.vo;

public class HotelRoomDetailVO {
    private Integer id;
    private Integer room_id;
    private Integer can_use;
    private String start_time;
    private String end_time;
    private Integer order_id;

    public Integer getCan_use() {
        return can_use;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setCan_use(Integer can_use) {
        this.can_use = can_use;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
}
