package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.enums.Breakfast;
import com.example.hotel.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId) {
        return roomMapper.selectRoomsByHotelId(hotelId);
    }

    @Override
    public void insertRoomInfo(HotelRoom hotelRoom) {
        System.out.println(hotelRoom.getRoomType());
        roomMapper.insertRoom(hotelRoom);
    }

    @Override
    public void changeRoom(HotelRoom hotelRoom){
        int hotelId=hotelRoom.getHotelId();
        String breakfast=hotelRoom.getBreakfast().toString();
        int peopleNum=hotelRoom.getPeopleNum();
        double price=hotelRoom.getPrice();
        int total=hotelRoom.getTotal();
        int id=hotelRoom.getId();
        roomMapper.changeRoom(id,hotelId,breakfast,total,price,peopleNum);
    }

    @Override
    public int deleteRoom(Integer id){
        HotelRoom room=roomMapper.selectRoomById(id);
        roomMapper.deleteRoom(id);
        return room.getHotelId();
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomMapper.updateRoomInfo(hotelId,roomType,rooms);
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomMapper.getRoomCurNum(hotelId,roomType);
    }

    @Override
    public List<HotelRoom> getHotelAllRooms(Integer hotelId){return roomMapper.selectRoomsByHotelId(hotelId);}

}
