package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.blImpl.order.OrderServiceImpl;
import com.example.hotel.data.hotel.RoomDetailMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.enums.Breakfast;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoomDetail;
import com.example.hotel.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;
import com.example.hotel.vo.OrderVO;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomDetailMapper roomDetailMapper;

    private boolean timeMatch(String start_time,String end_time,String checkin_time,String checkout_time){
        LocalDateTime a,b,c,d;String x=" 00:00:00";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        a=LocalDateTime.parse(start_time+x,df);
        b=LocalDateTime.parse(end_time+x,df);
        c=LocalDateTime.parse(checkin_time+x,df);
        d=LocalDateTime.parse(checkout_time+x,df);
        return (c.isAfter(a)&&c.isBefore(b))||(d.isAfter(a)&&d.isBefore(b));//判断前两者是否与后两者有交集
    }

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
        System.out.println(hotelRoom.getPrice()+" ()()(");
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
    public List<HotelRoom> getHotelAllRooms(Integer hotelId){
        //return roomMapper.selectRoomsByHotelId(hotelId);
        return getHotelAllRoomsInDate(hotelId,"2000-01-01","2030-01-01");
    }

    @Override
    public  List<HotelRoom> getHotelAllRoomsInDate(Integer hotelId,String start_time,String end_time){
        List<HotelRoom> rooms=roomMapper.selectRoomsByHotelId(hotelId);
        for(int i=0;i<rooms.size();i++){
            HotelRoom temp=rooms.get(i);
            rooms.get(i).setCurNum(getRoomCurNum(temp.getId(),start_time,end_time));

        }
        return rooms;
    }

    @Override
    public int getRoomCurNum(Integer roomId,String start_time,String end_time){
        List<HotelRoomDetail> detailrooms = roomDetailMapper.getAllDetailRooms(roomId);
        System.out.println("test: ");
        int total=roomMapper.selectRoomById(roomId).getTotal();
        int result=total;
        for(int i=0;i<detailrooms.size();i++){
            HotelRoomDetail tempDetail=detailrooms.get(i);
            if(timeMatch(start_time,end_time,tempDetail.getStart_time(),tempDetail.getEnd_time())){
                System.out.println(start_time+" "+end_time);
                System.out.println(tempDetail.getStart_time()+" "+tempDetail.getEnd_time());
                result--;
            }
        }
        System.out.println("总共："+total);
        System.out.println("还剩：" +result);
        if(result<0){result=0;}
        return result;
    }//动态房间数目的核心方法，用检索roomdetaiilmapper的方式找出可用房间数目

    //获得动态房间数目的桩程序

    public int getRoomCurNum_sub(Integer hotelId, String roomType) {
        return 1;
    }


}
