package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CouponServiceImpl implements CouponService {


    private final  TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;

    private final  TimeCouponStrategyImpl timeCouponStrategy;
    private final CouponMapper couponMapper;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();

    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy,
                             CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
    }



    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (int i = 0; i < hotelCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, hotelCoupons.get(i))) {
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }

        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
        System.out.println(hotelCoupons);
        return hotelCoupons;
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetMoney(couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setEndTime(couponVO.getEndTime());
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public void updateCouponInfo(CouponVO couponVO){
        System.out.println("114514 1919810");
        Coupon coupon=new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setEndTime(couponVO.getEndTime());
        coupon.setStatus(couponVO.getStatus());
        coupon.setId(couponVO.getId());
        couponMapper.updateCoupon(coupon.getId(),coupon.getDescription(),coupon.getCouponName(),coupon.getCouponType(),
                   coupon.getStatus(),coupon.getStartTime(),coupon.getEndTime());
    }

    @Override
    public void deleteCoupon(Integer id){
        couponMapper.deleteCoupon(id);
    }
}
