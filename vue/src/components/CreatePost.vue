<template>
  <div>
      <form v-on:submit="addPost">
          <label for="title">Title:</label>
          <input id="title" type="text" v-model="post.title">
          <br/>
          <label for="content">Content:</label>
          <textarea name="content" id="content" cols="30" rows="10" v-model="post.content"></textarea>
          <button type="submit">Post It!</button>
      </form>
  </div>
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
                author: 0,
                title: "",
                content: ""
            }
        }
    },
    methods: {
        addPost(){
            this.post.author = this.$store.state.user.id;
            PostService.createPost(this.post).then(response => {
                if(response.status === 201){
                  console.log("Post created!");
                //   TODO bootstrap alert instead of log
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
