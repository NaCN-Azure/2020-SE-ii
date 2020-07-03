package com.example.hotel.data.order;

import com.example.hotel.po.HistoryComment;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface OrderMapper {

    int addOrder(Order order);

    List<Order> getAllOrders();

    List<Order> getUserOrders(@Param("userid") int userid);

    int annulOrder(@Param("orderid") int orderid);

    Order getOrderById(@Param("orderid") int orderid);

    int deleteOrder(@Param("orderid") int orderid);

    int confirmOrder(@Param("orderid") int orderid);

    List<Order> getHotelOrders(@Param("hotelid") int hotelid);

    List<Order> getHistoryOrders(@Param("userId") Integer userId,@Param("hotelId") Integer hotelId);

    int changeOrder(@Param("id") Integer id,@Param("checkInDate") String checkInDate,
                    @Param("checkOutDate") String checkOutDate,@Param("phoneNumber") String phoneNumber,
                    @Param("peopleNum") Integer peopleNum,@Param("haveChild") Integer haveChild,
                    @Param("price") Double price,@Param("roomNum") Integer roomNum);

    Order getId(@Param("userId") Integer userId,@Param("price")Double price);

}
