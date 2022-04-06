<template>
  <div style="margin-top:30px">
    <div class="d-flex align-center justify-space-between">
      <p>총 {{ expectationLength }}개의 기대평이 등록되었습니다.</p>
      <v-btn color="black" style="float: right; color:white; opacity:0.8s; margin-left:25px" @click="drawUpExpectation">기대평 작성</v-btn>
    </div>
    <br>
    
    <div class="d-flex flex-column align-center" v-if="isDrawUp">
      <div style="width:500px">
        <v-text-field v-model.trim="title" label="제목(30자 이내로 작성해주세요)" :rules="titleRule"></v-text-field>
        <v-textarea v-model.trim="contents" label="내용(100자 이내로 작성해주세요)" rows="4" :rules="contentsRule"></v-textarea>
      </div>
      <v-btn @click="createShowExpectations(userId)" style="width:500px; color:white;" color="black">등록</v-btn>
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
            기대평 정렬
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
            <v-btn text style="width:100%" @click="sortExpectationList(category.title)">
              <v-list-item-title> {{ category.title }} </v-list-item-title>
            </v-btn>
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
    <div v-if="showExpectationsList.length == 0" >
      <h2 class="d-flex justify-center" style="margin: 150px 0px; ">기대평이 없습니다.</h2> 
    </div>
    <div v-else v-for="(showExpectation, idx) in showExpectationsList" :key="idx" style="margin-top:50px;">
      <div class="container" style="border: 1px solid rgba(0, 0, 0, .3); border-radius:20px;">
        <span class="opacity-text">닉네임 : {{showExpectation.userName}} | </span>
        <span class="opacity-text">{{ showExpectation.createDate.slice(0,10) }} | </span>
        <div>
          <br>
          <strong>{{showExpectation.title}}</strong>
          <v-btn style="float: right;" v-if="userId == showExpectation.userId" text @click="deleteShowExpectations(showExpectation.expectationId)">삭제</v-btn>
          <v-btn style="float: right;" v-if="userId == showExpectation.userId" text @click="openUpdate(showExpectation)">수정</v-btn>
        </div>
        <br>
        
        <span>{{showExpectation.contents}}</span>
        <br>
        <!-- 수정버튼을 누르면 나오는 수정창 -->
        <div v-if="reviewNum == showExpectation.expectationId" class="container d-flex flex-column align-center">
          <div style="width:300px">
            <v-text-field v-model="editTitle" :rules="titleRule"></v-text-field>
            <v-textarea v-model.trim="editContents" rows="4" :rules="contentsRule"></v-textarea>
          </div>
          <div>
            <v-btn style="width:150px; color:white;" color="primary" @click="putShowExpectations(showExpectation.expectationId)">수정완료</v-btn>
            <v-btn style="width:150px; color:white;" color="red" @click="openUpdate(showExpectation)">취소</v-btn>
          </div>
        </div>
      </div>
    </div>
    
    <v-btn style="float:right" text @click="goNextPage" v-if="!hide">더보기</v-btn>
  </div>
</template>

<script>

export default {
  name: 'ShowExpectations',

  props: {
    id: String,
    userId: Number
  },

  data: function() {
    return{
      showExpectationsList: [],
      title: '',
      contents: '',
      ckeckId: '',
      isDrawUp: false,
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
      ],
      reviewNum: 0,
      pageNum: 0,
      categoryNum: 0,
      expectationLength: 0,
      hide: false,
    }
  },

  methods: {
    // 기대평 목록 조회
    getShowExpectations: function(id, num, categoryNum) {
      this.$store.dispatch('getShowExpectations', {id:id, num:num, categoryNum: categoryNum})
      .then(res => {
        this.expectationLength = res.data.length 
        for (let data of res.data.items) {
          this.showExpectationsList.push(data)
        }
      })
      .then(() => {
        if (this.showExpectationsList.length == this.expectationLength) {
          this.hide = true
        } else {
          this.hide = false
        }
      })
    },
    // 기대평 생성
    createShowExpectations: function(userId) {
      this.$store.dispatch('createShowExpectations', {id:this.id, userId:userId, title: this.title, contents: this.contents})
      .then(() => {
        this.pageNum = 0
        this.showExpectationsList = []
        this.getShowExpectations(this.id, this.pageNum, this.categoryNum)
        this.title = ''
        this.contents = ''
        this.reviewNum = 0
      })
    },
    // 기대평 수정
    putShowExpectations: function (expectationId) {
      this.$store.dispatch('putShowExpectations', {expectationId:expectationId, title : this.editTitle, contents : this.editContents, userId:this.userId,})
      .then(() => {
        this.pageNum = 0
        this.showExpectationsList = []
        this.reviewNum = 0
        this.getShowExpectations(this.id, this.pageNum, this.categoryNum)
      })
    },
    // 기대평 제거
    deleteShowExpectations: function(expectationId) {
      this.$store.dispatch('deleteShowExpectations', {expectationId:expectationId})
      .then(() => {
        this.pageNum = 0
        this.showExpectationsList = []
        this.getShowExpectations(this.id, this.pageNum, this.categoryNum)
      })
    },

    drawUpExpectation: function() {
      this.isDrawUp = !this.isDrawUp
    },
    openUpdate: function(showExpectation) {
      if (this.reviewNum == 0) {
        this.editTitle = showExpectation.title
        this.editContents = showExpectation.contents
        this.reviewNum = showExpectation.expectationId
      } else {
        this.reviewNum = 0
      }
    },
    sortExpectationList: function(category) {
      if (category == '최신순') {
        this.categoryNum = 2
      } else if (category == '오래된순') {
        this.categoryNum = 1
      }
    },
    goNextPage: function() {
      this.pageNum += 1
      this.getShowExpectations(this.id, this.pageNum, this.categoryNum)
    },
    // searchExpectation: function(searchTitle) {
    //   this.getShowExpectations(this.id)
    //   console.log(searchTitle)
    //   this.showExpectationsList = this.showExpectationsList.filter(post => {
    //     return post.title.toLowerCase().includes(searchTitle.toLowerCase())
    //   })
    // },
  },
  created: function () {
    this.getShowExpectations(this.id, this.pageNum, this.categoryNum)
  },
  watch: {
    categoryNum: function () {
      this.showExpectationsList = []
      this.pageNum = 0
      this.getShowExpectations(this.id, this.pageNum, this.categoryNum)
      
    },
  }
}
</script>

<style>

</style>