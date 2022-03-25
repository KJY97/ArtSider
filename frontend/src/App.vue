<template>
  <v-app style="background-color: #FEFEF4">
    <NavBar />
    <v-main>
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
  }
}
</script>

<style>
html{
  overflow-y: auto !important;
}


</style>
