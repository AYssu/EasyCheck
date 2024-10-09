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
                component: () => import('@/view/data_table/index_view.vue'),
                title: '项目数据',
            },
            {
                path: '/home/project_list',
                name: 'project_list',
                component: () => import('@/view/project_manager/project_list.vue'),
                title: '项目列表',
            },
            {
                path: '/home/card_list',
                name: 'card_list',
                component: () => import('@/view/project_manager/card_list.vue'),
                title: '卡密列表',
            },
            {
                path: '/home/user_authentic',
                name: 'user_authentic',
                component: () => import('@/view/project_manager/user_authentic.vue'),
                title: '用户权限',
            },
            {
                path: '/home/user_authority',
                name: 'user_authority',
                component: () => import('@/view/project_manager/user_authority.vue'),
                title: '用户认证',
            },
            {
                path: '/home/user_list',
                name: 'user_list',
                component: () => import('@/view/project_manager/user_list.vue'),
                title: '用户列表',
            },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;