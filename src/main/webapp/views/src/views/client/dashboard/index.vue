<script lang="ts" setup>
import { fetchHotList, fetchNewList } from '@/api/product';
import { useBool } from '@/hooks';
import { Product } from 'app';
import { ElRow, ElCol, ElTag, ElEmpty } from 'element-plus'
import { onMounted, ref } from 'vue';
import { ProductCard } from '@/components'

const { bool: loading, toggle } = useBool();

const hotProducts = ref<Product[]>([]);
const newProducts = ref<Product[]>([]);

const fetchHot = async () => {
  const res = await fetchHotList();
  hotProducts.value = res.list || [];
}
const fetchNew = async () => {
  const res = await fetchNewList();
  newProducts.value = res.list || [];
}

onMounted(() => {
  toggle();
  Promise.all([fetchHot(), fetchNew()]).finally(() => {
    toggle();
  })
})
</script>

<template>
  <div v-loading="loading">
    <el-tag type="danger" size="large" effect="dark">热门商品</el-tag>
    <el-divider />
    <template v-if="hotProducts.length">
      <el-row class="w-full">
        <el-col
          v-for="(item) in hotProducts"
          :key="item.id"
          :xl="3" :lg="4" :md="6" :sm="8" :xs="12" :span="24"
        >
          <product-card :data="item" />
        </el-col>
      </el-row>
    </template>
    <template v-else>
      <el-empty description="暂无数据" />
    </template>
    <el-divider />
    <el-tag type="danger" size="large" effect="dark">最新商品</el-tag>
    <el-divider />
    <template v-if="newProducts.length">
      <el-row class="w-full">
        <el-col
          v-for="item in newProducts"
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
