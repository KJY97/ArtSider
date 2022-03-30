<template>
  <div style="margin-top:30px">
    <div class="d-flex align-center justify-space-between">
      <p>총 {{ showExpectationsList.length }}개의 기대평이 등록되었습니다.</p>
      <!-- <div>
        <v-text-field
          solo
          flat
          dense
          background-color="#FFF0E1"
          color="black"
          style="margin-left:230px; max-width:200px;"
          v-model="searchTitle"
        >
          <v-btn
            slot="append"
            icon
            @click="searchExpectation(searchTitle)"
          >
            <v-icon
              color="gray"
            >
              mdi-magnify
            </v-icon>
          </v-btn>
        </v-text-field>
      </div> -->
      <v-btn color="black" style="float: right; color:white; opacity:0.8s; margin-left:25px" @click="drawUpExpectation">기대평 작성</v-btn>
    </div>
    <br>
    
    <div class="d-flex flex-column align-center" v-if="isDrawUp">
      <div style="width:500px">
        <v-text-field v-model.trim="title" label="제목(30자 이내로 작성해주세요)" :rules="titleRule"></v-text-field>
        <v-textarea v-model.trim="contents" label="내용(100자 이내로 작성해주세요)" rows="4" :rules="contentsRule"></v-textarea>
      </div>
      <v-btn @click="createShowExpectation" style="width:500px; color:white;" color="black">등록</v-btn>
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
      기대평이 없네요.
    </div>
    <div v-else v-for="(showExpectation, idx) in showExpectationsList" :key="idx" style="margin-top:50px;">
      <div class="container" style="border: 1px solid rgba(0, 0, 0, .3); border-radius:20px;">
        <span class="opacity-text">닉네임 : {{showExpectation.userName}} | </span>
        <span>{{ showExpectation.created_date }}</span>
        <div>
          <br>
          <strong>{{showExpectation.title}}</strong>
          <v-btn style="float: right;" v-if="checkId == showExpectation.userId" text @click="deleteShowInfo(showExpectation.ExpectationId)">삭제</v-btn>
          <v-btn style="float: right;" v-if="checkId == showExpectation.userId" text @click="openUpdate(showExpectation)">수정</v-btn>
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
            <v-btn style="width:150px; color:white;" color="primary" @click="putShowInfo(showExpectation.ExpectationId)">수정완료</v-btn>
            <v-btn style="width:150px; color:white;" color="red" @click="openUpdate(showExpectation)">취소</v-btn>
          </div>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>

export default {
  name: 'ShowExpectations',

  props: {
    id: Object    
  },

  data: function() {
    return{
      showExpectationsList: [
        {  
          expectationId : 1,
          userId: 3,
          userName: "LOVE천사",
          title: "정말 기대됩니다.",
          contents: "제가 정말 보고 싶어하는 배우...",
          created_date: '2022-03-12',
        },
        {  
          expectationId : 2,
          userId: 2,
          userName: "LOVE천사",
          title: "나랑.",
          contents: "제가 정말 보고 싶어하는 배우...",
          created_date: '2022-03-15',
        }
      ],
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
      searchTitle: '',
      reviewNum: 0,
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
    // 기대평 목록 조회
    getShowExpectations: function(id) {
      this.$store.dispatch('getShowExpectations', {id:id})
      .then(res => {
        console.log(res)
      })
    },
    // 기대평 생성
    createShowExpectations: function(id) {
      this.$store.dispatch('createShowExpectations', {id:id, title: this.title, contents: this.contents})
      .then(res => {
        console.log(res)
        this.getShowExpectations(id)
      })
    },
    // 기대평 수정
    putShowExpectations: function (expectationId) {
      this.$store.dispatch('putShowExpectations', {expectationId:expectationId, title : this.editTitle, contents : this.editContents})
      .then(() => {
        console.log('수정 성공')
      })
    },
    // 기대평 제거
    deleteShowExpectations: function(expectationId) {
      this.$store.dispatch('deleteShowExpectations', {expectationId:expectationId})
      .then(() => {
        console.log('삭제 성공')
      })
    },
    // 유저 아이디 불러오기
    getUserId: function () {
      this.checkId = this.$store.dispatch('getUserId')
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
        this.showExpectationsList = this.showExpectationsList.sort(function(a,b){
          if(a.created_date > b.created_date) {
            return 1;
          }else if (a.created_date < b.created_date) {
            return -1;
          } else {
            return 0;
          }
        })
      } else if (category == '오래된순') {
        this.showExpectationsList = this.showExpectationsList.sort(function(a,b){
          if(a.created_date < b.created_date){
            return 1;
          }else if (a.created_date > b.created_date) {
            return -1;
          } else {
            return 0;
          }
        })
      } 
    },
    searchExpectation: function(searchTitle) {
      this.getShowExpectations(this.id)
      console.log(searchTitle)
      this.showExpectationsList = this.showExpectationsList.filter(post => {
        return post.title.toLowerCase().includes(searchTitle.toLowerCase())
      })
    },
  },
  created: function () {
    this.id = this.$route.params.id
    this.getShowExpectations(this.id)
    this.getUserId()
  }
}
</script>

<style>

</style>