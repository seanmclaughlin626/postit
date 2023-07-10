<template>
  <div>
    <form class="forum-search-form" v-on:submit.prevent="searchForums">
      <label for="search">Find a forum:</label>
      <br/>
      <input type="text" name="search" id="search" v-model="search.searchInput"/>
      <b-button type="submit" style="background-color: #60233f; margin-left: 1rem;"
        >Search it!</b-button
      >
    </form>
    <div class="search-list" v-show="$store.state.searchForumList.length > 0">
      <ul class="forum-list">
        <li v-for="forum in $store.state.searchForumList" v-bind:key="forum.id">
          <router-link v-bind:to="{name: 'forum', params: {id: forum.id}}">
            {{ forum.name }}
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import forumService from '../services/ForumService';

export default {
  name: "forum-search",
  data() {
    return {
      search: {
        searchInput: "",
      },
    };
  },
  methods: {
    searchForums() {
      if (this.search.searchInput !== "") {
        forumService.forumSearch(this.search).then((response) => {
            this.$store.commit("SET_FORUM_SEARCH_RESULTS", response.data);
          });
      } else {
        this.$store.commit("SET_FORUM_SEARCH_RESULTS", []);
      }
    },
  },
};
</script>

<style scoped>
.search-list{
   margin-top: 1rem;
  color: #555;
  font-size: 22px;
  padding: 0 !important;
  width: 100%;
  font-family: courier, monospace;
  border: 1px solid #dedede;
}
.forum-list{
  list-style: none;
  border-bottom: 1px dotted #ccc;
  text-indent: 25px;
  height: auto;
  padding: 10px;
  text-transform: capitalize;
  background-color: #f5f5f5;
  width: 100%;
  margin: 0 auto;
  padding: 0;
}
.forum-list li{
  color: #555;
  font-size: 22px;
  padding: 0 !important;
  width: 100%;
  font-family: courier, monospace;
  border: 1px solid #dedede;
}
</style>