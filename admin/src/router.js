import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login'

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
    }]
})