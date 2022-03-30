import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token : '',
    myContents: [],
  },

  getters: {
    loginStatus(){
      const token = JSON.parse(localStorage.getItem('userInfo')).accessToken
      return !!token
    }
  },

  mutations: {
    SET_MY_TOKEN(state, token){
      state.token = token
    },

    SET_MY_CONTENTS(state, posters){
      state.myContents = posters
    },
  },

  actions: {
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

    getToken({commit}, inputData){
      const url = '/api/auth/login'
      axios.post(url, {...inputData})
      .then((res) => {
        localStorage.setItem('userInfo', JSON.stringify(res.data))
        commit('SET_MY_TOKEN')
      })

    },

    getMyContents({commit}, token){
      const url = '/api/users/profile/preference'
      axios.get(url, {
        headers: {
          Authorization : `Bearer ${token}`
        }
      })
      .then((res) => {
        commit('SET_MY_CONTENTS', res.data.items)
      })
      .catch((err) => {
        console.log(err)
      })
    }

  },
  modules: {
  }
})
