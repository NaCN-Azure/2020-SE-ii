import Vue from 'vue'
import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { message } from 'ant-design-vue'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,
    enrollVIPAPI
} from '@/api/user'


import {
    getUserOrdersAPI,
    cancelOrderAPI,
    commentAddApI,
    getHistoryOrdersAPI
} from '@/api/order'
import {dataToArray} from "ant-design-vue/lib/vc-drawer/src/utils";

const getDefaultState = () => {
    return {
        userId: '',
        userInfo: {

        },
        userOrderList: [

        ],

    }
}

const user = {
    state : {userId: '',
        userInfo: {
            hotelid:-1,
            corporationName:""
        },
        enrollVIPVisible:false,
        userOrderList: [],
        historyOrderList:[],
        addCommentVisible:false,
        commentVisible:true,
        activeOrderId:-1,
        commentParams:{
            userId:-1,
            userName:"",
            credit:100,
            orderId:-1,
            content:"",
            rate:3,
            hotelId:-1,
            color:"",

        }

    },

    mutations: {
        reset_state: function(state) {
            state.token = '',
            state.userId = '',
            state.userInfo = {
                
            },
            state.userOrderList = []
        },
        set_enrollVIPVisible:(state,data)=>{
            state.enrollVIPVisible=data
        },
        set_token: function(state, token){
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_addCommentVisible:(state,data)=>{

            state.addCommentVisible=data

        },
        set_commentVisible:(state,data)=>{
            state.commentVisible=data
        },
        set_activeOrderId:(state,data)=>{
            state.activeOrderId=data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },
        set_historyOrderList:(state,data)=>{
            state.historyOrderList = data
        },
        set_commentParams:(state,data)=>{
             state.commentParams={
                 ...state.commentParams,
                 ...data
             }
        },
    },

    actions: {
        login: async ({ dispatch, commit }, userData) => {
            const res = await loginAPI(userData)
            if(res){
                console.log("er")
                console.log(res)
                setToken(res.id)
                commit('set_userId', res.id)
                dispatch('getUserInfo')
                if(res.userType=="HotelManager")
                    router.push( '/hotel/hotelDetail/'+ res.hotelid)
                else if(res.userType=="Saler"){
                    router.push('/saler/saler')
                }
                else if(res.userType=="Admin"){
                    router.push('/admin/manageUser')
                }
                else
                router.push('/hotel/hotelList')

            }
        },

        register: async({ commit }, data) => {
            console.log(data)
            const res = await registerAPI(data)
            if(res){
                message.success('注册成功')
            }
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
              getUserInfoAPI(state.userId).then(response => {
                const data = response
                if (!data) {
                  reject('登录已过期，请重新登录')
                }
                commit('set_userInfo', data)
                commit('set_userId', data.id)
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
        },
        updateUserInfo: async({ state, dispatch }, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        getUserOrders: async({ state, commit }) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            console.log("user")
            console.log(res)
            if(res){
                commit('set_userOrderList', res)
                console.log(state.userOrderList)
            }
        },
        getHistoryOrders: async({ state, commit },data) => {
            console.log(data)
            const params = {
                userId: data[0],
                hotelId: data[1],
            }
            const res = await getHistoryOrdersAPI(params)
            console.log(res)
            if(res){
                commit('set_historyOrderList', res)
                console.log(state.historyOrderList)
            }
        },
        cancelOrder: async({ state, dispatch }, orderId) => {
            const res = await cancelOrderAPI(orderId)
            if(res) {
                dispatch('getUserOrders')
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        logout: async({ commit }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
          // remove token
        resetToken({ commit }) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },

        addComment: async ({state,commit,dispatch})=>{
            const res=await commentAddApI(state.commentParams)
            console.log("addcomment",res)
            if(res){
                    commit('set_addCommentVisible',false)
                    message.success('评论成功')
                    dispatch('getUserOrders')
                    commit('set_commentVisible',true)

            }
            else{
                message.error('添加失败')
            }
        },
        enrollVIPi:async ({state,commit,dispatch},data)=>{
            let value;
            if(data.corporationName==""){
                value={'isVIP':1}
            }
            else {
                value={...data}
            }
            const res=await enrollVIPAPI({
                ...state.userInfo,
                ...value
            })
            console.log(res)
            if(!res){
                commit('set_enrollVIPVisible',false)
                message.success('注册成功')
            }
            else{
                message.error('注册失败')
            }

        }
    }
}
const  a=1;
export default  user