<template>
    <a-modal
            :visible="addCommentVisible"
            title="添加订单评论"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
<!--            <a-form-item label="给出你的星级" v-bind="formItemLayout">-->
<!--                <a-select-->
<!--                        v-decorator="[-->
<!--                        'rate',-->
<!--                        {rules:[{required:true,message: '请选择你的评分'}]}-->
<!--                    ]">-->
<!--                    <a-select-option value=1>1:terrible</a-select-option>-->
<!--                    <a-select-option value=2>2:bad</a-select-option>-->
<!--                    <a-select-option value=3>3:normal</a-select-option>-->
<!--                    <a-select-option value=4>4:good</a-select-option>-->
<!--                    <a-select-option value=5>5:wonderful</a-select-option>-->
<!--                </a-select>-->
<!--            </a-form-item>-->

            <a-form-item label="给出你的星级" v-bind="formItemLayout">
                <span>
                    <a-rate v-model="values" :tooltips="desc" />
                    <span class="ant-rate-text">{{ desc[value - 1] }}</span>
                </span>
            </a-form-item>

            <a-form-item label="评价" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :row="4"
                        placeholder="请填写你的评论"
                        v-decorator="['description',{rules:[{required:true, message:'请填写优惠简介'}]}]"/>
            </a-form-item>

        </a-form>
        <!-- 这里是添加策略模态框区域，请编写表单 -->
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'addCommentModal',
        data() {
            return {
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
                desc: ['terrible', 'bad', 'normal', 'good', 'wonderful'],
                values:1,
            }
        },
        computed: {
            ...mapGetters([
                'activeOrderId',
                'addCommentVisible',
                'userId'
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'addCommentModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addCommentVisible',
                'set_commentParams'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'addComment'
            ]),
            cancel() {
                this.set_addCommentVisible(false)
            },
            changeType(v){
                if( v == '3') {

                }else{
                    this.$message.warning('请实现')
                }
            },
            handleSubmit(e) {

                e.preventDefault();

                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                         content: this.form.getFieldValue('description'),
                            rate: this.values,
                        }
                        // alert("wow1")
                        // this.set_addCouponStrategyParams(data)
                        //alert("wow2")
                        this.set_commentParams(data)
                        this.addComment()
                    }
                });
            },
        }
    }
</script>