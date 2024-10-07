import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/index.ts'

// 导入 element plus 样式和组件
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'

const app = createApp(App)

app.use(ElementPlus)
app.use(router)
app.mount('#app')
