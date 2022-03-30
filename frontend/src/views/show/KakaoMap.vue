<template>
 <!-- <div style="margin-bottom:100px">
  <div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <ul id="category">
      <li id="CT1" data-order="0"> 
        <a @click="changeCategory(0)" class="category_bg">문화시설</a>
      </li>       
      <li id="FD6" data-order="1"> 
        <a @click="changeCategory(1)" class="category_bg">음식점</a>
      </li>  
      <li id="CE7" data-order="2"> 
        <a @click="changeCategory(2)" class="category_bg">카페</a>
      </li>  
    </ul>
  </div>
 </div> -->
<div style="margin-bottom:100px">
  <div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <ul id="category">
        <v-btn @click="changeCategory(0)" class="category_bg">문화시설</v-btn>     
        <v-btn @click="changeCategory(1)" class="category_bg">음식점</v-btn>
        <v-btn @click="changeCategory(2)" class="category_bg">카페</v-btn>
    </ul>
  </div>
  <br>
</div>
</template>

<script>
export default {
  name: "KakaoMap",
  props: {
    latitude: Number,
    longitude: Number,
    name: String,
  },
  data() {
    return {
      markers: [], // 마커를 담을 배열입니다
      infowindow: null,
      markerPositions: [[this.latitude,this.longitude]],
      ps: '',
      placeOverlay: '',
      contentNode: '', // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다 
      currCategory: '', // 현재 선택된 카테고리를 가지고 있을 변수입니다
      map: '',
      category: '',
      children: '',
      marker: '',
      content: '',
      cg: '',
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=c5c67b82497fef1c4c464ebe107de3ea&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const mapContainer = document.getElementById("map"),
        mapOption = {
              center: new kakao.maps.LatLng(this.latitude, this.longitude), // 지도의 중심좌표
              level: 5 // 지도의 확대 레벨
          };  
      // 지도를 생성합니다    
      this.map = new kakao.maps.Map(mapContainer, mapOption); 
      this.placeOverlay = new kakao.maps.CustomOverlay({zIndex:1})
    
      // this.markers.forEach((marker) => marker.setMap(null));

      // // 마커 생성 및 맵에 적용
      // const positions = this.markerPositions.map(
      //   (position) => new kakao.maps.LatLng(...position)
      // );
      // if (positions.length > 0) {
      //   this.markers = positions.map(
      //     (position) =>
      //       new kakao.maps.Marker({
      //         map: this.map,
      //         position,
      //       })
      //   );
      //   const bounds = positions.reduce(
      //     (bounds, latlng) => bounds.extend(latlng),
      //     new kakao.maps.LatLngBounds()
      //   );

      //   this.map.setBounds(bounds)
      // }
      var markerPosition  = new kakao.maps.LatLng(this.latitude, this.longitude); 
      var marker = new kakao.maps.Marker({
          position: markerPosition
      });
      marker.setMap(this.map);
    },
    changeCategory: function (num) {
      // 커스텀 오버레이를 숨깁니다 

      if (num == 0){
        this.cg = "CT1"
      } else if (num == 1) {
        this.cg = "FD6"
      } else {
        this.cg = "CE7"
      }
      // 커스텀 오버레이를 숨깁니다 
      this.placeOverlay.setMap(null);
      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker(); 
      var places = new kakao.maps.services.Places(this.map);

      
      // 공공기관 코드 검색
      places.categorySearch(this.cg, this.placesSearchCB, {useMapBounds:true});
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB: function (data, status, ) {

      if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      }
    },

    // 지도에 마커를 표출하는 함수입니다
    displayPlaces: function (places) {

        for ( var i=0; i<places.length; i++ ) {

          // 마커를 생성하고 지도에 표시합니다
          var marker = this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x));
          // 마커와 검색결과 항목을 클릭 했을 때
          // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
          var placeOverlay = new kakao.maps.CustomOverlay({zIndex:1})
          kakao.maps.event.addListener(marker, 'click', this.displayPlaceInfo(places[i], placeOverlay))
        }
    },
 
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker: function (position, ) {
      // console.log('마커를 생성')!
      var marker = new kakao.maps.Marker({
        position: position
      });

        marker.setMap(this.map); // 지도 위에 마커를 표출합니다
        this.markers.push(marker);  // 배열에 생성된 마커를 추가합니다

        return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker: function () {

        for ( var i = 0; i < this.markers.length; i++ ) {
            this.markers[i].setMap(null);
        }   
        this.markers = [];
    },

    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayPlaceInfo: function (place, placeOverlay) {
      placeOverlay.setMap(null);
        var content = '<div class="placeinfo">' +
                        '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a><br>';   

        if (place.road_address_name) {
            content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span><br>' +
                        '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span><br>';
        }  else {
            content += '    <span title="' + place.address_name + '">' + place.address_name + '</span><br>';
        }                
      
        content += '    <span class="tel">' + place.phone + '</span>' + 
                    '</div>' + 
                    '<div class="after"></div>';
        placeOverlay.setContent(content);
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(this.map);
    },
    
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 500px;
  height: 500px;
}
.map_wrap, .map_wrap * {margin:0; padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap {position:relative;width:100%;height:350px;}
#category {position:absolute;top:10px;left:10px;border-radius: 5px; border:1px solid #909090;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);background: #fff;overflow: hidden;z-index: 2;}

.placeinfo {position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background-color: #fff;}
.placeinfo:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.placeinfo_wrap .after {content:'';position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.placeinfo a, .placeinfo a:hover, .placeinfo a:active{color:#fff;text-decoration: none;}
.placeinfo a, .placeinfo span {display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
.placeinfo span {margin:5px 5px 0 5px;cursor: default;font-size:13px;}
.placeinfo .title {font-weight: bold; font-size:14px;border-radius: 6px 6px 0 0;margin: -1px -1px 0 -1px;padding:10px; color: #fff;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.placeinfo .tel {color:#0f7833;}
.placeinfo .jibun {color:#999;font-size:11px;margin-top:0;}
</style>
