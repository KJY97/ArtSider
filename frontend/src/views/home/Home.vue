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
      <v-row v-if="SimilarityShowList.length == 6">
        <v-col
          v-for="(show, i) in SimilarityShowList"
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
            style="width:100%; height:100%; cursor:pointer;"
            max-height="600"
            max-width="450"
            :src="show.posterPath"
            @click="moveShowDetail(show.id)"
          ></v-img>
          </v-card>
        </v-col>
      </v-row>
      <v-row v-if="SimilarityShowList.length < 6">
        <v-col
          v-for="(show, i) in PopularShowList"
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
            style="width:100%; height:100%; cursor:pointer;"
            max-height="600"
            max-width="450"
            :src="show.posterPath"
            @click="moveShowDetail(show.id)"
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
      SimilarityShowList: [],
      PopularShowList: [],
    }
  }, 

  components: {
    HomeCarousel
  },

  methods: {
    getSimilarityShow: function() {
      this.$store.dispatch('getSimilarityShow', {userId: this.userId})
      .then(res => {
        if (res.data.items.length > 5) {
          this.SimilarityShowList = res.data.items.slice(0,6)
        }
      })
    },
    getCategoryPopularShow: function(num) {
      this.$store.dispatch('getCategoryPopularShow', num)
      .then(res => {
        this.PopularShowList = res.data.items.slice(0,6)
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
    this.getSimilarityShow()
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
