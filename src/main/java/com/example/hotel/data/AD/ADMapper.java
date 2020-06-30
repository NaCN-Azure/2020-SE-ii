package com.example.hotel.data.AD;

import com.example.hotel.po.AD;
import com.example.hotel.po.SalerCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ADMapper {
    List<AD> getADs();

    int insertAD(AD ad);
}
