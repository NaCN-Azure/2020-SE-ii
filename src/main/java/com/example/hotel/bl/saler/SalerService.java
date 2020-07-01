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

     /**
      * 得到所有的网站优惠券
      * @return
      */



     List<SalerCoupon> getWebAllCoupon();

     /**
      * 得到所有的广告
      * @return
      */


     List<AD> getAD();

     /**
      * 增加一条网站优惠策略
      * @param salercouponVO
      * @return
      */

     SalerCouponVO addWebCoupon(SalerCouponVO salercouponVO);

     /**
      * 增加一条广告
      * @param adVO
      * @return
      */

     ADVO addAD(ADVO adVO);

     /**
      * 更新网站营销优惠券信息
      * @param salercouponVO
      * @return
      */

     void updateSalerCouponInfo(SalerCouponVO salercouponVO);

     /**
      * 删除网站优惠券
      * @param webCouponId
      * @return
      */

     void deleteWebCoupon(Integer webCouponId);

     /**
      * 得到网站优惠券
      * @param webCouponId
      * @return
      */

     SalerCouponVO getWebCouponById(Integer webCouponId);

}
