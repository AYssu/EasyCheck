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
        meta: {
            keepAlive: false,
            title: '关于',
        }
    },
    {
        path: '/home',
        name: 'home',
        component: () => import('@/view/HomeView.vue'),
        meta: {
            keepAlive: false,
            title: '主页',

        },
        redirect: {name: 'table'},
        children: [
            {
                path: '/home/table',
                name: 'table',
                component: () => import('@/view/data_table/index_view.vue'),
                meta: {
                    keepAlive: false,
                    title: '项目数据',
                },
            },
            {
                path: '/home/project_list',
                name: 'project_list',
                component: () => import('@/view/project_manager/project_list.vue'),
                meta: {
                    keepAlive: true,
                    title: '项目列表',
                },
            },
            {
                path: '/home/user_list',
                name: 'user_list',
                component: () => import('@/view/project_manager/user_list.vue'),
                meta: {
                    keepAlive: false,
                    title: '用户列表',
                },
            },
            {
                path: '/home/manage/',
                name: 'manage_menu',
                meta: {
                    keepAlive: false,
                    title: '项目管理',
                },
                children: [

                    {
                        path: '/home/manage/card_list',
                        name: 'card_list',
                        component: () => import('@/view/project_manager/card_list.vue'),
                        meta: {
                            keepAlive: false,
                            title: '卡密列表',
                        },
                    },
                    {
                        path: '/home/manage/user_authentic',
                        name: 'user_authentic',
                        component: () => import('@/view/project_manager/user_authentic.vue'),
                        meta: {
                            keepAlive: false,
                            title: '用户认证',
                        },
                    },
                    {
                        path: '/home/manage/user_authority',
                        name: 'user_authority',
                        component: () => import('@/view/project_manager/user_authority.vue'),
                        meta: {
                            keepAlive: false,
                            title: '用户权限',
                        },
                    },
                ]
            },

        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;