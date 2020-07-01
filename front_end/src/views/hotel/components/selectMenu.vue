
<template>
    <div>
        <a-button type="primary" @click="showDrawer"> <a-icon type="plus" /> 详细搜索 </a-button>
        <a-drawer
                title="Create a new account"
                :width="720"
                :visible="visible"
                :body-style="{ paddingBottom: '80px' }"
                @close="onClose"
        >
            <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
                <a-form-item label="酒店名">
                    <a-input
                            placeholder="请填写酒店名称关键词"
                            v-decorator="['hotelName', { rules: [{  message: '请填写酒店名称' }] }]"
                    />
                </a-form-item>
                <a-form-item label="酒店地址" v-bind="formItemLayout">
                    <a-input
                            placeholder="请填写酒店地址关键词"
                            v-decorator="['address', { rules: [{ message: '请填写酒店地址' }] }]"
                    />
                </a-form-item >

                <a-form-item label="酒店星级" v-bind="formItemLayout">

                     最低星级<a-rate v-model="hotelStarNum1"  />
                    <br/>
                    最高星级:<a-rate v-model="hotelStarNum2"  />
                </a-form-item>
                <a-form-item label="价格区间" v-bind="formItemLayout">
                    <a-slider range :marks="marks" :default-value="[100, 500]" :min="0" :max="2000" @change="getMoney"/>
                </a-form-item>
                <a-form-item label="评分区间" v-bind="formItemLayout">
                    <a-row>
                        <a-col :span="4">最低评分</a-col>
                        <a-col :span="12">
                            <a-slider v-model="inputValue1" :min="0" :max="5" />
                        </a-col>
                        <a-col :span="4">
                            <a-input-number v-model="inputValue1" :min="0" :max="5" style="marginLeft: 16px" />
                        </a-col>
                    </a-row>
                    <a-row>
                        <a-col :span="4">最高评分</a-col>
                        <a-col :span="12">
                            <a-slider v-model="inputValue" :min="0" :max="5" />
                        </a-col>
                        <a-col :span="4">
                            <a-input-number
                                    v-model="inputValue"
                                    :min="0" :max="5"
                                    style="marginLeft: 16px"
                            />
                        </a-col>
                    </a-row>
                </a-form-item>
            </a-form>
            <div
                    :style="{
                      position: 'absolute',
                      right: 0,
                      bottom: 0,
                      width: '100%',
                      borderTop: '1px solid #e9e9e9',
                      padding: '10px 16px',
                      background: '#fff',
                      textAlign: 'right',
                      zIndex: 1,
                    }"
                        >
                <a-button :style="{ marginRight: '8px' }" @click="onClose">
                    Cancel
                </a-button>
                <a-button type="primary" @click="handleSubmit">
                    Submit
                </a-button>
            </div>
        </a-drawer>
    </div>
</template>

<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    export default {
        name: "selectMenu",
        data() {
            return {
                marks: {
                    0: '0',
                    100: '100',
                    500: '500',
                    2000: {
                        style: {
                            color: '#f50',
                        },
                        label: '2000',
                     }
                },
                inputValue: 5,
                inputValue1: 0,
                money1:100,
                money2:500,
                hotelStarNum1:1,
                hotelStarNum2:5,
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
                form: this.$form.createForm(this),
                visible: false,
                selectMenuVisible:false
            };
        },

        computed: {
            ...mapGetters([

            ]),
            image_url:function () {
               return "https://test-nju-1.oss-cn-shenzhen.aliyuncs.com/hotel/timg.png"
            },


        },
        async mounted() {

        },

        methods: {
            ...mapMutations([

            ]),
            ...mapActions([
                'getHotelMatch',
            ]),
            showDrawer() {
                this.visible = true;
            },
            onClose() {
                this.visible = false;
            },
            getMoney(value){
                this.money1=value[0]
                this.money2=value[1]
                console.log(value)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            name: this.form.getFieldValue('hotelName')==undefined?"":this.form.getFieldValue('hotelName'),
                            address: this.form.getFieldValue('address')==undefined?"":this.form.getFieldValue('address'),
                            rate:[this.inputValue1,this.inputValue],
                            money:[this.money1,this.money2],
                            hotelStar:[this.hotelStarNum1,this.hotelStarNum2]
                        }
                        this.getHotelMatch(data)
                        this.onClose()
                    }
                });
            },
        },


    };
</script>