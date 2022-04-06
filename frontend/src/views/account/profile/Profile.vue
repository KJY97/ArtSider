<template>
  <div id="profile">
    <Loading v-if="loading" />
    <v-container
      v-else
      class="profile-wrapper"
    >
      <div class="profile-info d-flex flex-wrap">
        <v-card
          width="20%"
          height="40%"
          flat
          class="d-inline-block"
        >
          <v-img
            max-width="100%"
            max-height="100%"
            :aspect-ratio="1"
            :src="userImgSrc"
            @click="imageTrigger"
          >
          </v-img>
           <input @change="uploadImage" style="display: none;" accept="image/*" ref="image" type="file">
        </v-card>
        <div class="profile-detail d-inline-block ms-5">
          <h1 class="d-inline-block"> {{ userInfo.nickname }} </h1>
          <v-text-field
            class="d-inlie-block"
            v-show="updateTrigger === true"
            v-model="updatedName"
            solo
            dense
            :label="userInfo.nickname"
            clearable
          >
          </v-text-field>
            <v-btn
              v-show="!updateTrigger"
              icon
              @click="() => { updateTrigger = true}"
            >
              <v-icon>
                mdi-account-cog-outline
              </v-icon>
            </v-btn>
            <v-btn
              v-show="updateTrigger"
              text
              @click="() => { putUserName(), updateTrigger = false}"
            >
              수정
            </v-btn>
             <v-btn
             v-show="updateTrigger"
              text
              @click="() => { updateTrigger = false }"
            >
              취소
            </v-btn>

          <div class="profile-tag-wrapper mt-4">
            <h2> 선호 태그 </h2>
            <v-chip-group
              column
            >
              <v-chip
                v-for="(tag, i) in userInfo.preferTag"
                :key="i"
                outlined
                draggable
                large
                color="pink"
                text-color="red"
                label
              >
                <v-icon left>
                  mdi-pound
                </v-icon>
                <span class="font-weight-bold"> {{ tag.tag }} </span> 
              </v-chip>
            </v-chip-group>
          </div>
        </div>
      </div>

      <div class="mt-5">
        <!-- <v-btn text x-large @click="$router.push({name: 'ProfileDefault'})" > 공연 보관함 </v-btn>
        <v-btn text x-large @click="$router.push({name: 'ProfileMyReviews'})" > 리뷰 보관함 </v-btn>
     -->
        <router-link 
          style="color:black; font-size: 1.3rem; font-weight: bold;" 
          class="profile-page mr-3" 
          :to="{name: 'ProfileDefault'}"> 공연 보관함 </router-link>
        <router-link 
          style="color:black; font-size: 1.3rem; font-weight: bold;" 
          class="profile-page mr-3" 
          :to="{name: 'ProfileMyReviews'}"> 리뷰 보관함 </router-link>
        <!-- <router-link :to="{name: 'ProfileCalendar'}"> 캘린더 </router-link>   -->
      </div>

      <hr/>
      <router-view></router-view>
    </v-container>
  </div>
</template>

<script>
import defaultImage from '@/assets/profile_default.png'
import Loading from '@/views/show/Loading.vue'

export default {
  name: 'Profile',
  components: {
    Loading
  },
  data(){
    return{
      userInfo: {},
      userImgName: null,
      userImgSrc : null,

      updatedName : '',

      updateTrigger : false,
      loading : true
    }
  },

  methods:{
    imageTrigger(){
      this.$refs.image.click()
    },

    putUserName(){
      if(this.updateTrigger === true){
        this.$store.dispatch('putUserName', this.updatedName.trim())
        .then(() => {
          this.userInfo.nickname = this.updatedName
        })
      } 
    },

    uploadImage(){
      const image = this.$refs.image.files
      if(image && image[0]){
        if(image[0].size/(1024*1024) > 3){
          this.$refs.image.value = ''
          alert('이미지 파일은 최대 3MB까지 가능합니다.')
        } else{
          this.$store.dispatch('setUserImage', image[0])
          .then((res) => {
            // 이미지 이름 갱신
            this.userImgName = res.data.profileImg
            this.userInfo.profileImg = res.data.profileImg
          })
        }
      }
    },


  },

  created(){
    if(this.$store.getters.loginStatus){
      this.$store.dispatch('getUserInfo')
      .then((res) => {
        this.userInfo = res.data
        this.userImgName = res.data.profileImg !== '' ? res.data.profileImg : '' 
        this.loading = false
      })
      .catch(() => {
        console.log('error: get profile info')
      })
    } else{
      console.log('wrong contact')
    }
  },

  watch: {
    userImgName: function(){
      if(this.userImgName){
        this.$store.dispatch('getUserImageSrc', this.userImgName)
        .then((res) => {
          // console.log('이미지 조회: ', res.config.baseURL + res.config.url)
          this.userImgSrc = res.config.baseURL + res.config.url
        })
      } else{
        this.userImgSrc = defaultImage
      } 
    },

  }

}
</script>

<style>
#profile{
  width: 100%;
  /* height: 100%; */

}

.default-msg{
  opacity: 0.3;
}

.profile-wrapper{
  width: 100%;
  /* height: 100%; */
}

.profile-page{
  text-decoration: none;
}

.profile-page:hover{
  background-color: rgba(216, 211, 211, 0.5);
}
</style>