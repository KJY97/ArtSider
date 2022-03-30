export default [
    {
        path: '/etiquette/clap',
        name: 'clap',
        component: () => import('@/views/show/etiquette/etiquetteDetail/Clap.vue')
    },
    {
        path: '/etiquette/covid19',
        name: 'covid-19',
        component: () => import('@/views/show/etiquette/etiquetteDetail/Covid19.vue')
    },
    {
        path: '/etiquette/food',
        name: 'food',
        component: () => import('@/views/show/etiquette/etiquetteDetail/Food.vue')
    },
    {
        path: '/etiquette/phone',
        name: 'phone',
        component: () => import('@/views/show/etiquette/etiquetteDetail/Phone.vue')
    },
    {
        path: '/etiquette/talk',
        name: 'talk',
        component: () => import('@/views/show/etiquette/etiquetteDetail/Talk.vue')
    },
    {
        path: '/etiquette/time',
        name: 'time',
        component: () => import('@/views/show/etiquette/etiquetteDetail/Time.vue')
    },
]