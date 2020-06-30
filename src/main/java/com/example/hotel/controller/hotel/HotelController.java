package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.po.HistoryComment;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelPicVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.HistoryCommentVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;

    @GetMapping("allUrls")
    public ResponseVO allUrls(){
        return ResponseVO.buildSuccess(hotelService.getPiclist());
    }

    @GetMapping("/{hotelId}/hotelPic")
    public ResponseVO getPic(@PathVariable Integer hotelId){
        return ResponseVO.buildSuccess(hotelService.getPicbyid(hotelId));
    }

    @PostMapping("/update")
    public ResponseVO updateHotelInfo(@RequestBody HotelVO hotelVO){
        System.out.println("I am here!");
        hotelService.updateHotelInfo(hotelVO);
        return ResponseVO.buildSuccess(true);
    }
    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) throws ServiceException {
        hotelService.addHotel(hotelVO);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/all")
    public ResponseVO retrieveAllHotels(){
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        roomService.insertRoomInfo(hotelRoom);
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/changeRoomAPI")
    public ResponseVO changeRoomInfo(@RequestBody HotelRoom hotelRoom) {
        roomService.changeRoom(hotelRoom);
        return ResponseVO.buildSuccess(true);
    }
    @PostMapping("/updateUrl")
    public ResponseVO updateHotelUrl(@RequestBody HotelPicVO hotelPicVO) {
        hotelService.updatePic(hotelPicVO.getId(),hotelPicVO.getUrl());
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/deleteRoomAPI")
    public ResponseVO deleteRoomInfo(@RequestParam Integer id) {
        return ResponseVO.buildSuccess(roomService.deleteRoom(id));
    }

    @GetMapping("/hotelAllRooms")
    public ResponseVO getHotelAllRooms(@RequestParam Integer hotelId) {
        return ResponseVO.buildSuccess(roomService.getHotelAllRooms(hotelId));
    }

    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }
    @GetMapping("/{hotelId}/deleteHotel")
    public ResponseVO deleteHotel(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.deleteHotel(hotelId));
    }

    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.getHotelOrders(hotelId));
    }

    @GetMapping("/searchHotel")
    public ResponseVO searchHotel(@RequestParam String key){
        return ResponseVO.buildSuccess(hotelService.searchHotel(key));
    }



}
