import {createRouter, createWebHistory} from 'vue-router'

// 统一在这里声明所有路由
const routes = [
    {
        path: '/', // 路由地址
        component: () => import('@/views/Index.vue'), // 对应组件
        meta: { // meta 信息
            title: '首页' // 页面标题
        }
    }
]

// 创建路由
const router = createRouter({
    // 使用 History 模式，基础路径与 Vite 的 base 配置保持一致
    history: createWebHistory(import.meta.env.BASE_URL),
    // routes: routes 的缩写
    routes,
})

// 保存 index.html 中的站点标题，避免路由切换时重复拼接
const siteTitle = document.title

router.afterEach((to, from, failure) => {
    if (failure) return

    document.title = to.meta.title ? `${to.meta.title} - ${siteTitle}` : siteTitle
})

// ES6 模块导出语句，它用于将 router 对象导出，以便其他文件可以导入和使用这个对象
export default router
