package com.example.hotel.controller;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.saler.SalerService;
import com.example.hotel.blImpl.coupon.TargetMoneyCouponStrategyImpl;
import com.example.hotel.controller.coupon.CouponController;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.AD;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.Order;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.*;

public class ExampleTest extends HotelApplicationTest{
    @Autowired
    private CouponService service;
    @Autowired
    private CouponMapper couponmapper;
    @Autowired
    private RoomService roomService;
    @Autowired
    private SalerService salerService;
    @Autowired
    private OrderService orderService;



    @Autowired
    private HotelService hotelService;

    @Test
    public void deleteRoomTest() throws Exception {
        //成功删除后会报错java.lang.NullPointerException
        Assert.assertSame(roomService.deleteRoom(2),1);
        Assert.assertSame(roomService.deleteRoom(3),1);
        Assert.assertSame(roomService.deleteRoom(4),1);
        Assert.assertSame(roomService.deleteRoom(6),1);
    }

    //三个有代表性的单元测试方法

    @Test
    public void getFileTest(){
        ArrayList<AD>temp=new ArrayList<>();
        Assert.assertEquals(salerService.getAD(),temp);
    }

    @Test
    public void getUserOrdersTest(){

        Assert.assertEquals(orderService.getUserOrders(5).get(0).getUserId(),new Integer(5));
        Assert.assertEquals(orderService.getUserOrders(5).get(0).getHotelId(),new Integer(3));
        Assert.assertEquals(orderService.getUserOrders(5).get(0).getCheckInDate(),new String("2020-07-01"));
        Assert.assertEquals(orderService.getUserOrders(5).get(0).getCheckOutDate(),new String("2020-08-02"));

        Assert.assertEquals(orderService.getUserOrders(5).get(0).getRoomNum(),new Integer(1));


    }

    @Test
    public void CouponMatchTest()
    {
        OrderVO orderVO=new OrderVO();
        orderVO.setPrice(3000.0);
        Coupon coupon=new Coupon();
        coupon.setTargetMoney(2000.0);
        coupon.setStatus(1);
        coupon.setCouponType(3);
        TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy=new TargetMoneyCouponStrategyImpl();
        Assert.assertEquals(targetMoneyCouponStrategy.isMatch(orderVO,coupon),true);

    }

    //检查retrieveHotelDetails集成测试的桩程序
    public HotelVO retrieveHotelDetails_sub(Integer hotelId) {
        HotelVO hotelVO=new HotelVO();
        hotelVO.setAddress("111");
        hotelVO.setBizRegion("xidan");
        hotelVO.setDescription("shanghai");
        hotelVO.setId(1);
        return hotelVO;
    }



    //驱动检测retrievehotel程序
    @Test
    public void testdrive_retrieveHotelDetails()
    {
        HotelVO hotelVO=hotelService.retrieveHotelDetails(1);
        Assert.assertEquals(hotelVO.getHotelStar(),4);
        Assert.assertEquals(hotelVO.getName(),"汉庭酒店");
    }

    @Test
    public void testdrive_selectcoupon()
    {
        List<Coupon>  couponList=couponmapper.selectByHotelId(2);
        Assert.assertSame(couponList.get(0).getHotelId(),2);
        Assert.assertSame(couponList.get(0).getHotelId(),2);


    }


    @Test
    public void testdrive_order()
    {
        List<Order> orderlist=orderService.getUserOrders(5);
        Assert.assertSame(orderlist.get(0).getHotelId(),3);


    }



}
