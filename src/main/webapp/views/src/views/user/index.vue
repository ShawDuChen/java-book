<script lang="ts" setup>
import { createUser, deleteUser, fetchAll, updateUser } from '@/api/user';
import { useBool } from '@/hooks';
import { User } from 'app';
import {
  ElTable, ElTableColumn, ElTag, ElButton, ElMessageBox, ElMessage, ElDialog,
  ElForm, ElFormItem, ElInput, ElSelect, ElOption,
  FormInstance
} from 'element-plus'
import { onMounted, ref } from 'vue';
import { cloneDeep } from 'lodash'

const list = ref<Array<Partial<User>>>([])
const { bool: loading, toggle } = useBool()

const getList = () => {
  fetchAll().then(res => {
    list.value = res.list || [];
  })
}

const handleEdit = (row: Partial<User>)=> {
  formData.value = cloneDeep(row);
  toggleVisible();
}
const handleDelete = (id: number) => {
  ElMessageBox.confirm('确定删除吗？', '提示', {type:'warning'})
    .then(() => {
      toggle()
      deleteUser(id).then(() => {
        ElMessage.success('删除成功~');
        getList();
      }).finally(() => toggle())
    })
    .catch(() => {
      ElMessage.info('取消操作~');
    })
}

const { bool: visible, toggle: toggleVisible } = useBool();
const formData = ref<Partial<User>>({})
const formRef = ref<FormInstance>();
const submit = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      formData.value.id ? update(formData.value) : create(formData.value)
    }
  })
}

const update = (data: Partial<User>) => {
  toggle()
  updateUser(data).then(() => {
    ElMessage.success('更新成功~');
    toggleVisible();
    getList()
  }).finally(() => toggle())
}
const create = (data: Partial<User>) => {
  toggle()
  createUser(data).then(() => {
    ElMessage.success('创建成功~');
    toggleVisible();
    getList()
  }).finally(() => toggle())
}

onMounted(() => {
  getList()
})

const queryForm = ref<Partial<User>>({})

</script>

<template>
  <div v-loading="loading">
    <el-form inline>
      <el-form-item prop="username">
        <el-input v-model="queryForm.username" placeholder="用户名" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList">查询</el-button>
        <el-button type="success" @click="handleEdit({})">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="list" border>
      <el-table-column prop="username" label="用户名" align="center" />
      <el-table-column prop="password" label="密码" align="center">
        <template #default>******</template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号" align="center" />
      <el-table-column prop="email" label="邮箱" align="center" />
      <el-table-column prop="address" label="住址" align="center" />
      <el-table-column prop="gender" label="性别" align="center">
        <template #default="{ row }">
          <el-tag :type="row.gender === 1 ? 'primary' : 'danger'">{{
            row.gender === 1 ? "男" : "女"
          }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" align="center" >
        <template #default="{ row }">
          <el-tag :type="row.status === 1 ? 'success' : 'danger'">{{
            row.status === 1 ? "正常" : "禁用"
          }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createdAt" label="创建时间" align="center" />
      <el-table-column prop="updatedAt" label="更新时间" align="center" />
      <el-table-column prop="action" label="操作" align="center">
        <template #default="{ row }">
          <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" link @click="handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <el-dialog v-model="visible" :title="formData.id ? '编辑用户' : '添加用户'" width="30%">
    <el-form ref="formRef":model="formData" label-position="top">
      <el-form-item prop="username" label="用户名" :rules="[{ required: true, message: '请输入用户名' }]">
        <el-input v-model="formData.username" :disabled="!!formData.id" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item prop="password" label="密码" :rules="[{ required: true, message: '请输入密码' }]">
        <el-input v-model="formData.password" type="password" :disabled="!!formData.id" placeholder="请输入密码" />
      </el-form-item>
      <el-form-item prop="phone" label="手机号" :rules="[{ required: true, message: '请输入手机号' }]">
        <el-input v-model="formData.phone" placeholder="请输入手机号" :maxlength="11" />
      </el-form-item>
      <el-form-item prop="email" label="邮箱" :rules="[{ required: true, type: 'email', message: '请输入邮箱' }]">
        <el-input v-model="formData.email" placeholder="请输入邮箱" />
      </el-form-item>
      <el-form-item prop="gender" label="性别" :rules="[{ required: true, type: 'number', message: '请选择性别' }]">
        <el-select v-model="formData.gender" placeholder="请选择性别">
          <el-option label="男" :value="1" />
          <el-option label="女" :value="0" />
        </el-select>
      </el-form-item>
      <el-form-item prop="address" label="住址">
        <el-input v-model="formData.address" placeholder="请输入住址" />
      </el-form-item>
      <el-form-item prop="status" label="状态">
        <el-select v-model="formData.status" placeholder="请选择状态">
          <el-option label="正常" :value="1" />
          <el-option label="禁用" :value="0" />
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="text-right">
      <el-button :loading="loading" @click="toggleVisible">取 消</el-button>
      <el-button :loading="loading" type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>