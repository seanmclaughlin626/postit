<template>
  <div class="home">
    <popular-posts/>
  </div>
</template>

<script>
import PopularPosts from '../components/PopularPosts.vue';
export default {
  components:{
    PopularPosts
  },
  name: "home"
};
</script>

    PopularPosts