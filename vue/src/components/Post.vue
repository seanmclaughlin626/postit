<template>
    <div class="post-card">
      <h2>{{ post.title }}</h2>
      <p>{{ post.content }}</p>
      <p class="signature" v-if="forum">
        <i
          >Posted in
          <router-link
            v-bind:to="{ name: 'forum', params: { id: this.post.forumId } }"
            >{{ forumName }}</router-link
          >
          by {{ post.authorName }} on {{ post.timeFormatted }}</i
        >
      </p>
    </div>
</template>

<script>
import forumService from "../services/ForumService";

export default {
  name: "post",
  props: { post: Object },
  data() {
    return {
      forum: {},
    };
  },
  computed: {
    forumName() {
      return this.forum.name ? this.forum.name : "loading...";
    },
  },
  created() {
    try {
      forumService.getForum(this.post.forumId).then((response) => {
        this.forum = response.data;
      })
    } catch (error) {
      console.error('Error fetching forum:', error)
    }
  },
};
</script>

<style scoped>

body {
  margin: 20px auto;
  font-family: "Lato";
  background: #666;
  color: #fff;
}
* {
  margin: 0;
  padding: 0;
}
h2 {
  font-weight: bold;
  font-size: 1.5rem;
  font-family: courier, monospace;
}
p {
  font-size: 1rem;
  font-weight: normal;
  font-family: courier, monospace;
}
.post-card {
  text-decoration: none;
  color: #000;
  background: #ffc;
  display: block;
  height: 20em;
  width: 20em;
  padding: 1em;
  margin: 1em;
  box-shadow: 5px 5px 7px rgba(33, 33, 33, 0.7);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transform: rotate(-0.5deg);
}

.signature {
  font-size: 0.85rem;
}
</style>

