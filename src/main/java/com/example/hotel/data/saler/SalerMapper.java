package com.example.hotel.data.saler;



import com.example.hotel.po.*;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.SalerCouponVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface SalerMapper {



    List<SalerCoupon> getWebCoupons();

    int insertSalerCoupon(SalerCoupon salercoupon);



    int updateSalerCoupon(@Param("id") Integer id, @Param("description") String description, @Param("couponName") String couponName,
                     @Param("couponType") Integer couponType, @Param("status") Integer status,
                     @Param("start_time") String start_time, @Param("end_time") String end_time,
    @Param("discount_money") double discount_money,@Param("target_money") double target_money);


    int deleteWebCoupon(@Param("id") Integer id);

    SalerCouponVO getWebCouponByID(@Param("id") Integer id);




}
