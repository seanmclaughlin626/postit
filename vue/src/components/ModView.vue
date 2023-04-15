<template>
  <div>
    <form class="mod-search" v-on:submit.prevent="searchUsers">
      <label for="search-user">Search for a user:  </label>
      <br>
      <input name="search-user" type="text" v-model="search.searchInput" />
      <b-button style="background-color: #60233f; margin-left: 1rem;" type="submit">Search</b-button>
    </form>
    <div class="search-list">
    <ul class="user-list">
        <li v-for="(username, index) in $store.state.searchUserList" v-bind:key="index">
            <p>{{username}} <button v-on:click="promoteUser(username)">Promote to Mod</button></p>
        </li>
    </ul>
    </div>
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
                  console.log("Mod promoted!");
                //   TODO make this a snazzy alert instead of a log
              }
          });
      }
  }
};
</script>

<style>
.mod-search {
  color: white;
  background-color: #a46434;
  padding: 0.5rem;
  border: 2px #60233f solid;
  display: block;
}
.search-list {
  color: #555;
  font-size: 22px;
  padding: 0 !important;
  width: 500px;
  font-family: courier, monospace;
  border: 1px solid #dedede;
  
}

.user-list{
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
.user-list li{
  color: #555;
  font-size: 22px;
  padding: 0 !important;
  width: 100%;
  font-family: courier, monospace;
  border: 1px solid #dedede;
}



</style>

