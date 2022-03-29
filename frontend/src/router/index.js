import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/home/Home.vue'
import Intro from '../views/home/Intro.vue'
import Home from './home'
// import Intro from './intro'
import Main from './main'
import test from '../views/swiperTest.vue'

// 에티켓
import Etiquette from './etiquette'
import Classic from './etiquettes/etiquetteClassic'
import KTraditionalMusic from './etiquettes/etiquetteKTraditionalMusic'
import Musical from './etiquettes/etiquetteMusical'
import Opera from './etiquettes/etiquetteOpera'
import Theatre from './etiquettes/etiquetteTheatre'

/// 에티켓 디테일
import EtiquetteDetail from './etiquettes/etiquetteDetail'
// import Clap from './etiquetteDetail/Clap.vue'
// import Phone from './'
// import Talk from './'
// import Time from './'
// import Food from './'

// 추천 목록
import Popular from './recommand/popular'

import ShowDetail from './showDetail'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Intro',
    component: Intro
  },
  {
    path: '/test',
    name: 'test',
    component: test
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
  ...Popular
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
