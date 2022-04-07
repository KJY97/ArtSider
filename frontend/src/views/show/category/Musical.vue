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
   <RelatedTagShow
    :swiperOption="swiperOption"
    :num = 4
  />
  <!-- <div class=recommand-list>
  <MusicalPreferenceTag />
  </div>
  <div class=recommand-list>
  <MusicalSimilar />
  </div>
  <div class=recommand-list>
  <MusicalPlace />
  </div> -->
</div>
</template>

<script>
import Popular from '../recommand/Popular.vue'
import ComingEnd from '../recommand/ComingSoon.vue'
import ComingSoon from '../recommand/ComingEnd.vue'
import RelatedTagShow from '../recommand/RelatedTagShow.vue'
// import MusicalPreferenceTag from '../views/show/recommand/musical/PreferenceTag.vue'
// import MusicalSimilar from '../views/show/recommand/musical/Similar.vue'
// import MusicalPlace from '../views/show/recommand/musical/Place.vue'


export default {
  name: 'Musical',
  data(){
    return{
      num: 4,
      genre: '뮤지컬',
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
    RelatedTagShow,
    // MusicalPreferenceTag,
    // MusicalSimilar,
    // MusicalPlace,
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