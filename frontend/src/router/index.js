import Vue from 'vue'
import VueRouter from 'vue-router'
import Intro from '../views/home/Intro.vue'
import Home from './home'
import Main from './main'

// 에티켓
import Etiquette from './etiquette'

// 추천 목록

import ShowDetail from './showDetail'
import Profile from './profile'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Intro',
    component: Intro
  },
  ...Etiquette,
  ...Main,
  ...Home,
  ...ShowDetail,
  //profile
  ...Profile
  
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


// router 네비게이션 전역 가드
router.beforeEach((to, from, next) => {
  const accessToken = localStorage.getItem('accessToken')
  if (to.name === 'Intro'){
    if(accessToken){
      next({name: 'Home'})
    } else{
      next()
    }
  } else{
    if(!accessToken){
      alert('로그인이 필요합니다.')
      // next(from)
      next()
    } else{
      next()
    }
  }
})

export default router
