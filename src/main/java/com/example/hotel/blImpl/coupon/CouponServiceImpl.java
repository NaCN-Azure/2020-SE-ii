package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.User;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CouponServiceImpl implements CouponService {


    private final  TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;

    private final  TimeCouponStrategyImpl timeCouponStrategy;
    private final CouponMapper couponMapper;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();
    @Autowired
    AccountMapper accountMapper;

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
    //找到订单满足的所有优惠策略

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
        System.out.println(hotelCoupons);
        return hotelCoupons;
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        //首先把VO对象转换成插入到数据库中的PO对象
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
        System.out.println("114514 1919810");//判断前后端接口是否顺利接上的测试代码
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

    @Override
    public List<Coupon> getMatchHotelCoupon(int userid, int hotelid,double orderprice,int roomNum,String checkIn, String checkOut){
        List<Coupon> hotelCoupons = getHotelAllCoupon(hotelid);

        List<Coupon> availAbleCoupons = new ArrayList<>();

        User user=accountMapper.getAccountById(userid);

        String birthday=user.getBirthday();

        LocalDateTime nowTime= LocalDateTime.now();

        String ldt=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        for (int i = 0; i < hotelCoupons.size(); i++) {
            if(hotelCoupons.get(i).getCouponType()==1){
                if( ldt.substring(0,10).equals(birthday)) {

                }
                else
                {
                    hotelCoupons.get(i).setDiscount(0);
                    hotelCoupons.get(i).setDiscountMoney(0);
                    hotelCoupons.get(i).setDescription(hotelCoupons.get(i).getDescription()+"未到生日");
                }
            }}
        return hotelCoupons;








    }

}
