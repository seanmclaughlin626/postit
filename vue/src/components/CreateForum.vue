<template>
  <div>
  <form v-on:submit.prevent="createForum">
    <label for="name">Name it: </label>
    <input type="text" name="name" id="name" v-model="forum.name"/>
    <b-button style="background-color: #a46434;" type="submit">PostIt!</b-button>
  </form>
  <div id="alert">
      <b-alert v-model="displayError" variant="danger" dismissible>
      {{ errorMsg }}
    </b-alert>
  </div>  
  </div>
</template>

<script>
import forumService from "../services/ForumService";

export default{
  data() {
    return {
      forum: {
        name: "",
      },
      displayError: false,
      errorMsg: "Invalid or taken forum name. Try something else.",
    };
  },
  methods: {
    createForum() {
      if (this.forum.name.trim().length > 0){
      forumService.createForum(this.forum).then((response) => {
        if (response.status === 201) {
          this.$router.push({name: 'forum', params: {id: response.data}})
        } 
      }).catch(() => {
          this.displayError = true;
        })
      } else {
        this.displayError = true;
      }
    },
  }
};
</script>

<style scoped>
label{
  margin-right: 1rem;
}
#alert{
  margin-top: 1rem;
}
button{
  margin-left: 1rem;
}
@media only screen and (max-width: 600px) {
  button{
    width: 100%;
    margin-left: 0;
  }
  form{
    display: flex;
    flex-direction: column;
    align-items: start;
  }
}
</style>