<template>
  <div>
    <h3><b>Popular posts</b></h3>
    <div class="list">
      <div
        v-for="post in popularPosts"
        v-bind:key="post.postId"
        class="post-container">
        <post v-bind:post="post" />
      </div>
    </div>
  </div>
</template>

<script>
import postService from "../services/PostService";
import Post from "./Post.vue";

export default {
  name: "popular-posts",
  components: {
    Post,
  },
  methods: {
    getPosts() {
      postService.getPostList().then((response) => {
        this.$store.commit("SET_POSTS", response.data);
      });
    },
  },

  computed: {
    recentPosts() {
      let yesterday = new Date();
      let lastWeek = new Date();
      let lastThirtyDays = new Date();
      let lastYear = new Date();
      yesterday.setHours(yesterday.getHours() - 24);
      lastWeek.setHours(lastWeek.getHours() - 168);
      lastThirtyDays.setHours(lastThirtyDays.getHours() - 720);
      lastYear.setHours(lastYear.getHours() - 8760);
      let postArray = this.$store.state.posts.filter((post) => {
        let postDate = new Date(post.lastInteraction);
        return postDate.getTime() >= yesterday.getTime();
      });
      if (postArray.length < 10) {
        postArray = this.$store.state.posts.filter((post) => {
          let postDate = new Date(post.lastInteraction);
          return postDate.getTime() >= lastWeek.getTime();
        });
      }
      if (postArray.length < 10) {
        postArray = this.$store.state.posts.filter((post) => {
          let postDate = new Date(post.lastInteraction);
          return (
            postDate.getTime() >= lastThirtyDays.getTime()
          );
        });
      }
      if (postArray.length < 10) {
        postArray = this.$store.state.posts.filter((post) => {
          let postDate = new Date(post.lastInteraction);
          return postDate.getTime() >= lastYear.getTime();
        });
      }
      return postArray;
    },
    popularPosts() {
      let recentPosts = this.recentPosts;
      let orderedPosts = recentPosts.sort((post1, post2) => {
        return post1.upvoteScore > post2.upvoteScore
          ? -1
          : post2.upvoteScore > post1.upvoteScore
          ? 1
          : 0;
      });
      if (orderedPosts.length > 10) {
        return orderedPosts.slice(0, 10);
      } else {
        return orderedPosts;
      }
    },
  },
  created() {
    this.getPosts();
  },
};
</script>

<style scoped>
.list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.list post {
  margin: 1em;
}

.post-container:hover,
.post-container:focus {
  transform: scale(1.25);
  position: relative;
  z-index: 1;
  transition: transform 0.15s linear;
  text-decoration: none;
}

h3 {
  text-align: center;
  font-family: courier, monospace;
  position: relative;
  width: 100%;
  margin: 40px auto;
  filter: drop-shadow(-1px 6px 3px rgba(0, 0, 0, 0.5));
  height: auto;
  overflow: hidden;
  padding: 20px;
  background: #f5f5f5;

  clip-path: polygon(
    1% 2%,
    3.5% 0.1%,
    8.9% 2.7%,
    16.2% 1.2%,
    19.7% 0.8%,
    24.7% 2%,
    31.2% 0%,
    35.9% 2.3%,
    40.6% 0.5%,
    47.1% 1.8%,
    49.9% 1%,
    53.8% 2.5%,
    62.2% 0.4%,
    67.3% 1.2%,
    70.3% 0.3%,
    75.8% 1.4%,
    78.5% 1.7%,
    84% 2.6%,
    91.1% 0.5%,
    93.6% 2.4%,
    97.3% 0.3%,
    98.9% 6%,
    98.1% 11.4%,
    98.6% 15.5%,
    97.3% 17.8%,
    97.7% 27.4%,
    98.3% 28.5%,
    99.7% 34.1%,
    98.8% 41.9%,
    98.3% 45.7%,
    98.4% 50.9%,
    98.7% 57.4%,
    99.6% 60.4%,
    99.4% 66.6%,
    99.2% 69.5%,
    97.4% 77.1%,
    98.1% 82.3%,
    99.9% 83.5%,
    98.2% 91.5%,
    99.3% 95.3%,
    98% 98.6%,
    93.6% 97.4%,
    90.3% 98.3%,
    86.8% 98.3%,
    78.6% 98.5%,
    76.4% 99.2%,
    69.7% 99.8%,
    64.2% 99.3%,
    61.8% 99.2%,
    57.2% 98.1%,
    48.7% 98.3%,
    46.8% 99%,
    39.6% 98.9%,
    33.8% 97.3%,
    28.2% 99.4%,
    27% 98.3%,
    22.1% 98.6%,
    13.1% 97.1%,
    8.7% 99.3%,
    2.7% 97.4%,
    2% 98.2%,
    2.1% 94.3%,
    0.7% 90.5%,
    0.9% 86.3%,
    2.2% 78.2%,
    0.4% 76.7%,
    1.2% 72.1%,
    2% 64%,
    1.4% 59.8%,
    0.4% 57.3%,
    0.3% 49.1%,
    2.9% 44.7%,
    1.9% 41.3%,
    0.8% 35.7%,
    2.9% 31.3%,
    0.8% 24.9%,
    2.7% 20.5%,
    0.8% 17.4%,
    2.5% 8.6%,
    1.1% 5.5%
  );
}
</style>
