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
import Popular from '../views/show/recommand/category/Popular.vue'
import ComingEnd from '../views/show/recommand/category/ComingSoon.vue'
import ComingSoon from '../views/show/recommand/category/ComingEnd.vue'
// import FamilyPreferenceTag from '../views/show/recommand/family/PreferenceTag.vue'
// import FamilySimilar from '../views/show/recommand/family/Similar.vue'
// import FamilyPlace from '../views/show/recommand/family/Place.vue'


export default {
  name: 'Family',
  data(){
    return{
      num : 3,
      genre: '가족/어린이'
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
</style>