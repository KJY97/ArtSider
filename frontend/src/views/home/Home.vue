<template>
  <div class="home">
    <HomeCarousel />
    <v-container>
      <p class="text-center">
        <span class="text-h2"> Poster </span>
      </p>
      <v-row>
        <v-col
          v-for="(poster, i) in posters"
          :key="i"
          cols="4"
        >
          <v-card
            height="400px"
          >
          </v-card>
        </v-col>
      </v-row>
      <div class="d-flex justify-center">
        <v-btn
          class="mx-auto mt-5"
          width="20%"
          text
          outlined
          color="red"
          style="border-color: red;"
        >
          더보기
        </v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
import HomeCarousel from '@/views/home/components/HomeCarousel'


export default {
  name: 'Home',
  data(){
    return{
      loginDialog : false,
      posters: ['poster1', 'poster2', 'poster3', 'poster4', 'poster5', 'poster6']
    }
  }, 

  components: {
    HomeCarousel
  },

  methods: {

  }
}
</script>
<style>
.home{
  position: relative;
  width: 100%;
  height: fit-content;
  background-color: #FEFEF4;
}

</style>
