<template>
  <div class="home">
    <div class="forum-bar">
      <div class="forum-search">
        <forum-search/>
        </div>
    <div class="forum-creation" v-show="$store.state.token !== ''">
      <p>Don't see a forum you like? Make one!</p> <create-forum/>
      </div>
      </div>
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
// import forumService from '../services/ForumService';
import ForumSearch from '../components/ForumSearch.vue';

export default {
  components:{
    PopularPosts,
    ForumList,
    CreateForum,
    FavoriteForums,
    ForumSearch
  },
  name: "home",
  created(){
    //     if(this.$store.state.token !== ''){
    // forumService.getFavoriteForumIds().then((response) => {
    //   this.$store.commit("SET_FAVORITE_FORUM_IDS", response.data);
    // })
    // forumService.getFavoriteForums().then((response) => {
    //   this.$store.commit("SET_FAVORITE_FORUMS", response.data);
    //  })}
    }
};
</script>

<style scoped>
.forum-bar {
  color: white;
  background-image: linear-gradient(to right, #a46434, #60233f);
  padding: 0.5rem;
  border: 2px #60233f solid;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}

.home{
  padding-bottom: 3rem;
}

p{
  margin-bottom: 2px;
}

.forum-lists{
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
