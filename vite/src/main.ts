import {computed, createApp, onUnmounted, ref} from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/index.ts'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import zhCn from 'element-plus/es/locale/lang/zh-cn'

const app = createApp(App)
import ElementPlus from 'element-plus'

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}


import ECharts from 'vue-echarts'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-plugin-persistedstate'

app.component('v-chart', ECharts)
app.use(ElementPlus, { locale: zhCn })
app.use(createPinia().use(createPersistedState()))
app.use(router)
app.use(Antd)
app.mount('#app')


// 全局监听屏幕宽度
const screenWidth = ref(window.innerWidth);
export const phone_bool = computed(()=>{
    return screenWidth.value < 1200;
})
const updateScreenWidth = ()=> {
    screenWidth.value = window.innerWidth;
}
window.addEventListener('resize', updateScreenWidth);

onUnmounted(() => {
    window.removeEventListener('resize', updateScreenWidth);
})