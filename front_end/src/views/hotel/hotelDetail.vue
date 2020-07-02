<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 300px;">
                        <img
                            alt="example"
                            :src="image_url"
                            slot="cover"
                            referrerPolicy="no-referrer"
                            />
                    </a-card>
                    <div class="info">
                        <a-form :form="form" style="margin-top: 10px">
                            <a-form-item label="酒店名称" :label-col="{ span: 10 }" :wrapper-col="{ span: 10, offset: 3  }">
                                <a-input
                                        placeholder="请填写酒店名称"
                                        v-decorator="['name', { rules: [{ required: true, message: '请输入酒店名称' }] }]"
                                        v-if="modify"
                                />
                                <span v-else>{{ currentHotelInfo.name }}</span>
                            </a-form-item>
                            <a-form-item label="地址" :label-col="{ span: 10 }" :wrapper-col="{ span: 10, offset: 3 }">
                                <a-input
                                        placeholder="请填写酒店地址"
                                        v-decorator="['address', { rules: [{ required: true, message: '请输入酒店地址' }] }]"
                                        v-if="modify"
                                />
                                <span v-else>{{ currentHotelInfo.address }}</span>
                            </a-form-item>
                                <a-form-item label="商圈" :label-col="{ span: 10 }" :wrapper-col="{ span: 10, offset: 3 }">
                                    <a-input
                                            placeholder="请填写酒店所在商圈"
                                            v-decorator="['bizRegion', { rules: [{ required: true, message: '请输入酒店所在商圈' }] }]"
                                            v-if="modify"
                                    />
                                    <span v-else>{{ currentHotelInfo.bizRegion }}</span>
                              </a-form-item>
                            <a-form-item label="评分" :label-col="{ span: 10 }" :wrapper-col="{ span: 10, offset: 3 }">
                                <span  class="value">{{ currentHotelInfo.rate }}</span>
                            </a-form-item>

                            <a-form-item label="星级" :label-col="{ span: 10 }" :wrapper-col="{ span: 10, offset: 3 }">
                                <a-rate style="font-size: 15px" v-if="modify" v-model="values"/>
                                <a-rate style="font-size: 15px" :value="currentHotelInfo.hotelStar" v-else disabled/>
                            </a-form-item>

                            <a-form-item label="酒店简介" :label-col="{ span: 10 }" :wrapper-col="{ span: 10, offset: 3 }">
                                <a-input
                                        placeholder="请填写酒店简介"
                                        v-decorator="['description', { rules: [{ required: true, message: '请输入酒店简介' }] }]"
                                        v-if="modify"
                                />
                                <span v-else>{{ currentHotelInfo.description }}</span>
                            </a-form-item>
                            <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                                <a-button type="primary" @click="saveModify">
                                    保存
                                </a-button>
                                <a-button type="default" style="margin-left: 10px" @click="cancelModify">
                                    取消
                                </a-button>
                            </a-form-item>
                            <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else-if="userInfo.userType=='HotelManager'" >
                                <a-button type="primary" @click="modifyInfo" >
                                    修改信息
                                </a-button>
                            </a-form-item>
                        </a-form>
                        <div style=" margin-left: 40px;">
                            <a-upload
                                    v-if="userInfo.userType=='HotelManager'"
                                    action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                                    list-type="text"
                                    :customRequest="testUpload"
                                    :fileList="temp"
                            >
                                <a-button > <a-icon type="upload" /> 重新上传图片 </a-button>
                            </a-upload>
                        </div>

                    </div>
                </div>

                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <div style="width: 100%; text-align: right; margin:20px 0"><a-range-picker v-if="userInfo.userType=='Client'" @change="onChange" /></div>
                        <a-alert
                                v-if="userInfo.userType=='Client'&&flag==true"
                                message="提示"
                                description="如果您没有选择时间段，将默认返回近一周内一直空闲的房间"
                                type="info"
                                show-icon
                        />
                        <br/>
                        <a-table
                                v-if="userInfo.userType=='Client'"
                                :columns="columns"
                                :dataSource="roomList"
                                bordered
                        >

                        <span slot="action" slot-scope="record">
                            <a-button type="primary" @click="order(record)">预定</a-button>
                        </span>
                        </a-table>

                        <RoomList :rooms="currentHotelInfo.rooms" v-if="userInfo.userType=='HotelManager'"></RoomList>
                    </a-tab-pane>
                    <a-tab-pane tab="酒店详情" key="2">

                    </a-tab-pane>
                    <a-tab-pane tab="历史评价" key="3">
                        <HistoryComment :comments="currentHotelInfo.historyCommentVOs"></HistoryComment>

                    </a-tab-pane>
                </a-tabs>
            </div>
            <order-modal></order-modal>
        </a-layout-content>
    </a-layout>
</template>
<script>
    import OrderModal from "./components/orderModal";
    let OSS = require('ali-oss');
    // let client=new OSS({
    //     accessKeyId:'LTAI4G5y6f1Hk4R5wVsPbQZ4',
    //     accessKeySecret:'Aw3CbQFmBw8vyk7lmW3j1jL7LL6xpn',
    //     bucket:'test-nju-1',
    //     region:'oss-cn-shenzhen',
    //     secure: true,
    // })
    let client=new OSS({
        accessKeyId:'LTAI4GAh9znuBWPxpPX1q5z5',
        accessKeySecret:'2s6H9ljuP6oO3aqLosalsUfTx35siO',
        bucket:'obsidian222',
        region:'oss-cn-shenzhen',
        secure: true,
    })
    import HistoryComment from "./components/historyComment";
    const listData = [
        {
            userName: '刘钦',
            rate:3,
            color:'#f52de8',
            credit:100,
            content:
                'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
        },
        {
            userName: '任桐炜',
            rate:5,
            color:'#7265e6',
            credit:120,
            content:
                'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
        }
    ];
     const columns = [
        {
            title: '房型',
            dataIndex: 'roomType',
            key: 'roomType',
        },
        {
            title: '早餐',
            dataIndex: 'breakfast',
            key: 'breakfast',
        },
        {
            title:'可用房间',
            dataIndex: 'total',
            key:'total',
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

import { mapGetters, mapActions, mapMutations } from 'vuex'
import RoomList from './components/roomList'

export default {
    name: 'hotelDetail',
    components: {
        OrderModal,
        HistoryComment,
        RoomList
    },
    data() {
        return {
            flag:true,
            columns,
            temp:[],
            listData,
            modify: false,
            values:'',
            form: this.$form.createForm(this, { name: 'coordinated' }),

        }
    },
    props:{},
    computed: {
        ...mapGetters([
            'currentHotelInfo',
            'userInfo',
            'currentHotelId',
            'currentHotelUrl',
            'roomList'
        ]),
        image_url:function () {
            // return "https://test-nju-1.oss-cn-shenzhen.aliyuncs.com/hotel/timg.png"
            return this.currentHotelUrl
        },


    },
    async mounted() {
        this.set_currentHotelId(Number(this.$route.params.hotelId))
        await this.getHotelById()
        await this.getHotelUrlById()
        this.getHotelRoom(this.currentHotelId)
        this.values=this.currentHotelInfo.hotelStar
        // console.log(this.currentHotelInfo)
        // console.log(this.currentHotelInfo.historyComments)
        console.log(this.currentHotelUrl)
        this.testMethods()
    },
    beforeRouteUpdate(to, from, next) {
        this.set_currentHotelId(Number(to.params.hotelId))
        this.getHotelById()
        next()
    },
    methods: {
        ...mapMutations([
            'set_currentHotelId',
            'set_currentOrderRoom',
            'set_orderModalVisible'
        ]),
        ...mapActions([
            'getHotelById',
            'updateHotelInfo',
            'getHotelUrlById',
            'updateUrl',
            'searchRoomlByDate',
            'getHotelRoom'

        ]),
        order(record) {
           this.set_currentOrderRoom(record)
            this.set_orderModalVisible(true)
        },
        onChange(date, dateString) {
            console.log(date, dateString);
            this.searchRoomlByDate({
                start:dateString[0],
                end:dateString[1]
            })
            if(dateString[0]==""&&dateString[1]==""){this.flag=true}
            else this.flag=false
        },
        saveModify() {
            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        name: this.form.getFieldValue('name'),
                        address: this.form.getFieldValue('address'),
                        bizRegion:this.form.getFieldValue('bizRegion'),
                        hotelStar: this.values,
                        description:this.form.getFieldValue('description'),
                        id:this.currentHotelInfo.id
                    }
                    this.updateHotelInfo(data).then(()=>{
                        this.modify = false
                    })
                }
            });
        },


        cancelModify() {
            this.modify = false
        },
        modifyInfo() {
            console.log(this.currentHotelInfo)
            setTimeout(() => {
                this.form.setFieldsValue({
                    'name': this.currentHotelInfo.name,
                    'address': this.currentHotelInfo.address,
                    'bizRegion':this.currentHotelInfo.bizRegion,
                    'hotelStar':this.values,
                    'description': this.currentHotelInfo.description,
                })
            }, 0)
            this.modify = true
        },
        testMethods(){

        },
        updateUrls(data){
            this.updateUrl(data)
        },
        async testUpload(file){
            try {
                //object-name可以自定义为文件名（例如file.txt）或目录（例如abc/test/file.txt）的形式，实现将文件上传至当前Bucket或Bucket下的指定目录。
                let result = await client.put('hotel/'+file.file.name, file.file);
                const tempData={
                    url:result.url
                }
                await this.updateUrls(tempData)

            } catch (e) {
                console.log(e);
            }
            return new Promise(resolve => {

            });
        },

    }
}
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }
    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 10px 0;
            display: flex;
            width: 280px;
            flex-direction: column;
            margin-left: 20px;

                .label{
                    margin-right: 15px;
                    font-size: 18px;
                }
                .value {
                    margin-right: 15px
                }

        }
    }
</style>