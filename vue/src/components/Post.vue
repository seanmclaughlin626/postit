<template>
    <div class="body">
    <div class="post-card" v-show="!isShowingImage">
      <router-link class="title-link" v-bind:to="{name: 'comments', params: {id: post.postId}}">
      <h2>{{ post.title }}</h2>
      </router-link>
      <p>{{ post.content }}</p>
      <p class="signature">
        <i
          >Posted in
          <router-link
            v-bind:to="{ name: 'forum', params: { id: this.post.forumId } }"
            >{{ post.forumName }}</router-link
          >
          by {{ post.authorName }} on {{ post.timeFormatted }}</i
        >
      </p>
      <div class="vote-stuff-container">
      <div class="vote-button-container" v-show="!cantVoteOnPost">
      <b-button style="background-color: #60233f;" v-on:click="addVotedUser(post.postId, 1)" >Upvote</b-button>
      <b-button style="background-color: #60233f;" v-on:click="addVotedUser(post.postId, -1)">Downvote</b-button>
      </div>
      <div class="upvote-score-container">
        <h5>Upvote score: {{post.upvoteScore}}</h5>
      </div>
      </div>
    </div>
    <div class="post-card" v-show="isShowingImage">
        <h2>{{post.title}}</h2>
        <div class="image-container">
        <img :src="post.url" alt="">
        </div>
    </div>
    <div class="button-bar-container">
    <div class="button-bar" v-if="buttonsVisible">
    <b-button class="delete-button" v-if="canDeletePosts === true" style="background-color: #60233f; padding:0.25rem;" v-on:click="deletePost()">Delete post</b-button>
    <div class="toggle-image-buttons">
    <b-button class="view-image-button" v-if="!isShowingImage && post.url != null" v-on:click="changePostView" style="background-color: #60233f; padding:0.25rem;">View Image</b-button>
    <b-button class="view-post-button" v-if="isShowingImage" v-on:click="changePostView" style="background-color: #60233f; padding:0.25rem;">View Post</b-button>
    </div>
    </div>
    </div>
    </div>
</template>

<script>
import userService from '../services/UserService';
import postService from '../services/PostService';

export default {
  name: "post",
  props: { post: Object },
  data() {
    return {
      isShowingImage: false,
      mods: [],
      votedUserList: []
    };
  },
  methods: {
    changePostView(){
        this.isShowingImage = !this.isShowingImage;
    },
    deletePost() {
      postService.deletePostx(this.post, this.$store.state.token);
      this.$router.push({name: 'forum', params: {id: this.post.forumId}});
      this.$router.go(0);
    },
    addVotedUser(postId, vote){
        let userId = this.$store.state.user.id;
        console.log("here");
        console.log(this.post);
        postService.addVotedUser(userId, postId, vote);
        userService.votedUserSearch(postId).then(response => {
        this.votedUserList = response.data;
        location.reload();
    })
    }
  },
  computed: {
    buttonsVisible(){
      return this.$route.name === 'comments';
    },
    canDeletePosts(){
      if(this.$store.state.token !== ""){
      return this.$store.state.user.username === this.post.authorName ||
      this.mods.includes(this.$store.state.user.username) ||
      this.$store.state.user.authorities[0].name === "ROLE_ADMIN";
      } else {
        return false;
      }
    },
    cantVoteOnPost(){
        return (this.votedUserList.includes(parseInt(this.$store.state.user.id)) || this.$store.state.token === '');
    }
  },
  async created(){
    if(this.$route.name == 'comments'){
    userService.modSearch(this.$route.params.id).then((response) => {
      this.mods = response.data;
    });
    userService.votedUserSearch(this.$route.params.id).then((response) => {
        this.votedUserList = response.data;
    });
    }
    else{
       userService.modSearch(this.post.forumId).then((response) => {
      this.mods = response.data;
    });
    userService.votedUserSearch(this.post.postId).then((response) => {
        this.votedUserList = response.data;
    });   
    }
  },
};
</script>

<style scoped>
.vote-stuff-containter{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.vote-button-container{
  display: flex;
  flex-direction: column;
}

.upvote-score-container{
text-align: center;
}

.title-link{
  color: black;
}
body {
  margin: 20px auto;
  font-family: "Lato";
  background: #666;
  color: #fff;
}
* {
  margin: 0;
  padding: 0;
}
h2 {
  font-weight: bold;
  font-size: 1.5rem;
  font-family: courier, monospace;
}
p {
  font-size: 1rem;
  font-weight: normal;
  font-family: courier, monospace;
}
.post-card {
  text-decoration: none;
  color: #000;
  background: #ffc;
  display: block;
  height: 20em;
  width: 20em;
  padding: 1em;
  margin: 1em;
  box-shadow: 5px 5px 7px rgba(33, 33, 33, 0.7);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transform: rotate(-0.5deg);
}

.signature {
  font-size: 0.85rem;
}

.image-container{
  display: flex;
  align-items: start;
  justify-content: center;
}

img{
  height: 13em;
  width: 13em;
}

.button-bar{
  display: flex;
  justify-content: center;
}

</style>

