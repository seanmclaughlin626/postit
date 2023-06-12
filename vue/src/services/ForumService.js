import axios from "axios";
import store from '../store/index'

const http = axios.create({
    baseURL: process.env.VUE_APP_REMOTE_API
})

export default {
    getForumList(){
        return http.get('/forums');
    },
    getForum(id){
        return http.get(`/forums/${id}`);
    },
    getMods(id){
        return http.get(`/forums/${id}/mods`);
    },
    createForum(forum){
        return http.post('/forums', forum, {headers: {'Authorization': 'Bearer ' + store.state.token}});
    },
    promoteUserToMod(id, user){
        return http.post(`/forums/${id}/mods`, user);
    },
    getFavoriteForumIds(){
        return http.get('/favorite-forum-ids', {params: {id: parseInt(store.state.user.id)}});
    },
    getFavoriteForums(){
        return http.get('/favorite-forums', {params: {id: parseInt(store.state.user.id)}});
    },
    addFavoriteForum(forumId){
        return http.post("/favorite-forums", {forumId: parseInt(forumId), userId: parseInt(store.state.user.id)}, {headers: {'Authorization': 'Bearer ' + store.state.token}});
    },
    forumSearch(search){
        return http.get("/forum-search", {params: {searchInput: search.searchInput}});
    }
}