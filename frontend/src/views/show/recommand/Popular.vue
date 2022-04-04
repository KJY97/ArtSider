<template>
<div class="swiper mt-6">
  <h1 class="ml-4 mb-4">인기 순위</h1> 
  <div class="swiper-wrapper">
    <div class="swiper-slide" v-for="popular in popularList" :key="popular.id">
      <v-img
        @click="addInquire(popular.id)"
        :aspect-ratio="3/4"
        :src="popular.posterPath"
        style="width:50%;height:50%;"
        >
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

export default {
  name: 'Popular',
  data(){
    return{
      swiper: null,
      popularList: [],
    }
  },

  methods: {
    getPopularShow: function() {
      this.$store.dispatch('getPopularShow')
      .then(res => {
        this.popularList = res.data.items
        console.log(this.popularList)
      })
    },
    addInquire: function (id) {
      this.$store.dispatch('addInquire',{id:id})
      .then(() => {
        this.$router.push({name: `ShowDetail`, params: { showId: id}})
      })
    }
  },
  mounted(){
    this.swiper = new Swiper('.swiper', {
      observer : true,
      observerParents : true,
      slidesPerView: 5,
      slidePerGroup: 5,
      spaceBetween : 30,
      touchRatio: 0,
      direction: 'horizontal',
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      },
    });

  },
  created: function() {
    // 새로 시작할 때 발생하는 이벤트
    // 페이지 처음 들어올때 수행하는 이벤트
    this.getPopularShow() 
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