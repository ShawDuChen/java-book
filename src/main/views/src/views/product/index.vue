<script lang="ts" setup>
import { createProduct, deleteProduct, fetchAll, updateProduct } from '@/api/product';
import { fetchAll as fetchCategoryAll } from '@/api/category'
import { useBool } from '@/hooks';
import { Category, Product } from 'app';
import {
  ElTable, ElTableColumn, ElButton, ElMessageBox, ElMessage, ElDialog,
  ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElInputNumber,
  FormInstance, ElTag
} from 'element-plus'
import { onMounted, reactive, ref } from 'vue';
import { cloneDeep } from 'lodash'
import { useRouter } from 'vue-router';

const list = ref<Array<Partial<Product>>>([])
const { bool: loading, toggle } = useBool()

const queryForm = reactive<Partial<Product>>({})

const getList = () => {
  fetchAll(queryForm).then(res => {
    list.value = res.list || [];
  })
}

const handleEdit = (row: Partial<Product>)=> {
  formData.value = cloneDeep(row);
  toggleVisible();
  formRef.value?.resetFields()
}
const handleDelete = (id: number) => {
  ElMessageBox.confirm('确定删除吗？', '提示', {type:'warning'})
    .then(() => {
      toggle()
      deleteProduct(id).then(() => {
        ElMessage.success('删除成功~');
        getList();
      }).finally(() => toggle())
    })
    .catch(() => {
      ElMessage.info('取消操作~');
    })
}

const { bool: visible, toggle: toggleVisible } = useBool();
const formData = ref<Partial<Product>>({})
const formRef = ref<FormInstance>();
const submit = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      formData.value.id ? update(formData.value) : create(formData.value)
    }
  })
}

const update = (data: Partial<Product>) => {
  toggle()
  updateProduct(data).then(() => {
    ElMessage.success('更新成功~');
    toggleVisible();
    getList()
  }).finally(() => toggle())
}
const create = (data: Partial<Product>) => {
  toggle()
  createProduct(data).then(() => {
    ElMessage.success('创建成功~');
    toggleVisible();
    getList()
  }).finally(() => toggle())
}

const categories = ref<Category[]>([])

const fetchCategorys = () => {
  fetchCategoryAll().then(res => {
    categories.value = res.list || []
  })
}

const router = useRouter();

const toDetial = (row: Product) => {
  router.push({
    name: 'productDetail',
    params: {
      id: row.id
    }
  })
}

onMounted(() => {
  getList()
  fetchCategorys()
})

</script>

<template>
  <div v-loading="loading">
    <el-form inline>
      <el-form-item prop="name">
        <el-input v-model="queryForm.name" placeholder="商品名" clearable />
      </el-form-item>
      <el-form-item prop="categoryId">
        <el-select v-model="queryForm.categoryId" placeholder="商品分类" clearable style="width: 120px;">
          <el-option v-for="item in categories" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList">查询</el-button>
        <el-button type="success" @click="handleEdit({ price: 1.00 })">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="list" border>
      <el-table-column prop="name" label="商品名称" align="center" />
      <el-table-column prop="code" label="商品标识" align="center" />
      <el-table-column prop="price" label="商品单价" align="center">
        <template #default="{ row }">
          <el-tag type="danger">￥{{ row.price }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述" align="center" />
      <el-table-column prop="categoryId" label="所属类别" align="center">
        <template #default="{ row }">
          <el-tag type="primary">{{ row.category?.name }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="sellCount" label="商品销量" align="center" />
      <el-table-column prop="score" label="评分" align="center" />
      <el-table-column prop="createdAt" label="创建时间" align="center" />
      <el-table-column prop="updatedAt" label="更新时间" align="center" />
      <el-table-column prop="action" label="操作" align="center" min-width="120px">
        <template #default="{ row }">
          <el-button type="success" link @click="toDetial(row)">详情</el-button>
          <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" link @click="handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <el-dialog v-model="visible" :title="formData.id ? '编辑商品' : '添加商品'" width="30%">
    <el-form ref="formRef":model="formData" label-position="top">
      <el-form-item prop="name" label="商品名" :rules="[{ required: true, message: '请输入商品名' }]">
        <el-input v-model="formData.name" :disabled="!!formData.id" placeholder="请输入商品名" />
      </el-form-item>
      <el-form-item prop="code" label="商品标识" :rules="[{ required: true, message: '请输入商品标识' }]">
        <el-input v-model="formData.code" :disabled="!!formData.id" placeholder="请输入商品标识" />
      </el-form-item>
      <el-form-item prop="price" label="商品单价" :rules="[{ required: true, type: 'number', message: '请输入商品单价' }]">
        <el-input-number v-model="formData.price" :min="0.01" :max="999999" :step="0.01" />
      </el-form-item>
      <el-form-item prop="description" label="描述" :rules="[{ required: true, message: '请输入描述' }]">
        <el-input v-model="formData.description" placeholder="请输入描述" type="textarea" :rows="3" :maxlength="255" show-word-limit />
      </el-form-item>
      <el-form-item prop="categoryId" label="所属类别" :rules="[{ required: true, type: 'number', message: '请选择所属类别'}]">
        <el-select v-model="formData.categoryId" placeholder="请选择所属类别">
          <el-option v-for="item in categories" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="text-right">
      <el-button :loading="loading" @click="toggleVisible">取 消</el-button>
      <el-button :loading="loading" type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>