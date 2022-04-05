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
  <!-- <div class=recommand-list>
  <ClassicPreferenceTag />
  </div>
  <div class=recommand-list>
  <ClassicSimilar />
  </div>
  <div class=recommand-list>
  <ClassicPlace />
  </div> -->
</div>
</template>

<script>
import Popular from '../recommand/Popular.vue'
import ComingEnd from '../recommand/ComingSoon.vue'
import ComingSoon from '../recommand/ComingEnd.vue'
// import ClassicPreferenceTag from '../views/show/recommand/classic/PreferenceTag.vue'
// import ClassicSimilar from '../views/show/recommand/classic/Similar.vue'
// import ClassicPlace from '../views/show/recommand/classic/Place.vue'


export default {
  name: 'Classic',
  data(){
    return{
      num : 1,
      genre: '클래식',
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
    // ClassicPreferenceTag,
    // ClassicSimilar,
    // ClassicPlace,
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