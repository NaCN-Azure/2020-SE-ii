package com.example.hotel.blImpl.saler;

import com.example.hotel.bl.saler.SalerCouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.SalerCoupon;
import com.example.hotel.vo.OrderVO;

public class TargetMoneySalerCouponStrategyImpl implements SalerCouponMatchStrategy {
    /**
     * 判断某个订单是否满足某种满减金额优惠策略
     * @param orderVO
     * @param salercoupon
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, SalerCoupon salercoupon) {

        if (salercoupon.getCouponType() == 3
                && orderVO.getPrice() >= salercoupon.getTargetMoney()
                && salercoupon.getStatus()==1)
        {
            return true;
        }

        return false;
    }


}
