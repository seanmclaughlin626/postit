import axios from "axios";
import store from '../store/index';

const http = axios.create({
    baseURL: process.env.VUE_APP_REMOTE_API
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

    deletePostx(post, token){
        return http.delete('/posts', {params: {forumId: parseInt(post.forumId), authorName: post.authorName, postId: parseInt(post.postId)}, headers: {'Authorization': 'Bearer ' + token}})
    },

    addVotedUser(userId, postId, vote){
        return http.post(`/posts/${postId}/voted-user`, {userId: parseInt(userId), vote: parseInt(vote)},  {headers: {'Authorization': 'Bearer ' + store.state.token}})
    }
}