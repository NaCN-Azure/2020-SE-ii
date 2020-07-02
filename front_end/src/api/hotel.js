import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function getHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'get',
    })
}
export function getHotelUrlsAPI() {
    return axios({
        url: `${api.hotelPre}/allUrls`,
        method: 'GET',
    })
}
export function getHotelUrlByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/hotelPic`,
        method: 'GET',
    })
}
export function getHotelByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/detail`,
        method: 'GET',
    })
}
export function deleteHotelByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/deleteHotel`,
        method: 'GET',
    })
}

export function getRoomsByHotelIdAPI(hotelId) {
    return axios({
        url: `${api.hotelPre}/hotelAllRooms?hotelId=${hotelId}`,
        method: 'GET',
    })
}

export function updateHotelUrlAPI(data) {

    return axios({
        url: `${api.hotelPre}/updateUrl`,
        method: 'POST',
        data
    })
}
export function updateHotelInfoAPI(data) {

    return axios({
        url: `${api.hotelPre}/update`,
        method: 'POST',
        data
    })
}
export function searchRoomlByDateAPI(data) {
    console.log(data)
    return axios({
        url: `${api.hotelPre}/searchRoomlByDate?hotelId=${data.hotelId}&start=${data.start}&end=${data.end}`,
        method: 'GET',
    })
}
export function searchHotelAPI(data) {

    return axios({
        url: `${api.hotelPre}/searchHotel?key=${data}`,
        method: 'GET',
    })
}
export function searchMultipleHotelAPI(data) {
    console.log('data',data)

    return axios({
        url:`${api.hotelPre}/searchMultipleHotel`,
        method: 'POST',
        data
    })
}

