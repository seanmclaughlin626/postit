<template>
  <div class="home">
    <div class="forum-creation" v-show="$store.state.token !== ''">Don't see a forum you like? Make one! <create-forum/></div>
    <popular-posts/>
    <div class="forum-lists">
    <forum-list/>
    <favorite-forums v-show="$store.state.favoriteForumIds.length > 0"/>
    </div>
  </div>
</template>

<script>
import PopularPosts from '../components/PopularPosts.vue';
import ForumList from '../components/ForumList.vue';
import FavoriteForums from '../components/FavoriteForums.vue';
import CreateForum from '../components/CreateForum.vue';
import forumService from '../services/ForumService';

export default {
  components:{
    PopularPosts,
    ForumList,
    CreateForum,
    FavoriteForums
  },
  name: "home",
  created(){
        if(this.$store.state.token !== ''){
    forumService.getFavoriteForumIds().then((response) => {
      this.$store.commit("SET_FAVORITE_FORUM_IDS", response.data);
    })}
  }
};
</script>

<style scoped>
.forum-creation {
  color: white;
  background-image: linear-gradient(to right, #a46434, #60233f);
  padding: 0.5rem;
  border: 2px #60233f solid;
  display: block;
}

.home{
  padding-bottom: 3rem;
}

.forum-lists{
  display: flex;
  flex-direction: column;
}
</style>
