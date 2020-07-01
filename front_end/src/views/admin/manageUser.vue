<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店管理" key="3">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addHotell"><a-icon type="plus" />添加酒店</a-button>
                </div>

                <a-table
                        :columns="columns1"
                        :dataSource="data"
                        borderd>
                    <template
                            v-for="col in ['id','name','address','phoneNum','hotelStar','bizRegion']"
                            :slot="col"
                            slot-scope="text, record"
                    >
                        <div :key="col">
                            <a-input
                                    v-if="record.editable&&col!=='hotelStar'"
                                    style="margin: -5px 0"
                                    :value="text"
                                    @change="e => handleChange(e.target.value, record.key, col)"
                            />
                            <a-rate v-else-if="(!record.editable)&&col==='hotelStar'" :value="record.hotelStar"  disabled/>
                            <a-rate v-else-if="record.editable&&col==='hotelStar'" v-model="record.hotelStar"  @click="e => handleChange(e.target.value, record.key, col)" />
                            <template v-else>
                                {{ text }}
                            </template>
                        </div>
                    </template>
                    <template slot="operation" slot-scope="text, record">
                        <div class="editable-row-operations">
            <span v-if="record.editable">
              <a @click="() => save(record.key)">Save</a>
                <a-divider type="vertical"></a-divider>
              <a-popconfirm title="Sure to cancel?" @confirm="() => cancel(record.key)">
                <a>Cancel</a>
              </a-popconfirm>
            </span>
            <span v-else>
              <a :disabled="editingKey !== ''" @click="() => edit(record.key)">Edit</a>
                <a-divider type="vertical"></a-divider>
                <a-popconfirm title="Sure to delete?" @confirm="()=>deleteHotel(record)">
                <a>Del</a>
              </a-popconfirm>
            </span>

                        </div>
                    </template>
                </a-table>
            </a-tab-pane>

            <a-tab-pane tab="酒店工作人员管理" key="1">
            <div style="width: 100%; text-align: right; margin:20px 0">
                <a-button type="primary" @click="addManager"><a-icon type="plus" />添加用户</a-button>
            </div>

            <a-table
                    :columns="columnsForManager"
                    :dataSource="managerList"
                    bordered
            >

                <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="changeUser('HotelManager',record)">修改用户信息</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="danger" @click="deleteUser(record)">删除用户</a-button>
                    </span>
            </a-table>
        </a-tab-pane>

            <a-tab-pane tab="用户管理" key="2">
                <div style="width: 100%; text-align: right; margin:20px 0">

                </div>
                <a-table
                        :columns="columns"
                        :dataSource="clientList"
                        bordered
                >
                    <span slot="credit" slot-scope="record">
                        <span>{{ record }}</span>

                    </span>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="changeUser('Client',record)">修改用户信息</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="danger" @click="deleteUser(record)">删除用户</a-button>
                    </span>
                </a-table>
            </a-tab-pane>


        </a-tabs>
        <AddManagerModal></AddManagerModal>
        <update-users-modal></update-users-modal>
        <AddHotelModal></AddHotelModal>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddManagerModal from './components/addManagerModal'
import UpdateUsersModal from "./components/updateUsersModal";
import AddHotelModal from "../hotelManager/components/addHotelModal";
import hotelList from "../hotel/hotelList";

const columnsForManager=[
    {
        title: '经理邮箱',
        dataIndex: 'email',
    },

    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '经理名字',
        dataIndex: 'userName',
    },
    {
       title:'管理的酒店ID',
       dataIndex: 'hotelid',
    },

    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
];
const columns = [
    {  
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {  
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
    {
        title: '信用值',
        dataIndex: 'credit',
        scopedSlots: { customRender: 'credit' },
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
//col,v-for,mounted均要修改和对应
const columns1 =[
    {
        title: '酒店id',
        dataIndex: 'id',
        scopedSlots: { customRender: 'id' },
    },
    {
        title: '酒店名字',
        dataIndex: 'name',
        scopedSlots: { customRender: 'name' },
    },
    {
        title: '酒店地址',
        dataIndex: 'address',
        scopedSlots: { customRender: 'address' },
    },
    {
        title: '咨询电话',
        dataIndex: 'phoneNum',
        scopedSlots: { customRender: 'phoneNum' },
    },
    {
        title: '商圈',
        dataIndex: 'bizRegion',
        scopedSlots: { customRender: 'bizRegion' },
    },
    {
        title: '星级',
        dataIndex: 'hotelStar',
        scopedSlots: { customRender: 'hotelStar' },
    },
    {
        title: 'operation',
        dataIndex: 'operation',
        scopedSlots: { customRender: 'operation' },
    },
]
var data=[]
export default {
    name: 'manageHotel',
    data(){
        this.cacheData = data.map(item => ({ ...item }));
        return {
            data,
            // cacheData :function (){ return data.map(item => ({ ...item }));},

            formLayout: 'horizontal',
            pagination: {},
            columns,
            columns1,
            columnsForManager,
            editingKey: '',
            form: this.$form.createForm(this, { name: 'manageUser' }),
        }
    },
    components: {
        UpdateUsersModal,
        AddManagerModal,
        AddHotelModal
    },
    computed: {
        ...mapGetters([
            'addManagerModalVisible',
            'updateUserModalVisible',
            'clientList',
            'managerList',
            'hotelList',
        ]),

    },
    async mounted() {
        await this.getHotelList()
        await this.getManagerList()
        await this.getClientList()
            console.log("lis",this.hotelList)
        // this.init()

        for (let i = 0; i < this.hotelList.length && data.length< this.hotelList.length; i++) {
            data.push({
                key: i.toString(),
                id:this.hotelList[i].id,
                name:this.hotelList[i].name,
                address:this.hotelList[i].address,
                phoneNum:this.hotelList[i].phoneNum,
                bizRegion:this.hotelList[i].bizRegion,
                hotelStar:this.hotelList[i].hotelStar,
            });
            //深拷贝两个数组
            this.cacheData.push({
                key: i.toString(),
                id:this.hotelList[i].id,
                name:this.hotelList[i].name,
                address:this.hotelList[i].address,
                phoneNum:this.hotelList[i].phoneNum,
                bizRegion:this.hotelList[i].bizRegion,
                hotelStar:this.hotelList[i].hotelStar,
            })
        }
        // data=[{key:0,id:1,name:'?'}]


    },
    methods: {
        ...mapActions([
            'getManagerList',
            'updateUsers',
            'getClientList',
            'getHotelList',
            'updateHotelInfo',
            'addHotel',
            'deleteHotelById'

        ]),
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_updateUsersParams',
            'set_updateUserModalVisible',
            'set_addHotelModalVisible',


        ]),
        addManager(){
            this.set_addManagerModalVisible(true)
        },
        init(){
            data=[];
            console.log("ddd",this.hotelList)
            for (let i = 0; i < this.hotelList.length && data.length< this.hotelList.length; i++) {
                data.push({
                    key: i.toString(),
                    id:this.hotelList[i].id,
                    name:this.hotelList[i].name,
                    address:this.hotelList[i].address,
                    phoneNum:this.hotelList[i].phoneNum,
                    bizRegion:this.hotelList[i].bizRegion,
                    hotelStar:this.hotelList[i].hotelStar,
                });
                //深拷贝两个数组
                this.cacheData.push({
                    key: i.toString(),
                    id:this.hotelList[i].id,
                    name:this.hotelList[i].name,
                    address:this.hotelList[i].address,
                    phoneNum:this.hotelList[i].phoneNum,
                    bizRegion:this.hotelList[i].bizRegion,
                    hotelStar:this.hotelList[i].hotelStar,
                })
            }
            console.log("ddd",data)
        },
        changeUser(record,r){
            const data={
                userType:record,
                id:r.id
            }
            this.set_updateUsersParams(data);
            this.set_updateUserModalVisible(true);

        },
        deleteUser(record){
            const value={record:record,type:'delete'}
            this.updateUsers(value);
        },
        deleteHotel(record) {
            console.log(record)
            this.deleteHotelById(record.id)
        },

        edit(key) {
            //解引用是浅拷贝

            const newData = [...this.data];
            console.log(key)
            const target = newData.filter(item => key === item.key)[0];
            this.editingKey = key;
            if (target) {
                target.editable = true;
                this.data = newData;
            }
        },
        handleChange(value, key, column) {
            const newData = [...this.data];
            //map，filter是深拷贝（独立，但filter仅仅针对第一层
            const target = newData.filter(item => key === item.key)[0];

            if (target) {

                 target[column] = value;

                this.data = newData;
            }
        },
        save(key) {
            const newData = [...this.data];
            const newCacheData = [...this.cacheData];
            const target = newData.filter(item => key === item.key)[0];
            const targetCache = newCacheData.filter(item => key === item.key)[0];
            console.log("outside")
            if (target && targetCache) {
                console.log("Inside",target)
                this.changeHotel(target)
                delete target.editable;
                this.data = newData;
                Object.assign(targetCache, target);
                this.cacheData = newCacheData;
            }
            this.editingKey = '';
        },
        cancel(key) {
            const newData = [...this.data];
            const target = newData.filter(item => key === item.key)[0];
            this.editingKey = '';
            if (target) {
                console.log("temp")
                console.log(data)
                console.log(this.cacheData)
                //好像是浅拷贝（第一层深）
                Object.assign(target, this.cacheData.filter(item => key === item.key)[0]);
                delete target.editable;
                this.data = newData;
                console.log(data)
                console.log(this.cacheData)
            }
        },
        addHotell(){
            this.set_addHotelModalVisible(true)

        },
        changeHotel(record){
            this.updateHotelInfo(record)
        }
    }
}
</script>
<style scoped lang="less">
    .manageUser-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>