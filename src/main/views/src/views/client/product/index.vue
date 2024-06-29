<script lang="ts" setup>
import { useBool } from '@/hooks';
import { Category, Product } from 'app';
import {
  ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElTag, ElDivider, ElEmpty,
  ElRow, ElCol
} from 'element-plus'
import { computed, onMounted, reactive, ref } from 'vue';
import { fetchAll as fetchCategories } from '@/api/category'
import { fetchAll, fetchHotList, fetchNewList } from '@/api/product';
import { ProductCard } from '@/components'

const { bool: loading, toggle } = useBool()

const categories = ref<Category[]>([]);
const getCategories = async () => {
  const res = await fetchCategories()
  categories.value = res.list || [];
}

const categoryMap = computed(() => {
  return categories.value.reduce((map, item) => {
    map[`${item.id}`] = item.name;
    return map;
  }, {} as Record<string, string>)
})

const queryForm = reactive<Partial<Product>>({})

const list = ref<Product[]>([]);

const getList = async () => {
  toggle()
  const res = await fetchAll(queryForm).finally(() => toggle());
  list.value = res.list || [];
}

const getHots = async () => {
  toggle();
  const res = await fetchHotList().finally(() => toggle());
  list.value = res.list || [];
}

const getNews = async () => {
  toggle();
  const res = await fetchNewList().finally(() => toggle());
  list.value = res.list || [];
}

onMounted(() => {
  getCategories();
  getList();
})
</script>

<template>
  <div v-loading="loading">
    <el-form inline>
      <el-form-item>
        <el-tag type="danger" class="pointer" @click="getHots">热门商品</el-tag>
      </el-form-item>
      <el-form-item>
        <el-tag type="danger" class="pointer" @click="getNews">最新商品</el-tag>
      </el-form-item>
      <el-form-item prop="categoryId">
        <el-select v-model="queryForm.categoryId" placeholder="商品分类" style="width: 200px;" clearable filterable>
          <el-option
            v-for="item in categories"
            :key="item.id"
            :label="`${item.parentId ? categoryMap[item.parentId] + '-' + item.name: item.name}`"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item prop="name">
        <el-input v-model="queryForm.name" placeholder="商品名称" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList">查询</el-button>
      </el-form-item>
    </el-form>
    <el-divider />
    <template v-if="list.length">
      <el-row class="w-full">
        <el-col
          v-for="item in list"
          :key="item.id"
          :xl="3" :lg="4" :md="6" :sm="8" :xs="12" :span="24"
        >
          <product-card :data="item" />
        </el-col>
      </el-row>
    </template>
    <template v-else>
      <el-empty />
    </template>
    <el-divider />
  </div>
</template>
