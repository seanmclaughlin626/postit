<template>
  <div>
    <create-comment
      v-show="$store.state.token !== ''"
      :postId="parseInt(this.$route.params.id)"
    />
    <div class="post-header">
      <post v-bind:post="post" />
      <b-button
        v-show="canDeletePosts === true"
        style="background-color: #60233f; margin-right: 12rem"
        v-on:click="deletePost"
        >Delete post</b-button
      >
    </div>
    <comments-list />
  </div>
</template>

<script>
import commentService from "../services/CommentService";
import CommentsList from "../components/CommentsList";
import Post from "../components/Post.vue";
import PostService from "../services/PostService";
import CreateComment from "../components/CreateComment.vue";
import userService from "../services/UserService.js";

export default {
  data() {
    return {
      post: {
        id: 0,
        title: "",
        authorName: "",
        forumId: 0,
        content: "",
        timeFormatted: "",
      },
      mods: [],
      canDeletePosts: false,
    };
  },
  created() {
    commentService
      .getCommentsByPostId(this.$route.params.id)
      .then((response) => {
        this.$store.commit("SET_COMMENTS", response.data);
      });
    PostService.getPostById(this.$route.params.id).then((response) => {
      this.post = response.data;
    });
    userService.modSearch(this.post.forumId).then((response) => {
      console.log(response.data);
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
  updated() {
    if (
      this.$store.state.user.username === this.post.authorName ||
      this.mods.includes(this.$store.state.user.username) ||
      this.$store.state.user.authorities[0].name === "ROLE_ADMIN"
    ) {
      this.canDeletePosts = true;
    }
  },
  components: {
    CommentsList,
    Post,
    CreateComment,
  },
  methods: {
    deletePost() {
      PostService.deletePost(this.post);
      this.$router.push({name: 'forum', params: {id: this.post.forumId}})
    },
  },
};
</script>

<style>
.post-header {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>