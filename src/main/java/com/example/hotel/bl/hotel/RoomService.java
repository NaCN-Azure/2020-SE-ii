package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;

import java.util.List;

public interface RoomService {

    /**
     * 获取某个酒店的全部房间信息
     * @param hotelId
     * @return
     */
    List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId);

    /**
     * 添加酒店客房信息
     * @param hotelRoom
     */
    void insertRoomInfo(HotelRoom hotelRoom);

    void changeRoom(HotelRoom hotelRoom);

    int deleteRoom(Integer id);

    int getRoomCurNum(Integer roomId,String start_time,String end_time);

    List<HotelRoom> getHotelAllRooms(Integer hotelId);

    List<HotelRoom> getHotelAllRoomsInDate(Integer hotelId,String start_time,String end_time);
}
