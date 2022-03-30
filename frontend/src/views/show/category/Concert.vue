<template>
<div>
  <div class=recommand-list>
  <Popular 
  :num = num
  :genre = genre
  />
  </div>
  <div class=recommand-list>
  <ComingEnd 
  :num = num
  />
  </div>
  <div class=recommand-list>
  <ComingSoon 
  :num = num
  />
  </div>
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

import Popular from '../views/show/recommand/category/Popular.vue'
import ComingEnd from '../views/show/recommand/category/ComingSoon.vue'
import ComingSoon from '../views/show/recommand/category/ComingEnd.vue'
// import ConcertPreferenceTag from '../views/show/recommand/concert/PreferenceTag.vue'
// import ConcertSimilar from '../views/show/recommand/concert/Similar.vue'
// import ConcertPlace from '../views/show/recommand/concert/Place.vue'


export default {
  name: 'Concert',
  data(){
    return{
      num : 2,
      genre: '콘서트'
      // swiper: null,
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
</style>