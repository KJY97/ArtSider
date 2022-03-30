<template>
<div>
  <div class=recommand-list>
  <TheatrePopular />
  </div>
  <div class=recommand-list>
  <TheatreComingEnd />
  </div>
  <div class=recommand-list>
  <TheatreComingSoon />
  </div>
  <div class=recommand-list>
  <TheatrePreferenceTag />
  </div>
  <div class=recommand-list>
  <TheatreSimilar />
  </div>
  <div class=recommand-list>
  <TheatrePlace />
  </div>
</div>
</template>

<script>
// import Swiper from 'swiper/js/swiper.esm.bundle'
// import 'swiper/css/swiper.css'

import TheatrePopular from '../views/show/recommand/theatre/Popular.vue'
import TheatreComingEnd from '../views/show/recommand/theatre/ComingSoon.vue'
import TheatreComingSoon from '../views/show/recommand/theatre/ComingEnd.vue'
import TheatrePreferenceTag from '../views/show/recommand/theatre/PreferenceTag.vue'
import TheatreSimilar from '../views/show/recommand/theatre/Similar.vue'
import TheatrePlace from '../views/show/recommand/theatre/Place.vue'


export default {
  name: 'Main',
  data(){
    return{
      // swiper: null,
    }
  },

  components: {
    TheatrePopular,
    TheatreComingEnd,
    TheatreComingSoon,
    TheatrePreferenceTag,
    TheatreSimilar,
    TheatrePlace,
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