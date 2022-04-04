<template>
<div>
  <h1 class="ml-4 mb-4">곧 상영 예정</h1> 
  <swiper class="swiper" 
    :options="swiperOption">
  <swiper-slide
   v-for="comingSoon in comingSoonList" 
   :key="comingSoon.id"
  > 
   <v-img
        @click="addInquire(comingSoon.id)"
        :aspect-ratio="3/4"
        :src="comingSoon.posterPath"
        style="width:50%;height:50%;"
        >
  </v-img>
  </swiper-slide>
  <div class="swiper-button-prev" slot="button-prev"></div> 
  <div class="swiper-button-next" slot="button-next"></div>
</swiper>
</div>
</template>

<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper'

export default {
  name: 'ComingSoon',
  data(){
    return{
      swiper: null,
      comingSoonList: [],
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
    getCategoryComingSoonShow: function(num) {
      this.$store.dispatch('getCategoryComingSoonShow', {num:num})
      .then(res => {
        this.comingSoonList = res.data.items
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
    this.getCategoryComingSoonShow(this.num) 
  }
}
</script>

<style>
.rank {
  color: #000;
  -webkit-text-stroke: 1px #fff;
  position: absolute;
  font-size: 4rem;
}

</style>