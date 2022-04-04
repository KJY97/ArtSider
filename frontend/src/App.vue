<template>
  <v-app 
    app
    id="inspire"
    style="background-color: #FEFEF4;">
      <NavBar />
      <v-main id="main" style="overflow:auto; height:100vh;" >
        <router-view/>
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
html{
  overflow-y: auto !important;
}

</style>
