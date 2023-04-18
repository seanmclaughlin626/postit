import axios from 'axios';
import store from '../store/index';

const http = axios.create({
    baseURL: "http://localhost:9000"
})

export default {
    getCommentsByPostId(id) {
        return http.get(`/posts/${id}/comments`);
    },

    getCommentByCommentId(id) {
        return http.get(`/comments/${id}`)
    },

    createComment(comment) {
        return http.post('/comments', comment, { headers: { 'Authorization': 'Bearer ' + store.state.token } })
    },

    deleteComment(comment) {
        return http.delete('/comments', { params: { commentId: parseInt(comment.commentId), authorId: comment.authorId, postId: parseInt(comment.postId) } }, { headers: { 'Authorization': 'Bearer ' + store.state.token } })
    }

}