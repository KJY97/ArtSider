<template>
<div>
  <Popular 
  :swiperOption="swiperOption"
  :num = num
  :genre = genre
  />
  <ComingSoon 
  :swiperOption="swiperOption"
  :num = num
  />
  <ComingEnd 
  :swiperOption="swiperOption"
  :num = num
  />
  <!-- <div class=recommand-list>
  <ConcertPreferenceTag />
  </div>
  <div class=recommand-list>
  <ConcertSimilar />
  </div>
  <div class=recommand-list>
  <ConcertPlace />
  </div> -->
</div>
</template>

<script>
// import Swiper from 'swiper/js/swiper.esm.bundle'
// import 'swiper/css/swiper.css'

import Popular from '../recommand/Popular.vue'
import ComingEnd from '../recommand/ComingSoon.vue'
import ComingSoon from '../recommand/ComingEnd.vue'
// import ConcertPreferenceTag from '../views/show/recommand/concert/PreferenceTag.vue'
// import ConcertSimilar from '../views/show/recommand/concert/Similar.vue'
// import ConcertPlace from '../views/show/recommand/concert/Place.vue'


export default {
  name: 'Concert',
  data(){
    return{
      num : 2,
      genre: '콘서트',
      swiperOption: {
        slidesPerView: 5,
        spaceBetween: 30,
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        }
      }
    }
  },

  components: {
    Popular,
    ComingEnd,
    ComingSoon,
    // ConcertPreferenceTag,
    // ConcertSimilar,
    // ConcertPlace,
  },
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
</style>