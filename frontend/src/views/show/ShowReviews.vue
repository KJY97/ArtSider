<template>
  <div style="margin-top:30px">
    <div class="d-flex justify-space-between">
      <p>총 {{ showReviewsList.length }}개의 관람후기가 등록되었습니다.</p>
      <v-btn color="black" style="color:white; opacity:0.8s" @click="drawUpReview">관람후기 작성</v-btn>
    </div>
    <div class="d-flex flex-column align-center" v-if="isDrawUp">
      <star-rating :star-size="30" :inline="true" :show-rating="false" :increment="0.5" :animate="true" :active-color="['#ae0000','#003333']" :active-border-color="['#F6546A','#a8c3c0']" :border-width="1" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]" :active-on-click="true" :clearable="true" v-model="rating"></star-rating>
      <span>{{ this.rating * 2 }}점</span>
      <div style="width:500px">
        <v-text-field label="제목(30자 이내로 작성해주세요)" :rules="titleRule"></v-text-field>
        <v-textarea v-model.trim="contents" label="내용(100자 이내로 작성해주세요)" rows="4" :rules="contentsRule"></v-textarea>
      </div>
      <v-btn @click="createShowReview" style="width:500px; color:white;" color="black">등록</v-btn>
      <br>
    </div>
    <div v-if="showReviewsList.length == 0">
      리뷰가 없네요.
      리뷰를 작성해주세요!!
    </div>
    <div v-else v-for="(showreview, idx) in showReviewsList" :key="idx">
      <div class="container" style="border: 1px solid rgba(0, 0, 0, .3); border-radius:20px;">
        <span class="opacity-text">닉네임 : {{showreview.userName}} | </span>
        <span class="opacity-text">평점 : {{ showreview.rating }} | </span>
        
        <div>
          <br>
          <strong>{{showreview.title}}</strong>
          <v-btn style="float: right;" v-if="checkId == showreview.userId" text @click="deleteShowInfo(showreview.reviewId)">삭제</v-btn>
          <v-btn style="float: right;" v-if="checkId == showreview.userId" text @click="putShowInfo(showreview.reviewId)">수정</v-btn>
        </div>
        <br>
        <!-- <span>{{$moment(showreview.created_date).format('YYYY-MM-DD hh:mm:ss')}}</span> -->
        <span>{{showreview.contents}}</span>
      </div>
    </div>
    
  </div>
</template>

<script>
import axios from'axios'
import StarRating from 'vue-star-rating'

export default {
  name: 'ShowReviews',
  props: {
    showId: Object
  },
  components: {
    StarRating
  },
  data: function() {
    return{
      showReviewsList: [{  
              reviewId : 1,
              userId: 2,
              userName: "LOVE천사",
              title: "꼭 보세요.",
              contents: "정말 재미있는 공연이었...",
              rating: 5,
              created_date: '2022-03-12',
        }],
      title: '',
      contents: '',
      rating: 0,
      ckeckId: '',
      isDrawUp: false,
      titleRule: [
        v => !( v && v.length > 30) || '30자 이내로 입력해주세요'
      ],
      contentsRule: [
        v => !( v && v.length > 100) || '100자 이내로 입력해주세요'
      ]
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
    // 관람 후기 목록 조회
    getShowReviews: function(showId) {
      axios({
        method: 'get',
        url: `http://127.0.0.1:8000/api/show/reviews/${showId}`,
      })
      .then(res => {
        console.log(res)
        this.showReviewsList = res
      })
    },
    // 관람 후기 생성
    createShowReview: function(showId) {
      axios({
        method: 'post',
        url: `http://127.0.0.1:8000/api/show/reviews/${showId}`,
        headers: this.setToken(),
        data: {
          title : this.title,
          contents : this.contents,
          rating : this.rating * 2
        },
      })
      .then(() => {
        console.log('성공')
      })
    },
    // 관람 후기 수정
    putShowInfo: function (reviewId) {
      axios({
        method: 'post',
        url: `http://127.0.0.1:8000/api/show/reviews/${reviewId}`,
        headers: this.setToken()
      })
      .then(() => {
        console.log('삭제 성공')
      })
    },
    // 관람 후기 제거
    deleteShowReview: function(reviewId) {
      axios({
        method: 'post',
        url: `http://127.0.0.1:8000/api/show/reviews/${reviewId}`,
        headers: this.setToken()
      })
      .then(() => {
        console.log('삭제 성공')
      })
    },
    drawUpReview: function() {
      this.isDrawUp = !this.isDrawUp
    },

  },
  created: function () {
    this.showId = this.$route.params.showId
    this.getShowReviews(this.showId)
    this.checkId = localStorage.getItem('userId')
  }
}
</script>

<style>
  .opacity-text {
    opacity: 0.6;
  }
</style>