<template>
  <div>
      <h4>Recently Active Forums</h4>
      <ul v-for="forum in recentForumInteractions" v-bind:key="forum.id">
          <router-link v-bind:to="{name: 'forum', params:{id: forum.id}}">{{forum.name}}</router-link>
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
        }
    },
    computed: {
        recentForumInteractions(){
        let topFiveForums = this.$store.state.forums;
        if(topFiveForums.length > 5){
        return topFiveForums.slice(0,5);
        } else {
            return topFiveForums;
        }
    }
    },
    created(){
        this.getForumsList();
    }
}
</script>

<style>
ul, h4{
    border-bottom: 1px solid darkgray;
}
</style>