<template>
  <div id="app">
    <div id="nav" class="nav">
      <router-link class="nav" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link class="nav" to="/forums">Forums</router-link>
      <div id="login-stuff" class="nav">
        <router-link v-bind:to="{name: 'login'}" v-if="$store.state.token == ''" class="nav">Login</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'register'}" v-if="$store.state.token == ''" class="nav">Sign up</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="nav">Logout</router-link>
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
*{
    background-image: url("./assets/pictures/corkboard2.jpg");
    
}
.nav{
  background-color: darkgrey;
  background-image: none;
}
</style>