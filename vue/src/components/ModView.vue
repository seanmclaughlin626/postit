<template>
  <div>
    <form v-on:submit.prevent="">
      <label for="search-user">Search for a user</label>
      <input name="search-user" type="text" v-model="search.searchInput" />
      <button type="submit">Search</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "user-search",
  data(){
    return{
        search: {
        searchInput: ''
        }
    }
  }
};
</script>

<style>
</style>

