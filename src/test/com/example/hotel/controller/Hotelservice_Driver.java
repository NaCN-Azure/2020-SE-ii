package com.example.hotel.controller;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.blImpl.hotel.HotelServiceImpl;
import com.example.hotel.vo.HotelVO;

public class Hotelservice_Driver {
    public void drive(HotelService hotelService)
    {
        hotelService.retrieveHotels();

    }


    public static void main(String[] args)
        {


        }


}
