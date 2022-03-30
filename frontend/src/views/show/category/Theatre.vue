<template>
<div>
  <div class=recommand-list>
  <Popular 
    :num = num
    :genre = genre
  />
  <!-- 하위 컴포넌트에 num 값을 내려줌 -->
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
  <TheatrePreferenceTag />
  </div>
  <div class=recommand-list>
  <TheatreSimilar />
  </div>
  <div class=recommand-list>
  <TheatrePlace />
  </div> -->
</div>
</template>

<script>
import Popular from '../views/show/recommand/category/Popular.vue'
import ComingEnd from '../views/show/recommand/category/ComingSoon.vue'
import ComingSoon from '../views/show/recommand/category/ComingEnd.vue'
// import TheatrePreferenceTag from '../views/show/recommand/theatre/PreferenceTag.vue'
// import TheatreSimilar from '../views/show/recommand/theatre/Similar.vue'
// import TheatrePlace from '../views/show/recommand/theatre/Place.vue'


export default {
  name: 'Theatre',
  data(){
    return{
      num: 5,
      genre: '연극'
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
</style>