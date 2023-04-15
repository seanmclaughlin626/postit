<template>
  <div>
    <div class="buttons">
      <b-button style="margin-top: 0.2rem; margin-right: 0.1rem; background-color: #60233f;" v-on:click="displayByPopular = true">Most Popular</b-button>
      <b-button style="margin-top: 0.2rem; margin-left: 0.1rem; background-color: #60233f;" v-on:click="displayByPopular = false">Most Recent</b-button>
    </div>
    <div v-show="!displayByPopular" class="list">
      <post v-for="post in recentPosts" v-bind:key="post.id" v-bind:post="post"/>
    </div>
    <div v-show="displayByPopular" class="list">
      <post v-for="post in popularPostsByUpvoteScore" v-bind:key="post.id" v-bind:post="post"/>
    </div>
  </div>
</template>

<script>
import postService from '../services/PostService';
import Post from './Post.vue';


export default {
name: 'posts-in-forum',
props: {forum: Object},
components: {Post},
computed: {

  recentPosts(){
          let posts =  this.$store.state.currentForumPosts;
          return posts.sort((post1, post2) => {
              let time1 = new Date(post1.timeCreated);
              let time2 = new Date(post2.timeCreated);
              return time1.getTime() > time2.getTime() ? -1 : time2.getTime() > time1.getTime() ? 1 : 0;
          })
    },

  popularPostsByUpvoteScore(){
        let posts =  this.$store.state.currentForumPosts;
        return posts.sort((post1, post2) => {
            return post1.upvoteScore > post2.upvoteScore ? -1 : post2.upvoteScore > post1.upvoteScore ? 1 : 0;
        })
  },

},
created(){
  postService.getPostsByForumId(this.$route.params.id).then((response) => {
        this.$store.commit("SET_CURRENT_FORUM_POSTS", response.data);
});
},
data() {
  return {
  displayByPopular: true
  }
}
}
</script>

<style>
.list{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.list post{
  margin:1em;
}
</style>
