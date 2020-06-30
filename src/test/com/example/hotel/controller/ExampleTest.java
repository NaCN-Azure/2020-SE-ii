            package com.example.hotel.controller;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.saler.SalerService;
import com.example.hotel.controller.coupon.CouponController;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.AD;
import com.example.hotel.po.Coupon;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.*;

public class ExampleTest extends HotelApplicationTest{
    @Autowired
    private CouponService service;
    @Autowired
    private CouponMapper mapper;
    @Autowired
    private RoomService roomService;
    @Autowired
    private SalerService salerService;
    @Autowired
    private OrderService orderService;

    @Test
    public void deleteRoomTest() throws Exception {
        //成功删除后会报错java.lang.NullPointerException
        Assert.assertSame(roomService.deleteRoom(2),1);
        Assert.assertSame(roomService.deleteRoom(3),1);
        Assert.assertSame(roomService.deleteRoom(4),1);
        Assert.assertSame(roomService.deleteRoom(6),1);
    }

    @Test
    public void getFileTest(){
        ArrayList<AD>temp=new ArrayList<>();
        Assert.assertEquals(salerService.getAD(),temp);
    }

    @Test
    public void getUserOrdersTest(){
        ArrayList<AD>temp=new ArrayList<>();
        Assert.assertEquals(orderService.getUserOrders(1),temp);
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getUserId(),new Integer(1));
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getHotelId(),new Integer(1));
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getCheckInDate(),new String("2020-06-30"));
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getCheckOutDate(),new String("2020-07-26"));
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getCheckOutDate(),new String("2020-07-26"));
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getRoomNum(),new Integer(2));
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getRoomType(),new String("BidBed"));
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getPeopleNum(),new Integer(2));
        Assert.assertTrue(orderService.getUserOrders(1).get(0).isHaveChild());
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getPrice(),new Double(486.5));
        Assert.assertEquals(orderService.getUserOrders(1).get(0).getPhoneNumber(),new String("133888555444"));

    }
}
