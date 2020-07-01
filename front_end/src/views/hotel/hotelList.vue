<template>
  <div class="hotelList">
    <a-layout>
        <a-layout-content style="min-width: 800px">
          <a-spin :spinning="hotelListLoading">

              <div class="temp">
                  <coupon-active :file-list1="fileList"></coupon-active></div>
              <div style="width: 100%;">
                  <div style="width: 100%; text-align: right; margin:20px 0">
                      <a-input-search
                              placeholder="请输入酒店名字或者是地址"
                              enter-button="Search"
                              style="width: 400px;"
                              @search="onSearch"
                      />
                      <br/><br/>
                      <select-menu >详细搜索</select-menu>
                  </div>

              </div>
            <div class="card-wrapper">

                <HotelCard :hotel="item" :hotel-url="getUrl(item)" v-for="item in hotelList" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
                <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                </div>
                <a-pagination showQuickJumper :total="hotelList.totalElements" :defaultCurrent="1" @change="pageChange"></a-pagination>
            </div>

          </a-spin>
      </a-layout-content>
    </a-layout>
  </div>
</template>
<script>
import HotelCard from './components/hotelCard'
import { mapGetters, mapActions, mapMutations } from 'vuex'
import Coupon from "../hotelManager/components/coupon";
 import CouponActive from "./components/couponActive";
import SelectMenu from "./components/selectMenu";

export default {
  name: 'home',
  components: {
      SelectMenu,
      CouponActive,
      // Coupon,
    HotelCard
  },
  data(){
    return{
      emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}]
    }
  },
  async mounted() {
    await this.getHotelList()
      await this.getHotelUrlList()
      await this.getFileList()
  },
  computed: {
    ...mapGetters([
      'hotelList',
      'hotelListLoading',
        'fileList',
        'hotelUrlList'
    ])
  },
  methods: {
    ...mapMutations([
      'set_hotelListParams',
      'set_hotelListLoading'
    ]),
    ...mapActions([
      'getHotelList',
        'getFileList',
        'searchHotel',
        'getHotelUrlList'
    ]),
      getUrl(item){
        for(var i=0;i<this.hotelUrlList.length;i++){
            if(item.id==this.hotelUrlList[i].id){return this.hotelUrlList[i]}
        }
        return {url:require("@/assets/cover.jpeg")}
      },

    pageChange(page, pageSize) {
      const data = {
        pageNo: page - 1
      }
      this.set_hotelListParams(data)
      this.set_hotelListLoading(true)
      this.getHotelList()
    },
    jumpToDetails(id){
      this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
    },
      onSearch(value){

        this.searchHotel(value)
    },
  }
}
</script>
<style scoped lang="less">
  .hotelList {
    text-align: center;
    padding: 50px 0;
    .emptyBox {
      height: 0;
      margin: 10px 10px
    }
    .card-wrapper{
      display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
        flex-grow: 3;
        min-height: 800px
    }
    .card-wrapper .card-item {
      margin: 30px;
      position: relative;
      height: 188px;
    }
      .temp{
          text-align: center;
          width: 70%;
          margin-left: auto;
          margin-right: auto;

      }
  }
</style>