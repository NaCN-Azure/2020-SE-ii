package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.HistoryCommentVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获得指定用户的所有订单信息
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);

    /**
     * 撤销订单
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 更改订单
     * @param orderVO
     * @return
     */
    ResponseVO changeOrder(OrderVO orderVO);

    /**
     * 删除
     * @param orderid
     * @return
     */

    ResponseVO deleteOrder(int orderid);

    ResponseVO confirmOrder(int orderid);

    List<Order> getHotelOrders(Integer hotelid);

    ResponseVO addComment(HistoryCommentVO historyCommentVO);

}
