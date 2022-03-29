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
            src="@/assets/profile_default.png"
          >
          </v-img>
        </v-card>
        <div class="profile-detail d-inline-block ms-5">
          <h1> 김공연 </h1>
          <div class="profile-tag-wrapper">
            <h2> 선호 태그 </h2>
            <v-chip-group
              column
            >
              <v-chip
                v-for="(tag, i) in userTags"
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
// import ProfileMyContents from './ProfileMyContents'

export default {
  name: 'Profile',
  components: {
    // ProfileMyContents
  },
  data(){
    return{
      userName: '김공연',
      userTags: ['좋아요', '명배우', '웃겨요'],
      clickItem1: false,
      clickItem2: false,
      clickItem3: false
    }
  },

}
</script>

<style>
#profile{
  width: 100%;
  height: 100%;
}

.profile-wrapper{
  width: 100%;
  height: 100%;
}

</style>