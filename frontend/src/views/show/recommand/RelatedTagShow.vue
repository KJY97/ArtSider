<template>
<div>
  <div v-for="(value, key, idx) in RelatedTagShowList" :key="idx">
      <div v-if="value.length > 0">
        <br>
        <h1 class="recommend-title mom ml-4 mb-4 d-flex align-center"> <span class="tag" style="margin-right:5px">{{ key }} </span> 태그와 관련된 공연</h1>
        <swiper class="swiper" 
          :options="swiperOption">
          <swiper-slide
          v-for="tag in value" 
          :key="tag.id"
          > 
          <v-img
                @click="moveShowDetail(tag.id)"
                :aspect-ratio="3/4"
                :src="tag.poster_path"
                style="width:50%;height:50%;"
                >
          </v-img>
          </swiper-slide>
          <div class="swiper-button-prev" slot="button-prev"></div> 
          <div class="swiper-button-next" slot="button-next"></div>
        </swiper> 
        <br>
      </div>
  </div>
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
    }
  },
   props:{
    swiperOption: Object,
    num: Number,
  },

  components:{
    Swiper,
    SwiperSlide,
  },

  methods: {
    getRelatedTagShow: function(num) {
      this.$store.dispatch('getRelatedTagShow', {num: num})
      .then((res) => {
        this.RelatedTagShowList = res.data.item
      })
    },
    getUserInfo: function() {
      this.$store.dispatch('getUserInfo')
      .then((res) => {
        this.userNickname = res.data.nickname
        this.userId = res.data.userId
        this.userTag = res.data.preferTag
      })
      .then(() => {
        if (this.userTag) {
          this.getRelatedTagShow(this.num) 
        }
      })
    },
    moveShowDetail: function (id) {
      this.$router.push({name: `ShowDetail`, params: { showId: id}})
    }
  },
  created: function() {
    this.getUserInfo()
    
  },
}
</script>

<style scoped>
.rank {
  color: #000;
  -webkit-text-stroke: 1px #fff;
  position: absolute;
  font-size: 4rem;
}
.tag {
  color: transparent; 
  background: linear-gradient(45deg,Violet, Orange);
  background-clip: text; 
}
</style>