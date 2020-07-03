package com.example.hotel.data.coupon;

import com.example.hotel.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

import java.util.List;

@Mapper
@Repository
public interface CouponMapper {
    int insertCoupon(Coupon coupon);

    List<Coupon> selectByHotelId(Integer hotelId);

    int deleteCoupon(Integer id);

    int updateCoupon(@Param("id") Integer id,@Param("description") String description,@Param("couponName") String couponName,
                     @Param("couponType") Integer couponType,@Param("status") Integer status,
                     @Param("start_time") String start_time,@Param("end_time") String end_time,
    @Param("discount_money") Double discount_money,@Param("discount") Double discount,
                     @Param("target_money") Double target_money);
}
