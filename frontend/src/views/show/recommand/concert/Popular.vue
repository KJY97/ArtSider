<template>
<div class="swiper mt-6">
  <h1 class="ml-4 mb-4">인기 순위</h1> 
  <div class="swiper-wrapper">
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://w.namu.la/s/1837eead0acfe94c64358c7662f75cc5af8a71765f05e2ff9f2ee8558ef463f51cac6f7b60f9d5b11461a754629d5c70c944a241ce5bf5d0b3bbb884fb06674db0e1a93c30a5f5b808cd2ba4f39943fb"    
        >
        <!-- src="posterpath -->
        <h1 class="rank">1</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
        <v-img
          @click="$router.push({path: `show-detail/${showId}`})"
          :aspect-ratio="3/4"
          src="https://cdn.energykorea.co.kr/news/photo/202111/44265_36074_2612.jpg"
          >
          <h1 class="rank">2</h1>
        </v-img>
    </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://image.yes24.com/themusical/upFiles/Themusical/Play/post_poster(s)22.jpg"
        >
        <h1 class="rank">3</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://hobbyen.co.kr/news/data/20210727/p1065599709407562_865_thum.jpg"
        >
        <h1 class="rank">4</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://i.pinimg.com/474x/fb/21/bf/fb21bff4095f918fdf2b771e36d8fed2.jpg"
        >
        <h1 class="rank">5</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://newsimg.sedaily.com/2020/01/21/1YXQDTM7WM_1.jpg"
        >
        <h1 class="rank">6</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://www.m-i.kr/news/photo/202101/784601_561474_542.jpg"
        >
        <h1 class="rank">7</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://img1.yna.co.kr/etc/inner/KR/2020/01/02/AKR20200102075700005_02_i_P2.jpg"
        >
        <h1 class="rank">8</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://image.ajunews.com/content/image/2018/08/03/20180803104751108116.jpg"
        >
        <h1 class="rank">9</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://img.hankyung.com/photo/201906/2019061008373553224-540x763.jpg"
        >
        <h1 class="rank">10</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="http://ojsfile.ohmynews.com/PHT_IMG_FILE/2021/0309/IE002770932_PHT.jpg"
        >
        <h1 class="rank">11</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://img.hankyung.com/photo/202001/BF.21391966.1-1200x.jpg"
        >
        <h1 class="rank">12</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://cdn.gukjenews.com/news/photo/202106/2235289_2227032_824.jpg"
        >
        <h1 class="rank">13</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://pbs.twimg.com/media/FLxD2R0aIAQEam4?format=jpg&name=large"
        >
        <h1 class="rank">14</h1>
        </v-img>
        </div>
    <div class="swiper-slide">
      <v-img
        @click="$router.push({path: `show-detail/${showId}`})"
        :aspect-ratio="3/4"
        src="https://cdn.energykorea.co.kr/news/photo/202111/44265_36074_2612.jpg"
        >
        <h1 class="rank">15</h1>
        </v-img>
        </div>
    </div>
<div class="swiper-button-next"></div>
<div class="swiper-button-prev"></div>
</div>
</template>

<script>
import Swiper from 'swiper/js/swiper.esm.bundle'
import 'swiper/css/swiper.css'
// import axios from'axios'


export default {
  name: 'Popular',
  data(){
    return{
      swiper: null,
    }
  },

  components: {
  },
  mounted(){
    this.swiper = new Swiper('.swiper', {
      observer : true,
      observerParents : true,
      slidesPerView: 7,
      slidePerGroup: 5,
      spaceBetween : 30,
      direction: 'horizontal',
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      },
    })
  }
}
</script>

<style>

.swiper {
  position: relative;
  width: 100%;
  height: 40%;
  
}

.swiper-slide {
  text-align: center;
  font-size: 18px;
  background: gray;

  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  -webkit-align-items: center;
  align-items: center;
  cursor: pointer;
}

.rank {
  color: #000;
  -webkit-text-stroke: 1px #fff;
  position: absolute;
  font-size: 4rem;
}

</style>