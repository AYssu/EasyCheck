import { createWebHistory, createRouter } from 'vue-router'


const routes = [
    {
        path: "/",
        redirect: {name: 'about'}
    }
    ,
    {
        path: '/about',
        name: 'about',
        component: () => import('@/view/AboutView.vue'),
        title: 'about',
    },
    {
        path: '/home',
        name: 'home',
        component: () => import('@/view/HomeView.vue'),
        title: 'home',
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;