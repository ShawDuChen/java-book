<script lang="ts" setup>
import { useBool } from '@/hooks';
import { ElMessage, FormInstance } from 'element-plus';
import { onMounted, ref } from 'vue';
import { User } from 'app';
import { resetPassword } from '@/api/auth'
import { useRoute, useRouter } from 'vue-router';

const { bool: loading, toggle } = useBool()
const formRef = ref<FormInstance>();
const formData = ref<Partial<User>>({
  id: 0,
  username: '',
  password: ''
});

const findPassword = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      confirm()
    }
  })
}

const route = useRoute();
const router = useRouter();
const confirm = () => {
  toggle()
  resetPassword(formData.value).then(() => {
    toggle()
    ElMessage.success('修改成功~');
    router.push("/loign")
  }).finally(() => toggle())
}

onMounted(() => {
  const query = route.query;
  formData.value.id = parseInt(`${query.id}`);
  formData.value.username = `${query.username}`;
})
</script>

<template>
  <div v-loading="loading" class="container flex-center">
    <el-card header="重置密码" style="width: 420px;">
      <el-form ref="formRef" :model="formData" label-position="top">
        <el-form-item prop="username" label="用户名" :rules="[{ required: true, message: '请输入用户名' }]">
          <el-input v-model="formData.username" disabled placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item prop="password" label="新密码" :rules="[{ required: true, message: '请输入新密码' }]">
          <el-input v-model="formData.password" placeholder="请输入新密码" />
        </el-form-item>
        <el-button type="primary" class="w-full" @click="findPassword">修改密码</el-button>
      </el-form>
    </el-card>
  </div>
</template>
