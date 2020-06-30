import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
}
export function getManagerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllManagers`,
        method: 'POST'
    })
}
export function  getClientListAPI(){

    return axios({
        url: `${api.adminPre}/getClientList`,
        method: 'POST'
    })
}
export function addManagerAPI(data) {

    console.log(data)
    return axios({
        url: `${api.adminPre}/addManager`,
        method: 'POST',
        data
    })
}
export function updateUsersAPI(data) {

    return axios({
        url: `${api.adminPre}/updateUsers`,
        method: 'POST',
        data,
    })
}