<template>
  <div>
      <div class="comment-card">
      <p>{{comment.content}}</p>
      <p class="signature">
        <i>
         Commented by {{ comment.author }} on {{ comment.timeCreatedFormatted }}
        </i>
      </p>
  </div>
  </div>
</template>

<script>

export default {
  name: "comment",
  props: {comment: Object}
}
</script>

<style scoped>
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
p {
  font-size: 1rem;
  font-weight: normal;
  font-family: courier, monospace;
}
.comment-card{
  text-decoration:none;
  color:#000;
  background:#C0C0F2;
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