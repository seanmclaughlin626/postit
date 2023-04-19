<template>
    <div class="body">
    <div class="post-card" v-show="!isShowingImage">
      <h2>{{ post.title }}</h2>
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
    </div>
    <div class="post-card" v-show="isShowingImage">
        <h2>{{post.title}}</h2>
        <img :src="post.url" alt="">
    </div>
    <div class="button-bar-container">
    <div class="button-bar" v-if="buttonsVisible">
    <b-button class="delete-button" v-if="canDeletePosts === true" style="background-color: #60233f; margin-right: 8rem; padding:0.25rem;" v-on:click="deletePost()">Delete post</b-button>
    <div class="toggle-image-buttons">
    <b-button class="view-image-button" v-show="!isShowingImage && post.url != null" v-on:click="changePostView" style="background-color: #60233f; margin-left: 1rem; padding:0.25rem;">View Image</b-button>
    <b-button class="view-post-button" v-show="isShowingImage" v-on:click="changePostView" style="background-color: #60233f; margin-left:1rem;  padding:0.25rem;">View Post</b-button>
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
  },
  computed: {
    buttonsVisible(){
      return this.$route.name === 'comments';
    },
    canDeletePosts(){
      return this.$store.state.user.username === this.post.authorName ||
      this.mods.includes(this.$store.state.user.username) ||
      this.$store.state.user.authorities[0].name === "ROLE_ADMIN";
    }
  },
  async created(){

    userService.modSearch(this.post.forumId).then((response) => {
      this.mods = response.data;
    });
    // if (
    //   this.$store.state.user.username === this.post.authorName ||
    //   this.mods.includes(this.$store.state.user.username) ||
    //   this.$store.state.user.authorities[0].name === "ROLE_ADMIN"
    // ) {
    //   this.canDeletePosts = true;
    // }
  },
};
</script>

<style scoped>

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

img{
  height: 15em;
  width: 15em;
  align-self: center;
}

.button-bar{
  display: flex;
  justify-content: center;
}

</style>

