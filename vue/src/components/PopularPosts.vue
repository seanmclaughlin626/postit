<template>
<div>
    <post v-for= "post in popularPosts" v-bind:key="post.postId" v-bind:post="post"/>
</div>
</template>

<script>
import postService from '../services/PostService';
import Post from './Post.vue';

export default {
  name: "popular-posts",
  components: {
    Post
  },
  methods: {
    getPosts() {
      postService.getPostList().then((response) => {
        this.$store.commit("SET_POSTS", response.data);
      });
    }
  },

  computed: {
    recentPosts(){
      let yesterday = new Date();
      yesterday.setHours(yesterday.getHours() - 24);
      let array = this.$store.state.posts.filter((post) => {
        let postDate = new Date(post.lastInteraction);
        return postDate.getTime() >= yesterday.getTime();
      })
      return array;
    },
    popularPosts(){
        let recentPosts = this.recentPosts;
        let orderedPosts = recentPosts.sort((post1, post2) => {
            return post1.upvoteScore > post2.upvoteScore ? -1 : post2.upvoteScore > post1.upvoteScore ? 1 : 0;
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