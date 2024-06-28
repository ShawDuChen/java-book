import { createRouter, createWebHashHistory } from 'vue-router'
import Layout from '@/layout/index.vue'
import { registerGuard } from './guard';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: Layout,
      redirect: '/user',
      children: [
        {
          path: 'user',
          component: () => import('@/views/user/index.vue')
        },
        {
          path: 'category',
          component: () => import('@/views/category/index.vue')
        },
        {
          path: 'product',
          component: () => import('@/views/product/index.vue')
        },
        {
          path: 'order',
          component: () => import('@/views/order/index.vue')
        },
        {
          path: 'profile',
          component: () => import('@/views/user/profile.vue')
        }
      ]
    },
    {
      path: '/login',
      component: () => import("@/views/login/index.vue")
    },
    {
      path: "/register",
      component: () => import("@/views/login/register.vue")
    },
    {
      path: "/forgot",
      component: () => import("@/views/login/forgot.vue")
    }
  ]
})

registerGuard(router);

export default router;
