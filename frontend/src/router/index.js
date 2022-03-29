import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/home/Home.vue'
import Intro from '../views/home/Intro.vue'
import Home from './home'
// import Intro from './intro'
import Main from './main'

// 에티켓
import Etiquette from './etiquette'

import Classic from './etiquetteClassic'
import KTraditionalMusic from './etiquetteKTraditionalMusic'
import Musical from './etiquetteMusical'
import Opera from './etiquetteOpera'
import Theatre from './etiquetteTheatre'

/// 에티켓 디테일
import EtiquetteDetail from './etiquetteDetail'
// import Clap from './'
// import Phone from './'
// import Talk from './'
// import Time from './'
// import Food from './'

import ShowDetail from './showDetail'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Intro',
    component: Intro
  },
  // ...Intro,
  ...Etiquette,
  ...Main,
  ...Classic,
  ...KTraditionalMusic,
  ...Musical,
  ...Opera,
  ...Theatre,

  ...EtiquetteDetail,
  // ...Clap,
  // ...Phone,
  // ...Talk,
  // ...Time,
  // ...Food,
  ...Home,
  ...ShowDetail,
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
