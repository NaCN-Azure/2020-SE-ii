import {

} from '@/api/admin'
import {
    addCreditByIdAPI,
    getFileListAPI,
    addADAPI,
    addWebCouponAPI,
    webAllCouponsAPI,
    updateWebCouponAPI,
    deleteWebCouponAPI,
    getWebCouponByIdAPI
} from '@/api/saler'
import { message } from 'ant-design-vue'
import store from '@/store'
import Vue from 'vue'
import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
const saler={
    state:{
        fileList:[],
        updateCouponParams: {
            id:'',
            description:'',
            type:'',
            name:'',
            targetMoney:0,
            discountMoney:0,
            discount:0,
            startTime:'',
            endTime:'',
        },
        updateCouponVisible:false,
        currentCouponId:"",
        allCouponlist:[],
        addCouponModalVisible:false,
        addCouponModalParams:{
            description:'',
            type:'',
            name:'',
            targetMoney:0,
            discountMoney:0,
            discount:0,
            startTime:'',
            endTime:'',
        },
    },
    mutations:{
        set_updateCouponParams: function(state, data) {
            console.log(state.updateCouponParams);
            state.updateCouponParams = {
                ...state.updateCouponParams,
                ...data,
            }
        },
        set_addCouponModalParams: function(state, data) {
            state.addCouponModalParams = {
                ...state.addCouponModalParams,
                ...data,
            }
        },
        set_currentCouponId: function(state, data) {
            state.currentCouponId = data
        },
        set_updateCouponVisible: function(state, data) {
            state.updateCouponVisible = data
        },
        set_addCouponModalVisible: function(state, data) {
            console.log(data)
            state.addCouponModalVisible = data
        },
        set_allCouponlist: function(state, data) {
            state.allCouponlist = data
        },
        set_fileList:function (state,data) {
            state.fileList=data;
        },
    },
    actions:{
        addAD:async ({state,commit},data)=>{
            const res= await addADAPI(data)
            if(res){
                message.success('添加成功')
            }else{
                message.error('添加失败，可能是广告名重复')
            }
        },
        addCreditById: async ({state,commit},data)=>{
            const res=addCreditByIdAPI(data)
            if(res){
                message.success('添加成功')
            }else{
                message.error('添加失败,请刷新后重试')
            }
        },
        getAllCoupon: async({ state, commit }) => {
            const res = await webAllCouponsAPI()
            if(res) {
            // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
               commit('set_allCouponlist',res)
            }
            console.log(state.allCouponlist)
        },
        getWebCouponById: async({ state, commit },id) => {
            const res = await getWebCouponByIdAPI(id)
            console.log(res)
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_updateCouponParams',res)
            }
        },
        getFileList: async ({state,commit})=>{
            const res= await getFileListAPI()
            if(res){
                commit('set_fileList',res)

                message.success('获取广告清单成功')
            }else{
                message.error('获取广告清单失败')
            }
        },
        addWebCoupon: async({ commit, dispatch }, data) => {
        console.log(data)
        const res = await addWebCouponAPI(data)
        if(res){
            // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
            commit('set_addCouponModalVisible', false)
            commit('set_addCouponModalParams', {
                name: '',
                description: '',
                type: '',
                targetMoney: 0,
                discountMoney: 0,
            })
            message.success('添加成功')
            dispatch('getAllCoupon')

        }else{
            // 添加失败后的操作
            message.error('添加失败')
        }
    },
        updateWebCoupon: async({ commit, dispatch }, data) => {
        console.log(data)
        const res = await updateWebCouponAPI(data)
        if(res){
            console.log("AAA")
            // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
            commit('set_updateCouponVisible', false)
            commit('set_updateCouponParams', {
                name: '',
                description: '',
                type: '',
                targetMoney: 0,
                discountMoney: 0,
            })
            message.success('修改成功')
            dispatch('getAllCoupon')

        }else{
            // 添加失败后的操作
            message.error('修改失败')
        }
    },
        deleteCoupon: async({ commit, dispatch }, couponId) => {
            console.log(couponId)
            const res = await deleteWebCouponAPI(couponId)
            if(res){
                message.success('删除成功')
                dispatch('getAllCoupon')

            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
    },


}


export default saler