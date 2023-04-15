<template>
  <div class="post-card">
      <h2>{{ post.title }}</h2>
      <p>{{ post.content }}</p>
      <p class="signature">
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
      return this.forum.name;
    },
  },
  created() {
    forumService.getForum(this.post.forumId).then((response) => {
      this.forum = response.data;
    });
  },
};
</script>

<style scoped>
/* .postCard{
        border: 2px solid black;
    } */
    body {
  margin: 20px auto;
  font-family: 'Lato';
  background:#666;
  color:#fff;
}
*{
  margin:0;
  padding:0;
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
.post-card{
  text-decoration:none;
  color:#000;
  background:#ffc;
  display:block;
  height:20em;
  width:20em;
  padding:1em;
  margin:1em;
  box-shadow: 5px 5px 7px rgba(33,33,33,.7);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transform: rotate(-0.5deg);
}

.signature{
    font-size: 0.85rem;
}
</style>

