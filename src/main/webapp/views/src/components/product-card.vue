<script lang="ts" setup>
import { defineProps, reactive } from 'vue';
import { Product } from 'app';
import { ElSpace, ElTag, ElCard, ElIcon, ElInputNumber, ElButton, ElMessage } from 'element-plus'
import { ShoppingCart, Check, Close } from '@element-plus/icons-vue'
import { useBool } from '@/hooks';
import { createCart } from '@/api/cart';
import { readUser } from '@/utils/cache';

const props = defineProps<{
  data: Product;
  onlyshow?: boolean;
}>()

const { bool: loading, toggle } = useBool()

const formData = reactive({
  count: 1,
  edit: false,
})

const buyCount = () => {
  formData.edit = true;
  formData.count = 1;
}
const cancelAddToCart = () => {
  formData.edit = false;
  formData.count = 1;
}
const confirmAddToCart = async () => {
  toggle();
  await createCart({
    productId: props.data.id,
    userId: readUser().id,
    count: formData.count,
  }).finally(() => toggle())
  ElMessage.success('添加成功');
  cancelAddToCart();
}
</script>

<template>
  <el-card v-loading="loading">
    <template #header>
      <div class="flex-between">
        <span style="font-size: 20px;font-weight: bold;">{{ data.name }}</span>
        <template v-if="!onlyshow">
          <el-icon v-show="!formData.edit" color="#f56c6c" :size="20" class="pointer" @click="buyCount">
            <shopping-cart />
          </el-icon>
        </template>
      </div>
      <div v-show="formData.edit" class="flex-between" style="margin-top: 6px;">
        <div>
          <el-input-number v-model="formData.count" size="small" :min="1" :max="999" />
        </div>
        <div>
          <el-button type="danger" circle :icon="Close" size="small" @click="cancelAddToCart" />
          <el-button type="success" circle :icon="Check" size="small" @click="confirmAddToCart" />
        </div>
      </div>
    </template>
    <el-space direction="vertical" alignment="start">
      <div>商品描述: {{ data.description }}</div>
      <div>商品分类: {{ data.category?.name || '-' }}</div>
      <div>
        商品销量: <el-tag type="success">{{ data.sellCount || 0 }}</el-tag>
      </div>
      <div>商品单价: <el-tag type="danger">￥{{ data.price || 0 }}</el-tag></div>
    </el-space>
    <div v-show="formData.edit" class="text-right" style="font-size: 24px;color:#f56c6c;">￥{{ (data.price * formData.count).toFixed(2) }}</div>
  </el-card>
</template>
