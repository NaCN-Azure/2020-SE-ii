package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;

import com.example.hotel.vo.OrderVO;

public interface CouponMatchStrategy {


    //判断订单满足的优惠券

    boolean isMatch(OrderVO orderVO, Coupon coupon);

}
