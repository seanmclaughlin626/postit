<template>
  <div class="home">
    <a href="#" v-on:click="createForumVisible = !createForumVisible" v-show="$store.state.token !== ''">Don't see a forum you like? Make one!</a>
    <create-forum v-show="createForumVisible"/>
    <popular-posts/>
    <forum-list/>
  </div>
</template>

<script>
import PopularPosts from '../components/PopularPosts.vue';
import ForumList from '../components/ForumList.vue';
import CreateForum from '../components/CreateForum.vue';
export default {
  data(){
    return {
    createForumVisible: false
    }
  },
  components:{
    PopularPosts,
    ForumList,
    CreateForum
  },
  name: "home"
};
</script>
