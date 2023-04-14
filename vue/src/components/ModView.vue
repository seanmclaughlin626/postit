<template>
  <div>
    <form v-on:submit.prevent="">
      <label for="search-user">Search for a user</label>
      <input name="search-user" type="text" v-model="searchInput" />
      <button type="submit">Search</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "user-search",
  data(){
    return{
        searchInput: ''
    }
  }
};
</script>

<style>
</style>

