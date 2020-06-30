package com.example.hotel.data.Pic;



import com.example.hotel.po.HotelPic;
import com.example.hotel.vo.HotelPicVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PicMapper {
    int updatePic(@Param("id") int id,@Param("url") String url);

    HotelPicVO gethotelurlById(@Param("id") Integer id);

    List<HotelPicVO>  getHotelurlList();

    int insertPic(HotelPic hotelPic);




}
