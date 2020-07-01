package com.example.hotel.blImpl.coupon;


import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;

import java.lang.reflect.ParameterizedType;



public class CouponismatchTester {

    public boolean isMatch(OrderVO orderVO, Coupon coupon) {

        if (coupon.getCouponType() == 3
                && orderVO.getPrice() >= coupon.getTargetMoney()
                && coupon.getStatus()==1)
        {
            return true;
        }

        return false;
    }


    public static void main(String[] args)
    {
        OrderVO orderVO=new OrderVO();
        orderVO.setPrice(3000.0);
        Coupon coupon=new Coupon();
        coupon.setTargetMoney(2000.0);
        coupon.setStatus(1);
        coupon.setCouponType(3);
        TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy=new TargetMoneyCouponStrategyImpl();
        System.out.println(targetMoneyCouponStrategy.isMatch(orderVO,coupon));
    }
//单元测试代码，验证订单与优惠策略的匹配结果是否正确


}
