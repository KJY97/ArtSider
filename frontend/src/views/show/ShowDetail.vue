<template>
  <div class="d-flex justify-center" style="padding-top: 100px">
    <div>
      <div class="d-flex flex-column" style="max-width:900px; min-width:900px; margin-left: 150px">
        <h1 style="padding-left:30px">{{ showName }}</h1>
        <div class="d-flex justify-center">
          <div class="d-flex flex-column" style="margin-right:100px">
            <v-img :src="posterPath" style="max-width:300px; width:100%; min-width:300px; height:100%; min-height:400px; max-height:400px"></v-img>
            <div class="d-flex flex-wrap justify-center" style="max-width:300px;">
              <span v-for="(Tag,idx) in hashTag" :key="idx" class="py-2">
                <v-btn rounded text>#{{ Tag }}</v-btn>
              </span>
            </div>
          </div>

          <ul class="d-flex flex-column mt-6 ml-10 pb-2" style="min-width:400px; width:100%; max-width:400px">
            <li class="liInfo">
              <strong class="mainLabel">장소</strong>
              <div class="subLabel">
                {{ concertHall }}
              </div>
            </li>
            <li class="liInfo">
              <strong class="mainLabel">공연 카테고리</strong>
              <div class="subLabel">
                {{ category }}
              </div>
            </li>
            <li class="liInfo">
              <strong class="mainLabel">공연기간</strong>
              <div class="subLabel">
                {{ start_date }} ~ {{ end_date }}
              </div>
            </li>
            <li class="liInfo">
              <strong class="mainLabel">공연시간</strong>
              <div class="subLabel">
                {{ runTime }}
              </div>
            </li>
            <li class="liInfo">
              <strong class="mainLabel">관람연령</strong>
              <div class="subLabel">
                {{ age }}
              </div>
            </li>
            <li class="liInfo">
              <strong class="mainLabel">가격</strong>
              <div class="subLabel">
                {{ price }}원
              </div>
            </li>
            
            <div class="mt-auto">
              <v-dialog
                
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
                  <v-btn rounded large disabled v-if="isEnd" style="width:340px;">종료 된 공연입니다</v-btn>
                </template>
                <v-card>
                  <v-card-title class="text-h5">
                    선호 목록에 추가하시겠습니까?
                  </v-card-title>
                  <v-card-text>
                    선호 목록에 추가하면 맞춤형 공연을 추천 받을 수 있어요!
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
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
              <!-- disable 줄것 -->
            </div>
          </ul>
        </div>
      </div>
      <div style="max-width:1000px; min-width:1000px;">
        <div style="margin-left:170px; margin-top:30px">
          <li class="liBtn">
            <v-btn text @click="changeShow(1)" class="btnDetail">공연정보</v-btn>
          </li>
          <li class="liBtn">
            <v-btn text @click="changeShow(2)" class="btnDetail">관람후기</v-btn>
          </li>
          <li class="liBtn">
            <v-btn text @click="changeShow(3)" class="btnDetail">기대평</v-btn>
          </li>
          <li class="liBtn">
            <v-btn text @click="changeShow(4)" class="btnDetail">공연장 위치</v-btn>
          </li>
          <hr>
          <!-- router-view -->
          <show-info
            :showId="showId"
            v-if="number == 1"
            :subPosterPath = subPosterPath
            :story = story
            :cast = cast
            :producer = producer
          ></show-info>

          <show-reviews
            :showId="showId"
            v-if="number == 2"
          ></show-reviews>

          <show-expectations
            :showId="showId"
            v-if="number == 3"
          ></show-expectations>

          <show-art-center
            :ArtCenterName="this.concertHall"
            v-if="number == 4"
          ></show-art-center>
        </div>
      </div>
    </div>
    <div style="margin-left:300px;">
      우측 리모컨
    </div>
  </div>
</template>

<script>
import axios from'axios'
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
      showId: '',
      showName : '지킬 앤 하이드',
      state: '공연중',
      age: '9세 이상',
      posterPath: '//ticketimage.interpark.com/Play/image/large/21/21007693_p.gif',
      subPosterPath: [ '//ticketimage.interpark.com/Play/image/large/21/21007693_p.gif', '//ticketimage.interpark.com/Play/image/large/21/21007693_p.gif'],
      category: '뮤지컬',
      hashTag: [ '흥미진진', '존잼', '노래맛집'],
      concertHall: 'xx문화극장(서울)',
      runTime: '150분',
      price: 35000,
      start_date: '2022-01-30',
      end_date:  '2022-03-02',
      open_run: false,
      story: '어쩌구 저쩌구 줄거리 소개',
      time: '상시공연',
      ticketingUrl: 'https://interpark.com',
      cast: [ '배우1', '배우2', '배우3'],
      producer: ['제작사1', '제작사2'],
      relatedShow: [],
      isEnd: false,
      number: 1,
      dialog: false,
    }
  },

  methods: {
    setToken: function(){
      const token = localStorage.getItem('accessToken')

      const config = {
        Authorization: `Bearer ${token}`
      }
      return config
    },
    getDetail(showId) {
      axios({
        method: 'get',
        url: `http://127.0.0.1:8000/api/show/${showId}`,
      })
      .then(res => {
        console.log(res)
      })
    },
    changeShow: function(number) {
      this.number = number
    },
    // 티켓사이트 이동 & 선호 목록 추가
    goTicketSite: function(number) {
      if (number == 1) {
        axios({
            method: 'post',
            url: `http://127.0.0.1:8000/api/show/${this.showId}/preference`,
            headers: this.setToken(),
            data: {
              userId: ''
            }
          })
        }
      // 예매사이트로 가게 하기
    },
    // 조회한 공연 추가
    addLookUp: function () {
      axios({
        method: 'post',
        url: `http://127.0.0.1:8000/api/show/${this.showId}`,
        headers: this.setToken(),
      })
    },
  },
  created: function () {
    this.showId = this.$route.params.showId
    this.getDetail(this.showId)
    
  }
}
</script>

<style>
  .liInfo {
    list-style: none;
    margin-top: 16px;
  }
  .mainLabel {
    display: inline-block;
    width: 9rem;
    padding-right: 0.5rem;
    color: #000;
  }
  .subLabel {  
    display: inline-block;
    max-width: 39rem;
  }
  .liBtn{
    display: inline-block;
  }
  .btnDetail{
    display: inline-block;
  }
</style>