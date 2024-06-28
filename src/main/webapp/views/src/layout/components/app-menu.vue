<script lang="ts" setup>
import { ElMenu, ElMenuItem, ElIcon, ElScrollbar } from 'element-plus'
import { UserFilled, Suitcase, Handbag, Sell } from '@element-plus/icons-vue'
import { ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';

const defaultActive = ref('/user')
const route = useRoute();

watchEffect(() => {
  defaultActive.value = route.path;
})

</script>

<template>
  <el-scrollbar>
    <el-menu :default-active="defaultActive" :router="true">
      <el-menu-item index="/user">
        <el-icon><UserFilled /></el-icon>
        <span>用户管理</span>
      </el-menu-item>
      <el-menu-item index="/category">
        <el-icon><Suitcase /></el-icon>
        <span>类别管理</span>
      </el-menu-item>
      <el-menu-item index="/product">
        <el-icon><Handbag /></el-icon>
        <span>商品管理</span>
      </el-menu-item>
      <el-menu-item index="/order">
        <el-icon><Sell /></el-icon>
        <span>订单管理</span>
      </el-menu-item>
    </el-menu>
  </el-scrollbar>
</template>
