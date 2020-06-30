<template>
    <div class="manageHotel-wrapper">
        <a-tabs>

            <a-tab-pane tab="优惠策略" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addCoupon"><a-icon type="plus" />添加优惠策略</a-button>
                </div>
                <a-table
                        :columns="columns1"
                        :dataSource="data"
                        :scroll="{ x: 1500}"
                        bordered
                >
                    <template
                            v-for="col in ['couponName', 'couponType', 'description','targetMoney','discount','discountMoney','startTime','endTime']"
                            :slot="col"
                            slot-scope="text, record"
                    >
                        <div :key="col">
                            <a-input
                                    v-if="record.editable"
                                    style="margin: -5px 0"
                                    :value="text"
                                    @change="e => handleChange(e.target.value, record.key, col)"
                            />
                            <template v-else>
                                {{ text }}
                            </template>
                        </div>
                    </template>
                    <template slot="action.." slot-scope="text, record">
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
        </span>

                        </div>
                    </template>
<!--                    <span slot="action.." slot-scope="record1">-->
<!--                        <a-button type="info" size="small" @click="changeCoupon(record1)">修改策略</a-button>-->
<!--                        <a-divider type="vertical"></a-divider>-->
<!--                        <a-popconfirm-->
<!--                                title="确定想删除该优惠策略吗？"-->
<!--                                @confirm="deleteCoupon(record1)"-->
<!--                                okText="确定"-->
<!--                                cancelText="取消"-->
<!--                        >-->
<!--                            <a-button type="danger" size="small">删除策略</a-button>-->
<!--                        </a-popconfirm>-->
<!--                    </span>-->

                </a-table>
            </a-tab-pane>


            <a-tab-pane tab="订单管理" key="2">
                <a-table
                        :columns="columns2"
                        :dataSource="orderList"
                        bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
                    <span slot="action" slot-scope="record">

                        <a-button type="primary" size="small" @click="confirmOrders(record)" v-if="record.orderState=='已预订'">确认入住</a-button>
                        <a-divider type="vertical" v-if="record.orderState=='已预订'"></a-divider>
                        <a-popconfirm
                                title="确定想删除该订单吗？"
                                @confirm="deleteOrder(record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除订单</a-button>
                        </a-popconfirm>
                         <a-divider type="vertical"></a-divider>
                        <a-button type="primary" size="small" @click="changeOrder(record)">修改订单</a-button>
                    </span>
                </a-table>
            </a-tab-pane>

        </a-tabs>
        <Coupon></Coupon>
        <AddCoupon></AddCoupon>
        <up-date-order-modal></up-date-order-modal>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import Coupon from './components/coupon'
    import AddCoupon from './components/addCoupon';
    import UpDateOrderModal from "./components/updateOrderModal";
    const moment = require('moment')
    const columns1 = [
        {
            title: '优惠券名',
            dataIndex: 'couponName',
            fixed: 'left',
            width: 100,
            scopedSlots: { customRender: 'couponName' },
        },
        {
            title: '优惠券类型',
            dataIndex: 'couponType',
            fixed: 'left',
            width: 150,
            scopedSlots: { customRender: 'couponType' },
        },
        {
            title: '优惠简介',
            dataIndex: 'description',
            width: 150,
            scopedSlots: { customRender: 'description' },
        },
        {
            title: '达标金额',
            dataIndex: 'targetMoney',
            width: 150,
            scopedSlots: { customRender: 'targetMoney' },
        },
        {
            title: '折扣',
            dataIndex: 'discount',
            width: 150,
            scopedSlots: { customRender: 'discount' },
        },
        {
            title: '优惠金额',
            dataIndex: 'discountMoney',
            width: 150,
            scopedSlots: { customRender: 'discountMoney' },
        },
        {
            title: '开始日期',
            dataIndex: 'startTime',
            width: 150,
            scopedSlots: { customRender: 'startTime' },
        },
        {
            title: '失效日期',
            dataIndex: 'endTime',
            width: 150,
            scopedSlots: { customRender: 'endTime' },
        },
        {
            title: '操作',
            key: 'action',
            fixed: 'right',
            width: 250,
            scopedSlots: { customRender: 'action..' },
        },
    ];
    const columns2 = [
        {
            title: '订单号',
            dataIndex: 'id',
        },
        {
            title: '酒店名',
            dataIndex: 'hotelName',
        },
        {
            title: '房型',
            dataIndex: 'roomType',
            scopedSlots: { customRender: 'roomType' }
        },
        {
            title: '入住时间',
            dataIndex: 'checkInDate',
            scopedSlots: { customRender: 'checkInDate' }
        },
        {
            title: '离店时间',
            dataIndex: 'checkOutDate',
            scopedSlots: { customRender: 'checkOutDate' }
        },
        {
            title: '入住人数',
            dataIndex: 'peopleNum',
        },
        {
            title: '房价',
            dataIndex: 'price',
        },
        {
            title: '状态',
            filters: [{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已入住', value: '已入住' }],
            onFilter: (value, record) => record.orderState.includes(value),
            dataIndex: 'orderState',
            scopedSlots: { customRender: 'orderState' }
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
    const data = [];
    export default {
        name: 'manageHotel',
        data(){
            this.cacheData = data.map(item => ({ ...item }));
            return {
                data,
                formLayout: 'horizontal',
                pagination: {},
                columns1,
                columns2,
                editingKey: '',
                form: this.$form.createForm(this, { name: 'manageHotel' }),
            }
        },
        components: {
            UpDateOrderModal,
            AddCoupon,
            Coupon,
        },
        computed: {
            ...mapGetters([
                'orderList',
                'hotelList',
                'addHotelModalVisible',
                'addRoomModalVisible',
                'activeHotelId',
                'couponVisible',
                'updateOrderModalVisible',
                'manageId',
                'userInfo',
                'couponList',
            ]),
        },
        async mounted() {
             await this.getHotelOrders(this.manageId)
            await this.getHotelCoupon(this.manageId)
            for (let i = 0; i < this.couponList.length; i++) {
            data.push({
                //couponName', 'couponType', 'description','targetMoney','discount','discountMoney','startTime','endTime
                key: i.toString(),
                id:this.couponList[i].id,
                hotelId:this.couponList[i].hotelId,
                couponName: this.couponList[i].couponName,
                couponType: this.couponList[i].couponType,
                description: this.couponList[i].description,
                targetMoney: this.couponList[i].targetMoney,
                discount: this.couponList[i].discount,
                discountMoney: this.couponList[i].discountMoney,
                startTime: this.couponList[i].startTime,
                endTime: this.couponList[i].endTime,
            });
                this.cacheData.push({
                    //couponName', 'couponType', 'description','targetMoney','discount','discountMoney','startTime','endTime
                    key: i.toString(),
                    id:this.couponList[i].id,
                    hotelId:this.couponList[i].hotelId,
                    couponName: this.couponList[i].couponName,
                    couponType: this.couponList[i].couponType,
                    description: this.couponList[i].description,
                    targetMoney: this.couponList[i].targetMoney,
                    discount: this.couponList[i].discount,
                    discountMoney: this.couponList[i].discountMoney,
                    startTime: this.couponList[i].startTime,
                    endTime: this.couponList[i].endTime,
                });


            }


        },
        methods: {
            ...mapMutations([
                'set_addHotelModalVisible',
                'set_couponVisible',
                'set_activeHotelId',
                'set_updateOrdersParams',
                'set_updateOrderModalVisible',
                'set_addCouponVisible',
            ]),
            ...mapActions([
                'getHotelList',
                'getAllOrders',
                'getHotelCoupon',
                'getHotelOrders',
                'updateOrders',
                'updateCoupon'
            ]),
            addRoom(record) {
                this.set_activeHotelId(record.id)
                this.set_addRoomModalVisible(true)
            },
            addCoupon() {
                console.log(this.manageId)
                this.set_activeHotelId(this.manageId)
                this.set_addCouponVisible(true)
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
                    console.log("!!!",target)
                    this.changeCoupon(target)
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
                    Object.assign(target, this.cacheData.filter(item => key === item.key)[0]);
                    delete target.editable;
                    this.data = newData;
                }
            },





            changeCoupon(record) {
                this.updateCoupon(record)
            },
            deleteCoupon(record){

            },
            changeOrder(record){
                const data={
                    hotelId: record.hotelId,
                    hotelName: record.hotelName,
                    userId: record.userId,
                    createDate: record.createDate,
                }

                this.set_updateOrdersParams(data);
                this.set_updateOrderModalVisible(true);
                alert(this.updateOrderModalVisible)


            },
            deleteOrder(record){
               const value={record:record,type:'delete'}
                this.updateOrders(value);

            },
            confirmOrders(record){
                const value={record:record,type:'confirm'}
                this.updateOrders(value);

            },
        }
    }
</script>
<style scoped lang="less">
    .manageHotel-wrapper {
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
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>