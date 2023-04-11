<template>
  <div>
      <ul v-for="forum in $store.state.forums" v-bind:key="forum.id">
          <li>{{forum.name}}</li>
      </ul>
  </div>
</template>

<script>
import ForumService from '../services/ForumService.js';
export default {
    name: "forum-list",
    methods: {
        getForumsList(){
            ForumService.getForumList().then(response => {
                this.$store.commit("SET_FORUMS", response.data);
            })
        },

        
    },
    created(){
        this.getForumsList();
    }
}
</script>

<style>

</style>