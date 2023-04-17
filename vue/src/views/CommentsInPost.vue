<template>
  <div>
      
      <comments-list></comments-list>
  </div>
</template>

<script>
import commentService from '../services/CommentService';
import CommentsList from '../components/CommentsList';

export default {
   
    created(){
        commentService.getCommentsByPostId(this.$route.params.id).then(response => {
            this.$store.commit("SET_COMMENTS", response.data)
        })
    },
    components: {CommentsList}
}
</script>

<style>

</style>