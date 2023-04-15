<template>
  <div>
    <form v-on:submit.prevent="searchUsers">
      <label for="search-user">Search for a user:  </label>
      <input name="search-user" type="text" v-model="search.searchInput" />
      <button type="submit">Search</button>
    </form>
    <ul>
        <li v-for="(username, index) in $store.state.searchUserList" v-bind:key="index">
            <p>{{username}} <button v-on:click="promoteUser(username)">Promote to Mod</button></p>
        </li>
    </ul>
  </div>
</template>

<script>
import forumService from '../services/ForumService'
import userService from '../services/UserService'

export default {
  name: "user-search",
  data(){
    return{
        search: {
        searchInput: ''
        },
        userToPromote: {
            username: ''
        }
    }
  },
  methods: {
      searchUsers(){
          console.log(this.search.searchInput);
          userService.userSearch(this.search).then((response) => {
              this.$store.commit("SET_USER_SEARCH_RESULTS", response.data);
          })
      },
      promoteUser(username){
          this.userToPromote.username = username;
          forumService.promoteUserToMod(this.$route.params.id, this.userToPromote).then((response) => {
              if(response.status == 201){
                  location.reload();
              }
          });
      }
  }
};
</script>

<style>
</style>

