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

    preferencePosters(state){
      return state.preferencePosters
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
        data: data
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
        data: data
      })
    },
    // 관람 후기 목록 조회
    getShowReviews({state}, data){
      state
      const url = `/api/show/reviews/${data.id}?page=${data.num}`
      return axios.get(url)
    },

    // 유저 아이디 불러오기
    getUserId({state}){
      state
      return state.userInfo.userId
    },
    // 기대평 제거
    deleteShowExpectations({state},expectationId){
      const url = `/api/show/expectations/${expectationId}`
      return axios.delete(url, { 
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },
    // 기대평 수정
    putShowExpectations({state}, data){
      state
      const url = `/api/show/expectations/${data.expectationId}`
      return axios.put(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
        data: data,
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
        data: data,
      })
    },
    // 기대평 목록 조회
    getShowExpectations({state}, data){
      state
      const url = `/api/show/expectations/${data.id}`
      return axios.get(url)
    },
    // 연관 공연 추가
    addRelatedShow({state}, data){
      state
      const url = `/api/${data.id}/hashTag`
      return axios.get(url)
    },
    // 조회한 공연 추가
    addLookUp({state}, data){
      state
      const url = `/api/show/${data.id}`
      return axios.post(url, {
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
      state
      const url = `/api/users/${inputEmail}`
      axios.get(url)
      .then((res) => {
        if(res.data.emailCheck){
          alert('이메일 중복검사 통과')
          return true
        } else{
          alert('이메일 중복검사 실패')
          return false
        }
      })
      .catch(() => {
        alert('이메일 중복검사 실패')
        return false
      })
    },

    createUser({dispatch}, data){
      const userData = data.userData
      const signUpVue = data.this
      const url = '/api/users'
      axios.post(url, {...userData})
      .then(() => {
        dispatch('getToken', {userEmail: userData.userEmail, password: userData.password})
      })
      .then(() => {
        alert('회원가입 성공!')
        signUpVue.confirmationNumber = ''
        signUpVue.inputNumber = ''
        signUpVue.step = 1
        signUpVue.$emit('close-sign-up')
      })
      .catch(()=>{
        alert('이메일 인증 실패')
        signUpVue.confirmationNumber = ''
        signUpVue.inputNumber = ''
        signUpVue.step = 1
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

    getMyPreference({commit, state}){
      const url = '/api/users/profile/preference'
      axios.get(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        }
      })
      .then((res) => {
        commit('SET_MY_PREFERENCE', res.data.items)
      })
      .catch((err) => {
        console.log(err)
      })
    },

    // 내가 조회한 공연 목록 조회
    getMyHistory({commit, state}){
        const url = '/api/users/profile/watchHistory'
        axios.get(url, {
          headers: {
            Authorization : `Bearer ${state.token}`
          }
        })
        .then((res) => {
          commit('SET_MY_HISTORY', res.data.items)
        })
    },

    // 유저 정보 얻어오기
    getUserInfo({commit, state}){
      const url = '/api/users/profile'
      axios.get(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        }
      })
      .then((res) => {
        commit('SET_USER_INFO', res.data)
      })
      .catch(() => {
        console.log('error: get profile info')
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
    setUserImage({dispatch, state}, image){
      const url = '/api/users/profile/photo'
      const headers = {
        'Content-Type': 'multipart/form-data',
        'Authorization' : `Bearer ${state.token}`
      }
      
      let formData = new FormData()
      formData.append('multipart', image)
      
      axios.post(url, formData, {
        headers: headers
      })
      .then(()=>{
        // 유저 정보 갱신
        dispatch('getUserInfo')
      })
    },

    // 프로필 이미지 소스 얻어오기
    getUserImage({commit, state}){
      if(state.profileImg){
        const url = `/api/users/profile/photo/${state.profileImg}`
        axios.get(url, {
          headers:{
            Authorization : `Bearer ${state.token}`
          }
        })
        .then((res) => {
          commit('SET_USER_IMG_SRC', res.data.profileImg)
        })
      }
    },
        // 인기 공연 불러오기
        getPopularShow({ state }){
          state
          const url = '/api/show/popular/'
          // const userdata = data
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
