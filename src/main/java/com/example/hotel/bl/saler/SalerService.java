package com.example.hotel.bl.saler;

import com.example.hotel.po.AD;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.SalerCoupon;
import com.example.hotel.vo.ADVO;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.SalerCouponVO;

import java.util.List;

public interface SalerService {

     List<SalerCoupon> getWebAllCoupon();

     List<AD> getAD();

     SalerCouponVO addWebCoupon(SalerCouponVO salercouponVO);

     ADVO addAD(ADVO adVO);

     void updateSalerCouponInfo(SalerCouponVO salercouponVO);

     void deleteWebCoupon(Integer webCouponId);

     SalerCouponVO getWebCouponById(Integer webCouponId);

}
