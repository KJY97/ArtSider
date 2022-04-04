<template>
  <div class="home">
    <HomeCarousel />
    <v-container>
      <p class="text-center">
        <span class="text-h2"> Poster </span>
      </p>
      <v-row>
        <v-col
          v-for="(popular, i) in popularList"
          :key="i"
          cols="4"
          class="d-flex justify-center"
        >
          <v-card
            max-height="400"
            max-width="300"
          >
          <v-img
            max-height="400"
            max-width="300"
            :src="popular.posterPath"
          ></v-img>
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
          @click="moveToMain"
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
      popularList: [],
    }
  }, 

  components: {
    HomeCarousel
  },

  methods: {
    getPopularShow: function() {
      this.$store.dispatch('getPopularShow')
      .then(res => {
        this.popularList = res.data.items.slice(0,6)
      })
    },
    moveToMain: function () {
      this.$router.push({name: 'Main'})
    }
  },
  created: function () {
    console.log('생성')
    this.getPopularShow()
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
