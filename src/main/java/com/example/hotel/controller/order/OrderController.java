package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.po.HistoryComment;
import com.example.hotel.vo.HistoryCommentVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.typeOrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.lang.*;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */


@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){
        System.out.println(orderVO.getCheckInDate());
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{hotelid}/getHotelorders")
    public ResponseVO getHotelOrders(@PathVariable int hotelid){
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelid));};

    @GetMapping("/{userid}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable int userid){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }
    @GetMapping("getHistoryOrders")
    public  ResponseVO retrieveHistoryOrders(@RequestParam Integer userId,
                                          @RequestParam Integer hotelId){
        return ResponseVO.buildSuccess(orderService.getHistoryOrders(userId,hotelId));
    }
    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid){
        return orderService.annulOrder(orderid);
    }

    @PostMapping("/updateOrdersAPI")
    public ResponseVO updateOrdersAPI(@RequestBody typeOrderVO typeorder){
        if (typeorder.getUpdateType().equals("change")) {

            return orderService.changeOrder(typeorder.getOrder());
        } else if (typeorder.getUpdateType().equals("delete")) {
            return orderService.deleteOrder(typeorder.getOrder().getId());
        } else {
            return orderService.confirmOrder(typeorder.getOrder().getId());
        }

    }

    @PostMapping("/addComment")
    public ResponseVO writeHotelComment(@RequestBody HistoryCommentVO historyCommentVO){
        System.out.println("chaomo1");
        return orderService.addComment(historyCommentVO);
    }
}
