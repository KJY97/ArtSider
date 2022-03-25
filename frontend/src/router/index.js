import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from './home'
import ShowDetail from './showdetail'

Vue.use(VueRouter)

const routes = [
  ...Home,
  ...ShowDetail,
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
