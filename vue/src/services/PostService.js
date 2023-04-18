import axios from "axios";
import store from '../store/index';

const http = axios.create({
    baseURL: "http://localhost:9000"
})

export default{
    getPostList(){
        return http.get('/posts')
    },

    getPostById(id){
        return http.get(`/posts/${id}`)
    },

    getPostsByForumId(id){
        return http.get(`/forums/${id}/posts`)
    },

    createPost(post){
        return http.post('/posts', post, {headers: {'Authorization': 'Bearer ' + store.state.token}})
    },

    deletePost(post){
        console.log(post)
        return http.delete('/posts', {params: {forumId: parseInt(post.forumId), authorName: post.authorName, postId: parseInt(post.postId)}}, {headers: {'Authorization': 'Bearer ' + store.state.token}})
    }
}