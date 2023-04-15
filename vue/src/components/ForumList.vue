<template>
  <div>
    <h4><b>Recently Active Forums</b></h4>
    <ul
      class="forum-list"
      v-for="forum in recentForumInteractions"
      v-bind:key="forum.id"
    >
      <router-link v-bind:to="{ name: 'forum', params: { id: forum.id } }">{{
        forum.name
      }}</router-link>
    </ul>
  </div>
</template>

<script>
import ForumService from "../services/ForumService.js";
export default {
  name: "forum-list",
  methods: {
    getForumsList() {
      ForumService.getForumList().then((response) => {
        this.$store.commit("SET_FORUMS", response.data);
      });
    },
  },
  computed: {
    recentForumInteractions() {
      let topFiveForums = this.$store.state.forums;
      if (topFiveForums.length > 5) {
        return topFiveForums.slice(0, 5);
      } else {
        return topFiveForums;
      }
    },
  },
  created() {
    this.getForumsList();
  },
};
</script>

<style scoped>
ul,
h4 {
  border-bottom: 1px solid darkgray;
  margin-bottom: 0%;
}
div {
  color: #555;
  font-size: 22px;
  padding: 0 !important;
  width: 500px;
  font-family: courier, monospace;
  border: 1px solid #dedede;
  
}
.forum-list, h4 {
  list-style: none;
  border-bottom: 1px dotted #ccc;
  text-indent: 25px;
  height: auto;
  padding: 10px;
  text-transform: capitalize;
  background-color: #f5f5f5;
  width: 600px;
  margin: 0 auto;
  padding: 0;
  
}
.forum-list li:hover {
  background-color: #f0f0f0;
  -webkit-transition: all 0.2s;
  -moz-transition:    all 0.2s;
  -ms-transition:     all 0.2s;
  -o-transition:      all 0.2s;
}
</style>