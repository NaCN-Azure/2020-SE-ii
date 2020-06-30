<template>
        <a-carousel autoplay>
<!--        <a slot="customPaging" slot-scope="props">-->
<!--            <img :src="getImgUrl(props.i)" />-->
<!--        </a>-->

<!--            <div><h3>1</h3></div>-->
        <div v-for="item in 4" :key="item" >
            <div><img class="img" :src="getImgUrl(item)" /></div>
        </div>
    </a-carousel>
</template>
<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    const baseUrl ='https://test-nju-2.oss-cn-beijing.aliyuncs.com/OIP.jpg'
    export default {
        name:'couponActive',
        data() {
            return {
                baseUrl,
            };
        },
        props:{fileList1:{type:Array},},
        computed: {
            ...mapGetters([
                'hotelList',
                'hotelListLoading',
            ])
        },
        async mounted(){

        },
        methods: {
            getImgUrl(i) {
                return this.fileList1[i].url;
            },
            onChange(a, b, c) {
                console.log(a, b, c);
            },
            ...mapActions([
                'getFileList',
            ]),

        },
    };
</script>

<style scoped>
    /* For demo */
    .ant-carousel >>> .slick-slide {
        text-align: center;
        height: 160px;
        width: 90%;
        line-height: 50px;
        /*background: #364d79;*/
        overflow:auto;

    }

    .ant-carousel >>> .slick-slide h3 {
        color: #fff;
    }
    .img{
        max-width: 100%;
        /*max-height: 100%;*/
        display: block;
        margin: auto;
    }
</style>
