package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HotelMapper {

    int updateHotelInfo(@Param("id") int id, @Param("hotelName") String hotelName, @Param("hotelStar")int hotelStar,@Param("address")String address,@Param("hotelDescription")String hotelDescription);

    int insertHotel(Hotel hotel);

    List<HotelVO> selectAllHotel();

    HotelVO selectById(@Param("id") Integer id);

    boolean deleteHotel(@Param("id") Integer id);


}
