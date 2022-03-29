<template>
 <div style="margin-bottom:100px">
  <div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <ul id="category">
      <li id="CT1" data-order="0"> 
        <span class="category_bg"></span>
        문화시설
      </li>       
      <li id="FD6" data-order="1"> 
        <span class="category_bg"></span>
        음식점
      </li>  
      <li id="CE7" data-order="2"> 
        <span class="category_bg pharmacy"></span>
        카페
      </li>  
    </ul>
  </div>
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
      contentNode: document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다 
      currCategory: '', // 현재 선택된 카테고리를 가지고 있을 변수입니다
      map: '',
      category: '',
      children: '',
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
      console.log('처음')
      // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      this.placeOverlay = new kakao.maps.CustomOverlay({zIndex:1}),
      this.contentNode = document.createElement('div') // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
      this.markers = []
      this.currCategory = ''

      const mapContainer = document.getElementById("map"),
        mapOption = {
              center: new kakao.maps.LatLng(this.latitude, this.longitude), // 지도의 중심좌표
              level: 5 // 지도의 확대 레벨
          };  
      
      // 지도를 생성합니다    
      this.map = new kakao.maps.Map(mapContainer, mapOption); 

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places(this.map); 

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, 'idle', this.searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다 
      this.contentNode.className = 'placeinfo_wrap';

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다 
      this.addEventHandle(this.contentNode, 'mousedown', kakao.maps.event.preventMap);
      this.addEventHandle(this.contentNode, 'touchstart', kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);  

      // 각 카테고리에 클릭 이벤트를 등록합니다
      this.addCategoryClickEvent();

      this.markers.forEach((marker) => marker.setMap(null));

      const positions = this.markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );
      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds)
      }
    },
    
    
        

    

    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle: function (target, type, callback) {
      console.log('엘리먼트에 이벤트 핸들러')
        if (target.addEventListener) {
            target.addEventListener(type, callback);
        } else {
            target.attachEvent('on' + type, callback);
        }
    },

    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces: function () {
        console.log('카테고리 검색')
        if (!this.currCategory) {
            return;
        }
        
        // 커스텀 오버레이를 숨깁니다 
        this.placeOverlay.setMap(null);

        // 지도에 표시되고 있는 마커를 제거합니다
        this.removeMarker();
        console.log('전달')
        this.ps.categorySearch(this.currCategory, this.placesSearchCB, {useMapBounds:true}); 
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB: function (data, status, ) {
        console.log('장소 검색이 완료됬을때')
        if (status === kakao.maps.services.Status.OK) {

            // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
            this.displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

        } else if (status === kakao.maps.services.Status.ERROR) {
            // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
            
        }
    },

    // 지도에 마커를 표출하는 함수입니다
    displayPlaces: function (places) {
      // console.log('지도에 마커')!

        // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
        // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
        var order = document.getElementById(this.currCategory).getAttribute('data-order');

        

        for ( var i=0; i<places.length; i++ ) {

                // 마커를 생성하고 지도에 표시합니다
                var marker = this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

                // 마커와 검색결과 항목을 클릭 했을 때
                // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
                (function(marker, place) {
                    kakao.maps.event.addListener(marker, 'click', function() {
                        this.displayPlaceInfo(place);
                    });
                })(marker, places[i]);
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

        return this.marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker: function () {
      console.log('마커 제거')
        for ( var i = 0; i < this.markers.length; i++ ) {
            this.markers[i].setMap(null);
        }   
        this.markers = [];
    },

    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayPlaceInfo: function (place) {
      // console.log('클릭한 마커에 대한 장소 상세정보')!
        var content = '<div class="placeinfo">' +
                        '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';   

        if (place.road_address_name) {
            content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                        '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
        }  else {
            content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
        }                
      
        content += '    <span class="tel">' + place.phone + '</span>' + 
                    '</div>' + 
                    '<div class="after"></div>';

        this.contentNode.innerHTML = content;
        this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        this.placeOverlay.setMap(this.map);  
    },

    // 각 카테고리에 클릭 이벤트를 등록합니다
    addCategoryClickEvent: function () {
      console.log('카테고리에 클릭이벤트 등록')
        this.category = document.getElementById('category'),
        this.children = this.category.children;

        for (var i=0; i<this.children.length; i++) {
            this.children[i].onclick = this.onClickCategory();
        }
    },

    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory: function () {
      console.log('카테고리를 클릭했을때 호출')
        var id = this.id,
            className = this.className;

        this.placeOverlay.setMap(null);

        if (className === 'on') {
            this.currCategory = '';
            this.changeCategoryClass();
            this.removeMarker();
        } else {
            this.currCategory = id;
            this.changeCategoryClass(this);
            this.searchPlaces();
        }
    },

    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass: function (el) {
      console.log('클릭된 카테고리에만 스타일 적용')
        this.category = document.getElementById('category')
        this.children = this.category.children

        for ( var i=0; i<this.children.length; i++ ) {
            this.children[i].className = '';
        }

        if (el) {
            el.className = 'on';
        } 
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
#category li {float:left;list-style: none;width:50px;border-right:1px solid #acacac;padding:6px 0;text-align: center; cursor: pointer;}
#category li.on {background: #eee;}
#category li:hover {background: #ffe6e6;border-left:1px solid #acacac;margin-left: -1px;}
#category li:last-child{margin-right:0;border-right:0;}

.placeinfo_wrap {position:absolute;bottom:28px;left:-150px;width:300px;}
.placeinfo {position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background: #fff;}
.placeinfo:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.placeinfo_wrap .after {content:'';position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.placeinfo a, .placeinfo a:hover, .placeinfo a:active{color:#fff;text-decoration: none;}
.placeinfo a, .placeinfo span {display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
.placeinfo span {margin:5px 5px 0 5px;cursor: default;font-size:13px;}
.placeinfo .title {font-weight: bold; font-size:14px;border-radius: 6px 6px 0 0;margin: -1px -1px 0 -1px;padding:10px; color: #fff;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.placeinfo .tel {color:#0f7833;}
.placeinfo .jibun {color:#999;font-size:11px;margin-top:0;}
</style>
