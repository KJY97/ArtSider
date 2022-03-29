<template>
  <div>
    <p>공연장 위치</p>
    <p>{{ showArtCenterList.name }}</p>
    <p>{{ showArtCenterList.address }}</p>
    <p>{{ showArtCenterList.infor.seat_count }}</p>
    <p>{{ showArtCenterList.infor.tel_num }}</p>
    <p>{{ showArtCenterList.infor.url }}</p>
    <p>{{ showArtCenterList.infor.specificity }}</p>
    <KakaoMap 
      :latitude= showArtCenterList.latitude
      :longitude= showArtCenterList.longitude
      :name= showArtCenterList.name
    />
    
  </div>
</template>

<script>
import axios from'axios'
import KakaoMap from "./KakaoMap";

export default {
  name: 'ShowArtCenter',
  props: {
    ArtCenterName: Object
  },
  components: {
    KakaoMap
  },
  data: function() {
    return{
      showArtCenterList: {
        concertId: 1,
        area: "경기",
        name: "문화누리공연장",
        latitude: 37.53,
        longitude: 127.5,
        address: "경기 고양시 덕양구 ??로 ??번길",
        infor: {
          open_year : 2001,
          seat_count: 300,
          tel_num: "031-854-1222",
          url: "https://munhwacity.go.kr",
          specificity : "주차시설 협소",
        }
      },
    }
  },
  methods: {
    getshowArtCenter: function (ArtCenterName) {
      axios({
        method: 'get',
        url: `http://127.0.0.1:8000/api/show/${ArtCenterName}/artcenter`,
      })
      .then(res => {
        console.log(res)
        this.showArtCenterList = res.data.items[0]
      })
    },
  },
  created: function () {
    this.id = this.$route.params.id
    this.getShowArtCenter(this.ArtCenterName)
  },

}
</script>

<style>

</style>