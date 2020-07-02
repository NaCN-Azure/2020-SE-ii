const getters = {
  //user
    enrollVIPVisible: state=>state.user.enrollVIPVisible,
    manageId: state=>state.user.userInfo.hotelid,
  token: state => state.user.token,
  userId: state => state.user.userId,
  userInfo: state => state.user.userInfo,
  userOrderList: state => state.user.userOrderList,
    historyOrderList: state => state.user.historyOrderList,
    //hotel
    currentHotelUrl:state => state.hotel.currentHotelUrl,
    hotelUrlList:state => state.hotel.hotelUrlList,
  hotelListLoading: state => state.hotel.hotelListLoading,
  hotelList: state => state.hotel.hotelList,
  roomList: state => state.hotel.roomList,
  currentHotelInfo: state => state.hotel.currentHotelInfo,
  currentHotelId: state => state.hotel.currentHotelId,
  orderModalVisible: state => state.hotel.orderModalVisible,
  currentOrderRoom: state => state.hotel.currentOrderRoom,
    addRoomModalVisible: state => state.hotel.addRoomModalVisible,
  orderMatchCouponList: state => state.hotel.orderMatchCouponList,
 addCommentVisible:state=>state.user.addCommentVisible,
  activeOrderId:state=>state.user.activeOrderId,

  //admin
    updateUsersParams:state=>state.admin.updateUsersParams,
    updateUserModalVisible:state=>state.admin.updateUserModalVisible,
  managerList: state => state.admin.managerList,
  addManagerModalVisible: state => state.admin.addManagerModalVisible,
  addManagerParams: state => state.admin.addManagerParams,
    clientList:state=>state.admin.clientList,
  //hotelManager
  orderList: state => state.hotelManager.orderList,
  addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
  couponVisible: state => state.hotelManager.couponVisible,
  addCouponVisible: state => state.hotelManager.addCouponVisible,
  activeHotelId: state => state.hotelManager.activeHotelId,
  couponList: state => state.hotelManager.couponList,
    updateOrdersParams:state=>state.hotelManager.updateOrdersParams,
    updateOrderModalVisible: state=>state.hotelManager.updateOrderModalVisible,
//saler
    allCouponlist:state=>state.saler.allCouponlist,
    updateCouponVisible:state=>state.saler.updateCouponVisible,
    updateCouponParams:state=>state.saler.updateCouponParams,
    addCouponModalVisible:state=>state.saler.addCouponModalVisible,
    addCouponModalParams:state=>state.saler.addCouponModalParams,
    fileList:state=>state.saler.fileList,
    currentCouponId:state=>state.saler.currentCouponId,
  }
  
  export default getters