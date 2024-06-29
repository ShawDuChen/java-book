<script lang="ts" setup>
import { useBool } from '@/hooks';
import { ElMessage, FormInstance } from 'element-plus';
import { ref } from 'vue';
import { User } from 'app';
import { verify } from '@/api/auth'
import { useRouter } from 'vue-router';

const { bool: loading, toggle } = useBool()
const formRef = ref<FormInstance>();
const formData = ref<Partial<User>>({
  username: '',
  phone: ''
});

const findPassword = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      confirm()
    }
  })
}

const router = useRouter();
const confirm = () => {
  toggle()
  verify(formData.value).then(({ data }) => {
    toggle()
    ElMessage.success('验证已通过');
    router.push(`/reset?id=${data.id}&username=${data.username}`)
  }).finally(() => toggle())
}
</script>

<template>
  <div v-loading="loading" class="container flex-center">
    <el-card header="找回密码" style="width: 420px;">
      <el-form ref="formRef" :model="formData" label-position="top">
        <el-form-item prop="username" label="用户名" :rules="[{ required: true, message: '请输入用户名' }]">
          <el-input v-model="formData.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item prop="phone" label="手机号" :rules="[{ required: true, message: '请输入手机号' }]">
          <el-input v-model="formData.phone" placeholder="请输入预留的手机号" :maxlength="11" />
        </el-form-item>
        <el-button type="primary" class="w-full" @click="findPassword">找回密码</el-button>
      </el-form>
    </el-card>
  </div>
</template>
