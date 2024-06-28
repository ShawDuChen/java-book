<script lang="ts" setup>
import { ElContainer, ElHeader, ElMain } from 'element-plus';
import ClientHeader from './components/client-header.vue';
import { RouterView } from 'vue-router'
</script>

<template>
  <el-container class="container">
    <el-header class="client-header">
      <client-header />
    </el-header>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>
