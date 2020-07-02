import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI,
    updateHotelInfoAPI,
    deleteHotelByIdAPI,
    searchHotelAPI,
    getHotelUrlsAPI,
    getHotelUrlByIdAPI,
    updateHotelUrlAPI,
    searchMultipleHotelAPI,
    searchRoomlByDateAPI,
} from '@/api/hotel'
import {
    addRoomAPI,
    deleteRoomAPI,
    hotelAllRoomsAPI,
    changeRoomAPI,
} from '@/api/hotelManager'
import {
    reserveHotelAPI
} from '@/api/order'
import {
    orderMatchCouponsAPI,
} from '@/api/coupon'

const hotel = {
    state: {
        hotelList: [],
        hotelUrlList:[],
        roomList:[],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: true,
        currentHotelId: '',
        currentHotelInfo: {

        },
        currentHotelUrl:require("@/assets/cover.jpeg"),
        orderModalVisible: false,
        currentOrderRoom: {

        },
        orderMatchCouponList: [

        ],
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
            peopleNum:0,
            breakfast: '',
        },
        addRoomModalVisible: false,
        roomDate:{}
    },
    mutations: {
        set_roomDate:function(state, data) {
            state.roomDate = data
        },
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        set_roomList:function(state, data) {
            state.roomList = data
        },
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },
        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_orderMatchCouponList: function(state, data) {
            state.orderMatchCouponList = data
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            console.log(state.addRoomParams);
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_currentHotelUrl:function (state,data) {
            state.currentHotelUrl=data
        },
        set_hotelUrlList:function (state,data) {
            state.hotelUrlList=data
        },

    },

    actions: {
        getHotelList: async({commit, state}) => {
            const res = await getHotelsAPI()
            if(res){
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
                // window.location.reload()
            }
        },
        getHotelUrlList: async({commit, state}) => {
            const res = await getHotelUrlsAPI()
            if(res){
                commit('set_hotelUrlList', res)
                // window.location.reload()
            }
        },
        getHotelById: async({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId
            })
            if(res){
                commit('set_currentHotelInfo', res)
            }
        },
        getHotelUrlById: async({commit, state}) => {
            const res = await getHotelUrlByIdAPI({
                hotelId: state.currentHotelId
            })
            console.log('res',res)
            if(res){
                commit('set_currentHotelUrl', res.url)
            }
            else {
                commit('set_currentHotelUrl', require("@/assets/cover.jpeg"))
            }
        },

        searchRoomlByDate:async({commit, state},dates) => {

            commit('set_roomDate',dates)
            const res = await searchRoomlByDateAPI({
               ...dates,
                hotelId: state.currentHotelId
            })
            console.log('res',res)
            if(res){
                commit('set_roomList', res)
            }

        },
        getHotelRoom: async({ state, commit },hotelid) => {
            console.log(hotelid)
            const res = await hotelAllRoomsAPI(hotelid)
            console.log(res)
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_roomList',res)
            }
        },
        searchHotel:async({state,commit},key)=>{
            const res = await searchHotelAPI(key)
            console.log(res)
            if(res){

               commit('set_hotelList',res)
                message.success('search成功')
            }
            else {
                message.error('无匹配')
            }
        },
        updateUrl:async({ state, dispatch }, data) => {
            console.log(data)
            const params = {
                ...data,
                id:state.currentHotelId,
            }
            const res = await updateHotelUrlAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getHotelUrlById')
            }
        },
        updateHotelInfo: async({ state, dispatch }, data) => {
            console.log(data)
            const params = {
                ...data,
            }
            const res = await updateHotelInfoAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getHotelById')
            }
        },
        addOrder: async({ state, commit,dispatch }, data) => {
            const res = await reserveHotelAPI(data)
            console.log(res)
            if(res){
                message.success('预定成功')
                window.location.reload()
                dispatch('searchRoomlByDate',state.roomDate)
                commit('set_orderModalVisible', false)
            }
        },
        getOrderMatchCoupons: async({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI(data)
            if(res){
                commit('set_orderMatchCouponList', res)
            }
        },

        getHotelMatch: async({ state, commit }, data) => {
            
            const res = await searchMultipleHotelAPI(data)
            console.log(res)
            if(res){
                commit('set_hotelList', res)
            }
        },
        addRoom: async({ state, dispatch, commit }) => {
            console.log(state.addRoomParams)
            const res = await addRoomAPI(state.addRoomParams)
            if(res){
                commit('set_addRoomModalVisible', false)
                commit('set_addRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                    peopleNum: 0,
                    breakfast: '',
                })
                message.success('添加成功')
                window.location.reload()
                dispatch('getHotelRoom',state.addRoomParams.hotelId)
            }else{
                message.error('添加失败')
            }
        },
        deleteRoomById: async({ state, commit,dispatch }, id) => {
            const res = await deleteRoomAPI(id)
            console.log(res)
            if(res){
                message.success('删除成功')
                window.location.reload()
                dispatch('getHotelRoom',res)
            }else{
                message.error('删除失败')
            }
        },
        changeRoom: async({ state, dispatch, commit },data) => {
            console.log(data)
            const res = await changeRoomAPI(data)
            if(res){
                message.success('添加成功')
                window.location.reload()
                dispatch('getHotelRoom',state.addRoomParams.hotelId)
            }else{
                message.error('添加失败')
            }
        },
        deleteHotelById:async({state},id)=>{
            const res=deleteHotelByIdAPI({'hotelId':id})
            if(res){

                window.location.reload()
                message.success('删除成功')
            }else{
                message.error('删除失败')
            }
        }
    }
}

export default hotel