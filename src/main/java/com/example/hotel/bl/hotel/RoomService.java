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

    /**
     * 改变酒店客房信息
     * @param hotelRoom
     */

    void changeRoom(HotelRoom hotelRoom);

    /**
     * 删除酒店客房信息
     * @param id
     */

    int deleteRoom(Integer id);

    //获得动态酒店房间数目

    int getRoomCurNum(Integer roomId,String start_time,String end_time);

    //得到某酒店的所有房间

    List<HotelRoom> getHotelAllRooms(Integer hotelId);

    //得到酒店在某一时间段内所有可用的房间

    List<HotelRoom> getHotelAllRoomsInDate(Integer hotelId,String start_time,String end_time);
}
