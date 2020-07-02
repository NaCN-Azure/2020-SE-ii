import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function addRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}
export function addHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })
}
export function deleteRoomAPI(data) {
    console.log(data)
    return axios({
        url: `${api.hotelPre}/deleteRoomAPI`,
        method: 'POST',
        params: {id: data},
    })
}
export function changeRoomAPI(data) {
    console.log(data)
    return axios({
        url: `${api.hotelPre}/changeRoomAPI`,
        method: 'POST',
        data,
    })
}
export function hotelAllRoomsAPI(hotelId) {
    console.log('here')
    return axios({
        url: `${api.hotelPre}/hotelAllRooms`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}