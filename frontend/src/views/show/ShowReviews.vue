<template>
  <div style="margin-top:30px">
    <div class="d-flex align-center justify-space-between">
      <p>총 {{ showReviewsList.length }}개의 관람후기가 등록되었습니다.</p>
      <!-- <div>
        <v-text-field
          solo
          flat
          dense
          background-color="#FFF0E1"
          color="black"
          style="margin-left:200px; max-width:200px;"
          v-model="searchTitle"
        >
          <v-btn
            slot="append"
            icon
            @click="searchReview(searchTitle)"
          >
            <v-icon
              color="gray"
            >
              mdi-magnify
            </v-icon>
          </v-btn>
        </v-text-field>
      </div> -->
      <v-btn color="black" style="float: right; color:white; opacity:0.8s; margin-left:25px" @click="drawUpReview">관람후기 작성</v-btn>
    </div>
    <br>
    
    <div class="d-flex flex-column align-center" v-if="isDrawUp">
      <star-rating :star-size="30" :inline="true" :show-rating="false" :increment="0.5" :animate="true" :active-color="['#ae0000','#003333']" :active-border-color="['#F6546A','#a8c3c0']" :border-width="1" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]" :active-on-click="true" :clearable="true" v-model="rating"></star-rating>
      <span>{{ rating * 2 }}점</span>
      <div style="width:500px">
        <v-text-field v-model.trim="title" label="제목(30자 이내로 작성해주세요)" :rules="titleRule"></v-text-field>
        <v-textarea v-model.trim="contents" label="내용(100자 이내로 작성해주세요)" rows="4" :rules="contentsRule"></v-textarea>
      </div>
      <v-btn @click="createShowReview" style="width:500px; color:white;" color="black">등록</v-btn>
      <br>
    </div>

    <hr>
    <div style="float: right;">
      <v-menu
        bottom
      >
        <template v-slot:activator="{on, attrs}">
          <v-btn
            text  
            v-bind="attrs"
            v-on="on"
          >
            관람후기 정렬
          <v-icon
          right
          >
            mdi-arrow-down-drop-circle-outline
          </v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            v-for="(category, i) in categories"
            :key="i"
          >
            <v-btn text style="width:100%" @click="sortReviewList(category.title)">
              <v-list-item-title> {{ category.title }} </v-list-item-title>
            </v-btn>
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
    <div v-if="showReviewsList.length == 0" >
      리뷰가 없네요.
    </div>
    <div v-else v-for="(showReview, idx) in showReviewsList" :key="idx" style="margin-top:50px;">
      <div class="container" style="border: 1px solid rgba(0, 0, 0, .3); border-radius:20px;">
        <span class="opacity-text">닉네임 : {{showReview.userName}} | </span>
        <span class="opacity-text">평점 : {{ showReview.rating }} | </span>
        
        <div>
          <br>
          <strong>{{showReview.title}}</strong>
          <v-btn style="float: right;" v-if="checkId == showReview.userId" text @click="deleteShowInfo(showReview.reviewId)">삭제</v-btn>
          <v-btn style="float: right;" v-if="checkId == showReview.userId" text @click="openUpdate(showReview)">수정</v-btn>
        </div>
        <br>
        <!-- <span>{{$moment(showReview.created_date).format('YYYY-MM-DD hh:mm:ss')}}</span> -->
        <span>{{showReview.contents}}</span>
        <br>
        <!-- 수정버튼을 누르면 나오는 수정창 -->
        <div v-if="reviewNum == showReview.reviewId" class="container d-flex flex-column align-center">
          <star-rating :star-size="30" :inline="true" :show-rating="false" :increment="0.5" :animate="true" :active-color="['#ae0000','#003333']" :active-border-color="['#F6546A','#a8c3c0']" :border-width="1" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]" :active-on-click="true" :clearable="true" v-model="editRating"></star-rating>
          <span>{{ editRating * 2 }}점</span>
          <div style="width:300px">
            <v-text-field v-model="editTitle" :rules="titleRule"></v-text-field>
            <v-textarea v-model.trim="editContents" rows="4" :rules="contentsRule"></v-textarea>
          </div>
          <div>
            <v-btn style="width:150px; color:white;" color="primary" @click="putShowInfo(showReview.reviewId)">수정완료</v-btn>
            <v-btn style="width:150px; color:white;" color="red" @click="openUpdate(showReview)">취소</v-btn>
          </div>
        </div>
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
    id: Object
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
              rating: 6,
              created_date: '2022-03-12',
        },{  
              reviewId : 2,
              userId: 3,
              userName: "LOVE천사",
              title: "내맘대로",
              contents: "정말 재미있는 공연이었...",
              rating: 9,
              created_date: '2022-03-14',
        }],
      title: '',
      contents: '',
      rating: 0,
      ckeckId: '',
      isDrawUp: false,
      reviewNum: 0,
      editRating: 1,
      editTitle: '',
      editContents: '',
      titleRule: [
        v => !( v && v.length > 30) || '30자 이내로 입력해주세요'
      ],
      contentsRule: [
        v => !( v && v.length > 100) || '100자 이내로 입력해주세요'
      ],
      categories: [
        { title: "최신순"},
        { title: "오래된순"},
        { title: "평점높은순"},
        { title: "평점낮은순"},
      ],
      searchTitle: '',
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
    getShowReviews: function(id) {
      axios({
        method: 'get',
        url: `http://127.0.0.1:8000/api/show/reviews/${id}`,
      })
      .then(res => {
        console.log(res)
        this.showReviewsList = res.data
      })
    },
    // 관람 후기 생성
    createShowReview: function(id) {
      axios({
        method: 'post',
        url: `http://127.0.0.1:8000/api/show/reviews/${id}`,
        headers: this.setToken(),
        data: {
          title : this.title,
          contents : this.contents,
          rating : this.rating * 2
        },
      })
      .then(() => {
        console.log('성공')
        this.getShowReviews(id)
      })
    },
    // 관람 후기 수정
    putShowInfo: function (reviewId) {
      axios({
        method: 'put',
        url: `http://127.0.0.1:8000/api/show/reviews/${reviewId}`,
        headers: this.setToken(),
        data: {
          title : this.editTitle,
          contents : this.editContents,
          rating : this.editRating * 2
        }
      })
      .then(() => {
        console.log('수정 성공')
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
    openUpdate: function(showReview) {
      if (this.reviewNum == 0) {
        this.editTitle = showReview.title
        this.editContents = showReview.contents
        this.editRating = showReview.rating / 2
        this.reviewNum = showReview.reviewId
      } else {
        this.reviewNum = 0
      }

    },
    sortReviewList: function(category) {
      if (category == '최신순') {
        this.showReviewsList = this.showReviewsList.sort(function(a,b){
          if(a.created_date > b.created_date) {
            return 1;
          }else if (a.created_date < b.created_date) {
            return -1;
          } else {
            return 0;
          }
        })
      } else if (category == '오래된순') {
        this.showReviewsList = this.showReviewsList.sort(function(a,b){
          if(a.created_date < b.created_date){
            return 1;
          }else if (a.created_date > b.created_date) {
            return -1;
          } else {
            return 0;
          }
        })
      } else if (category == '평점높은순') {
        this.showReviewsList = this.showReviewsList.sort(function(a,b){
          if(a.rating < b.rating){
            return 1;
          }else if (a.rating > b.rating) {
            return -1;
          } else {
            return 0;
          }
        })
      } else if (category == '평점낮은순') {
        this.showReviewsList = this.showReviewsList.sort(function(a,b){
          if(a.rating > b.rating){
            return 1;
          }else if (a.rating < b.rating) {
            return -1;
          } else {
            return 0;
          }
        })
      }
    },
    searchReview: function(searchTitle) {
      this.getShowReviews(this.id)
      this.showReviewsList = this.showReviewsList.filter(post => {
        return post.title.toLowerCase().includes(searchTitle.toLowerCase())
      })
    },
  },
  created: function () {
    this.id = this.$route.params.id
    this.getShowReviews(this.id)
    this.checkId = localStorage.getItem('userId')
  }
}
</script>

<style>
  .opacity-text {
    opacity: 0.6;
  }
</style>