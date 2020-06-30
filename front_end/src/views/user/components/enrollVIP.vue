<template>
    <a-modal
            :visible="enrollVIPVisible"
            title="注册会员"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">

            <a-form-item label="会员类型" v-bind="formItemLayout">
                   <a-select
                           @change="changeVisible"
                           v-decorator="[
                        'VIPType',
                        {rules:[{required:true,message: '请选择注册会员的类型'}]}
                    ]">
                    <a-select-option value=1>普通会员</a-select-option>
                    <a-select-option value=2>企业会员</a-select-option>
                </a-select>
            </a-form-item>

            <a-form-item label="企业名称" v-bind="formItemLayout" v-if="corporation">
                <a-input placeholder="请填写企业名称" v-decorator="['name',{rules: [{required: true,message: '请填写企业名称'}]}]"/>
            </a-form-item>

        </a-form>
        <!-- 这里是添加策略模态框区域，请编写表单 -->
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'enrollVIP',
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
                corporation:false,
            }
        },
        computed: {
            ...mapGetters([
                "enrollVIPVisible"
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'enrollVIP' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_enrollVIPVisible',
                'set_commentParams'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                // 'addComment'
                'enrollVIPi'
            ]),
            cancel() {
                this.set_enrollVIPVisible(false)
            },
            changeVisible(v){
                this.corporation=v==2?true:false;
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
                            // VIPType: this.form.getFieldValue('VIPType'),
                            corporationName:this.form.getFieldValue('name')
                        }
                        if(data.corporationName==undefined){data.corporationName=""}
                        this.enrollVIPi(data)
                        // this.set_commentParams(data)
                        // this.addComment()
                    }
                });
            },
        }
    }
</script>