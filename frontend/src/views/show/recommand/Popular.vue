<template>
<div>
  <br>
  
  <h1 class="ml-4 mb-4 d-flex align-center" v-if="genre != 'main'"><v-icon size="48px" color="red">mdi-fire</v-icon>{{ genre }} 인기 순위</h1> 
  <h1 class="ml-4 mb-4 d-flex align-center" v-if="genre == 'main'"><v-icon size="48px" color="red">mdi-fire</v-icon>인기 순위</h1> 
  <swiper class="swiper" 
    :options="swiperOption">
    <swiper-slide
    v-for="popular in popularList" 
    :key="popular.id"
    > 
    <v-img
          @click="addInquire(popular.id)"
          :aspect-ratio="3/4"
          :src="popular.posterPath"
          style="width:50%;height:50%;"
          >
    </v-img>
    </swiper-slide>
    <div class="swiper-button-prev" slot="button-prev"></div> 
    <div class="swiper-button-next" slot="button-next"></div>
  </swiper>
  <br>
</div>
</template>

<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper'

export default {
  name: 'Popular',
  data(){
    return{
      swiper: null,
      popularList: [],
    }
  },

  props:{
    swiperOption: Object,
    num: Number,
    genre: String 
  },

  components:{
    Swiper,
    SwiperSlide,
  },

  methods: {
    getCategoryPopularShow: function(num) {
      this.$store.dispatch('getCategoryPopularShow', num)
      .then(res => {
        this.popularList = res.data.items
      })
    },
    addInquire: function (id) {
      this.$store.dispatch('addInquire',{id:id})
      .then(() => {
        this.$router.push({name: `ShowDetail`, params: { showId: id}})
      })
    }
  },
  created: function() {
    // 새로 시작할 때 발생하는 이벤트
    // 페이지 처음 들어올때 수행하는 이벤트
    this.getCategoryPopularShow(this.num) 
  }
}
</script>

<style scoped>
.rank {
  color: #000;
  -webkit-text-stroke: 1px #fff;
  position: absolute;
  font-size: 4rem;
}
.swiper-button-prev {
  color: red;
}
.swiper-button-next {
  color: red;
}
</style>