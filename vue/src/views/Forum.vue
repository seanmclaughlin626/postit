<template>
  <div>
    <h1 id="forum-name">{{forum.name}}</h1>
    <create-post :forumId="parseInt(this.$route.params.id)"/>
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