import { axios } from '@/utils/request'
const api = {
    salePre: '/api/saler'
}
//temp
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.salePre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function addWebCouponAPI(data) {
    console.log(data)
    return axios({
        url: `${api.salePre}/addWebCoupon`,
        method: 'POST',
        data,
    })
}
export function webAllCouponsAPI() {
    return axios({
        url: `${api.salePre}/webAllCoupons`,
        method: 'GET',
    })
}
export function getWebCouponByIdAPI(id) {
    return axios({
        url: `${api.salePre}/getWebCouponById`,
        method: 'GET',
        params: {id:id},
    })
}
export function updateWebCouponAPI(data) {

    return axios({
        url: `${api.salePre}/updateWebCoupon`,
        method: 'POST',
        data,
    })
}
export function deleteWebCouponAPI(couponId) {
    return axios({
        url: `${api.salePre}/deleteWebCoupon`,
        method: 'POST',
        params: {CouponId:couponId},
    })
}
export function addCreditByIdAPI(data) {
    console.log(data)
    return axios({
        url: `${api.salePre}/addCreditById`,
        method: 'POST',
        data,
    })
}
export function addADAPI(data) {

    return axios({
        url: `${api.salePre}/addAD`,
        method: 'POST',
        data,
    })
}
export function getFileListAPI(){

    return axios({
        url: `${api.salePre}/getFileList`,
        method: 'GET',
    })
}