<script lang="ts" setup>
import { House } from '@element-plus/icons-vue';
import { useRouter } from 'vue-router';

const props = defineProps<{
  path: string;
}>()
const router = useRouter();
const navigate = () => {
  router.push(props.path);
}
</script>

<template>
  <el-button type="info" circle :icon="House" @click="navigate" class="fixed-home-button" />
</template>

<style lang="scss">
.fixed-home-button {
  position: fixed;
  bottom: 16px;
  right: 16px;
  z-index: 9;
}
</style>
