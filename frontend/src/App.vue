<template>
  <v-app 
    app
    id="inspire"
    style="background-color: #FEFEF4;">
      <NavBar />
      <v-main id="main" style="overflow:auto; height:100vh;" >
        <router-view :key="$route.fullPath"/>
      </v-main>
   
  </v-app>
</template>

<script>
import NavBar from '@/components/NavBar'

export default {
  name: 'App',

  components: {
    NavBar
  },

  data(){
    return{
    }
  },

  created(){
    const token = localStorage.getItem('accessToken')
    if(token){
      this.$store.dispatch('setToken', token)
    } 
  }
}
</script>

<style>
@import '../src/assets/css/font.css';

html{
  overflow-y: auto !important;
}

</style>
