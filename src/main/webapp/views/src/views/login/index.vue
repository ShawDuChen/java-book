<script lang="ts" setup>
import { ref } from 'vue'
import { FormInstance } from 'element-plus'
import { login } from '@/api/auth';
import { useRouter, RouterLink } from 'vue-router';

const formRef = ref<FormInstance>()

const formData = ref<{ username: string; password: string }>({
  username:'shaw',
  password:'shawduchen'
})

const router = useRouter();

const toLogin = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      login(formData.value).then(() => {
        router.push('/user')
      })
    }
  })
}

</script>

<template>
  <div class="container flex-center">
    <el-card header="购物系统登录" style="width: 420px;">
      <el-form ref="formRef" :model="formData" label-position="top">
        <el-form-item prop="username" label="用户名" :rules="[{ required: true, message: '请输入用户名' }]">
          <el-input v-model="formData.username" placeholder="请输入用户名" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item prop="password" label="用户密码" :rules="[{ required: true, message: '请输入用户密码' }]">
          <el-input v-model="formData.password" type="password" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="w-full" @click="toLogin">登录</el-button>
        </el-form-item>
        <div class="flex-between">
          <router-link to="/forgot">忘记密码？</router-link>
          <router-link to="/register">注册账号</router-link>
        </div>
      </el-form>
    </el-card>
  </div>
</template>