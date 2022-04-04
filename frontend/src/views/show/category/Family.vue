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
  <PreferenceTag />
  </div>
  <div class=recommand-list>
  <FamilySimilar />
  </div>
  <div class=recommand-list>
  <FamilyPlace />
  </div> -->
</div>
</template>

<script>
import Popular from '../recommand/Popular.vue'
import ComingEnd from '../recommand/ComingSoon.vue'
import ComingSoon from '../recommand/ComingEnd.vue'
// import FamilyPreferenceTag from '../views/show/recommand/family/PreferenceTag.vue'
// import FamilySimilar from '../views/show/recommand/family/Similar.vue'
// import FamilyPlace from '../views/show/recommand/family/Place.vue'



export default {
  name: 'Family',
  data(){
    return{
      num : 3,
      genre: '가족/어린이',
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
    // FamilyPreferenceTag,
    // FamilySimilar,
    // FamilyPlace,
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