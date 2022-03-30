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
import Popular from '../views/show/recommand/category/Popular.vue'
import ComingEnd from '../views/show/recommand/category/ComingSoon.vue'
import ComingSoon from '../views/show/recommand/category/ComingEnd.vue'
// import ClassicPreferenceTag from '../views/show/recommand/classic/PreferenceTag.vue'
// import ClassicSimilar from '../views/show/recommand/classic/Similar.vue'
// import ClassicPlace from '../views/show/recommand/classic/Place.vue'


export default {
  name: 'Classic',
  data(){
    return{
      num : 1,
      genre: '클래식'
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
</style>