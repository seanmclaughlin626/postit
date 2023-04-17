<template>
  <div>
      <form v-on:submit="addComment">
          <label for="content">Content:</label>
          <br/>
          <textarea name="content" id="" cols="30" rows="10" v-model="comment.content"></textarea>
      </form>
  </div>
</template>

<script>
import commentService from '../services/CommentService';

export default {
    name: 'create-comment',
    props: ['postId'],
    data(){
        return{
            comment: {
                authorId: this.$store.state.user.id,
                postId: this.postId,
                content: ""
            }
        }
    },
    methods: {
        addComment(){
            commentService.createComment(this.comment).then(response => {
                if(response.status === 201){
                    console.log("Comment Created");
                   //   TODO bootstrap alert instead of log
                }
            })
        }
    }
}
</script>

<style>

</style>