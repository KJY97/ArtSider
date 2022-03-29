<template>
  <v-app 
    app
    id="inspire"
    style="background-color: #FEFEF4">
    <NavBar />
    <v-sheet max-height="100vh" id="main" style="overflow: auto">
      <v-main>
        <router-view/>
      </v-main>
    </v-sheet>
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
