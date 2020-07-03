package com.example.hotel.controller.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/hotelTargetMoney")
    public ResponseVO addHotelTargetMoneyCoupon(@RequestBody CouponVO CouponVO) {
        CouponVO couponVO = couponService.addHotelTargetMoneyCoupon(CouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }

    @GetMapping("/hotelAllCoupons")
    public ResponseVO getHotelAllCoupons(@RequestParam Integer hotelId) {
        System.out.println(couponService.getHotelAllCoupon(hotelId));
        return ResponseVO.buildSuccess(couponService.getHotelAllCoupon(hotelId));
    }

    @GetMapping("/orderMatchCoupons")
    public ResponseVO getOrderMatchCoupons(@RequestParam Integer userId,
                                           @RequestParam Integer hotelId,
                                           @RequestParam Double orderPrice,
                                           @RequestParam Integer roomNum,
                                           @RequestParam String checkIn,
                                           @RequestParam String checkOut) {
        System.out.println("mapp");
        OrderVO requestOrderVO = new OrderVO();
        System.out.println(userId);
        System.out.println(hotelId);

        requestOrderVO.setUserId(userId);
        requestOrderVO.setHotelId(hotelId);
        requestOrderVO.setPrice(orderPrice);
        requestOrderVO.setRoomNum(roomNum);
        System.out.println("2");
        requestOrderVO.setCheckInDate(checkIn);
        System.out.println("3");
        requestOrderVO.setCheckOutDate(checkOut);
        List<Coupon> oneList = couponService.getMatchHotelCoupon(userId,hotelId, orderPrice,roomNum,checkIn, checkOut);
        List<Coupon> otherList=couponService.getMatchOrderCoupon(requestOrderVO);
        for (int i = 0; i < otherList.size(); i++) {
            oneList.add(otherList.get(i));
        }
        System.out.println(oneList);
        System.out.println(otherList);
        return ResponseVO.buildSuccess(oneList);
    }

    @PostMapping("/updateCouponInfo")
    public ResponseVO updateCouponInfo(@RequestBody CouponVO couponVO){

        System.out.println("1");
        System.out.println(couponVO.getDescription());
        couponService.updateCouponInfo(couponVO);
        System.out.println(5);
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/deleteCoupon")
    public ResponseVO deleteCoupon(@RequestBody Integer id){
        couponService.deleteCoupon(id);
        return ResponseVO.buildSuccess(true);
    }
}
