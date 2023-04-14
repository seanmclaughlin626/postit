import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
})
// axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.$store.state.token;

export default {
    getForumList(){
        return http.get('/forums');
    },
    getForum(id){
        return http.get(`/forums/${id}`);
    },
    createForum(forum){
        return http.post('/forums', forum);
    }
}