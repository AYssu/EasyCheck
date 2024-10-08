import {createApp} from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/index.ts'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
import ElementPlus from 'element-plus'

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
import { use } from "echarts/core";

// 手动引入 ECharts 各模块来减小打包体积
import {
    CanvasRenderer
} from 'echarts/renderers'
import {
    BarChart
} from 'echarts/charts'
import {
    GridComponent,
    TooltipComponent

} from 'echarts/components'

use([
    CanvasRenderer,
    BarChart,
    GridComponent,
    TooltipComponent
]);

import ECharts from 'vue-echarts'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-plugin-persistedstate'

app.component('v-chart', ECharts)
app.use(ElementPlus)
app.use(createPinia().use(createPersistedState()))
app.use(router)
app.mount('#app')

