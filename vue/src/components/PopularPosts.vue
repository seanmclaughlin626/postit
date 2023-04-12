<template>
<div>
    <h1 v-for= "post in this.$store.state.posts" v-bind:key="post.postId">{{post.title}}</h1>
</div>
</template>

<script>
import postService from '../services/PostService';

export default {

  name: "popular-posts",
  methods: {
    getPosts() {
      postService.getPostList().then((response) => {
        this.$store.commit("SET_POSTS", response.data);
      });
    },
  },

  computed: {
    recentPosts(){
      let yesterday = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
      return this.$store.posts.filter((post) => {
        return post.timeCreated >= yesterday; 
      })
    },
    popularPosts(){
        let orderedPosts = this.recentPosts().sort((post1, post2) => {
            return post1.upvoteScore > post2.upvoteScore ? 1 : post2.upvoteScore > post1.upvoteScore ? -1 : 0;
        })
        if(orderedPosts.length > 10){
        return orderedPosts.slice(0,10);
        } else {
            return orderedPosts.slice(0, orderedPosts.length);
        }
    }
  },
    created(){
        this.getPosts();
    }
};
</script>

<style scoped>
</style>