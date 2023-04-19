<template>
  <div>
    <div class="buttons">
      <b-button style="margin-top: 0.2rem; margin-right: 0.1rem; background-color: #60233f;" v-on:click="displayByPopular = true">Most Popular</b-button>
      <b-button style="margin-top: 0.2rem; margin-left: 0.1rem; background-color: #60233f;" v-on:click="displayByPopular = false">Most Recent</b-button>
    </div>
    <div v-show="!displayByPopular" class="list">
      <div class="post-container" v-for="post in recentPosts" v-bind:key="post.postId">
      <router-link class="post-link" v-bind:to="{name: 'comments', params: {id: post.postId}}">
        <post  v-bind:post="post"/>
      </router-link>
      <button v-on:click="addVotedUser(post.postId, 1)" v-if="!canVoteOnPost">Upvote</button>
      <button v-on:click="addVotedUser(post.postId, -1)" v-if="!canVoteOnPost">Downvote</button>
      </div>
    </div>
    <div v-show="displayByPopular" class="list">
      <div class="post-container" v-for="post in popularPostsByUpvoteScore" v-bind:key="post.postId">
      <router-link class="post-link" v-bind:to="{name: 'comments', params: {id: post.postId}}">
        <post  v-bind:post="post"/>
      </router-link>
      <button v-on:click="addVotedUser(post.postId, 1)" v-if="!canVoteOnPost">Upvote</button>
      <button v-on:click="addVotedUser(post.postId, -1)" v-if="!canVoteOnPost">Downvote</button>
      </div>
    </div>
  </div>
</template>

<script>
import postService from '../services/PostService';
import Post from './Post.vue';
import userService from '../services/UserService';


export default {
name: 'posts-in-forum',
props: {forum: Object},
components: {Post},
computed: {

  recentPosts(){
          let posts =  this.$store.state.currentForumPosts;
          return posts.sort((post1, post2) => {
              let time1 = new Date(post1.timeCreated);
              let time2 = new Date(post2.timeCreated);
              return time1.getTime() > time2.getTime() ? -1 : time2.getTime() > time1.getTime() ? 1 : 0;
          })
    },

  popularPostsByUpvoteScore(){
        let posts =  this.$store.state.currentForumPosts;
        return posts.sort((post1, post2) => {
            return post1.upvoteScore > post2.upvoteScore ? -1 : post2.upvoteScore > post1.upvoteScore ? 1 : 0;
        })
  },
    canVoteOnPost(){
    return this.votedUserList.includes(parseInt(this.$store.state.user.id));
  }
},
methods: {
    addVotedUser(postId, vote){
        let userId = this.$store.state.user.id;
        postService.addVotedUser(userId, postId, vote);
    }
},
created(){
  postService.getPostsByForumId(this.$route.params.id).then((response) => {
        this.$store.commit("SET_CURRENT_FORUM_POSTS", response.data);
});

    userService.votedUserSearch(this.post.id).then(response => {
        this.votedUserList = response.data;
    })
},
data() {
  return {
  displayByPopular: true,
  votedUserList: []
  }
}
}
</script>

<style scoped>
.list{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.list post{
  margin:1em;
}

.post-link:hover, .post-link:focus{
  transform: scale(1.25);
  position:relative;
  z-index:1;
  transition: transform .15s linear;
  text-decoration: none;
}
</style>
