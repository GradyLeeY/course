import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login'
import Admin from './views/admin'
import Welcome from './views/admin/welcome'
import Chapter from './views/admin/chapter'

Vue.use(Router);
export default new Router({
    //mode 一般有两种格式 1 history 2 hash(前面会有#/不美观)
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
        path: '*',
        redirect: "/login"
    },{
        path: '/login',
        component: Login
    },{
        path: '/admin',
        component: Admin,
        children: [{
            path: 'welcome',
            component: Welcome
        },{
            path: 'chapter',
            component: Chapter
        }]
    }]
})