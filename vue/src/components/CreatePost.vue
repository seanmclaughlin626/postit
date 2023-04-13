<template>
  <div></div>
</template>

<script>
import PostService from '../services/PostService'
import UserService from '../services/UserService'

export default {
    name: 'create-post',
    props: ['forumId'],
    data(){
        return {
            post: {
                forumId: this.forumId,
                author: "",
                title: "",
                content: ""
            }
        }
    },
    methods: {
        addPost(){
            PostService.createPost(this.post).then(response => {
                if(response.status === 201){
                  location.reload()
                }
            })
        },
        getUser(){
            UserService.getUserIdByUsername().then(response => {
                return response.data;
            })
        },
    },
    created() {
        this.getUser()
    }
}
</script>

<style>

</style>