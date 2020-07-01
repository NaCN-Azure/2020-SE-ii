package com.example.hotel.bl.hotel;

import com.example.hotel.po.HistoryComment;
import com.example.hotel.po.HotelPic;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelPicVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.HistoryCommentVO;

import java.util.List;

public interface HotelService {

    /**
     * 添加酒店
     * @param hotelVO
     * @throws
     */
    void addHotel(HotelVO hotelVO) throws ServiceException;

    void updateHotelInfo(HotelVO hotelVO);


    /**
     * 预订酒店修改剩余客房信息
     * @param hotelId
     * @param roomType
     * @param rooms
     */
    void updateRoomInfo(Integer hotelId, String roomType,Integer rooms);

    /**
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    List<HotelPicVO> getPiclist();

    HotelPicVO getPicbyid(Integer id);


    /**
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    boolean deleteHotel(Integer hotelId);
    /**
     * 查看酒店剩余某种房间数量
     * @param hotelId
     * @param roomType
     * @return
     */
    int getRoomCurNum(Integer hotelId,String roomType);

    /**
     * 查看酒店的所有订单
     * @param hotelId
     * @return
     */
    List<Order> getHotelOrders(Integer hotelId);

    List<HotelVO> searchHotel(String key);


    List<HotelVO> searchHotelbysection(String address,int[] hotelStar,int[] money,String name,int[] rate );

    void updatePic(Integer id, String url);

    void insertPic(HotelPic hotelPic);



}
