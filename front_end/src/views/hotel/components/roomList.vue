<template>
    <div class="room-list">
        <div class="filter">

        </div>

        <div style="width: 100%; text-align: right; margin:20px 0">
            <a-button type="primary" @click="addRoom" v-if="userInfo.userType=='HotelManager'"><a-icon type="plus" />添加房间</a-button>
        </div>

        <div class="list">
            <a-table
                :columns="columns"
                :dataSource="data"
        >
                <template
                        v-for="col in ['roomType', 'breakfast', 'total','curNum','peopleNum','price']"
                        :slot="col"
                        slot-scope="text, record"
                >
                    <div :key="col">

                        <a-input
                                v-if="record.editable&&col!=='roomType'&&col!=='breakfast'"
                                style="margin: -5px 0"
                                :value="text"
                                @change="e => handleChange(e.target.value, record.key, col)"
                        />
                        <a-select v-else-if="record.editable&&col==='roomType'" v-model="record.roomType"  @click="e => handleChange(e.target.value, record.key, col)" >
                            <a-select-option value="BigBed">大床房</a-select-option>
                            <a-select-option value="DoubleBed">双床房</a-select-option>
                            <a-select-option value="Family">家庭房</a-select-option>
                        </a-select>
                        <a-select v-else-if="record.editable&&col==='breakfast'" v-model="record.breakfast"  @click="e => handleChange(e.target.value, record.key, col)" >
                            <a-select-option value=yes>有</a-select-option>
                            <a-select-option value=no>无</a-select-option>
                        </a-select>
                        <template v-else>
                            {{ text }}
                        </template>
                    </div>
                </template>
                <template slot="action" slot-scope="text, record">
                    <div class="editable-row-operations" v-if="userInfo.userType=='HotelManager'">
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

                            <a-popconfirm
                                title="Are you sure delete this task?"
                                ok-text="Yes"
                                cancel-text="No"
                                @confirm="() => confirm(record)"
                                @cancel="cancel"
                        >
                                <a href="#">Delete</a>
                            </a-popconfirm>
                        </span>
                    </div>
                    <div class="order-row-operations" v-if="userInfo.userType=='Client'">
                        <a-button type="primary" @click="order(record)" v-if="userInfo.userType=='Client'">预定</a-button>
                    </div>
                </template>
        </a-table>
        </div>
        <AddRoomModal></AddRoomModal>
        <OrderModal></OrderModal>
    </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import OrderModal from './orderModal'
import AddRoomModal from './addRoomModal'
import hotelList from "../hotelList";
const columns = [
    {  
      title: '房型',
      dataIndex: 'roomType',
      key: 'roomType',
        scopedSlots: { customRender: 'roomType'}
    },
    {
      title: '早餐',
      dataIndex: 'breakfast',
      key: 'breakfast',
        scopedSlots: { customRender: 'breakfast'}
    },
    {
        title:'可用房间',
        dataIndex: 'total',
        key:'total',
        scopedSlots: { customRender: 'total'}
    },
    {
      title:'剩余房间',
      dataIndex: 'curNum',
      key:'curNum',
    },
    {
      title: '入住人数',
      key: 'peopleNum',
      dataIndex: 'peopleNum',
       scopedSlots: { customRender: 'peopleNum'}
    },
    {
      title: '房价',
      key: 'price',
      dataIndex: 'price',
      scopedSlots: { customRender: 'price'}
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
const data = [];
export default {
    name:'roomList',
    props: {
        rooms: {
            type: Array
        }
    },
    data() {
        this.cacheData = data.map(item => ({ ...item }));
        return {
            data,
            columns,
            editingKey: '',
        }
    },
    components: {
        OrderModal,
        AddRoomModal,
    },
    computed: {
        ...mapGetters([
            'orderModalVisible',
            'userInfo',
            'hotelList',
            'currentHotelId',
            'addRoomModalVisible',
            'manageId',
            'currentHotelInfo',
            'roomList',
        ])
    },
    async mounted() {
        data.splice(0,data.length)//清空
        this.set_currentHotelId(Number(this.$route.params.hotelId))
        await this.getHotelRoom(this.currentHotelId)
        console.log(this.roomList)
        for (let i = 0; i < this.roomList.length; i++) {
            data.push({
                //'roomType', 'breakfast', 'peopleNum','price','action'
                key: i.toString(),
                id:this.roomList[i].id,
                hotelId:this.roomList[i].hotelId,
                roomType: this.roomList[i].roomType,
                breakfast: this.roomList[i].breakfast,
                total:this.roomList[i].total,
                curNum:this.roomList[i].curNum,
                peopleNum: this.roomList[i].peopleNum,
                price: this.roomList[i].price,
            });
            this.cacheData.push({
                //'roomType', 'breakfast', 'peopleNum','price','action'
                key: i.toString(),
                id:this.roomList[i].id,
                hotelId:this.roomList[i].hotelId,
                roomType: this.roomList[i].roomType,
                breakfast: this.roomList[i].breakfast,
                total:this.roomList[i].total,
                curNum:this.roomList[i].curNum,
                peopleNum: this.roomList[i].peopleNum,
                price: this.roomList[i].price,
            });
        }
        console.log("dat")
        console.log(data)
        console.log(this.cacheData)
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_currentOrderRoom',
            'set_addRoomModalVisible',
            'set_currentHotelId'
        ]),
        ...mapActions([
            'getHotelList',
            'deleteRoomById',
            'getHotelById',
            'getHotelRoom',
            'changeRoom'
        ]),
        order(record) {

            this.set_currentOrderRoom(record)
            this.set_orderModalVisible(true)
        },
        change(record){
            this.set_activeHotelId(record.id)
            this.set_addRoomModalVisible(true)
        },
        addRoom() {
            this.set_currentHotelId(Number(this.$route.params.hotelId))
            this.set_addRoomModalVisible(true)
        },
        deleteRoom(record){
            this.deleteRoomById(record.id)
        },
        confirm(record) {
            console.log(record);
            this.deleteRoom(record);
        },
        handleChange(value, key, column) {
            const newData = [...this.data];
            const target = newData.filter(item => key === item.key)[0];
            if (target) {
                target[column] = value;
                this.data = newData;
            }
        },
        edit(key) {
            const newData = [...this.data];
            const target = newData.filter(item => key === item.key)[0];
            console.log(target)
            this.editingKey = key;
            if (target) {
                target.editable = true;
                this.data = newData;
            }
        },
        save(key) {
            const newData = [...this.data];
            const newCacheData = [...this.cacheData];
            const target = newData.filter(item => key === item.key)[0];
            const targetCache = newCacheData.filter(item => key === item.key)[0];
            if (target && targetCache) {
                this.changeRoom(target)
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
                Object.assign(target, this.cacheData.filter(item => key === item.key)[0]);
                delete target.editable;
                this.data = newData;
            }
        },
    }
}
</script>