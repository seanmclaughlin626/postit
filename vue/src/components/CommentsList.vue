<template>
  <div class="list">
    <comment
      v-for="comment in $store.state.comments"
      v-bind:key="comment.commentId"
      v-bind:comment="comment"
    />
  </div>
</template>

<script>
import Comment from "./Comment.vue";
import commentService from "../services/CommentService";

export default {
  name: "comments-list",
  components: { Comment },
  created() {
    commentService
      .getCommentsByPostId(this.$route.params.id)
      .then((response) => {
        this.$store.commit("SET_COMMENTS", response.data);
      });
  },

  data() {
    return {
      postInCommentsList: {
        title: "",
        name: "",
        authorName: "",
        timeFormatted: "",
      },
    };
  },
};
</script>

<style>
.list {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}

.list comment {
  margin: 1em;
}
</style>