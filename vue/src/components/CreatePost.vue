<template>
  <div>
      <form v-on:submit="addPost">
          <label for="title">Title:</label> 
          <br>
          <input id="title" type="text" v-model="post.title">
          <br>
          <label for="content">Content:</label>
          <br>
          <input type="text" name="content" id="content" v-model="post.content">
          <br>
          <label for="url">Add an Image (optional): </label>
          <br>
          <input type="text" name="url" v-model="post.url">
          <br>
          <b-button style="background-color: #60233f;" type="submit">Post It!</b-button>
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
                content: "",
                url: null
            }
        }
    },
    methods: {
        addPost(){
            this.post.author = this.$store.state.user.id;
            PostService.createPost(this.post).then(response => {
                if(response.status === 201){
                //   console.log("Post created!");
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


<style scoped>
form{
  color:#000;
  background:#ffc;
  display:block;
  height:20em;
  width:20em;
  padding:1em;
  margin:1em;
  box-shadow: 5px 5px 7px rgba(33,33,33,.7);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
label{
font-weight: bold;
  font-size: 1rem;
  font-family: courier, monospace;
  margin-bottom: -1rem;
}

</style>
