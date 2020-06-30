package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种限时优惠策略
     * @param orderVO
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String nowtime = dateFormat.format( now );
        LocalDateTime ldt = LocalDateTime.parse(nowtime,df);
        if (coupon.getCouponType() == 4
                && ldt.isAfter(LocalDateTime.parse(coupon.getStartTime()+" 00:00:00",df))
                && ldt.isBefore(LocalDateTime.parse(coupon.getEndTime()+" 00:00:00",df))
                && coupon.getStatus()==1)
        {
            return true;
        }
        return false;
    }
}
