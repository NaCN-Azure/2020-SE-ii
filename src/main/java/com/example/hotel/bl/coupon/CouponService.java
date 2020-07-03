package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.OrderVO;

import java.util.List;

public interface CouponService {
    /**
     * 返回某一订单可用的优惠策略列表
     * @param orderVO
     * @return
     */
    List<Coupon> getMatchOrderCoupon(OrderVO orderVO);

    /**
     * 返回对应酒店的优惠策略列表
     * @param
     * @return
     */

    List<Coupon> getMatchHotelCoupon(int userid, int hotelid,double orderprice,int roomNum,String checkIn, String checkOut);


    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId
     * @return
     */
    List<Coupon> getHotelAllCoupon(Integer hotelId);

    /**
     * 添加酒店满减优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelTargetMoneyCoupon(CouponVO couponVO);

    /**
     * 更新酒店满减优惠策略
     * @param couponVO
     * @return
     */

    void updateCouponInfo(CouponVO couponVO);


    /**
     * 删除酒店满减优惠策略
     * @param id
     * @return
     */

    void deleteCoupon(Integer id);
}
