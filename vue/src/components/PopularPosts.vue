<template>
<div>
    <h1 v-for= "post in this.popularPosts()" v-bind:key="post.postId">{{post.title}}</h1>
</div>
</template>

<script>
import HomeService from "../services/HomeService";

export default {
  name: "popular-posts",
  methods: {
    getPosts() {
      HomeService.getAllPosts().then((response) => {
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
            return post1.timeCreated > post2.timeCreated ? 1 : post2.timeCreated > post1.timeCreated ? -1 : 0;
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