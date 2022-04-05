<template>
<div>
  <br>
  <h1 class="ml-4 mb-4 d-flex align-center"><v-icon size="48px" color="pink">mdi-speedometer</v-icon>곧 상영 마감</h1> 
  <swiper class="swiper" 
    :options="swiperOption">
    <swiper-slide
    v-for="comingEnd in comingEndList" 
    :key="comingEnd.id"
    > 
    <v-img
          @click="addInquire(comingEnd.id)"
          :aspect-ratio="3/4"
          :src="comingEnd.posterPath"
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
  name: 'ComingEnd',
  data(){
    return{
      swiper: null,
      comingEndList: [],
    }
  },
  props:{
    swiperOption: Object,
    num: Number,

  },

  components:{
    Swiper,
    SwiperSlide,
  },

  methods: {
    getCategoryComingEndShow: function(num) {
      this.$store.dispatch('getCategoryComingEndShow', {num:num})
      .then(res => {
        this.comingEndList = res.data.items
      })
    },
    addInquire: function (id) {
      this.$store.dispatch('addInquire', {id:id})
      .then(() => {
        this.$router.push({name: `ShowDetail`, params: { showId: id}})
      })
    }
  },
  created: function() {
    this.getCategoryComingEndShow(this.num) 
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
  color: pink;
}
.swiper-button-next {
  color: pink;
}

</style>