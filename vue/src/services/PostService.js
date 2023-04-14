import axios from "axios";
import store from '../store/index';

const http = axios.create({
    baseURL: "http://localhost:9000"
})

axios.defaults.headers.common['Authorization'] = 'Bearer ' + store.state.token;

export default{
    getPostList(){
        return http.get('/posts')
    },

    getPostsByForumId(id){
        return http.get(`/forums/${id}/posts`)
    },

    createPost(post){
        return http.post('/posts', post)
    }
}