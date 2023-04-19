<template>
  <div class="button-bar">
    <b-button v-if="canDeletePosts === true" style="background-color: #60233f; margin-right: 12rem; padding:0.25rem;" v-on:click="deletePostx()">Delete post</b-button>
    <b-button v-show="!isShowingImage && post.url != null" v-on:click="changePostView" style="background-color: #60233f; margin-left:15rem; padding:0.25rem;">View Image</b-button>
    <b-button v-show="isShowingImage" v-on:click="changePostView" style="background-color: #60233f; margin-left:16rem; padding:0.25rem;">View Post</b-button>
  </div>
</template>

<script>
export default {
    name: "post-buttons"
}
</script>

<style scoped>
.button-bar {
    width: 20em;
    display: flex;
    justify-content: space-evenly;
}
</style>