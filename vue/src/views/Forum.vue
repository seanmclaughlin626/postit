<template>
  <div>
    <h1 id="forum-name">{{forum.name}}</h1>
    <button v-on:click="creatingPost = !creatingPost" v-if="$store.state.token !== ''">Make a Post!</button>
    <create-post :forumId="parseInt(this.$route.params.id)" v-show="creatingPost"/>
    <posts-in-forum v-bind:forum="forum"/>
  </div>
</template>

<script>
import CreatePost from '../components/CreatePost.vue';
import PostsInForum from '../components/PostsInForum.vue';
import ForumService from '../services/ForumService';


export default {
    name: "forum",
    components: {
      PostsInForum,
      CreatePost
    },
    data(){
      return {
      creatingPost: false
      }
    },
    computed: {
      forum(){
        let forumArray = this.$store.state.forums.filter((f) => {
          return f.id == this.$route.params.id;
        });
        if(forumArray.length > 0){
          return forumArray[0];
        } else {
          return {id: 0, name: "", lastInteraction: null};
        }
      }
    },
    created(){
      ForumService.getForumList().then(response => {
        this.$store.commit("SET_FORUMS", response.data);
    });
}
}
</script>

<style>
#forum-name{
  text-align: center;
}
</style>