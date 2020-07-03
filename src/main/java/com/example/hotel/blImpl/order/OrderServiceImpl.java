package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.CommentMapper;
import com.example.hotel.data.hotel.RoomDetailMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.OrderStates;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HistoryComment;
import com.example.hotel.po.HotelRoomDetail;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.HistoryCommentVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.time.format.*;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    private final static String PunishedCredit="信用不足，无法预订";
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    RoomService roomService;
    @Autowired
    AccountService accountService;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    RoomDetailMapper roomDetailMapper;
    @Autowired
    RoomMapper roomMapper;

    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        int reserveRoomNum = orderVO.getRoomNum();
        int curNum = roomService.getRoomCurNum(orderVO.getRoomId(),orderVO.getCheckInDate(),orderVO.getCheckOutDate());
        if(reserveRoomNum>curNum){
            return ResponseVO.buildFailure(ROOMNUM_LACK);
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);
            orderVO.setCreateDate(curdate);
            orderVO.setOrderState("已预订");
            User user = accountService.getUserInfo(orderVO.getUserId());
            if(user.getCredit()<=0){
                return ResponseVO.buildFailure(PunishedCredit);
            }
            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO,order);
            orderMapper.addOrder(order);
            accountMapper.plusCredit(user.getId(),2.0);
            for(int i=0;i<reserveRoomNum;i++) {
                HotelRoomDetail hotelRoomDetail = new HotelRoomDetail();
                hotelRoomDetail.setCan_use(0);
                hotelRoomDetail.setStart_time(order.getCheckInDate());
                hotelRoomDetail.setEnd_time(order.getCheckOutDate());
                hotelRoomDetail.setOrder_id(order.getId());
                hotelRoomDetail.setRoom_id(order.getRoomId());
                roomDetailMapper.insertDetailRoom(hotelRoomDetail);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
       return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }
    //集成测试用来检查获取所有订单列表的桩程序

    public List<Order> getAllOrders_sub() {
        List<Order> res=new ArrayList<>();
        Order order=new Order();
        order.setUserId(3);
        order.setHotelName("dayue");
        order.setHaveChild(true);
        order.setCreateDate("2020-06-14");
        order.setPrice(200.0);
        res.add(order);
        return res;
    }


    @Override
    public List<Order> getUserOrders(int userid) {
        return orderMapper.getUserOrders(userid);
    }

    @Override
    public List<Order> getHotelOrders(Integer hotelid){
        return orderMapper.getHotelOrders(hotelid);
    }

    @Override
    public ResponseVO annulOrder(int orderid) {
        //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        LocalDateTime cancelTime=LocalDateTime.now();
        Order checkOrder=orderMapper.getOrderById(orderid);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime=LocalDateTime.parse(checkOrder.getCheckInDate()+" 12:00:00",df);
        int userId=checkOrder.getUserId();
        for(int i=0;i<3;i++) {
            if ((cancelTime.plusDays(i)).isAfter(startTime)) {
                accountMapper.punishCredit(userId, 15-i*5);
            }
        }
        orderMapper.annulOrder(orderid);
        roomDetailMapper.deleteDetailRoom(checkOrder.getId());
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO changeOrder(OrderVO order){
        System.out.println("进入changeOrder");
        System.out.println(order.getCheckInDate());
        System.out.println(order.getId());
        System.out.println(order.getCheckOutDate());
        System.out.println(order.getPrice());
        System.out.println(order.getRoomNum());
        System.out.println(order.getPeopleNum());
        System.out.println(order.getPhoneNumber());
        System.out.println(order.isHaveChild());
        int orderid=order.getId();
        roomDetailMapper.changeDetailRoom(orderid,order.getCheckInDate(),order.getCheckOutDate());
//        orderMapper.deleteOrder(orderid);
//        return addOrder(order);
        orderMapper.changeOrder(order.getId(),order.getCheckInDate(),order.getCheckOutDate(),
                order.getPhoneNumber(),order.getPeopleNum(),order.isHaveChild()?1:0,order.getPrice(),
                order.getRoomNum());
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO deleteOrder(int orderid){
        Order order=orderMapper.getOrderById(orderid);
        if(order.getOrderState().equals(OrderStates.CancelledOrder.toString()) || order.getOrderState().equals(OrderStates.AbnormalOrder.toString())){
            orderMapper.deleteOrder(orderid);
            return ResponseVO.buildSuccess(true);
        }
        else{
            return ResponseVO.buildFailure("用户未选择撤销，订单删除失败");
        }
    }

    @Override
    public ResponseVO confirmOrder(int orderid) {
        orderMapper.confirmOrder(orderid);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO addComment(HistoryCommentVO historyCommentVO) {
        HistoryComment historyComment=new HistoryComment();
        BeanUtils.copyProperties(historyCommentVO,historyComment);
        commentMapper.addComment(historyComment);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getHistoryOrders(Integer userid,Integer hotelId){
        return orderMapper.getHistoryOrders(userid,hotelId);
    }
}
