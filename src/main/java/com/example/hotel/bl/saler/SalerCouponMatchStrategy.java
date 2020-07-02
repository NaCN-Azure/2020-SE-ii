package com.example.hotel.bl.saler;

import com.example.hotel.po.Coupon;
import com.example.hotel.po.SalerCoupon;
import com.example.hotel.vo.OrderVO;

public interface SalerCouponMatchStrategy {

    boolean isMatch(OrderVO orderVO, SalerCoupon salercoupon);

}
