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
  <!-- <div class=recommand-list>
  <PreferenceTag />
  </div>
  <div class=recommand-list>
  <Similar />
  </div>
  <div class=recommand-list>
  <Place />
  </div> -->
</div>
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
}
</script>
<style>
</style>