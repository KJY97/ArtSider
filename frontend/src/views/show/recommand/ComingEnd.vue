<template>
<div class="swiper mt-6">
  <h1 class="ml-4 mb-4">종료 임박</h1> 
  <div class="swiper-wrapper">
    <div v-for="(comingEnd, idx) in comingEndList" :key="idx">
      <div class="swiper-slide">
        <v-img
          @click="addInquire(comingSoon.id)"
          :aspect-ratio="3/4"
          :src="comingEnd.posterPath"    
          >
          </v-img>
      </div>
    </div>
    </div>
  <div class="swiper-button-next"></div>
  <div class="swiper-button-prev"></div>
</div>
</template>

<script>
import Swiper from 'swiper/js/swiper.esm.bundle'
import 'swiper/css/swiper.css'

export default {
  name: 'ComingEnd',
  data(){
    return{
      swiper: null,
      comingEndList: [],
    }
  },

  methods: {
    getComingEndShow: function() {
      this.$store.dispatch('getComingEndShow')
      .then(res => {
        console.log(res.data)
        this.comingEndList = res.data.items
      })
    },
    addInquire: function (id) {
      this.$store.dispatch('addInquire', id)
      .then(() => {
        this.$router.push({name: `ShowDetail`, params: { showId: id}})
      })
    }
  },
   mounted(){
    this.swiper = new Swiper('.swiper', {
      observer : true,
      observerParents : true,
      slidesPerView: 5,
      slidePerGroup: 5,
      spaceBetween : 30,
      touchRatio: 0,
      direction: 'horizontal',
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      },
    })
  },
  created: function() {
    this.getComingEndShow() 
  }
}
</script>

<style>

.swiper {
  position: relative;
  width: 100%;
  height: 40%;
  
}

.swiper-slide {
  text-align: center;
  font-size: 18px;
  background: gray;

  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  -webkit-align-items: center;
  align-items: center;
  cursor: pointer;
}

.rank {
  color: #000;
  -webkit-text-stroke: 1px #fff;
  position: absolute;
  font-size: 4rem;
}

</style>