<script lang="ts" setup>
import { defineProps } from 'vue'
import { Cart } from 'app'
import { ElTag } from 'element-plus'
const props = defineProps<{
  data?: Cart[]
}>()

</script>

<template>
  <div v-if="!props.data?.length">订单无商品</div>
  <div v-else class="space-y-4">
    <div v-for="item in props.data" :key="item.id">
      <el-tag type="primary">商品: {{ item.product?.name }} ￥{{ item.product?.price || 0 }} * {{ item.count }}</el-tag>
    </div>
  </div>
</template>
