import {createApp} from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/index.ts'

const app = createApp(App)
import ElementPlus from 'element-plus'

import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-plugin-persistedstate'


app.use(ElementPlus)
app.use(createPinia().use(createPersistedState()))
app.use(router)
app.mount('#app')

