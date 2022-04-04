<template>
  <div>
    <div class="d-flex justify-center flex-column align-center mt-5">
      <h3>공연장 및 주변시설</h3>
      <p v-if="showArtCenterList.artCenterTel">공연장 전화 번호 : {{ showArtCenterList.artCenterTel }}</p>
    </div>
    <br>
    <KakaoMap 
      :artCenterAddress= showArtCenterList.artCenterAddress
      :name= showArtCenterList.artCenterName
    />
    <br>
  </div>
</template>

<script>
import KakaoMap from "./KakaoMap";

export default {
  name: 'ShowArtCenter',
  props: {
    ArtCenterName: String,
  },
  components: {
    KakaoMap
  },
  data: function() {
    return{
      showArtCenterList: [],
    }
  },
  methods: {
    getShowArtCenter: function (ArtCenterName) {
      this.$store.dispatch('getshowArtCenter', {ArtCenterName:ArtCenterName})
      .then(res => {
        this.showArtCenterList = res.data.items[0]
      })
    },
  },
  created: function () {
    this.getShowArtCenter(this.ArtCenterName)
  },

}
</script>

<style>

</style>