<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>

<template>
  <div class="container">
    <el-container>
      <el-header></el-header>
      <el-container class="content">
        <el-aside width="200px"></el-aside>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
