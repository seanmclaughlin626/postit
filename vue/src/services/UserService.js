import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"})

    export default{
        getUserIdByUsername(){
            return http.get('/creator')
        },

        userSearch(search){
            console.log(search)
            return http.get('/search-users', {params: {searchInput: search.searchInput}})
        }
    }