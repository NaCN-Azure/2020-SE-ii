<template>
    <a-modal
            :visible="updateCouponVisible"
            title="修改优惠策略"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="优惠劵类型" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                        'coupontype',
                        {rules:[{required:true,message: '请选择优惠券类型'}]}
                    ]">
                    <a-select-option value=1>生日优惠</a-select-option>
                    <a-select-option value=2>多间优惠</a-select-option>
                    <a-select-option value=3>满减优惠</a-select-option>
                    <a-select-option value=4>限时优惠</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="券名" v-bind="formItemLayout">
                <a-input placeholder="请填写券名" v-decorator="['name',{rules: [{required: true,message: '请填写券名'}]}]"/>
            </a-form-item>
            <a-form-item label="优惠简介" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :row="4"
                        placeholder="请填写优惠简介"
                        v-decorator="['description',{rules:[{required:true, message:'请填写优惠简介'}]}]"/>
            </a-form-item>
            <a-form-item label="有效时间" v-bind="formItemLayout">
                <a-range-picker
                        format="YYYY-MM-DD"
                        v-decorator="[
                        'date',
                        {
                            rules: [{ required: true, message: '请选择优惠时间' }]
                        }
                    ]"
                        :placeholder="['开始日期','失效日期']"
                />
            </a-form-item>
            <a-form-item label="达标金额" v-bind="formItemLayout">
                <a-input placeholder="请填写达标金额" v-decorator="['targetnum',{rules: [{required: true,message: '请填写达标金额'}]}]"/>
            </a-form-item>
            <a-form-item label="优惠金额" v-bind="formItemLayout">
                <a-input placeholder="请填写优惠金额" v-decorator="['decreasenum',{rules: [{required: true,message: '请填写优惠金额'}]}]"/>
            </a-form-item>
            <a-form-item label="折扣" v-bind="formItemLayout">
                <a-input-number style="width: 100%" placeholder="请填写折扣" v-decorator="['discount',{rules: [{required: true,message: '请填写折扣'}]}]"
                                :min="0" :max="9.5" :default-value="-1" :step="0.5"
                />
            </a-form-item>
        </a-form>
    </a-modal>


</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    const moment = require('moment')
    export default {
        name: "updateCoupon",
        data(){
            return{
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
            }
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'addCouponModal' });
        },
        async mounted() {
            await this.getWebCouponById()
        },
        computed: {
            ...mapGetters([
                'updateCouponVisible',
                'currentCouponId'
            ])
        },
        methods:{
            ...mapMutations([
                "set_updateCouponParams",
                'set_updateCouponVisible'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'updateWebCoupon',
                'getWebCouponById'
            ]),
            cancel() {
                this.set_updateCouponVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                            name: this.form.getFieldValue('name'),
                            description: this.form.getFieldValue('description'),
                            type: this.form.getFieldValue('coupontype'),
                            startTime: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                            endTime: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
                            targetMoney: this.form.getFieldValue('targetnum'),
                            discountMoney: this.form.getFieldValue('decreasenum'),
                            discount:this.form.getFieldValue('discount'),
                            id:this.currentCouponId,
                        }
                        //this.set_addCouponStrategyParams(data)
                        //alert("wow2")
                        console.log(data)
                        this.updateWebCoupon(data)
                    }
                });
            },
        }
    }
</script>

<style scoped>

</style>