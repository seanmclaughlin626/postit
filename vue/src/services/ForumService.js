import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
})

export default {
    getForumList(){
        return http.get('/forums');
    },
    getForum(id){
        return http.get(`/forums/${id}`)
    }
}