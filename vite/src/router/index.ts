import { createWebHistory, createRouter } from 'vue-router'

import AboutView from '@/view/AboutView.vue'

const routes = [
    {
        path: "/",
        redirect: {name: 'about'}
    }
    ,
    {
        path: '/about',
        name: 'about',
        component: AboutView,
        title: 'about',
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;