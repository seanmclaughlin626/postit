<template>
  <div>
      <ul v-for="post in $store.state.posts" v-bind:key="post.id">
          <li>{{post.content}}</li>
      </ul>
  </div>
</template>

<script>
import PostService from '../services/PostService.js';

export default {
    name: 'post-list',
    methods: {
        getPostsList(){
            PostService.getPostList().then(response => {
                this.$store.commit("SET_POSTS", response.data)
            })
        }
    },
    created(){
        this.getPostsList();
    }
}
</script>

<style>

</style>