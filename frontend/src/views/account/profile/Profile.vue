<template>
  <div id="profile">
    <v-container
      class="profile-wrapper"
    >
      <div class="profile-info d-flex flex-wrap">
        <v-card
          width="300"
          height="300"
          flat
          class="d-inline-block"
        >
          <v-img
            max-width="100%"
            max-height="100%"
            :aspect-ratio="1"
            :src="userImg"
            @click="imageTrigger"
          >
          </v-img>
           <input @change="uploadImage" style="display: none;" accept="image/*" ref="image" type="file">
        </v-card>
        <div class="profile-detail d-inline-block ms-5">
          <h1> 김공연 </h1>
          <v-btn>
            수정하기
          </v-btn>
          <div class="profile-tag-wrapper">
            <h2> 선호 태그 </h2>
            <v-chip-group
              column
            >
              <v-chip
                v-for="(tag, i) in userInfo.preferTag"
                :key="i"
                large
                label
              >
                <span class="font-weight-bold"> {{ tag }} </span> 
              </v-chip>
            </v-chip-group>
          </div>
        </div>
      </div>

      <div>
        <router-link :to="{name: 'ProfileDefault'}"> 공연 보관함 </router-link>
        <router-link :to="{name: 'ProfileMyReviews'}"> 리뷰 보관함 </router-link> 
        <router-link :to="{name: 'ProfileCalendar'}"> 캘린더 </router-link>  
      </div>

      <hr/>
      <router-view></router-view>
    </v-container>
  </div>
</template>

<script>
import defaultImage from '@/assets/profile_default.png'


export default {
  name: 'Profile',
  components: {
  },
  data(){
    return{
      userName: '김공연',
      userTags: ['좋아요', '명배우', '웃겨요'],
    }
  },

  computed:{
    isLogin(){
      return this.$store.getters.loginStatus
    },

    userInfo(){
      return this.$store.getters.userInfo
    },

    userImgSrc(){
      return this.$store.getters.userImgSrc ? this.$store.getters.userImgSrc : defaultImage
    },
  },

  methods:{
    imageTrigger(){
      this.$refs.image.click()
    },

    uploadImage(){
      const image = this.$refs.image.files
      if(image && image[0]){
        if(image[0].size/(1024*1024) > 3){
          this.$refs.image.value = ''
          alert('이미지 파일은 최대 3MB까지 가능합니다.')
        } else{
          this.$store.dispatch('setUserImage', image[0])
        }
      }
    }
  },

  created(){
    if(this.$store.getters.loginStatus){
      this.$store.dispatch('getUserInfo')
    } else{
      console.log('wrong contact')
    }
  }

}
</script>

<style>
#profile{
  width: 100%;
  height: 100%;
  overflow-y: auto;

}

.profile-wrapper{
  width: 100%;
  height: 100%;
}

</style>