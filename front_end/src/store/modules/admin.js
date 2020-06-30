import {
    getManagerListAPI,
    addManagerAPI,
    updateUsersAPI,
    getClientListAPI,
} from '@/api/admin'
import { message } from 'ant-design-vue'

const admin = {
    state: {
        managerList: [

        ],
        clientList:[],
        addManagerModalVisible: false,
        addManagerParams: {
            id:-1,
            userType:'HotelManager',
            email:'',
            password:'',
            userName:'',
            hotelid:-1,
        },
        updateUserModalVisible:false,
        updateUsersParams:{
            id:-1,
            userType:'HotelManager',
            email:'',
            password:'',
            userName:'',
            hotelid:-1,
        },
    },
    mutations: {
        set_updateUserModalVisible:function(state,data){
          state.updateUserModalVisible=data
        },
        set_updateUsersParams:function(state,data){
            state.updateUsersParams={
                ...state.updateUsersParams,
                ...data,
            }
        },
        set_managerList: function(state, data) {
            state.managerList = data
        },
        set_clientList:function(state, data) {
            state.clientList = data
        },
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        }
    },
    actions: {
        getManagerList: async({ commit }) => {
            const res = await getManagerListAPI()
            if(res){
                commit('set_managerList', res)
            }
        },
        getClientList:async({ commit }) => {
            // const res = await getManagerListAPI()
             const res = await getClientListAPI()
            console.log('clientList')
            console.log(res)
            if(res){
                commit('set_clientList', res)
            }
        },
        addManager: async({ state, commit, dispatch }) => {

            const res = await addManagerAPI(state.addManagerParams)
            if(res) {
                commit('set_addManagerParams',{
                    userType:'HotelManager',
                    email:'',
                    password:'',
                    userName:'',
                    hotelid:-1,
                })
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
            }else{
                message.error('添加失败')
            }
        },
        updateUsers:async({ state, commit, dispatch },data)=>{
            const value={
                updateType:data.type,
                user:data.record,
            }
            console.log(value)
            const res = await updateUsersAPI(value)
            if(res){
                message.success('修改成功')
                commit('set_updateUserModalVisible',false)
                dispatch('getManagerList')
                dispatch('getClientList')
            }
            else{
                message.error('修改失败')
            }


        }
    }
}
export default admin