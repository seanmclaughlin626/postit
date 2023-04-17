<template>
  <div>
    <create-comment v-show="$store.state.token !== ''" :postId="parseInt(this.$route.params.id)"/>
    <div class="post-header">
    <post v-bind:post="this.post" />
    <b-button v-show="this.post.authorName === $store.state.user.username" style="background-color: #60233f; margin-right: 12rem;" v-on:click="deletePost">Delete post</b-button>
    </div>
    <comments-list/>
  </div>
</template>

<script>
import commentService from '../services/CommentService';
import CommentsList from '../components/CommentsList';
import Post from '../components/Post.vue'
import PostService from '../services/PostService';
import CreateComment from '../components/CreateComment.vue';

export default {
    data(){
    return{
        post: {
            id: 0,
            title: "",
            authorName: "",
            forumId: 0,
            content: "",
            timeFormatted: ""
        }
    }
    },
    created(){
        commentService.getCommentsByPostId(this.$route.params.id).then(response => {
            this.$store.commit("SET_COMMENTS", response.data)
        })
        PostService.getPostById(this.$route.params.id).then((response) => {
            this.post = response.data;
        })
    },
    components: {
        CommentsList, 
        Post, 
        CreateComment
        }
}
</script>

<style>
.post-header{
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>