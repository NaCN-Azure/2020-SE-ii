<template>
    <div>
        <a-modal
                :visible="couponVisible"
                title="优惠策略"
                width="900px"
                :footer="null"
                @cancel="cancel"
        >
            <div style="width: 100%; text-align: right; margin:20px 0">
                <a-button type="primary" @click="addCoupon"><a-icon type="plus" />添加优惠策略</a-button>
            </div>
            <a-table
                    :columns="columns"
                    :dataSource="couponList"
                    bordered
            >
            <span slot="couponType">
                <a-tag color="red">优惠类型</a-tag>
                <!--            <a-button type="danger" size="small">删除酒店</a-button>-->
            </span>
            </a-table>
            <!-- 这里是模态框内容区域，请编写列表代码与添加策略按钮 -->
        </a-modal>
        <AddCoupon></AddCoupon>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AddCoupon from './addCoupon'

    const columns = [
        // 这里定义列表头
    ];
    export default {
        name: 'coupon',
        data() {
            return {
                columns
            }
        },
        components: {
            AddCoupon,
        },
        computed: {
            ...mapGetters([
                'couponVisible',
                'couponList',
            ])
        },
        // async mounted() {
        //     await this.getHotelCoupon()
        //
        // },
        methods: {
            ...mapMutations([
                'set_addCouponVisible',
                'set_couponVisible',
            ]),
            ...mapActions([
                'getHotelCoupon'
            ]),
            cancel() {
                this.set_couponVisible(false)
            },
            addCoupon() {
                this.set_addCouponVisible(true),
                    this.set_couponVisible(false)
            },
        },
    }
</script>
<style scoped>

</style>