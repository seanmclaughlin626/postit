<template>
  <form v-on:submit.prevent="createForum">
    <label for="name">Forum name: </label>
    <input type="text" name="name" id="name" v-model="forum.name" />
    <button type="submit">Create it!</button>
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
          location.reload();
 //   TODO: ROUTE TO NEWLY CREATED FORUM PAGE
        }
      });
    },
  },
};
</script>

<style>
</style>