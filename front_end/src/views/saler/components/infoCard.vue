<template>
    <a-card hoverable style="width: 300px">
        <img
                slot="cover"
                alt="example"
                src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
        />
        <template slot="actions" class="ant-card-actions">
            <a-icon key="setting" type="setting" />
            <a-icon key="edit" type="edit" />
            <a-icon key="ellipsis" type="ellipsis" />
        </template>
        <a-card-meta :title="hotel.title">
        </a-card-meta>
    </a-card>
</template>

<script>
    export default {
        name: "infoCard",
        props: {
            hotel: {}
        },
        data() {
            return{
            }
        },
    }

</script>

<style scoped lang="less">
    .infoCard{
        margin: 10px 10px;
        min-width: 180px;
        max-height: 350px;
        img {
            height: 250px;
        }
    }
    .infoCard{
        .ant-card-body{
            padding: 12px
        }
    }

    .ant-card-hoverable:hover {
        box-shadow: 0 2px 8px rgba(0,0,0,0.4)
    }
</style>