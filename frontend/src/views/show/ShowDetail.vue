<template>
  <div>
    <div class="d-flex justify-center" style="padding-top: 100px">
      <div>
        <div class="d-flex flex-column" style="max-width:900px; min-width:900px; margin-left: 150px">
          <h1 style="padding-left:30px">{{ showDetail.showName }}</h1>
          <div class="d-flex justify-center">
            <div class="d-flex flex-column" style="margin-right:100px">
              <v-img :src="showDetail.posterPath" style="max-width:300px; width:100%; min-width:300px; height:100%; min-height:400px; max-height:400px"></v-img>
              <div class="d-flex justify-center" style="max-width:300px;">
                <v-btn
                v-if="isPreference"
                icon
                color="deep-orange"
                @click="changePreference()"
              >
                <v-icon>mdi-star</v-icon>
                선호 목록 제거
              </v-btn>
              <v-btn
                v-if="!isPreference"
                icon
                color="grey"
                @click="changePreference()"
              >
                <v-icon>mdi-star</v-icon>
                선호 목록 추가
              </v-btn>
              </div>
              <div class="d-flex flex-wrap justify-center" style="max-width:300px;">
                <span v-for="(Tag,idx) in hashTag" :key="idx" class="py-2">
                  <v-chip label color="pink" draggable text-color="white" class="mr-1"><v-icon left>mdi-label</v-icon>{{ Tag }}</v-chip>
                </span>
              </div>
            </div>

            <ul class="d-flex flex-column mt-6 ml-10 pb-2" style="min-width:400px; width:100%; max-width:400px">
              <li class="li-info">
                <strong class="main-label">장소</strong>
                <div class="sub-label">
                  {{ showDetail.artCenterName }}
                </div>
              </li>
              <li class="li-info">
                <strong class="main-label">공연 카테고리</strong>
                <div class="sub-label">
                  {{ showDetail.category }}
                </div>
              </li>
              <li class="li-info">
                <strong class="main-label">공연기간</strong>
                <div class="sub-label">
                  {{ showDetail.startDate }} ~ {{ showDetail.endDate }}
                </div>
              </li>
              <li class="li-info" v-if="!showDetail.endDate">
                <strong class="main-label">오픈런 여부</strong>
                <div class="sub-label">
                  Y
                </div>
              </li>
              <li class="li-info">
                <strong class="main-label">공연시간</strong>
                <div class="sub-label">
                  {{ showDetail.runtime }}
                </div>
              </li>
              <li class="li-info">
                <strong class="main-label">관람연령</strong>
                <div class="sub-label">
                  {{ showDetail.age }}
                </div>
              </li>
              <li class="li-info d-flex" v-if="showDetail.price">
                <strong class="main-label">가격</strong>
                <div class="sub-label price">
                </div>
              </li>
              <br>
              
              <div class="mt-auto">
                <v-btn
                  rounded 
                  large
                  color="red"
                  @click="goTicketSite(2)"
                  style="color:white; width:340px;"
                  v-if="!isEnd && isPreference"
                >
                  예매하러가기
                </v-btn>
                <v-dialog
                  v-if="!isPreference"
                  v-model="dialog"
                  max-width="450"
                  min-width="450"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      rounded 
                      large
                      color="red"
                      v-bind="attrs"
                      v-on="on"
                      style="color:white; width:340px;"
                      v-if="!isEnd"
                    >
                      예매하러가기
                    </v-btn>
                  </template>
                  <v-card style="background-color:rgba(0,0,0,0.8);"> 
                    <v-card-title class="text-h5" style="color:white;">
                      선호 목록에 추가하시겠습니까?
                    </v-card-title>
                    <v-card-text style="color:white;">
                      선호 목록에 추가하면 맞춤형 공연을 추천 받을 수 있어요!
                    </v-card-text>
                    <v-spacer></v-spacer>
                    <v-card-actions class="d-flex justify-space-around">
                      <v-btn
                        color="green darken-1"
                        text
                        @click="goTicketSite(1)"
                      >
                        추가할래요
                      </v-btn>
                      <v-btn
                        color="green darken-1"
                        text
                        @click="goTicketSite(2)"
                      >
                        안할래요
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>

                <v-btn rounded large disabled v-if="isEnd" style="width:340px;">종료 된 공연입니다</v-btn>
                <!-- disable 줄것 -->
              </div>
            </ul>
          </div>
        </div>
        <div style="max-width:1000px; min-width:1000px;">
          <div style="margin-left:170px; margin-top:30px">
            <li class="li-btn">
              <v-btn text @click="changeShow(1)" class="btn-detail">공연정보</v-btn>
            </li>
            <li class="li-btn">
              <v-btn text @click="changeShow(2)" class="btn-detail">관람후기</v-btn>
            </li>
            <li class="li-btn">
              <v-btn text @click="changeShow(3)" class="btn-detail">기대평</v-btn>
            </li>
            <li class="li-btn">
              <v-btn text @click="changeShow(4)" class="btn-detail">공연장 위치</v-btn>
            </li>
            <hr>
            <ShowInfo
              :id="id"
              v-if="number == 1"
              :showDetailImg = showDetail.showDetailImg
            ></ShowInfo>

            <ShowReviews
              :id="id"
              v-if="number == 2"
              :userId="userId"
              :startDate= showDetail.startDate
            ></ShowReviews>

            <ShowExpectations
              :id="id"
              v-if="number == 3"
              :userId="userId"
            ></ShowExpectations>

            <ShowArtCenter
              :ArtCenterName="showDetail.artCenterName"
              v-if="number == 4"
            ></ShowArtCenter>
          </div>
        </div>
      </div>
      <div style="margin-left:100px; max-height:400px; height:100%">
        <div class="container" style="border: 1px solid rgba(0, 0, 0, .3); position: sticky; top: 100px;">
          <p class="d-flex justify-center">연관공연</p>
          <swiper class="swiper" 
            :options="swiperOption"
            style="max-height: 300px;"
            >
            <swiper-slide
            v-for="(show, idx) in relatedShow" :key="idx"
            > 
            <v-card elevation="12" style="max-width:150px;max-height:200px;">
              <v-img @click="moveToShow(show.id)" :src="show.posterPath" contain max-height="200" max-width="150"></v-img>
            </v-card>
            </swiper-slide>
            <div class="swiper-scrollbar"></div>
          </swiper>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ShowArtCenter from'./ShowArtCenter.vue'
import ShowExpectations from './ShowExpectations.vue'
import ShowInfo from './ShowInfo.vue'
import ShowReviews from './ShowReviews.vue'

export default {
  name: 'ShowDetail',

  components: {
    ShowArtCenter,
    ShowExpectations,
    ShowInfo,
    ShowReviews,
  },

  data: function () {
    return {
      showDetail: [],
      id: '',
      isEnd: false,
      number: 1,
      dialog: false,
      hashTag: '',
      userId: '',
      isPreference: false,
      isDone: false,
      relatedShow: [],
      swiperOption: {
        slidesPerView: 2,
        spaceBetween: 30,
        direction: 'vertical',
        height : window.innerHeight,
      },

    }
  },

  methods: {
    getRelatedShow: function () {
      this.$store.dispatch('getRelatedShow', {showId: this.id})
      .then((res) => {
        this.relatedShow = res.data.items
      })
    },
    // 공연 상세 정보 불러오기
    getDetail(id) {
      this.$store.dispatch('getDetail', {id:id})
      .then(res => {
        console.log(res)
        this.showDetail = res.data.items[0]
        this.showDetail.startDate = this.showDetail.startDate.slice(0,10)
        const today = new Date()
        const year = today.getFullYear();
        const month = ('0' + (today.getMonth() + 1)).slice(-2);
        const day = ('0' + today.getDate()).slice(-2);
        const dateString = year + '-' + month  + '-' + day;
        if (this.showDetail.endDate) {
          this.showDetail.endDate = this.showDetail.endDate.slice(0,10)
        }
        if (this.showDetail.endDate < dateString) {
          this.isEnd = true
        }
      })
      .then(() => {
        if (this.showDetail.price) {
          const text = this.showDetail.price
          const firstText = text.replaceAll('원', '원\n')
          const newText = firstText.replaceAll('/\n/', '<br/>')
          const p = document.querySelector('.price')
          p.innerText = newText
        }
      })
    },
    changeShow: function(number) {
      this.number = number
    },
    // 선호목록 추가
    addPreference: function() {
      this.$store.dispatch('addPreference', {id:this.id})
      .then(() => {
        this.checkPreference()
      })
    },
    // 선호목록 제거
    deletePreference: function() {
      this.$store.dispatch('deletePreference', {id:this.id})
      .then(() => {
        this.checkPreference()
      })
    },
    // 티켓사이트 이동 & 선호 목록 추가
    goTicketSite: function(number) {
      if (number == 1) {
        this.addPreference()
      }
      // 예매사이트로 가게 하기
      window.location.href=`https://tickets.interpark.com/goods/${this.showDetail.showId}`
    },
    // 연관 공연 상세보기 페이지로 이동
    moveToShow: function(id) {
      console.log('이동', id)
      this.$router.go(this.$router.currentRoute, {params: {id : id}})
    },
    // 유저 정보 가져오기
    getUserInfo: function() {
      this.$store.dispatch('getUserInfo')
      .then((res) => {
        this.userId = res.data.userId
      })
    },
    // 선호 목록 추가 버튼 변경
    changePreference: function() {
      if (this.isPreference == false) {
        this.addPreference()
      } else {
        this.deletePreference()
      }
    },
    // 선호 목록에 존재하는지 체크
    checkPreference: function() {
      this.$store.dispatch('checkPreference', this.id)
      .then((res)=> {
        console.log(res)
        this.isPreference = res.data.isFavorite
      })
    },
    // 조회한 공연으로 추가
    addInquire: function (id) {
      this.$store.dispatch('addInquire', {id:id})
    }
  },
  created: function () {
    this.id = this.$route.params.showId
    // this.id = 4
    this.getDetail(this.id)
    this.getUserInfo()
    this.checkPreference()
    this.addInquire(this.id)
    this.getRelatedShow()
  }
}
</script>

<style>
  .li-info {
    list-style: none;
    margin-top: 16px;
  }
  .main-label {
    display: inline-block;
    width: 9rem;
    padding-right: 0.5rem;
    color: #000;
  }
  .sub-label {  
    display: inline-block;
    max-width: 39rem;
  }
  .li-btn{
    display: inline-block;
    color: rgb(246, 247, 235)
  }
  .btn-detail{
    display: inline-block;
  }
</style>