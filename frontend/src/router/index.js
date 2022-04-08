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

// 카테고리별
import Classic from './category/Classic'
import Concert from './category/Concert'
import Family from './category/Family'
import Musical from './category/Musical'
import Theatre from './category/Theatre'



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
  ...Profile,
  //category
  ...Classic,
  ...Concert,
  ...Family,
  ...Musical,
  ...Theatre,
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  scrollBehavior () {
    return { x: 0, y: 0 }
}
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
      next(from)
    } else{
      next()
    }
  }
})

export default router
