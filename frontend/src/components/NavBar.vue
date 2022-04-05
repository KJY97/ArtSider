<template>
  <v-app-bar
  v-if="!!$store.state.token"
  app
  scroll-target="#main"
  flat
  collapse-on-scroll
  style="background-color: rgba(233,30,99,0.9)"
  >
  <v-toolbar-title class="text-uppercase">
    <span class="font-weight-light"> ArtSider </span>
  </v-toolbar-title>
  
  <div class="menu-wrapper" style="margin-left: 50px;">
    <v-menu
      bottom
    >
      <template v-slot:activator="{on, attrs}">
        <v-btn
          width="218px"
          text  
          v-bind="attrs"
          v-on="on"
        >
        <span style="color: rgb(246,247,235);"> 공연 카테고리 </span>
        <v-icon
        right
        style="color: rgb(246,247,235)"
        >
          mdi-arrow-down-drop-circle-outline
        </v-icon>
        </v-btn>
      </template>

      <v-list>
        <v-list-item>
          <v-btn @click="$router.push({name: 'Musical'})"
            text style="width:100%;">
            <v-list-item-title> 뮤지컬 </v-list-item-title>
          </v-btn>
        </v-list-item>
        <v-list-item>
          <v-btn  @click="$router.push({name: 'Theatre'})" 
            text style="width:100%;">
            <v-list-item-title> 연극 </v-list-item-title>
          </v-btn>
        </v-list-item>
        <v-list-item>
          <v-btn @click="$router.push({name: 'Concert'})" 
            text style="width:100%;">
            <v-list-item-title> 콘서트 </v-list-item-title>
          </v-btn>
        </v-list-item>
        <v-list-item>
          <v-btn @click="$router.push({name: 'Classic'})" 
            text style="width:100%;">
            <v-list-item-title> 클래식/오페라/무용 </v-list-item-title>
          </v-btn>
        </v-list-item>
        <v-list-item>
          <v-btn @click="$router.push({name: 'Family'})" 
            text style="width:100%;">
            <v-list-item-title> 아동/키즈 </v-list-item-title>
          </v-btn>
        </v-list-item>
      </v-list>

    </v-menu>
  </div>

  <v-spacer></v-spacer>

  <div
    style="position: relative; width: 40%;"
  >
    <v-text-field
      :value="searchKeyword"
      @keyup.native="changeKeyword"
      type="text"
      solo
      flat
      dense
      rounded
      background-color="#FFFFFF"
      color="black"
    >
      <v-btn
        slot="append"
        icon
      >
        <v-icon
          color="gray"
          @click="() => { searchKeyword = '' }"
        > 
          mdi-close-circle-outline
        </v-icon>
      </v-btn>
      <v-btn
        slot="append"
        icon
      >
        <v-icon
          color="gray"
        >
          mdi-magnify
        </v-icon>
      </v-btn>
    </v-text-field>
    <v-container
      v-show="!!searchKeyword"
      style="position: absolute; background-color: white; height: 60vh;"
      class="search-box"
    >
      <ul
        style="list-style: none;"
      >
        <li
          v-for="result in searchResult"
          :key="result.id"
          style="height: 25%;  cursor:pointer;"
          @click="$router.push({name: 'ShowDetail', params: {showId: result.id}})"
        >
          <v-img
            :src="result.posterPath"
            max-width="25%"
            min-width="25%"
            style="display:inline-block; vertical-align:top;"
          >
          </v-img>
          <div 
            class="show-info d-inline-block ms-3"
          >
            <v-chip :ripple="false"> {{ result.category }} </v-chip>
            <h3> {{ result.showName }} </h3>
            <p>
              공연기간:   {{  result.startDate ? result.startDate.split(' ')[0] : '' }}  - {{  result.endDate ? result.endDate.split(' ')[0] : '' }} 
            </p>
            <p>
              공연장소: {{ result.artCenterName }}
            </p>
            <a :href="ticketBaseUrl + result.showId"> 예매하기 </a>
          </div>
        </li>
      </ul>
    </v-container>
  </div>
    <v-spacer></v-spacer>
<!-- 
    <v-btn text color="black">
      <span  style="color: rgb(246,247,235);"> 공연 에티켓 </span>
    </v-btn> -->

    <v-btn text color="gray" @click="deleteToken">
      <span  style="color: rgb(246,247,235);">로그아웃</span>
    </v-btn>

      <v-btn text color="gray" @click="$router.push({name: 'Profile'})">
      <span  style="color: rgb(246,247,235);">마이페이지</span>
    </v-btn>

  </v-app-bar>
</template>

<script>

export default {
  name: 'NavBar',
  data(){
    return{
      categories: [
        { title: "뮤지컬"},
        { title: "연극"},
        { title: "콘서트"},
        { title: "클래식/오페라/무용"},
        { title: "아동/키즈"}
      ],

      searchKeyword : '',
      searchResult : [],
      cache: [],
      ticketBaseUrl: 'https://tickets.interpark.com/goods/',
    }
  },

  methods:{
    deleteToken(){
      localStorage.removeItem('accessToken')
      this.$store.dispatch('deleteToken')
      this.$router.push({name: 'Intro'})
    },

    changeKeyword($event){
      this.searchKeyword = $event.target.value
    }
  },

  watch: {
    searchKeyword: function(){
      if(this.searchKeyword !== ''){
        if((this.searchKeyword in this.cache) === false ){
          this.$store.dispatch('getSearchResult', this.searchKeyword)
          .then((res) => {
            this.searchResult = res.data.items
            if(res.data.items !== []){
              const obj = {
                searchKeyword : res.data.items
              }
              this.cache.push(obj)
            }
          })
        } else{
          this.searchResult = this.cache[this.searchKeyword]
        }
      } else{
        this.cache = []
      }
    }
  }
}
</script>

<style>

.v-text-field.v-text-field--enclosed .v-text-field__details{
  display: none;
}

.search-box{
  overflow-y: auto;
}

.search-box ul li{
  /* overflow-x: hidden; */
  /* text-overflow: ellipsis; */
  white-space: nowrap;
  margin-bottom: 25px;
}

.search-box ul li p{
  margin-bottom: 0px;
}

</style>