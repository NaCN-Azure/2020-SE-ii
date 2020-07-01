package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.Pic.PicMapper;
import com.example.hotel.data.hotel.CommentMapper;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.*;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HistoryCommentVO;
import com.example.hotel.vo.HotelPicVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private RoomMapper roomMapper;


    @Autowired
    private PicMapper picMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    private boolean check(HotelVO hotelVO,String key){
        String address=hotelVO.getAddress();
        String name=hotelVO.getName();
        return address.contains(key)||name.contains(key);
    }

    @Override
    public List<HotelPicVO> getPiclist()
    {
        return picMapper.getHotelurlList();
    }

    @Override
    public HotelPicVO getPicbyid(Integer id)
    {
        return picMapper.gethotelurlById(id);

    }

    @Override
    public void updatePic(Integer id, String url)
    {
        List<HotelPicVO> temp=picMapper.getHotelurlList();
        int label=0;
        for(int i=0;i<temp.size();i++)
        {
            if(temp.get(i).getId()==id)
            {
                label=1;
                break;
            }
        }

        if(label==1)
        {
            picMapper.updatePic(id,url);
        }
        else{
            HotelPic hotelPic=new HotelPic();
            hotelPic.setId(id);
            hotelPic.setUrl(url);
            picMapper.insertPic(hotelPic);
        }


    }


    @Override
    public void updateHotelInfo(HotelVO hotelVO){
        int hotelStar=hotelVO.getHotelStar();
        String hotelName=hotelVO.getName();
        String hotelAddress=hotelVO.getAddress();
        String Description=hotelVO.getDescription();
        int id=hotelVO.getId();
        hotelMapper.updateHotelInfo(id,hotelName,hotelStar,hotelAddress,Description);
    }

    @Override
    public void addHotel(HotelVO hotelVO) throws ServiceException {
        User manager = accountService.getUserInfo(hotelVO.getManagerId());
//        if(manager == null || !manager.getUserType().equals(UserType.HotelManager)){
//            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
//        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(hotelVO.getHotelStar());
        hotelMapper.insertHotel(hotel);
    }

    @Override
    public List<HotelVO> retrieveHotels() {

        return hotelMapper.selectAllHotel();
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        List<HistoryComment> comments=commentMapper.selectAllComments(hotelId);
        List<HistoryCommentVO> commentVOS = comments.stream().map(r -> {
            HistoryCommentVO historycommentVO = new HistoryCommentVO();
            historycommentVO.setId(r.getId());
            historycommentVO.setUserName(r.getUserName());
            historycommentVO.setRate(r.getRate());
            historycommentVO.setColor(r.getColor());
            historycommentVO.setCredit(r.getCredit());
            historycommentVO.setContent(r.getContent());
            historycommentVO.setUserId(r.getUserId());
            historycommentVO.setHotelId(r.getHotelId());
            historycommentVO.setOrderid(r.getHotelId());
            return historycommentVO;
        }).collect(Collectors.toList());

        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPeopleNum(r.getPeopleNum());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setCurNum(r.getCurNum());
            roomVO.setTotal(r.getTotal());
            roomVO.setBreakfast(r.getBreakfast());
            return roomVO;
        }).collect(Collectors.toList());
        hotelVO.setRooms(roomVOS);
        hotelVO.setHistoryCommentVOs(commentVOS);

        return hotelVO;
    }
    public boolean deleteHotel(Integer hotelId){

        hotelMapper.deleteHotel( hotelId);
        return true;
    }
    
    /**
     * @param hotelId
     * @return
     */
    @Override
    public List<Order> getHotelOrders(Integer hotelId) {
        List<Order> orders = orderService.getAllOrders();
        return orders.stream().filter(order -> order.getHotelId().equals(hotelId)).collect(Collectors.toList());
    }

    @Override
    public List<HotelVO> searchHotel(String key){
        List<HotelVO> temp=retrieveHotels();
        List<HotelVO> result=new ArrayList<>();
        for(int i=0;i<temp.size();i++){
            if(check(temp.get(i),key)) {
                result.add(temp.get(i));
            }
        }
        return result;
    }

    @Override
    public void insertPic(HotelPic hotelPic) {

        picMapper.insertPic(hotelPic);
    }

    @Override
    public List<HotelVO> searchHotelbysection(String address,int[] hotelStar,int[] money,String name,int[] rate )
    {
        List<HotelVO> allHotels=retrieveHotels();
        List<HotelVO> res=new ArrayList<HotelVO>();
        for(int i=0;i<allHotels.size();i++)
        {
            if(getResult(allHotels.get(i).getAddress(),address)
               && getResult(allHotels.get(i).getName(),name)
               && allHotels.get(i).getHotelStar()>=hotelStar[0] && allHotels.get(i).getHotelStar()<=hotelStar[1]
               && findmin(allHotels.get(i).getId())>=money[0] && findmax(allHotels.get(i).getId())<=money[1]
               && allHotels.get(i).getRate()>=rate[0] && allHotels.get(i).getRate()<=rate[1])
            {
                res.add(allHotels.get(i));
            }

        }

        return res;



    }

    public  boolean getResult(String targetStr, String str) {
        if (targetStr.contains(str)) {
            return true;
        } else {
            return false;
        }
    }

    public double findmin(int hotelid)
    {
        List<HotelRoom> temp=roomMapper.selectRoomsByHotelId(hotelid);
        List<Double>  store_price=new ArrayList<Double>();
        for(int i=0;i<temp.size();i++)
        {
            store_price.add(temp.get(i).getPrice());
        }
        Collections.sort(store_price);
        return store_price.get(0);
    }

    public double findmax(int hotelid)
    {
        List<HotelRoom> temp=roomMapper.selectRoomsByHotelId(hotelid);
        List<Double>  store_price=new ArrayList<Double>();
        for(int i=0;i<temp.size();i++)
        {
            store_price.add(temp.get(i).getPrice());
        }
        Collections.sort(store_price);
        return store_price.get(store_price.size()-1);
    }




}
