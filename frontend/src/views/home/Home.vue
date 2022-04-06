<template>
  <div class="home">
    <HomeCarousel 
    :num=0
    />
    <br>
    <v-container class="poster-wrapper">
      <p class="text-center">
        <span class="text-h3 d-flex align-center justify-center" ><v-icon size="57.6px" color="yellow">mdi-star</v-icon> 맞춤 공연 추천 </span>
      </p>
      <br>
      <v-row>
        <v-col
          v-for="(popular, i) in popularList"
          :key="i"
          cols="4"
          class="d-flex justify-center"
        >
          <v-card
            style="width:100%; height:100%"
            max-height="600"
            max-width="450"
          >
          <v-img
            style="width:100%; height:100%"
            max-height="600"
            max-width="450"
            :src="popular.posterPath"
            @click="moveShowDetail(popular.id)"
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
    getCategoryPopularShow: function(num) {
      this.$store.dispatch('getCategoryPopularShow', num)
      .then(res => {
        this.popularList = res.data.items.slice(0,6)
      })
    },
    moveToMain: function () {
      this.$router.push({name: 'Main'})
    },
    moveShowDetail: function (id) {
      this.$router.push({name: `ShowDetail`, params: { showId: id}})
    }
  },
  created: function () {
    console.log('생성')
    this.getCategoryPopularShow(0)
  }
}
</script>
<style>
.home{
  position: relative;
  width: 100%;
  height: fit-content;
}


</style>
