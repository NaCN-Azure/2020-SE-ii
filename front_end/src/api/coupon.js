import { axios } from '@/utils/request'
const api = {
    couponPre: '/api/coupon'
}
export function hotelTargetMoneyAPI(data) {
    console.log(data)
    return axios({
        url: `${api.couponPre}/hotelTargetMoney`,
        method: 'POST',
        data,
    })
}

export function hotelAllCouponsAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}

export function orderMatchCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}

export function updateCouponAPI(params) {
    return axios({
        url: `${api.couponPre}/updateCouponInfo`,
        method: 'POST',
        params,
    })
}