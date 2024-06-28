<script setup lang="ts">
import { ElMenu, ElMenuItem } from 'element-plus';
import { HomeFilled, ShoppingCart, Suitcase, Money } from '@element-plus/icons-vue'
import { ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';

const defaultActive = ref('/user')
const route = useRoute();

watchEffect(() => {
  defaultActive.value = route.path;
})

</script>

<template>
  <el-menu mode="horizontal" :default-active="defaultActive" :router="true" :ellipsis="false">
    <el-menu-item index="/c/dashboard">
      <el-icon><home-filled /></el-icon>
      <span>购物平台</span>
    </el-menu-item>
    <el-menu-item index="/c/product">
      <el-icon><suitcase /></el-icon>
      <span>商品列表</span>
    </el-menu-item>
    <el-menu-item index="/c/cart">
      <el-icon><shopping-cart /></el-icon>
      <span>购物车</span>
    </el-menu-item>
    <el-menu-item index="/c/order">
      <el-icon><money /></el-icon>
      <span>我的订单</span>
    </el-menu-item>
  </el-menu>
</template>