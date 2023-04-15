<template>
  <form v-on:submit.prevent="createForum">
    <label for="name">Forum name: </label>
    <input type="text" name="name" id="name" v-model="forum.name" />
    <b-button style="margin-left: 1rem; background-color: #60233f;" type="submit">Post it!</b-button>
  </form>
</template>

<script>
import forumService from "../services/ForumService";

export default {
  data() {
    return {
      forum: {
        name: "",
      },
    };
  },
  methods: {
    createForum() {
      forumService.createForum(this.forum).then((response) => {
        if (response.status === 201) {
          this.$router.push({name: 'forum', params: {id: response.data}})
        }
        // TODO create alert messsage when a forum is created // if the forum name exists
      });
    },
  }
};
</script>

<style>
label{
  margin-right: 1rem;
}
</style>