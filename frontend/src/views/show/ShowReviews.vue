<template>
  <div style="margin-top:30px">
    <div class="d-flex align-center justify-space-between">
      <p class="review-result">총 {{ reviewLength }}개의 관람후기가 등록되었습니다.</p>
      <v-btn color="black" style="float: right; color:white; opacity:0.8s; margin-left:25px" @click="drawUpReview" v-if="!isYet">관람후기 작성</v-btn>
      <v-btn color="black" style="float: right; color:white; opacity:0.8s; margin-left:25px" disabled v-if="isYet">미오픈된 공연입니다.</v-btn>
    </div>
    <br>
    
    <div class="d-flex flex-column align-center" v-if="isDrawUp">
      <star-rating :star-size="30" :inline="true" :show-rating="false" :increment="0.5" :animate="true" :active-color="['#ae0000','#003333']" :active-border-color="['#F6546A','#a8c3c0']" :border-width="1" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]" :active-on-click="true" :clearable="true" v-model="rating"></star-rating>
      <span>{{ rating * 2 }}점</span>
      <div style="width:500px">
        <v-text-field v-model.trim="title" label="제목(30자 이내로 작성해주세요)" :rules="titleRule"></v-text-field>
        <v-textarea v-model.trim="contents" label="내용(100자 이내로 작성해주세요)" rows="4" :rules="contentsRule"></v-textarea>
      </div>
      <v-btn @click="createShowReview(userId)" style="width:500px; color:white;" color="black">등록</v-btn>
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
    <div v-if="showReviewsList.length == 0">
      <h2 class="no-content d-flex justify-center" style="margin: 150px 0px; ">리뷰가 없습니다.</h2> 
    </div>
    <div v-else v-for="(showReview, idx) in showReviewsList" :key="idx" style="margin-top:50px;">
      <div class="container" style="border: 1px solid rgba(0, 0, 0, .3); border-radius:20px;">
        <span class="opacity-text">닉네임 : {{showReview.userName}} | </span>
        <span class="opacity-text">평점 : {{ showReview.rating }} | </span>
        <span class="opacity-text">{{ showReview.createdDate.slice(0,10) }} | </span>
        <div>
          <br>
          <strong class="review-title">{{showReview.title}}</strong>
          <v-btn style="float: right;" v-if="userId == showReview.userId" text @click="deleteShowReview(showReview.reviewId)">삭제</v-btn>
          <v-btn style="float: right;" v-if="userId == showReview.userId" text @click="openUpdate(showReview)">수정</v-btn>
        </div>
        <br>
        <!-- <span>{{$moment(showReview.created_date).format('YYYY-MM-DD hh:mm:ss')}}</span> -->
        <span class="review-content">{{showReview.contents}}</span>
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
            <v-btn style="width:150px; color:white;" color="primary" @click="putShowReview(showReview.reviewId)">수정완료</v-btn>
            <v-btn style="width:150px; color:white;" color="red" @click="openUpdate(showReview)">취소</v-btn>
          </div>
        </div>
      </div>
    </div>
    <v-btn style="float:right" text @click="goNextPage" v-if="!hide">더보기</v-btn>
    
  </div>
</template>

<script>
import StarRating from 'vue-star-rating'

export default {
  name: 'ShowReviews',
  props: {
    id: String,
    userId: Number,
    startDate: String,
  },
  components: {
    StarRating
  },
  data: function() {
    return{
      showReviewsList: [],
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
      pageNum: 0,
      categoryNum: 0,
      reviewLength: 0,
      hide: false,
      isYet: false,
    }
  },
  methods: {
    // 관람 후기 목록 조회
    getShowReviews: function(id, num, categoryNum) {
      this.$store.dispatch('getShowReviews', {id:id, num:num, categoryNum: categoryNum})
      .then(res => {
        this.reviewLength = res.data.length 
        for (let data of res.data.items) {
          this.showReviewsList.push(data)
        }
      })
      .then(() => {
        if (this.showReviewsList.length == this.reviewLength) {
          this.hide = true
        } else {
          this.hide = false
        }
      })
    },
    // 관람 후기 생성
    createShowReview: function(userId) {
      this.$store.dispatch('createShowReview', {id:this.id, title : this.title, contents : this.contents, rating : this.rating * 2, userId:userId})
      .then(() => {
        this.pageNum = 0
        this.showReviewsList = []
        this.getShowReviews(this.id, this.pageNum, this.categoryNum)
        this.title = ''
        this.contents = ''
        this.rating = 0
        this.reviewNum = 0
      })
    },
    // 관람 후기 수정
    putShowReview: function (reviewId) {
      this.$store.dispatch('putShowReview', {reviewId:reviewId, title : this.editTitle,contents : this.editContents, rating : this.editRating * 2, userId:this.userId})
      .then(() => {
        this.pageNum = 0
        this.showReviewsList = []
        this.reviewNum = 0
        this.getShowReviews(this.id, this.pageNum, this.categoryNum)
      })
    },
    // 관람 후기 제거
    deleteShowReview: function(reviewId) {
      this.$store.dispatch('deleteShowReview', {reviewId:reviewId})
      .then(() => {
        this.pageNum = 0
        this.showReviewsList = []
        this.getShowReviews(this.id, this.pageNum, this.categoryNum)
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
    goNextPage: function() {
      this.pageNum += 1
      this.getShowReviews(this.id, this.pageNum, this.categoryNum)
    },
    
    // 정렬
    sortReviewList: function(category) {
      if (category == '최신순') {
        this.categoryNum = 2
      } else if (category == '오래된순') {
        this.categoryNum = 1
      } else if (category == '평점높은순') {
        this.categoryNum = 4
      } else if (category == '평점낮은순') {
        this.categoryNum = 3
      }
    },
  },
  created: function () {
    this.getShowReviews(this.id, this.pageNum, this.categoryNum)
    if (this.startDate) {
      const today = new Date()
      const year = today.getFullYear();
      const month = ('0' + (today.getMonth() + 1)).slice(-2);
      const day = ('0' + today.getDate()).slice(-2);
      const dateString = year + '-' + month  + '-' + day;
      if (this.startDate > dateString) {
        this.isYet = true
      } else {
        this.isYet = false
      }
    }
  },
  watch: {
    categoryNum: function () {
      this.showReviewsList = []
      this.pageNum = 0
      this.getShowReviews(this.id, this.pageNum, this.categoryNum)
    },
  }
  
}
</script>

<style>
  .opacity-text {
    opacity: 0.6;
  }
</style>