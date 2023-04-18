<template>
  <div>
    <create-comment
      v-show="$store.state.token !== ''"
      :postId="parseInt(this.$route.params.id)"
    />
    <div class="post-header">
      <post v-bind:post="post" />
    </div>
    <comments-list />
    <favorite-forums/>
  </div>
</template>

<script>
import commentService from "../services/CommentService";
import CommentsList from "../components/CommentsList";
import Post from "../components/Post.vue";
import PostService from "../services/PostService";
import CreateComment from "../components/CreateComment.vue";
import FavoriteForums from '../components/FavoriteForums.vue';

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
    
  },
  components: {
    CommentsList,
    Post,
    CreateComment,
    FavoriteForums
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