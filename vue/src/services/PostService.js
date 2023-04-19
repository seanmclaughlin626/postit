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

    deletePostx(post, token){
        console.log("the token should be here")
        console.log(token);
        console.log(post)
        return http.delete('/posts', {params: {forumId: parseInt(post.forumId), authorName: post.authorName, postId: parseInt(post.postId)}, headers: {'Authorization': 'Bearer ' + token}})
    },

    addVotedUser(userId, postId, vote){
        console.log("info should be here")
        console.log(userId)
        console.log(postId)
        console.log(vote)
        return http.post(`/posts/${postId}/voted-user`, {params: {userId: parseInt(userId), vote: parseInt(vote)},  headers: {'Authorization': 'Bearer ' + store.state.token}})
    }
}