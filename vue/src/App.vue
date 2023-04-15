<template>
  <div id="app">
    <b-container>
      <b-navbar toggleable="lg" type="dark" style="background-color: #60233f">
        <b-navbar-brand href="/">
          <img
            class="logo-image"
            src="./assets/pictures/cropped-logo.png"
            alt="PostIt"
          />
        </b-navbar-brand>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-form>
              <b-form-input
                size="sm"
                class="mr-sm-2"
                placeholder="Search"
              ></b-form-input>
              <b-button size="sm" class="my-2 my-sm-0" type="submit" style="background-color: #d69830; margin-right: 1rem;"
                >Search</b-button
              >
            </b-nav-form>
            <b-nav-item-dropdown right>
              <template #button-content>
                <em v-if="$store.state.token !== ''">{{
                  $store.state.user.username
                }}</em>
                <em v-if="$store.state.token === ''">Sign in</em>
              </template>
              <b-dropdown-item href="/login" v-show="$store.state.token === ''"
                >Login</b-dropdown-item
              >
              <b-dropdown-item
                href="/register"
                v-show="$store.state.token === ''"
                >Register</b-dropdown-item
              >
              <b-dropdown-item href="/logout" v-show="$store.state.token !== ''"
                >Sign Out</b-dropdown-item
              >
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
      <router-view />
    </b-container>
  </div>
</template>

<style lang="scss">
@import "~@/assets/scss/vendors/bootstrap-vue/index";
#nav {
  display: flex;
  justify-content: space-evenly;
  border-bottom: 1px solid black;
}

#login-stuff {
  display: flex;
  width: 33%;
  justify-content: flex-end;
}
body {
  background-image: url("./assets/pictures/corkboard2.jpg");
}
.nav {
  background-color: darkgrey;
  background-image: none;
}

.logo-image {
  width: 3rem;
  border: 1px solid #60233f;
  border-radius: 10%;
}

b-navbar{
  background-color: #a46434;
}
</style>