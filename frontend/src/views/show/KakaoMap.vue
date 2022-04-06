<template>
<div style="margin-bottom:100px">
  <div class="d-flex justify-space-around">
    <div @click="changeCategory(0)" class="d-flex flex-column justify-center align-center">
      <v-icon size="48px" color="red">mdi-run</v-icon>
      <p>주변 문화시설</p>
    </div>
    <div @click="changeCategory(1)" class="d-flex flex-column justify-center align-center">
      <v-icon size="48px" color="red">mdi-food-outline</v-icon>
      <p>주변 음식점</p>
    </div>
    <div @click="changeCategory(2)" class="d-flex flex-column justify-center align-center">
      <v-icon size="48px" color="red">mdi-coffee-outline</v-icon>
      <p>주변 카페</p>
    </div>
  </div>
  <div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
  </div>
  <br>
</div>
</template>

<script>
export default {
  name: "KakaoMap",
  props: {
    artCenterAddress: String,
    name: String
  },
  data() {
    return {
      markers: [], // 마커를 담을 배열입니다.
      infowindow: null,
      ps: '',
      placeOverlay: '',
      contentNode: '', // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다.
      currCategory: '', // 현재 선택된 카테고리를 가지고 있을 변수입니다.
      map: '',
      category: '',
      children: '',
      marker: '',
      content: '',
      cg: '',
    };
  },
  mounted() {
    this.$nextTick(function() {
      if (window.kakao && window.kakao.maps) {
        const script = document.createElement("script");
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=c5c67b82497fef1c4c464ebe107de3ea&libraries=services";
        document.head.appendChild(script);
      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=c5c67b82497fef1c4c464ebe107de3ea&libraries=services";
        document.head.appendChild(script);
      }
    })
  },
  methods: {
    initMap() {
      const address = this.artCenterAddress
      const name = this.name
      const mapContainer = document.getElementById("map"),
        mapOption = {
              center: new kakao.maps.LatLng(127.298, 36.354), // 지도의 중심좌표
              level: 5 // 지도의 확대 레벨
          };
      var map = new kakao.maps.Map(mapContainer, mapOption); 
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(address, function(result, status) {
      // 정상적으로 검색이 완료됐으면 
      if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords,
        });
        marker.setMap(map);
        var infowindow = new kakao.maps.InfoWindow({
          content: '<div style="width:150px;text-align:center;padding:6px 0;">' + name + '</div>',
        });
        kakao.maps.event.addListener(marker, 'mouseover', function() {
          // 마커 위에 인포윈도우를 표시합니다
          infowindow.open(map, marker);  
        });
        kakao.maps.event.addListener(marker, 'mouseout', function() {
          // 마커 위에 인포윈도우를 표시합니다
          setTimeout(() => {
            infowindow.close()
          }, 1500);  
        });

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
      }
    })
    this.map = map
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
      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker(this.markers)
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
          const marker = this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x));
          // 마커와 검색결과 항목을 클릭 했을 때
          // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
          const place = places[i]
          const infowindow = new kakao.maps.InfoWindow({
            });
          kakao.maps.event.addListener(marker, 'mouseover', () => {
            const content ='<button style="color:red; width:200px; height: 30px;" onclick="location.href=' + "'" + place.place_url + "'" +  '">' + place.place_name + '</button><br>';
            infowindow.setContent(content)
            infowindow.setPosition(new kakao.maps.LatLng(place.y, place.x))
            infowindow.open(this.map, marker);
          })
          kakao.maps.event.addListener(marker, 'mouseout', () => {
            setTimeout(() => {
              infowindow.close()
            }, 1500);
          })
        }
    },
 
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker: function (position) {
      var marker = new kakao.maps.Marker({
        position: position
      });

        marker.setMap(this.map); // 지도 위에 마커를 표출합니다
        this.markers.push(marker);  // 배열에 생성된 마커를 추가합니다

        return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker: function (markers) {

        markers.forEach((item) => {
          item.setMap(null);
        })
        this.markers = [];
    },

    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayPlaceInfo: function (place) {  
        var placeOverlay = new kakao.maps.CustomOverlay({zIndex:1})
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
<style lang="scss" scoped >
#map {
  width: 500px;
  height: 500px;
}
.map_wrap, .map_wrap * {margin:0; padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap {position:relative;width:100%;height:350px;}
#category {position:absolute;top:10px;left:10px;border-radius: 5px; border:1px solid #909090;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);background: #fff;overflow: hidden;z-index: 2;}

</style>
