<template>
  <div class="comment">
    <div class="comment-card">
      <p>{{ comment.content }}</p>
      <p class="signature">
        <i>
          Commented by {{ comment.author }} on
          {{ comment.timeCreatedFormatted }}
        </i>
      </p>
    </div>
    <b-button class="delete-button"
      v-show="canDeleteComments === true"
      style="background-color: #60233f; margin-right: 12rem"
      v-on:click="deleteComment"
      >Delete</b-button
    >
  </div>
</template>

<script>
import postService from "../services/PostService";
import userService from "../services/UserService";
import commentService from "../services/CommentService";

export default {
  name: "comment",
  props: { comment: Object },
  data() {
    return {
      mods: [],
      canDeleteComments: false,
    };
  },
  created() {
    postService.getPostById(this.comment.postId).then((response) => {
      userService.modSearch(response.data.forumId).then((response2) => {
        this.mods = response2.data;
      });
    });

    if (
      this.$store.state.user.username === this.comment.author ||
      this.mods.includes(this.$store.state.user.username) ||
      this.$store.state.user.authorities[0].name === "ROLE_ADMIN"
    ) {
      this.canDeleteComments = true;
    }
  },
  updated() {
    postService.getPostById(this.comment.postId).then((response) => {
      userService.modSearch(response.data.forumId).then((response2) => {
        this.mods = response2.data;
      });
    });

    if (
      this.$store.state.user.username === this.comment.author ||
      this.mods.includes(this.$store.state.user.username) ||
      this.$store.state.user.authorities[0].name === "ROLE_ADMIN"
    ) {
      this.canDeleteComments = true;
    }
  },
  methods: {
    deleteComment() {
      if(this.canDeleteComments){
      commentService.deleteComment(this.comment);
      location.reload();
      }
    },
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
p {
  font-size: 1rem;
  font-weight: normal;
  font-family: courier, monospace;
}
.comment-card {
  text-decoration: none;
  color: #000;
  background: #c0c0f2;
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

.delete-button{
  padding: 0.25rem;
  margin-left: 1rem;
}
</style>