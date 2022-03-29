import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token : ''
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
    }

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

    }

  },
  modules: {
  }
})
