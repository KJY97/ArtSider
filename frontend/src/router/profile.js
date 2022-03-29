import Profile from '@/views/account/profile/Profile.vue'
import ProfileDefault from '@/views/account/profile/ProfileDefault.vue'
import ProfileMyReviews from '@/views/account/profile/ProfileMyReviews.vue'
import ProfileCalendar from '@/views/account/profile/ProfileCalendar.vue'


export default [
  {
    path: '/profile',
    name: 'Profile',
    redirect: '/profile/my-contents',
    component: Profile,
    children: [
      {
        path: '/profile/my-contents',
        name: 'ProfileDefault',
        component: ProfileDefault
      },
      {
        path: '/profile/my-reviews',
        name: 'ProfileMyReviews',
        component: ProfileMyReviews
      }, 
      {
        path: '/profile/calendar',
        name: 'ProfileCalendar',
        component: ProfileCalendar
      },
    ],
  },
]