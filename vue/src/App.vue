<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link to="/forums">Forums</router-link>
      <div id="login-stuff">
        <router-link v-bind:to="{name: 'login'}" v-if="$store.state.token == ''">Login</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'register'}" v-if="$store.state.token == ''">Sign up</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<style lang="scss">
@import "~@/assets/scss/vendors/bootstrap-vue/index";
#nav{
  display: flex;
  justify-content: space-evenly;
  border-bottom: 1px solid black;
}
#login-stuff{
  display: flex;
  width: 33%;
  justify-content: flex-end;
}
</style>