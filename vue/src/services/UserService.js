import axios from "axios";

const http = axios.create({
    baseURL: process.env.VUE_APP_REMOTE_API})

    export default{
        getUserIdByUsername(){
            return http.get('/creator')
        },

        userSearch(search){
            return http.get('/search-users', {params: {searchInput: search.searchInput}})
        },

        nonModSearch(forumId, search){
            return http.get(`/${forumId}/non-mod-search`, {params: {searchInput: search.searchInput}})
        },

        modSearch(forumId){
            return http.get(`/${forumId}/get-mods`)
        },

        votedUserSearch(postId){
            return http.get(`/posts/${parseInt(postId)}/get-users-voted`)
        }
    }