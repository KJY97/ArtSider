import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import VueStarRating from 'vue-star-rating'
import 'swiper/css/swiper.css'

Vue.config.productionTip = false
Vue.component('star-rating', VueStarRating.default)
Vue.use(VueAwesomeSwiper)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
