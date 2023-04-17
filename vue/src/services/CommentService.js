import axios from 'axios';
import store from '../store/index';

const http = axios.create({
    baseURL: "http://localhost:9000"
})

export default{
    getCommentsByPostId(id){
        return http.get(`/posts/${id}/comments`);
    },

    getCommentByCommentId(id){
        return http.get(`/comments/${id}`)
    },

    createComment(comment){
            return http.post('/comments', comment, {headers: {'Authorization': 'Bearer ' + store.state.token}})
        }
}