import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'

Vue.use(Vuex)
axios.defaults.baseURL = 'http://localhost:8080'

export default new Vuex.Store({
  state: {
    token : '',
    myContents: [],
    isMultiEmail: true,
    emailConfirmNumber: null,

    preferencePosters: [],
    historyPosters : [],

    userInfo: {},
    profileImg: null,
    profileImgSrc: null,
    userReviews: [],
    userScores: [],
    userTagsCloud: {}
  },

  getters: {
    loginStatus(state){
      return !!state.token
    },

    historyPosters(state){
      return state.historyPosters
    },

    userInfo(state){
      return state.userInfo
    },

    userImgSrc(state){
      return state.profileImgSrc
    },

    userReviews(state){
      return state.userReviews
    },

    userScores(state){
      const scoreList = []
      state.userScores.forEach((elem) => {
        scoreList.push(elem.cnt)
      })
      return scoreList
    },

    userTagsCloud(state){
      return state.userTagsCloud
    }

  },

  mutations: {
    SET_MY_TOKEN(state, token){
      state.token = token
    },

    SET_MY_PREFERENCE(state, posters){
      state.preferencePosters = posters
    },

    SET_MY_HISTORY(state, history){
      state.historyPosters = history
    },

    SET_MY_REVIEWS(state, reviews){
      state.userReviews = reviews
    },
    
    SET_USER_INFO(state, userData){
      state.userInfo = userData
      state.profileImg = userData.profileImg
    },

    SET_USER_IMG_SRC(state, src){
      state.profileImgSrc = src
    },

    SET_MY_SCORES(state, scores){
      state.scores = scores
    },

    SET_MY_TAGS_CLOUD(state, tags){
      state.userTagsCloud = tags
    },

  


  },

  actions: {
    // 조회한 공연 추가
    addInquire({state}, data){
      state
      const url = `/api/users/show/${data.id}`
      return axios.post(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },
    // 관람 후기 제거
    deleteShowReview({state}, data){
      state
      const url = `/api/show/reviews/${data.reviewId}`
      return axios.delete(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },
    // 관람 후기 수정
    putShowReview({state}, data){
      state
      const url = `/api/show/reviews/${data.reviewId}`
      return axios.put(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
        userId: data.userId,
        title: data.title,
        contents: data.contents,
        rating: data.rating,
      })
    },
    // 관람 후기 생성
    createShowReview({state}, data){
      state
      const url = `/api/show/reviews/${data.id}`
      return axios.post(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
        userId: data.userId,
        title: data.title,
        contents: data.contents,
        rating: data.rating,
      })
    },
    // 관람 후기 목록 조회
    getShowReviews({state}, data){
      state
      let sort = ''
      if (data.categoryNum == 1) {
        sort = 'createDate,asc'
      } else if (data.categoryNum == 2) {
        sort = 'createDate,desc'
      } else if (data.categoryNum == 3) {
        sort = 'rating,asc'
      } else if (data.categoryNum == 4) {
        sort = 'rating,desc'
      }
      let url = ''
      if (sort == '') {
        url = `/api/show/reviews/${data.id}?page=${data.num}`
      } else {
        url = `/api/show/reviews/${data.id}?page=${data.num}&size=5&sort=${sort}`
      }
      
      return axios.get(url)
    },
    // 기대평 제거
    deleteShowExpectations({state},data){
      const url = `/api/show/expectations/${data.expectationId}`
      return axios.delete(url, { 
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },
    // 기대평 수정
    putShowExpectations({state}, data){
      state
      console.log(data)
      const url = `/api/show/expectations/${data.expectationId}`
      return axios.put(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
        userId: data.userId,
        title: data.title,
        contents: data.contents,
      })
    },

    // 기대평 생성
    createShowExpectations({state}, data){
      state
      const url = `/api/show/expectations/${data.id}`
      return axios.post(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
        userId: data.userId,
        title: data.title,
        contents: data.contents,
      })
    },
    // 기대평 목록 조회
    getShowExpectations({state}, data){
      state
      let sort = ''
      if (data.categoryNum == 1) {
        sort = 'createDate,asc'
      } else if (data.categoryNum == 2) {
        sort = 'createDate,desc'
      }
      let url = ''
      if (sort == '') {
        url = `/api/show/expectations/${data.id}?page=${data.num}`
      } else {
        url = `/api/show/expectations/${data.id}?page=${data.num}&size=5&sort=${sort}`
      }
      return axios.get(url)
    },
    // 유저 선호 태그 별 추천 공연 조회
    getRelatedTagShow({state}, data){
      state
      const url = `/api/show/recommand/user/tag`
      return axios.get(url,{
        headers: {
          Authorization : `Bearer ${state.token}`
        },
        userTag: data.userTag
      })
    },
    // 공연의 연관 공연 조회
    getRelatedShow: function ({state}, data) {
      state
      const url = `/api/show/recommand/${data.showId}/relatedShow`
      return axios.get(url,{
        headers: {
          Authorization : `Bearer ${state.token}`
        },
        userTag: data.showTag
      })
    },
    // 유저가 좋아할 확률 업데이트
    updateUserExpectation: function ({state},data) {
      const url = `/api/show/recommand/${data.showId}/probability`
      return axios.post(url,{
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },
    // 사용자간의 유사도 추천 서비스
    getSimilarityShow: function ({state}) {
      const url = `/api/show/recommend/similarity`
      return axios.get(url,{
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },
    // 유저가 좋아할 확률 조회
    getUserExpectation: function ({state},data) {
      const url = `/api/show/recommand/${data.showId}/probability`
      return axios.post(url,{
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },

    
    

    // 티켓사이트 이동 & 선호 목록 추가
    goTicketSite({state}, data){
      state
      const url = `/api/show/${data.id}/preference`
      return axios.post(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
        data: {
          userId: state.userInfo.userId,
        }
      })
    },
    // 공연 상세 정보 불러오기
    getDetail({state}, data){
      state
      const url = `/api/show/${data.id}`
      return axios.get(url)
    },
    // 공연장 위치 불러오기
    getshowArtCenter({state}, data){
      state
      const url = `/api/show/${data.ArtCenterName}/artcenter`
      return axios(url)
    },
    

    // 이메일 체크
    checkMultiEmail({state}, inputEmail){
      const url = `/api/users/${inputEmail}`
      axios.get(url)
      .then((res) => {
        if(res.data.emailCheck){
          console.log(res.data.emailCheck)
          alert('이메일 중복검사 통과')
          state.isMultiEmail = false
        } else{
          alert('이미 가입한 이메일입니다.')
          state.isMultiEmail = true
        }
      })
      .catch(() => {
        alert('이메일을 다시 확인해주세요.')
        state.isMultiEmail = true
      })
    },

    createUser({dispatch}, data){
      const userData = data.userData
      const signUpVue = data.this
      const url = '/api/users'
      axios.post(url, {...userData})
      .then(() => {
        alert('회원가입 성공!')
        signUpVue.confirmationNumber = ''
        signUpVue.inputNumber = ''
        signUpVue.step = 1
        signUpVue.$emit('close-sign-up')
      })
      .then(() => {
        dispatch('getToken', {userEmail: userData.userEmail, password: userData.password})
      })
      .catch(()=>{
        alert('회원가입 실패')
        signUpVue.confirmationNumber = ''
        signUpVue.inputNumber = ''
        signUpVue.step = 1
      })
    },

    // 이메일 본인 인증 번호 보내기
    getUserConfirmNumber({state}, userEmail){
      const url = `/api/users/${userEmail}`
      axios.post(url)
      .then((res)=>{
        console.log(res.data.emailNumber)
        state.emailConfirmNumber = res.data.emailNumber
      })
      .catch(()=>{
        alert('인증번호 발송 실패')
      })

    },


    setToken({commit}, token){
      commit('SET_MY_TOKEN', token)
    },

    getToken({commit}, inputData){
      const url = '/api/auth/login'
      axios.post(url, {...inputData})
      .then((res) => {
        localStorage.setItem('accessToken', res.data.accessToken)
        commit('SET_MY_TOKEN', res.data.accessToken)
        router.push({name: 'Main'})
      })

    },
    // 선호 목록 추가
    addPreference({state}, data) {
      state
      const url = `/api/users/show/${data.id}/preference`
      return axios.post(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },
    // 선호 목록 제거
    deletePreference({state}, data) {
      state
      const url = `/api/users/show/${data.id}/preference`
      return axios.delete(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },

    // 관심 공연 목록 조회
    getMyPreference({commit, state}){
      commit
      const url = '/api/users/profile/preference'
      return axios.get(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        }
      })
    },

    // 내가 조회한 공연 목록 조회
    getMyHistory({commit, state}){
      commit  
      const url = '/api/users/profile/watchHistory'
        return axios.get(url, {
          headers: {
            Authorization : `Bearer ${state.token}`
          }
        })
    },

    // 유저 정보 얻어오기
    getUserInfo({state}){
      const url = '/api/users/profile'
      return axios.get(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        }
      })
    },

    // 내가 작성한 리뷰 목록 조회
    getUserReviews({commit, state}){
      const url = '/api/users/profile/reviewList'
      axios.get(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        }
      })
      .then((res) => {
        commit('SET_MY_REVIEWS', res.data.items)
      })
    },

    // 유저 리뷰 평점 별 갯수
    getUserScores({commit, state}){
      const url = '/api/users/review/ratingStars'
      axios.get(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        }
      })
      .then((res) => {
        commit('SET_MY_SCORES', res.data.items)
      })
    },
    

    // 유저 관심 공연들의 태그 갯수
    getUserTags({commit, state}){
      const url = '/api/users/show/preference/tag'
      axios.get(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        }
      })
      .then((res) => {
        console.log('유저 관심 태그 워드 클라우드 response.data: ', res.data)
        commit('SET_MY_TAGS_CLOUD', res.data)
      })
    },

    // 프로필 이미지 등록
    setUserImage({state}, image){
      const url = '/api/users/profile/photo'
      const headers = {
        'Content-Type': 'multipart/form-data',
        'Authorization' : `Bearer ${state.token}`
      }
      
      let formData = new FormData()
      formData.append('file', image)
      
      return axios.post(url, formData, {
          headers: headers
        })
    },

    // 프로필 이미지 소스 얻어오기
    getUserImageSrc({state}, profileImg){
      if(profileImg){
        const url = `/api/users/profile/photo/${profileImg}`
        return axios.get(url, {
          headers:{
            Authorization : `Bearer ${state.token}`
          }
        })
      }
    },
    // 인기 공연 불러오기
    getPopularShow({ state }){
      state
      const url = '/api/show/popular/'
      // const userdata = data
      console.log('인기공연')
      return axios.get(url)
      
    },
    // 카테고리 별 인기공연
    getCategoryPopularShow({ state }, data){
      state
      const url = ''
      if ( data.num == 1) {
        this.url = '/api/show/CL/popular'
      } else if ( data.num == 2) {
        this.url = '/api/show/CO/popular'
      } else if ( data.num == 3) {
        this.url = '/api/show/FA/popular'
      } else if ( data.num == 4) {
        this.url = '/api/show/MU/popular'
      } else if ( data.num == 5) {
        this.url = '/api/show/DR/popular'
      }

      // 카테고리
      // -MU :뮤지컬
      // -CL: 클래식/오페라
        // -DA: 무용/전통예술
        // -FA: 아동/가족
        // -DR: 연극
        // -CO: 콘서트
      // const userdata = data
      return axios.get(url)
    },
    // 곧 개봉하는 공연 불러오기
    getComingSoonShow({ state }){
      state
      const url = '/api/show/startDate/'
      return axios.get(url)
    },

    getCategoryComingSoonShow({ state }, data){
      state
      const url = ''
      if ( data.num == 1) {
        this.url = '/api/show/CL/startDate'
      } else if ( data.num == 2) {
        this.url = '/api/show/CO/startDate'
      } else if ( data.num == 3) {
        this.url = '/api/show/FA/startDate'
      } else if ( data.num == 4) {
        this.url = '/api/show/MU/startDate'
      } else if ( data.num == 5) {
        this.url = '/api/show/DR/startDate'
      }

      // 카테고리
      // -MU :뮤지컬
      // -CL: 클래식/오페라
        // -DA: 무용/전통예술
        // -FA: 아동/가족
        // -DR: 연극
        // -CO: 콘서트
      // const userdata = data
      return axios.get(url)
    },

    // 곧 끝나는 공연 불러오기
    getComingEndShow({ state }){
      state
      const url ='/api/show/endDate/'
      return axios.get(url)
    },

    getCategoryComingEndShow({ state }, data){
      state
      const url = ''
      if ( data.num == 1) {
        this.url = '/api/show/CL/endDate'
      } else if ( data.num == 2) {
        this.url = '/api/show/CO/endDate'
      } else if ( data.num == 3) {
        this.url = '/api/show/FA/endDate'
      } else if ( data.num == 4) {
        this.url = '/api/show/MU/endDate'
      } else if ( data.num == 5) {
        this.url = '/api/show/DR/endDate'
      }

      // 카테고리
      // -MU :뮤지컬
      // -CL: 클래식/오페라
        // -DA: 무용/전통예술
        // -FA: 아동/가족
        // -DR: 연극
        // -CO: 콘서트
      // const userdata = data
      return axios.get(url)
    },

    // 선호 태그 별 공연 불러오기
    // getPreferenceTagShow({commit, }){
    //   const url = '/api/show/preferenceTag'
    //   // const userdata = data
    //   axios.get(url)
    //     .then(res => {
    //       commit(res.data)
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    // },


    // getSimilarShow({commit, }){
    //   const url = '/api/show/similar'
    //   // const userdata = data
    //   axios.get(url)
    //     .then(res => {
    //       commit(res.data)
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    // },
    // getCategorySimilarShow({commit, }){
    //   const url = '/api/show/similar/{category}'
    //   // const userdata = data
    //   axios.get(url)
    //     .then(res => {
    //       commit(res.data)
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    // },

    // getNearShow({commit, }){
    //   const url = '/api/show/popular/{category}'
    //   // const userdata = data
    //   axios.get(url)
    //     .then(res => {
    //       commit(res.data)
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    // },

    // getCategoryNearShow({commit, }){
    //   const url = '/api/show/popular/{category}'
    //   // const userdata = data
    //   axios.get(url)
    //     .then(res => {
    //       commit(res.data)
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    // },
  },
  modules: {
  }
})
