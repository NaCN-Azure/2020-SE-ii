package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.saler.SalerService;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.SalerCoupon;
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
    private final  TargetRoomNumberCouponStrategylmpl targetRoomNumberCouponStrategy;
    private final CouponMapper couponMapper;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    SalerService salerService;

    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy,TargetRoomNumberCouponStrategylmpl targetRoomNumberCouponStrategy,
                             CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.targetRoomNumberCouponStrategy=targetRoomNumberCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
        strategyList.add(targetRoomNumberCouponStrategy);
    }



    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());

        List<SalerCoupon> webCoupons=salerService.getWebAllCoupon();

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (int i = 0; i < hotelCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, hotelCoupons.get(i))) {
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }

        for (int i = 0; i < webCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                SalerCoupon temp=webCoupons.get(i);
                Coupon checkcoupom=new Coupon();
                    checkcoupom.setCouponName(temp.getCouponName());
                    checkcoupom.setCouponType(temp.getCouponType());
                    checkcoupom.setStatus(temp.getStatus());
                    checkcoupom.setDescription(temp.getDescription());
                    checkcoupom.setTargetMoney(temp.getTargetMoney());
                    checkcoupom.setDiscount(temp.getDiscount());
                    checkcoupom.setStartTime(temp.getStartTime());
                    checkcoupom.setEndTime(temp.getEndTime());
                    checkcoupom.setDiscountMoney(temp.getDiscountMoney());
                    checkcoupom.setId(temp.getId());
                    checkcoupom.setHotelId(temp.getHotelId());
                if (strategy.isMatch(orderVO, checkcoupom)) {
                    availAbleCoupons.add(checkcoupom);
                }
            }
        }

        System.out.println("+++++");
        System.out.println(availAbleCoupons);
        System.out.println("+++++");
        return availAbleCoupons;
    }
    //找到订单满足的所有优惠策略

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
//        System.out.println(hotelCoupons);
        return hotelCoupons;
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(CouponVO couponVO) {
        System.out.println(couponVO.getEndTime());
        System.out.println(couponVO.getCouponName());
        System.out.println(couponVO.getCouponType());
        //首先把VO对象转换成插入到数据库中的PO对象
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getCouponName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getCouponType());
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
        System.out.println("114514 1919810");
        System.out.println(couponVO.getId());

        couponMapper.updateCoupon(couponVO.getId(),couponVO.getDescription(),couponVO.getCouponName(),couponVO.getCouponType(),
                couponVO.getStatus(),couponVO.getStartTime(),couponVO.getEndTime());
    }

    @Override
    public void deleteCoupon(Integer id){
        couponMapper.deleteCoupon(id);
    }


    @Override
    public List<Coupon> getMatchHotelCoupon(int userid, int hotelid,double orderprice,int roomNum,String checkIn, String checkOut){
        List<Coupon> hotelCoupons = getHotelAllCoupon(hotelid);

        List<Coupon> availAbleCoupons = new ArrayList<>();

        List<SalerCoupon> webCoupons=salerService.getWebAllCoupon();

        User user=accountMapper.getAccountById(userid);

        String birthday=user.getBirthday().substring(5,10);

        LocalDateTime nowTime= LocalDateTime.now();

        String ldt=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        for (int i = 0; i < webCoupons.size(); i++) {
                SalerCoupon temp=webCoupons.get(i);
                Coupon checkcoupom=new Coupon();
                checkcoupom.setCouponName(temp.getCouponName());
                checkcoupom.setCouponType(temp.getCouponType());
                checkcoupom.setStatus(temp.getStatus());
                checkcoupom.setDescription(temp.getDescription());
                checkcoupom.setTargetMoney(temp.getTargetMoney());
                checkcoupom.setDiscount(temp.getDiscount());
                checkcoupom.setStartTime(temp.getStartTime());
                checkcoupom.setEndTime(temp.getEndTime());
                checkcoupom.setDiscountMoney(temp.getDiscountMoney());
                checkcoupom.setId(temp.getId());
                checkcoupom.setHotelId(temp.getHotelId());
            if(checkcoupom.getCouponType()==1){
                hotelCoupons.add(checkcoupom);
            }
        }

        for (int i = 0; i < hotelCoupons.size(); i++) {
            if(hotelCoupons.get(i).getCouponType()==1){
                if( ldt.substring(5,10).equals(birthday)) {
                    availAbleCoupons.add(hotelCoupons.get(i));

                }
                else
                {
                    hotelCoupons.get(i).setDiscount(0);
                    hotelCoupons.get(i).setDiscountMoney(0);
                    hotelCoupons.get(i).setDescription(hotelCoupons.get(i).getDescription()+"未到生日");
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }
        return availAbleCoupons;

    }

}
