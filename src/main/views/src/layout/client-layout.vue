<script lang="ts" setup>
import { ElContainer, ElHeader, ElMain } from 'element-plus';
import ClientHeader from './components/client-header.vue';
import { RouterView } from 'vue-router'
import { HomeButton } from '@/components'
import { readUser } from '@/utils/cache';

const user = readUser();
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
  <home-button v-if="!!user.isAdmin" path="/user" />
</template>
