<template>
<div>
  <Popular 
    :swiperOption="swiperOption1"
    :num = 0
    :genre = main
  />
  <ComingSoon
    :swiperOption="swiperOption"
    :num = 0
  />
  <ComingEnd 
    :swiperOption="swiperOption"
    :num = 0
  />
</div>



<!-- <swiper class="swiper" 
:options="swiperOption">
  <swiper-slide
   v-for="(test, i) in tests"
    :key="i"
  > 
    {{ i }}
  </swiper-slide>
  <div class="swiper-button-prev" slot="button-prev"></div> 
  <div class="swiper-button-next" slot="button-next"></div> -->
  <!-- <div class=recommand-list>
    <Popular />
  </div> -->
  <!-- <div class=recommand-list>
  <ComingEnd />
  </div>
  <div class=recommand-list>
  <ComingSoon />
  </div> -->
  <!-- <div class=recommand-list>
  <PreferenceTag />
  </div>
  <div class=recommand-list>
  <Similar />
  </div>
  <div class=recommand-list>
  <Place />
  </div> -->
<!-- </swiper> -->
</template>

<script>
import Popular from '../views/show/recommand/Popular.vue'
import ComingEnd from '../views/show/recommand/ComingSoon.vue'
import ComingSoon from '../views/show/recommand/ComingEnd.vue'
// import PreferenceTag from '../views/show/recommand/PreferenceTag.vue'
// import Similar from '../views/show/recommand/Similar.vue'
// import Place from '../views/show/recommand/Place.vue'


export default {
  name: 'Main',
  data(){
    return{
      swiperOption: {
        slidesPerView: 5,
        spaceBetween: 30,
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        },
      },
      swiperOption1: {
        slidesPerView: 5,
        spaceBetween: 30,
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        },
        autoplay: {
        delay:1500,
        }
      },
      main: 'main'
    }
  },

  components: {
    Popular,
    ComingEnd,
    ComingSoon,
    // PreferenceTag,
    // Similar,
    // Place,
  },

  // mounted(){
  //   this.swiper = new Swiper('.swiper', {
  //     observer : true,
  //     observerParents : true,
  //     initialSlide: 1,
  //     loop: true,
  //     slidesPerView: 5,
  //     slidePerGroup: 5,
  //     spaceBetween : 30,
  //     touchRatio: 0,
  //     direction: 'horizontal',
  //     navigation: {
  //       nextEl: '.swiper-button-next',
  //       prevEl: '.swiper-button-prev'
  //     },
  //   });
  // },
  
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