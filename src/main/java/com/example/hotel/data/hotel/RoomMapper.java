package com.example.hotel.data.hotel;

import com.example.hotel.enums.RoomType;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomMapper {

    int insertRoom(HotelRoom hotelRoom);

    List<HotelRoom> selectRoomsByHotelId(@Param("hotelId") Integer hotelId);

    HotelRoom selectRoomById(@Param("id") Integer id);

    int changeRoom(@Param("id") Integer id,@Param("hotelId")Integer hotelId,@Param("breakfast")String breakfast,@Param("total") int total,@Param("price") double price,@Param("peopleNum")int peopleNum);

    int deleteRoom(@Param("id") Integer id);

    HotelRoom findIdByThree(@Param("hotelId")Integer hotelId,@Param("roomType") String roomType);

}
