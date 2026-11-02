import {createApp} from 'vue'
import App from './App.vue'

import router from "@/router/index.js";
import './main.css'

const app = createApp(App)

// 应用路由
app.use(router)

app.mount('#app')
