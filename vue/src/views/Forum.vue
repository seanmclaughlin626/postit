<template>
  <div>
    <b-button style="background-color: #60233f; margin-top: 0.2rem; width: 100%" v-on:click="modView = !modView" v-show="currentUserIsMod">Moderator Page</b-button>
    <div id="modview" v-show="modView">
      <mod-view/>
    </div>
    <div id="main-forum-view" v-show="!modView">
    <h1 id="forum-name"><b>{{forum.name}}</b></h1>
    <div class="favorite-bar" v-show="!this.userHasFavoritedForum">
      <p>Loving this forum? <a v-on:click="addFavoriteForum" href="#" class="favorite-button">Favorite it!</a></p>
    </div>
    <b-button style="background-color: #60233f;" v-on:click="creatingPost = !creatingPost" v-if="$store.state.token !== ''">Make a Post!</b-button>
    <create-post :forumId="parseInt($route.params.id)" v-show="creatingPost"/>
    <posts-in-forum v-bind:forum="forum"/>
    </div>
    <favorite-forums v-show="$store.state.favoriteForumIds.length > 0"/>
  </div>
</template>

<script>
import CreatePost from '../components/CreatePost.vue';
import ModView from '../components/ModView.vue';
import PostsInForum from '../components/PostsInForum.vue';
import ForumService from '../services/ForumService';
import FavoriteForums from '../components/FavoriteForums.vue';


export default {
    name: "forum",
    components: {
      PostsInForum,
      CreatePost,
      ModView,
      FavoriteForums
    },
    data(){
      return {
      creatingPost: false,
      modView: false,
      currentUserIsMod: false,
      //userHasFavoritedForum: false
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
      },
       userHasFavoritedForum(){
         return (this.$store.state.favoriteForumIds.includes(parseInt(this.$route.params.id)) || this.$store.state.token === '');
       }
    },
    methods: {
      setFavorites(){
      if(this.$store.state.token !== ''){
          ForumService.getFavoriteForumIds().then((response) => {
          this.$store.commit("SET_FAVORITE_FORUM_IDS", response.data);
    })
          ForumService.getFavoriteForums().then((response) => {
            this.$store.commit("SET_FAVORITE_FORUMS", response.data);
          })}
      },
      checkModStatus(){
        ForumService.getMods(this.$route.params.id).then((response) => {
          let modArray = response.data;
         if(modArray.includes(this.$store.state.user.id)){
           this.currentUserIsMod = true;
         } else {
           this.currentUserIsMod = false;
         }
        })
      },
      addFavoriteForum(){
        ForumService.addFavoriteForum(this.$route.params.id).then((response) => {
          if(response.status === 201){
          this.setFavorites();
          this.userHasFavoritedForum = true;
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
.favorite-bar{
  display: block;
  background-color: #60233f;
  text-align: center;
  font-family: courier, monospace;
  color: white;
  margin-bottom: 2rem;
  font-size: 1.5rem;
}
.favorite-button{
  color: #d69830;
  font-weight: bold;
}
#forum-name{
  text-align: center;
  font-family: courier, monospace;
 position: relative;
    width: 100%;
    margin: 40px auto;
    margin-bottom: 1rem;
    filter: drop-shadow(-1px 6px 3px rgba(0, 0, 0, 0.5));
    height: auto;
        overflow: hidden;
        padding: 20px;
        background: #f5f5f5;

        clip-path: polygon(
            1% 2%,
            3.5% 0.1%,
            8.9% 2.7%,
            16.2% 1.2%,
            19.7% 0.8%,
            24.7% 2%,
            31.2% 0%,
            35.9% 2.3%,
            40.6% 0.5%,
            47.1% 1.8%,
            49.9% 1%,
            53.8% 2.5%,
            62.2% 0.4%,
            67.3% 1.2%,
            70.3% 0.3%,
            75.8% 1.4%,
            78.5% 1.7%,
            84% 2.6%,
            91.1% 0.5%,
            93.6% 2.4%,
            97.3% 0.3%,
            98.9% 6%,
            98.1% 11.4%,
            98.6% 15.5%,
            97.3% 17.8%,
            97.7% 27.4%,
            98.3% 28.5%,
            99.7% 34.1%,
            98.8% 41.9%,
            98.3% 45.7%,
            98.4% 50.9%,
            98.7% 57.4%,
            99.6% 60.4%,
            99.4% 66.6%,
            99.2% 69.5%,
            97.4% 77.1%,
            98.1% 82.3%,
            99.9% 83.5%,
            98.2% 91.5%,
            99.3% 95.3%,
            98% 98.6%,
            93.6% 97.4%,
            90.3% 98.3%,
            86.8% 98.3%,
            78.6% 98.5%,
            76.4% 99.2%,
            69.7% 99.8%,
            64.2% 99.3%,
            61.8% 99.2%,
            57.2% 98.1%,
            48.7% 98.3%,
            46.8% 99%,
            39.6% 98.9%,
            33.8% 97.3%,
            28.2% 99.4%,
            27% 98.3%,
            22.1% 98.6%,
            13.1% 97.1%,
            8.7% 99.3%,
            2.7% 97.4%,
            2% 98.2%,
            2.1% 94.3%,
            0.7% 90.5%,
            0.9% 86.3%,
            2.2% 78.2%,
            0.4% 76.7%,
            1.2% 72.1%,
            2% 64%,
            1.4% 59.8%,
            0.4% 57.3%,
            0.3% 49.1%,
            2.9% 44.7%,
            1.9% 41.3%,
            0.8% 35.7%,
            2.9% 31.3%,
            0.8% 24.9%,
            2.7% 20.5%,
            0.8% 17.4%,
            2.5% 8.6%,
            1.1% 5.5%
)}

body{
  padding-bottom: 5rem;
}
</style>