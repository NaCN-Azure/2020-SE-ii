package com.example.hotel.blImpl.saler;

import com.example.hotel.bl.saler.SalerCouponMatchStrategy;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.SalerCoupon;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;

public class BirthdaySalerCouponImpl implements SalerCouponMatchStrategy {

    /**
     * 判断某个订单是否满足某种生日优惠策略
     * @param orderVO
     * @param salercoupon
     * @return
     */

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public boolean isMatch(OrderVO orderVO, SalerCoupon salercoupon) {
        int id=orderVO.getUserId();
        User user=accountMapper.getAccountById(id);

        if (salercoupon.getCouponType() == 1
                
                && salercoupon.getStatus()==1)
        {
            return true;
        }

        return false;
    }

}
