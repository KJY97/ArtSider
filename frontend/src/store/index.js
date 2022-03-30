import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)
axios.defaults.baseURL = 'http://localhost:8080'

export default new Vuex.Store({
  state: {
    token : '',
    myContents: [],

    preferencePosters: [],
    historyPosters : [],

    userInfo: {},
  },

  getters: {
    loginStatus(state){
      return !!state.token
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
    }
  },

  actions: {
    // 관람 후기 제거
    deleteShowReview({state}, reviewId){
      state
      const url = `/api/show/reviews/${reviewId}`
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
    getShowReviews({state}, id){
      state
      const url = `/api/show/reviews/${id}`
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
    getShowExpectations({state}, id){
      state
      const url = `/api/show/expectations/${id}`
      return axios.get(url)
    },
    // 연관 공연 추가
    addRelatedShow({state}, id){
      state
      const url = `/api/${id}/hashTag`
      return axios.get(url)
    },
    // 조회한 공연 추가
    addLookUp({state}, id){
      state
      const url = `/api/show/${id}`
      return axios.post(url, {
        headers: {
          Authorization : `Bearer ${state.token}`
        },
      })
    },

    // 티켓사이트 이동 & 선호 목록 추가
    goTicketSite({state}, id){
      state
      const url = `/api/show/${id}/preference`
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
    }



  },
  modules: {
  }
})
