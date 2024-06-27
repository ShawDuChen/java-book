<script lang="ts" setup>
import { FormInstance } from 'element-plus';
import { ref } from 'vue';


const formRef = ref<FormInstance>();
const formData = ref({
  username: '',
  phone: ''
});

</script>

<template>
  <div class="container flex-center">
    <el-card header="找回密码" style="width: 420px;">
      <el-form ref="formRef" :model="formData" label-position="top">
        <el-form-item prop="username" label="用户名" :rules="[{ required: true, message: '请输入用户名' }]">
          <el-input v-model="formData.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item prop="phone" label="手机号" :rules="[{ required: true, message: '请输入手机号' }]">
          <el-input v-model="formData.phone" placeholder="请输入预留的手机号" />
        </el-form-item>
        <el-button type="primary" class="w-full">找回密码</el-button>
      </el-form>
    </el-card>
  </div>
</template>
