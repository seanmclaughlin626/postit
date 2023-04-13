<template>
  <div class="home">
    <popular-posts/>
    <forum-list/>
  </div>
</template>

<script>
import PopularPosts from '../components/PopularPosts.vue';
import ForumList from '../components/ForumList.vue';
export default {
  components:{
    PopularPosts,
    ForumList
  },
  name: "home"
};
</script>

    PopularPosts