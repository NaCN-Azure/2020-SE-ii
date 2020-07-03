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
     * 查看酒店的所有订单
     * @param hotelId
     * @return
     */
    List<Order> getHotelOrders(Integer hotelId);

    List<HotelVO> searchHotel(String key);

    /**
     * 根据区间信息搜索所有符合要求的酒店
     * @param address,hotelStar,money,name,rate
     * @return
     */


    List<HotelVO> searchHotelbysection(String address,int[] hotelStar,int[] money,String name,int[] rate );

    /**
     * 更新酒店图片
     * @param id,url
     * @return
     */

    void updatePic(Integer id, String url);

    /**
     * 更新酒店图片
     * @param hotelPic
     * @return
     */

    void insertPic(HotelPic hotelPic);



}
