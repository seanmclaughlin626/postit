<template>
  <div>
    <div class="buttons">
      <button>Most Recent</button>
      <button>Most Popular</button>
    </div>
    <div>
      <ul v-for="post in $store.state.currentForumPosts" v-bind:key="post.id">
        <li>{{post.title}}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import postService from '../services/PostService';

export default {
name: 'posts-in-forum',
props: {forum: Object},
computed: {

},
created(){
  postService.getPostsByForumId(this.$route.params.id).then((response) => {
        this.$store.commit("SET_CURRENT_FORUM_POSTS", response.data);
});
}
}
</script>

<style>
</style>
