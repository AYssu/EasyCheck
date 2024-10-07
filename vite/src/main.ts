import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/index.ts'

import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-plugin-persistedstate'
import locale from 'element-plus/dist/locale/zh-cn.js';

// 导入 element plus 样式和组件
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'

const app = createApp(App)

app.use(ElementPlus, { locale })
app.use(createPinia().use(createPersistedState()))
app.use(router)
app.mount('#app')
