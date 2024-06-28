<script setup lang="ts">
import { RouterView } from 'vue-router'
import { ElContainer, ElHeader, ElAside, ElScrollbar, ElMain } from 'element-plus'
import AppHeader from './components/app-header.vue';
import AppMenu from './components/app-menu.vue';
</script>

<template>
  <div class="container">
    <el-container>
      <el-header class="app-header flex-between">
        <app-header />
      </el-header>
      <el-container class="content">
        <el-aside width="200px" class="app-menu">
          <app-menu />
        </el-aside>
        <el-main>
          <el-scrollbar>
            <router-view />
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
