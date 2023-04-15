<template>
  <div class="home">
    <div class="forum-creation" v-show="$store.state.token !== ''">Don't see a forum you like? Make one! <create-forum/></div>
    <popular-posts/>
    <forum-list/>
  </div>
</template>

<script>
import PopularPosts from '../components/PopularPosts.vue';
import ForumList from '../components/ForumList.vue';
import CreateForum from '../components/CreateForum.vue';
export default {
  components:{
    PopularPosts,
    ForumList,
    CreateForum
  },
  name: "home"
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
</style>
