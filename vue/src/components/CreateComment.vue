<template>
  <div>
      <form v-on:submit="addComment">
          <label for="content">Want to weigh in? Post a comment!</label>
          <br/>
          <div class="text-and-button">
          <textarea name="content" id="" cols="30" rows="3" v-model="comment.content"></textarea>
        <b-button type="submit" class="post-button" style="background-color: #60233f">PostIt!</b-button>
        </div>
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
                    // console.log("Comment Created");
                   //   TODO bootstrap alert instead of log
                }
            })
        }
    }
}
</script>

<style scoped>
form {
color: white;
  background-image: linear-gradient(to right, #a46434, #60233f);
  padding: 0.5rem;
  border: 2px #60233f solid;
  display: block;
}
.text-and-button{
    flex-wrap: wrap;
    display: flex;
    flex-direction: row;
    align-items: flex-end;
    flex-wrap: wrap;
    justify-content: flex-start;
}
.post-button{
    margin-left: 1rem;
}
</style>