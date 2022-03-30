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
import Popular from '../views/show/recommand/category/Popular.vue'
import ComingEnd from '../views/show/recommand/category/ComingSoon.vue'
import ComingSoon from '../views/show/recommand/category/ComingEnd.vue'
// import MusicalPreferenceTag from '../views/show/recommand/musical/PreferenceTag.vue'
// import MusicalSimilar from '../views/show/recommand/musical/Similar.vue'
// import MusicalPlace from '../views/show/recommand/musical/Place.vue'


export default {
  name: 'Musical',
  data(){
    return{
      num: 4,
      genre: '뮤지컬'
      // swiper: null,
    }
  },

  components: {
    Popular,
    ComingEnd,
    ComingSoon,
    // MusicalPreferenceTag,
    // MusicalSimilar,
    // MusicalPlace,
  },
}
</script>
<style>
</style>