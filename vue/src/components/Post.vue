<template>
    <div class="postCard">
        <h1>{{post.title}}</h1>
        <p>{{post.content}}</p>
        <h2>{{post.author}}</h2>
        <p>{{post.timeCreated}}</p>
    </div>
</template>

<script>

export default {
    name: 'post',
    props: {post: Object},

}
    
</script>

<style scoped>
    .postCard{
        border: 1px solid black;
        
    }
</style>