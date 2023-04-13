<template>
    <div class="postCard">
        <h1>{{post.title}}</h1>
        <p>{{post.content}}</p>
        <p><i>Posted in <router-link v-bind:to="{name: 'forum', params: {id: this.post.forumId}}">{{forumName}}</router-link> by {{post.authorName}} on {{post.timeFormatted}}</i></p>
    </div>
</template>

<script>
import forumService from '../services/ForumService';

export default {


    name: 'post',
    props: {post: Object},
    data(){
        return{
        forum: {}
        }
    },
    computed: {
        forumName(){
            return this.forum.name;
        }
    },
    created(){
        forumService.getForum(this.post.forumId).then((response) => {
            this.forum = response.data;
        });
    }
}
    
</script>

<style scoped>
    .postCard{
        border: 2px solid black;
        
    }
</style>