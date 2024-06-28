<script lang="ts" setup>
import { createCategory, deleteCategory, fetchAll, updateCategory } from '@/api/category';
import { useBool } from '@/hooks';
import { Category } from 'app';
import {
  ElTable, ElTableColumn, ElButton, ElMessageBox, ElMessage, ElDialog,
  ElForm, ElFormItem, ElInput, ElSelect, ElOption,
  FormInstance
} from 'element-plus'
import { computed, onMounted, ref, reactive } from 'vue';
import { cloneDeep } from 'lodash'

const list = ref<Array<Partial<Category>>>([])
const { bool: loading, toggle } = useBool()

const queryForm = reactive<Partial<Category>>({})

const getList = () => {
  fetchAll(queryForm.name).then(res => {
    list.value = res.list || [];
  })
}

const selectableCategories = computed(() => list.value.filter(item => item.parentId === 0))
const categoryMap = computed(() => list.value.reduce((prev, curr) => {
  prev[`${curr.id}`] = curr.name
  return prev;
}, {} as Record<string,string | undefined>))

const handleEdit = (row: Partial<Category>)=> {
  formData.value = cloneDeep(row);
  toggleVisible();
  formRef.value?.resetFields()
}
const handleDelete = (id: number) => {
  ElMessageBox.confirm('确定删除吗？', '提示', {type:'warning'})
    .then(() => {
      toggle()
      deleteCategory(id).then(() => {
        ElMessage.success('删除成功~');
        getList();
      }).finally(() => toggle())
    })
    .catch(() => {
      ElMessage.info('取消操作~');
    })
}

const { bool: visible, toggle: toggleVisible } = useBool();
const formData = ref<Partial<Category>>({})
const formRef = ref<FormInstance>();
const submit = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      formData.value.id ? update(formData.value) : create(formData.value)
    }
  })
}

const update = (data: Partial<Category>) => {
  toggle()
  updateCategory(data).then(() => {
    ElMessage.success('更新成功~');
    toggleVisible();
    getList()
  }).finally(() => toggle())
}
const create = (data: Partial<Category>) => {
  toggle()
  createCategory(data).then(() => {
    ElMessage.success('创建成功~');
    toggleVisible();
    getList()
  }).finally(() => toggle())
}

onMounted(() => {
  getList()
})


</script>

<template>
  <div v-loading="loading">
    <el-form inline>
      <el-form-item prop="name">
        <el-input v-model="queryForm.name" placeholder="类别名" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList">查询</el-button>
        <el-button type="success" @click="handleEdit({})">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="list" border>
      <el-table-column prop="name" label="类别名称" align="center" />
      <el-table-column prop="code" label="类别标识" align="center" />
      <el-table-column prop="description" label="描述" align="center" />
      <el-table-column prop="parentId" label="所属类别" align="center">
        <template #default="{ row }">
          <el-tag :type="row.parentId === 0 ? 'success' : 'primary'">{{ row.parentId === 0 ? '一级类别' : categoryMap[row.parentId] }}</el-tag>
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
  <el-dialog v-model="visible" :title="formData.id ? '编辑类别' : '添加类别'" width="30%">
    <el-form ref="formRef":model="formData" label-position="top">
      <el-form-item prop="name" label="类别名" :rules="[{ required: true, message: '请输入类别名' }]">
        <el-input v-model="formData.name" :disabled="!!formData.id" placeholder="请输入类别名" />
      </el-form-item>
      <el-form-item prop="code" label="类别标识" :rules="[{ required: true, message: '请输入类别标识' }]">
        <el-input v-model="formData.code" :disabled="!!formData.id" placeholder="请输入类别标识" />
      </el-form-item>
      <el-form-item prop="description" label="描述" :rules="[{ required: true, message: '请输入描述' }]">
        <el-input v-model="formData.description" placeholder="请输入描述" type="textarea" :rows="3" :maxlength="255" show-word-limit />
      </el-form-item>
      <el-form-item prop="parentId" label="所属类别" :rules="[{ required: true, type: 'number', message: '请选择所属类别'}]">
        <el-select v-model="formData.parentId" placeholder="请选择所属类别">
          <el-option label="一级类别" :value="0" />
          <el-option v-for="item in selectableCategories" :key="item.id" :label="item.name" :value="item.id!" />
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="text-right">
      <el-button :loading="loading" @click="toggleVisible">取 消</el-button>
      <el-button :loading="loading" type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>