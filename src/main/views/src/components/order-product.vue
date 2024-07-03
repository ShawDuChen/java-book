<script lang="ts" setup>
import { defineProps } from 'vue'
import { Cart } from 'app'
import { ElTag, ElEmpty } from 'element-plus'
const props = defineProps<{
  data?: Cart[]
}>()
const $emit = defineEmits(["check"])
</script>

<template>
  <el-empty v-if="!props.data?.length" />
  <div v-else class="space-y-4">
    <div v-for="item in props.data" :key="item.id" @click="$emit('check', item.product)">
      <el-tag type="primary">{{ item.product?.name }} ￥{{ item.product?.price || 0 }} * {{ item.count }}</el-tag>
    </div>
  </div>
</template>
