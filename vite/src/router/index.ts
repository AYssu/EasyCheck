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
        redirect: {name: 'table'},
        children: [
            {
                path: '/home/table',
                name: 'table',
                component: () => import('@/view/data_table/indexView.vue'),
                title: 'table',
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;