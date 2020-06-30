<template>
    <a-modal
            :visible="updateUserModalVisible"
            title="修改用户信息"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-Form :form="form">
            <a-form-item v-bind="formItemLayout" label="用户邮箱">
                <a-input
                        v-decorator="[
                        'email',
                        { rules: [{required: true, message: '请输入用户邮箱', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="密码">
            <a-input
                    v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码', }] }
                    ]"
            />
            </a-form-item >
            <a-form-item v-bind="formItemLayout" label="用户名">
                <a-input
                        v-decorator="[
                        'name',
                        { rules: [{required: true, message: '请输入用户名字', }] }
                    ]"
                />
            </a-form-item >
            <a-form-item v-bind="formItemLayout" label="酒店编号">
                <a-input
                        v-decorator="[
                        'hotelid',
                        { rules: [{required: true, message: '请输入酒店id', }] }
                    ]"
                />
            </a-form-item >
        </a-Form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'updateUsersModal',
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
            }
        },
        computed: {
            ...mapGetters([
                'updateUserModalVisible',
                'managerList',
                'addManagerParams',
                'updateUsersParams'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'addManagerModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addManagerModalVisible',
                'set_addManagerParams',
                'set_updateUsersParams',
                'set_updateUserModalVisible',
            ]),
            ...mapActions([
                'getManagerList',
                'addManager',
                'updateUsers',
            ]),
            cancel() {
                this.set_updateUserModalVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            email: this.form.getFieldValue('email'),
                            password: this.form.getFieldValue('password'),
                            userName: this.form.getFieldValue('name'),
                            hotelid: this.form.getFieldValue('hotelid'),
                        }
                        this.set_updateUsersParams(data)
                        const value={record:this.updateUsersParams,type:'change'}
                        this.updateUsers(value);
                    }
                });
            },
        }
    }
</script>