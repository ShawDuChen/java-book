<script lang="ts" setup>
import { readUser } from '@/utils/cache';
import { User } from 'app';
import { ElRow, ElCol, ElCard, ElForm, ElFormItem, ElInput, ElSelect, ElOption } from 'element-plus'
import { ref } from 'vue';

const formData = ref<Partial<User>>(readUser())

</script>

<template>
  <el-row>
    <el-col :lg="12" :md="18" :sm="24">
      <el-card header="用户信息">
        <el-form :model="formData" label-position="top">
          <el-form-item prop="username" label="用户名">
            <el-input v-model="formData.username" disabled placeholder="请输入用户名" />
          </el-form-item>
          <el-form-item prop="password" label="密码">
            <el-input v-model="formData.password" type="password"disabled placeholder="请输入密码" />
          </el-form-item>
          <el-form-item prop="email" label="邮箱">
            <el-input v-model="formData.email" disabled placeholder="请输入邮箱" />
          </el-form-item>
          <el-form-item prop="phone" label="手机号">
            <el-input v-model="formData.phone" disabled placeholder="请输入手机号" />
          </el-form-item>
          <el-form-item prop="gender" label="性别">
            <el-select v-model="formData.gender" disabled placeholder="请选择性别">
              <el-option label="男" :value="1" />
              <el-option label="女" :value="0" />
            </el-select>
          </el-form-item>
          <el-form-item prop="address" label="住址">
            <el-input v-model="formData.address" disabled placeholder="请输入住址" />
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
  </el-row>
</template>
