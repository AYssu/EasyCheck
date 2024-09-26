import { createMemoryHistory, createRouter } from 'vue-router'

import AboutView from '@/view/AboutView.vue'

const routes = [
    {
        path: '/', component: AboutView,
        title: 'about'
    },
]

const router = createRouter({
    history: createMemoryHistory(),
    routes,
})

export default router;