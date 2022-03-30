<template>
<div class="swiper mt-6">
  <h1 class="ml-4 mb-4">{{ genre }} 인기 순위</h1> 
  <div class="swiper-wrapper">
    <div v-for="(idx, popular) in popularList" :key="idx">
      <div class="swiper-slide">
        <v-img
          @click="$router.push({path: `show-detail/${popular.id}`})"
          :aspect-ratio="3/4"
          :src="popular.posterPath"    
          >
          </v-img>
          <div class="rank">
            <h1>{{ popular.rank }}</h1>  
          </div>
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
  name: 'Popular',
  data(){
    return{
      swiper: null,
      popularList: [],
    }
  },
  props: {
    num: Number,
    genre: String,
  },

  methods: {
    getCategoryPopularShow: function() {
      this.$store.dispatch('getCategoryPopularShow', {num:this.num})
      .then(res => {
        console.log(res.data)
        this.popularList = res.data
      })
    }
  },
  mounted(){
    this.swiper = new Swiper('.swiper', {
      observer : true,
      observerParents : true,
      slidesPerView: 7,
      slidePerGroup: 5,
      spaceBetween : 30,
      direction: 'horizontal',
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      },
    })
  },
  created: function() {
    this.getCategoryPopularShow() 
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