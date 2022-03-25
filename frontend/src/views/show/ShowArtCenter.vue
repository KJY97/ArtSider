<template>
  <div>
    <p>공연장 위치</p>
  </div>
</template>

<script>
import axios from'axios'

export default {
  name: 'ShowArtCenter',
  props: {
    ArtCenterName: Object
  },
  data: function() {
    return{
      showArtCenterList: [],
    }
  },
  methods: {
    getshowArtCenter: function(ArtCenterName) {
      axios({
        method: 'get',
        url: `http://127.0.0.1:8000/api/show/${ArtCenterName}/artcenter`,
      })
      .then(res => {
        console.log(res)
        this.showArtCenterList = res
      })
    }

  },
  created: function () {
    this.showId = this.$route.params.showId
    this.getShowArtCenter(this.ArtCenterName)
  }
}
</script>

<style>

</style>