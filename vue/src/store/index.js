import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    forums: [],
    posts: [],
    comments: [],
    currentForumPosts: [],
    searchUserList: [],
    favoriteForumIds: [],
    favoriteForums: [],
    searchForumList: []
  },
  mutations: {
    SET_COMMENTS(state, data){
      state.comments = data;
    },

    SET_FORUM_SEARCH_RESULTS(state, data){
      state.searchForumList = data;
    },

    SET_FAVORITE_FORUMS(state, data){
      state.favoriteForums = data;
    },

    SET_FAVORITE_FORUM_IDS(state, data){
      state.favoriteForumIds = data;
    },

    SET_POSTS(state, data){
      state.posts = data;
    },

    SET_CURRENT_FORUM_POSTS(state, data){
      state.currentForumPosts = data;
    },

    SET_FORUMS(state, data){
      state.forums = data;
    },

    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    SET_USER_SEARCH_RESULTS(state, data){
      state.searchUserList = data;
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  }
})
