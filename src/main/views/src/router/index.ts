import { createRouter, createWebHashHistory } from 'vue-router'
import Layout from '@/layout/index.vue';
import ClientLayout from '@/layout/client-layout.vue';
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
          name: "productDetail",
          path: 'productDetail/:id',
          component: () => import("@/views/product/detail.vue")
        },
        {
          path: 'shopOrder',
          component: () => import('@/views/shopOrder/index.vue')
        },
        {
          path: 'profile',
          component: () => import('@/views/user/profile.vue')
        }
      ]
    },
    {
      path: '/c',
      component: ClientLayout,
      redirect: '/c/dashboard',
      children: [
        {
          path: 'dashboard',
          component: () => import('@/views/client/dashboard/index.vue')
        },
        {
          path: "product",
          component: () => import("@/views/client/product/index.vue")
        },
        {
          path: "order",
          component: () => import("@/views/client/order/index.vue")
        },
        {
          path: "cart",
          component: () => import("@/views/client/cart/index.vue")
        },
        {
          path: 'pay',
          component: () => import('@/views/client/pay/index.vue')
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
    },
    {
      path: '/reset',
      component: () => import("@/views/login/reset.vue")
    }
  ]
})

registerGuard(router);

export default router;
