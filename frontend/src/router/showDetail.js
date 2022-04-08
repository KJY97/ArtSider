export default [
  {
    path: '/show-detail/:showId',
    name: 'ShowDetail',
    component: () => import('@/views/show/ShowDetail.vue')
  }
]