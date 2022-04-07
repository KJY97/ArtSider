<template>
<div v-if="isShow">
  <br>
  <h1 class="recommend-title ml-4 mb-4 d-flex align-center"><v-icon size="48px" color="blue">mdi-thumb-up-outline</v-icon>{{ userNickname }}님의 선호 태그와 일치하는 공연</h1> 
  <swiper class="swiper" 
    :options="swiperOption">
    <swiper-slide
    v-for="tag in RelatedTagShowList" 
    :key="tag.id"
    > 
    <v-img
          @click="moveShowDetail(tag.id)"
          :aspect-ratio="3/4"
          :src="tag.posterPath"
          style="width:50%;height:50%;"
          >
    </v-img>
    </swiper-slide>
    <div class="swiper-button-prev" slot="button-prev"></div> 
    <div class="swiper-button-next" slot="button-next"></div>
  </swiper> 
  <br>
</div>
</template>


<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper'

export default {
  name: 'RelatedTagShow',
  data(){
    return{
      swiper: null,
      RelatedTagShowList: [],
      userId: '',
      userNickname: '',
      userTag: '',
      isShow: false,
    }
  },
   props:{
    swiperOption: Object,
  },

  components:{
    Swiper,
    SwiperSlide,
  },

  methods: {
    getRelatedTagShow: function() {
      this.$store.dispatch('getRelatedTagShow', {userTag: this.userTag})
      .then(res => {
        this.SimilarityShowList = res.data.items
      })
    },
    getUserInfo: function() {
      this.$store.dispatch('getUserInfo')
      .then((res) => {
        this.userNickname = res.data.nickname
        this.userId = res.data.userId
        this.userTag = res.data.userTag
      })
      .then(() => {
        if (this.userTag) {
          this.isShow = true
          this.getRelatedTagShow() 
        }
      })
    },
    moveShowDetail: function (id) {
      this.$router.push({name: `ShowDetail`, params: { showId: id}})
    }
  },
  created: function() {
    this.getUserInfo()
    
  }
}
</script>

<style scoped>
.rank {
  color: #000;
  -webkit-text-stroke: 1px #fff;
  position: absolute;
  font-size: 4rem;
}
</style>