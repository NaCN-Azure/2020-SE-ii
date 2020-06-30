<template>
    <div class="saler-coupon">
        <a-tabs>
            <a-tab-pane tab="网站优惠策略" key="1">
                <a-row>
                    <a-col :span="12">
                        优惠详情
                        <a-switch default-checked @change="onChange">
                            <a-icon slot="checkedChildren" type="check" />
                            <a-icon slot="unCheckedChildren" type="close" /></a-switch>
                        <a-divider type="vertical"></a-divider>
                    </a-col>
                    <a-col :span="6" :offset="1">
                        <a-button type="primary" @click="addCoupon"><a-icon type="plus" />添加优惠策略</a-button>
                    </a-col>
                </a-row>
                <a-divider ></a-divider>
                <a-list :grid="{ gutter: 16, column: 2 }" :data-source="allCouponlist">
                    <a-list-item slot="renderItem" slot-scope="item">
                        <a-card style="width:100%" :title="item.couponName">
                            <a slot="extra" >
                                <a-button type="primary" size='small' @click="changeCoupon(item.id)">
                                    修改
                                </a-button>
                                <a-button type="danger" size="small" style="margin-left: 20px" @click="deleteCouponB(item.id)">
                                    删除
                                </a-button>
                            </a>
                            <a-tabs default-active-key="1" @change="callback" v-if="modify">
                                <a-tab-pane key="2" tab="优惠简介" >
                                    <a-tag color="pink">
                                        {{item.description}}
                                    </a-tag>
                                </a-tab-pane>
                                <a-tab-pane key="1" tab="活动日期" force-render>
                                    <a-timeline>
                                    <a-timeline-item>Create at {{item.startDate}}</a-timeline-item>
                                    <a-timeline-item color="green">
                                        End at {{item.endDate}}
                                    </a-timeline-item>
                                    </a-timeline>
                                </a-tab-pane>
                                <a-tab-pane key="3" tab="优惠内容">
                                    折扣{{item.discount}}
                                    达标金额{{item.targetMoney}}
                                    优惠金额{{item.discountMoney}}
                                </a-tab-pane>
                            </a-tabs>
                        </a-card>

                    </a-list-item>
                    <a-list-item>

                    </a-list-item>
                </a-list>
            </a-tab-pane>
            <a-tab-pane tab="广告投放" key="2">
                    <a-upload
                            action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                            list-type="picture"
                            :file-list="fileList"
                            :customRequest="testUpload"
                    >
                        <a-button > <a-icon type="upload" /> upload </a-button>
                    </a-upload>
            </a-tab-pane>
            <a-tab-pane tab="酒店优惠策略" key="3">
                <InfoCard :hotel="item" v-for="item in hotelList" :key="item.index" @click.native="jumpToDetails(item.id)"></InfoCard>
            </a-tab-pane>
        </a-tabs>
        <AddCouponModal></AddCouponModal>
        <UpdateCoupon></UpdateCoupon>
    </div>
</template>

<script>
    let OSS = require('ali-oss');
    // let client=new OSS({
    //      accessKeyId:'LTAI4G5y6f1Hk4R5wVsPbQZ4',
    //      accessKeySecret:'Aw3CbQFmBw8vyk7lmW3j1jL7LL6xpn',
    //      bucket:'test-nju-1',
    //      region:'oss-cn-shenzhen',
    //      secure: true,
    // })
    let client=new OSS({
        accessKeyId:'LTAI4GAh9znuBWPxpPX1q5z5',
        accessKeySecret:'2s6H9ljuP6oO3aqLosalsUfTx35siO',
        bucket:'obsidian222',
        region:'oss-cn-shenzhen',
        secure: true,
    })
    function getBase64(file) {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(reader.result);
            reader.onerror = error => reject(error);
        });
    }
    const data = [
        {
            id:1,
            title: '优惠 1',
            description:'双十一满减，买买买买买买mmp',
            type:'',
            name:'',
            targetMoney:0,
            discountMoney:0,
            discount:0,
            startDate:'2000-01-02',
            endDate:'2000-2-14',
        },
        {
            title: '优惠 2',
            description:'生日优惠',
            type:'',
            name:'',
            targetMoney:0,
            discountMoney:0,
            discount:0,
            startDate:'2000-01-02',
            endDate:'2000-2-14',
        },
        {
            title: '优惠 3',
            description:'双十一满减',
            type:'',
            name:'',
            targetMoney:0,
            discountMoney:0,
            discount:0,
            startDate:'2000-01-02',
            endDate:'2000-2-14',
        },
        {
            title: '优惠 4',
            description:'双十一满减',
            type:'',
            name:'',
            targetMoney:0,
            discountMoney:0,
            discount:0,
            startDate:'2000-01-02',
            endDate:'2000-2-14',
        },
        {
            title: '优惠 5',
            description:'双十一满减',
            type:'',
            name:'',
            targetMoney:0,
            discountMoney:0,
            discount:0,
            startDate:'2000-01-02',
            endDate:'2000-2-14',
        },
        {
            title: '优惠 6',
            description:'双十一满减',
            type:'',
            name:'',
            targetMoney:0,
            discountMoney:0,
            discount:0,
            startDate:'2000-01-02',
            endDate:'2000-2-14',
        },
    ];
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AddCouponModal from './components/addCouponModal'
    import UpdateCoupon from './components/updateCoupon'
    import InfoCard from "./components/infoCard";
    const moment = require('moment')
    export default {
        name: "saler",
        data(){
            return{
                data,
                previewImage: '',
                modify: true,
                picList:Array,

            }
        },
        components:{
            AddCouponModal,
            UpdateCoupon,
            InfoCard
        },
        async mounted() {
            await this.getFileList()
            await this.getAllCoupon()

            console.log("list",this.fileList)
        },
        computed: {
            ...mapGetters([
                'hotelList',
                'updateCouponVisible',
                'addCouponModalVisible',
                'allCouponlist',
                'fileList'
            ]),
        },
        methods:{
            ...mapMutations([
                'set_addCouponModalVisible',
                'set_updateCouponVisible',
                'set_currentCouponId'
            ]),
            ...mapActions([
                'getAllCoupon',
                'deleteCoupon',
                'getFileList',
                'addAD'
            ]),
            onChange(checked) {
                if(checked)
                    this.modify=true;
                else this.modify=false;
                console.log(`a-switch to ${checked}`);
            },
            addCoupon() {
                console.log("!!!!")
                this.set_addCouponModalVisible(true)
                this.getAllCoupon()
            },
            changeCoupon(id){
                this.set_currentCouponId(id)
                this.set_updateCouponVisible(true)
                this.getAllCoupon()
            },
            deleteCouponB(id){
                this.deleteCoupon(id);
                this.getAllCoupon()
            },
            callback(){},
            async testUpload(file){
                console.log(file)

                try {
                    //object-name可以自定义为文件名（例如file.txt）或目录（例如abc/test/file.txt）的形式，实现将文件上传至当前Bucket或Bucket下的指定目录。
                    let result = await client.put(file.file.name, file.file);
                    console.log(result);
                    console.log("ipresult")
                    const tempData={
                        uid:(-1*this.fileList.length-1).toString(),
                        name:result.name,
                        status:'done',
                        url:result.url,
                        thumbUrl:result.url
                    }
                    await this.addADs(tempData)

                } catch (e) {
                    console.log(e);
                }
                return new Promise(resolve => {
                    // const reader = new FileReader();
                    // reader.readAsDataURL(file);
                    // reader.onload = () => {
                    //     const canvas = document.createElement('canvas');
                    //     const img = document.createElement('img');
                    //     img.src = reader.result;
                    //     img.onload = () => {
                    //         const ctx = canvas.getContext('2d');
                    //         ctx.drawImage(img, 0, 0);
                    //         ctx.fillStyle = 'red';
                    //         ctx.textBaseline = 'middle';
                    //         ctx.fillText('Ant Design', 20, 20);
                    //         canvas.toBlob(resolve);
                    //     };
                    // };
                });
            },
            async addADs(pic){
                await this.addAD(pic)
                await this.getFileList()
                console.log("???",this.fileList)
            }
        }

    }
</script>

<style scoped lang="less">
    .saler-coupon {
        .hotelCard {
            margin: 10px 10px;
            min-width: 180px;
            max-height: 350px;
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