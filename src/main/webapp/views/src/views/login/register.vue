<script lang="ts" setup>
import { FormInstance } from 'element-plus';
import { ref } from 'vue';

const formRef = ref<FormInstance>();
const formData = ref({
  username: '',
  password: '',
  address: '',
  email: '',
  phone: '',
  gender: undefined
})
</script>

<template>
  <div class="container flex-center">
    <el-card header="注册用户" style="width: 420px;">
      <el-form ref="formRef" :model="formData" label-position="top">
        <el-form-item prop="username" label="用户名" :rules="[{ required: true, message: '请输入用户名' }]">
          <el-input v-model="formData.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item prop="password" label="密码" :rules="[{ required: true, message: '请输入密码' }]">
          <el-input v-model="formData.password" placeholder="请输入密码" show-password />
        </el-form-item>
        <el-form-item prop="phone" label="手机号" :rules="[{ required: true, message: '请输入手机号' }]">
          <el-input v-model="formData.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item prop="email" label="邮箱" :rules="[{ required: true, message: '请输入邮箱' }]">
          <el-input v-model="formData.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item prop="gender" label="性别" :rules="[{ required: true, message: '请选择性别', trigger: 'change', type: 'number' }]">
          <el-select v-model="formData.gender" placeholder="请选择性别">
            <el-option label="男" :value="1" />
            <el-option label="女" :value="0" />
          </el-select>
        </el-form-item>
        <el-form-item prop="address" label="家庭住址">
          <el-input v-model="formData.address" placeholder="请输入家庭住址" />
        </el-form-item>
        <el-button type="primary" class="w-full">注册</el-button>
      </el-form>
    </el-card>
  </div>
</template>
