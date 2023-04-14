<template>
  <div>
    <button v-on:click="modView = !modView" v-show="currentUserIsMod">Moderator Page</button>
    <div id="modview" v-show="modView">
      <mod-view/>
    </div>
    <div id="main-forum-view" v-show="!modView">
    <h1 id="forum-name">{{forum.name}}</h1>
    <button v-on:click="creatingPost = !creatingPost" v-if="$store.state.token !== ''">Make a Post!</button>
    <create-post :forumId="parseInt(this.$route.params.id)" v-show="creatingPost"/>
    <posts-in-forum v-bind:forum="forum"/>
    </div>
  </div>
</template>

<script>
import CreatePost from '../components/CreatePost.vue';
import ModView from '../components/ModView.vue';
import PostsInForum from '../components/PostsInForum.vue';
import ForumService from '../services/ForumService';


export default {
    name: "forum",
    components: {
      PostsInForum,
      CreatePost,
      ModView
    },
    data(){
      return {
      creatingPost: false,
      modView: false,
      currentUserIsMod: false
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
    methods: {
      checkModStatus(){
        ForumService.getMods(this.$route.params.id).then((response) => {
          let modArray = response.data;
         if(modArray.includes(this.$store.state.user.id)){
           this.currentUserIsMod = true;
         } else {
           this.currentUserIsMod = false;
         }
        })
      }
    },
    created(){
      ForumService.getForumList().then(response => {
        this.$store.commit("SET_FORUMS", response.data);
    });
    this.checkModStatus();
}
}
</script>

<style>
#forum-name{
  text-align: center;
}
</style>