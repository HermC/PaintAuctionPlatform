import Vue from 'vue'
import Router from 'vue-router'
import Index from '../components/Index'
import Passport from "../components/Passport";
import Gallery from "../components/Gallery";
import Manage from "../components/Manage";
import User from "../components/User";

Vue.use(Router);

const routes = [
    {
        path: '/',
        name: 'index',
        component: Index,
        children: [
            {
                path: '/',
                name: 'gallery',
                component: Gallery,
            }, {
                path: '/user',
                name: 'user',
                component: User,
            }
        ]
    }, {
        path: '/passport',
        name: 'passport',
        component: Passport,
    }, {
        path: '/manage',
        name: 'manage',
        component: Manage
    }
];

const router = new Router({routes: routes});
// const cookies = require('js-cookie');

router.beforeEach((to, from, next) => {
    // if (to.path !== '/passport' && !cookies('token')) {
    // next('/passport')
    // } else {
    next()
    // }
});

export default router
