export default [
    {
        path: '/etiquette/classic',
        name: 'classic',
        component: () => import('@/views/show/etiquette/Classic.vue')
    },
]

// routerlink가 두단어 이상이면 케밥 그리고 소문자
