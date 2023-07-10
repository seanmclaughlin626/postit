<template>
  <div>
    <form class="mod-search" v-on:submit.prevent="searchUsers">
      <label for="search-user">Search for a user:  </label>
      <br>
      <input name="search-user" type="text" v-model="search.searchInput" />
      <b-button style="background-color: #60233f; margin-left: 1rem;" type="submit">Search</b-button>
    </form>
    <div class="search-list" v-show="$store.state.searchUserList.length > 0">
    <ul class="user-list">
        <li v-for="(username, index) in $store.state.searchUserList" v-bind:key="index">
            <p>{{username}} <b-button style="background-color: #60233f; margin-left: 0.5rem; margin-top: 0.5rem;" v-on:click="promoteUser(username)">Promote to Mod</b-button></p>
        </li>
    </ul>
    </div>
    <div class="mod-list">
      <h2 class="mod-list-content"><b>Current Moderators</b></h2>
      <ul class="mod-list-content">
        <li v-for="(username, index) in this.mods" v-bind:key="index"><p>{{username}}</p></li>
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
        },
        mods: []
    }
  },
  methods: {
      searchUsers(){
          if(this.search.searchInput !== ''){
          userService.nonModSearch(this.$route.params.id, this.search).then((response) => {
              this.$store.commit("SET_USER_SEARCH_RESULTS", response.data);
          })
          } else {
            this.$store.commit("SET_USER_SEARCH_RESULTS", []);
          }
      },
      promoteUser(username){
          this.userToPromote.username = username;
          forumService.promoteUserToMod(this.$route.params.id, this.userToPromote).then((response) => {
              if(response.status == 201){
                  // console.log("Mod promoted!");
                //   TODO make this a snazzy alert instead of a log
                this.mods.push(username);
                this.search.searchInput = "";
                this.searchUsers();
              }
          });
      } 
  },
  created(){
        userService.modSearch(this.$route.params.id).then((response) => {
          this.mods = response.data;
        })
      }
};
</script>

<style>
.mod-search {
  color: white;
  background-image: linear-gradient(to right, #a46434, #60233f);
  padding: 0.5rem;
  border: 2px #60233f solid;
  display: block;
}
.search-list, .mod-list {
  color: #555;
  font-size: 22px;
  text-align: center;
  padding: 0 !important;
  width: 100%;
  font-family: courier, monospace;
  border: 1px solid #dedede;
  
}

.user-list, .mod-list-content{
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
.user-list li, .mod-list-content li{
  color: #555;
  font-size: 22px;
  padding: 0 !important;
  width: 100%;
  font-family: courier, monospace;
  border: 1px solid #dedede;
}



</style>