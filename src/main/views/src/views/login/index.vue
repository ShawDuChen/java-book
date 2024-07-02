<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { FormInstance } from 'element-plus'
import { login } from '@/api/auth';
import { useRouter, RouterLink } from 'vue-router';
import { User } from 'app';
import useStore from '@/store';
import { cacheUser, clearUser, setToken } from '@/utils/cache';

const formRef = ref<FormInstance>()

const formData = ref<Partial<User & { type: string }>>({
  username:'',
  password:'',
  type: "admin"
})

const router = useRouter();

const store = useStore()

const toLogin = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      const data = formData.value;
      login(data, data.type).then((res) => {
        store.updateUser(res.data);
        setToken(res.token!)
        cacheUser(res.data);
        router.push(data.type === "admin" ? '/user' : '/c/dashboard')
      })
    }
  })
}

onMounted(() => {
  clearUser()
})
</script>

<template>
  <div class="container flex-center">
    <el-card header="购物系统登录" style="width: 420px;">
      <el-form ref="formRef" :model="formData" label-position="top">
        <el-form-item prop="username" label="用户名" :rules="[{ required: true, message: '请输入用户名' }]">
          <el-input v-model="formData.username" placeholder="请输入用户名" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item prop="password" label="用户密码" :rules="[{ required: true, message: '请输入用户密码' }]">
          <el-input v-model="formData.password" type="password" placeholder="请输入用户密码" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item prop="type" label="登录平台">
          <el-radio-group v-model="formData.type">
            <el-radio value="admin">管理后台</el-radio>
            <el-radio value="client">购物平台</el-radio>
          </el-radio-group>
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