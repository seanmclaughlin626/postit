<template>
    <div class="body">
    <div class="post-card" v-show="!isShowingImage">
      <h2>{{ post.title }}</h2>
      <p>{{ post.content }}</p>
      <p class="signature" v-if="forum">
        <i
          >Posted in
          <router-link
            v-bind:to="{ name: 'forum', params: { id: this.post.forumId } }"
            >{{ forumName }}</router-link
          >
          by {{ post.authorName }} on {{ post.timeFormatted }}</i
        >
      </p>
    </div>
    <div class="post-card" v-show="isShowingImage">
        <h2>{{post.title}}</h2>
        <img :src="post.url" alt="">
    </div>
    <div class="button-bar" v-if="buttonsVisible">
    <b-button class="delete-button" v-if="canDeletePosts === true" style="background-color: #60233f; margin-right: 12rem; padding:0.25rem;" v-on:click="deletePost()">Delete post</b-button>
    <div class="toggle-image-buttons">
    <b-button class="view-image-button" v-show="!isShowingImage && post.url != null" v-on:click="changePostView" style="background-color: #60233f; margin-left:15rem; padding:0.25rem;">View Image</b-button>
    <b-button class="view-post-button" v-show="isShowingImage" v-on:click="changePostView" style="background-color: #60233f; margin-left:16rem; padding:0.25rem;">View Post</b-button>
    </div>
    </div>
    </div>
</template>

<script>
import forumService from "../services/ForumService";
import userService from '../services/UserService';
import postService from '../services/PostService';

export default {
  name: "post",
  props: { post: Object },
  data() {
    return {
      forum: {},
      isShowingImage: false,
      mods: [],
      canDeletePosts: false,
    };
  },
  methods: {
    async getForum(){
      try {
      await forumService.getForum(this.post.forumId).then((response) => {
        this.forum = response.data;
      })
    } catch (error) {
      console.error('Error fetching forum:', error)
    }
    },
    changePostView(){
        this.isShowingImage = !this.isShowingImage;
    },
    deletePost() {
      postService.deletePost(this.post);
      this.$router.push({name: 'forum', params: {id: this.post.forumId}})
    },
  },
  computed: {
    forumName() {
      return this.forum.name ? this.forum.name : "loading...";
    },
    buttonsVisible(){
      return this.$route.name === 'comments';
    }
  },
  async created(){
    this.getForum();

    userService.modSearch(this.post.forumId).then((response) => {
      this.mods = response.data;
    });
    if (
      this.$store.state.user.username === this.post.authorName ||
      this.mods.includes(this.$store.state.user.username) ||
      this.$store.state.user.authorities[0].name === "ROLE_ADMIN"
    ) {
      this.canDeletePosts = true;
    }
  },
  // async updated() {
  //   if(this.post){
  //   this.getForum()
      
  //     userService.modSearch(this.post.forumId).then((response) => {
  //     this.mods = response.data;
  //   });
  //   if (
  //     this.$store.state.user.username === this.post.authorName ||
  //     this.mods.includes(this.$store.state.user.username) ||
  //     this.$store.state.user.authorities[0].name === "ROLE_ADMIN"
  //   ) {
  //     this.canDeletePosts = true;
  //   }
  //   }
  // },


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
  flex-direction: row;
  justify-content: space-between;
}



</style>

