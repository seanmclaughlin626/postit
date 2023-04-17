<template>
  <div>
      <div class="comment">
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
  props: {comment: Object},
    
}
</script>

<style>

</style>