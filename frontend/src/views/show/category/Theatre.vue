<template>
<div>
  <Popular 
  :swiperOption="swiperOption1"
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
</div>
</template>

<script>
import Popular from '../recommand/Popular.vue'
import ComingEnd from '../recommand/ComingSoon.vue'
import ComingSoon from '../recommand/ComingEnd.vue'
// import TheatrePreferenceTag from '../views/show/recommand/theatre/PreferenceTag.vue'
// import TheatreSimilar from '../views/show/recommand/theatre/Similar.vue'
// import TheatrePlace from '../views/show/recommand/theatre/Place.vue'


export default {
  name: 'Theatre',
  data(){
    return{
      num: 5,
      genre: '연극',
      swiperOption: {
        slidesPerView: 5,
        spaceBetween: 30,
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        }
      },
      swiperOption1: {
        slidesPerView: 5,
        spaceBetween: 30,
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        },
        autoplay: {
        delay:2500,
        }
      },
    }
  },

  components: {
    Popular,
    ComingEnd,
    ComingSoon,
    // TheatrePreferenceTag,
    // TheatreSimilar,
    // TheatrePlace,
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