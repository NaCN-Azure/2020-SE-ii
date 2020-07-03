package com.example.hotel.controller;

import com.example.hotel.controller.hotel.HotelController;
import com.example.hotel.controller.order.OrderController;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;

import java.util.ArrayList;
import java.util.List;

public class Driver_test_Order {


    public static void main(String[] args){

        OrderVO orderVO=new OrderVO();
        orderVO.setUserId(3);
        orderVO.setHotelName("dayue");
        orderVO.setHaveChild(true);
        orderVO.setCreateDate("2020-06-14");
        orderVO.setPrice(200.0);

        OrderController ordercontroller=new OrderController();
        ordercontroller.reserveHotel(orderVO);


    }





}
