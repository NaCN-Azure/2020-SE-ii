package com.example.hotel.data.hotel;

import com.example.hotel.po.HotelRoomDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomDetailMapper {

    int insertDetailRoom(HotelRoomDetail hotelRoomDetail);

    int deleteDetailRoom(@Param("order_id") Integer order_id);

    List<HotelRoomDetail> getAllDetailRooms(@Param("room_id") Integer room_id);

    int changeDetailRoom(@Param("order_id") Integer order_id,@Param("start_time") String start_time,@Param("end_time") String end_time);

}
