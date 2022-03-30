<template>
<div>
  <div class=recommand-list>
  <Popular />
  </div>
  <div class=recommand-list>
  <ComingEnd />
  </div>
  <div class=recommand-list>
  <ComingSoon />
  </div>
  <div class=recommand-list>
  <PreferenceTag />
  </div>
  <div class=recommand-list>
  <Similar />
  </div>
  <div class=recommand-list>
  <Place />
  </div>
</div>
</template>

<script>
// import Swiper from 'swiper/js/swiper.esm.bundle'
// import 'swiper/css/swiper.css'

import Popular from '../views/show/recommand/Popular.vue'
import ComingEnd from '../views/show/recommand/ComingSoon.vue'
import ComingSoon from '../views/show/recommand/ComingEnd.vue'
import PreferenceTag from '../views/show/recommand/PreferenceTag.vue'
import Similar from '../views/show/recommand/Similar.vue'
import Place from '../views/show/recommand/Place.vue'


export default {
  name: 'swiperTest',
  data(){
    return{
      // swiper: null,
    }
  },

  components: {
    Popular,
    ComingEnd,
    ComingSoon,
    PreferenceTag,
    Similar,
    Place,
  },
  // axios 로직 
  // 클릭했을때 상세정보로 이동
  // 카테고리별 일반추천 항목
  // 카테고리 별 메인화면 컴포넌트
  // 카테고리 별 createHook 포스터 정보 받아오게
  // 일단 에티켓 쳐내고 
  // 
  // mounted(){
  //   this.swiper = new Swiper('.swiper', {
  //     slidesPerView: 9,
  //     slidePerGroup: 6,
  //     direction: 'horizontal',
  //     navigation: {
  //       nextEl: '.swiper-button-next',
  //       prevEl: '.swiper-button-prev'
  //     },
  //   })
  // }
}
</script>
<style>

/* .swiper {
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
}

.rank {
  color: #000;
  -webkit-text-stroke: 1px #fff;
  position: absolute;
  font-size: 4rem;
}

.recommand-list {
  margin-bottom: 4rem;
} */

</style>