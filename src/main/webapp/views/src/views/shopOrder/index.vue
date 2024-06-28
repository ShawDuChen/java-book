<script lang="ts" setup>
import { fetchAll } from '@/api/shopOrder';
import { useBool } from '@/hooks';
import { ShopOrder } from 'app';
import { ElTable, ElTableColumn } from 'element-plus'
import { onMounted, ref } from 'vue';
import OrderProduct from './modules/order-product.vue';

const list = ref<Array<Partial<ShopOrder>>>([])
const { bool: loading } = useBool()

const getList = () => {
  fetchAll().then(res => {
    list.value = res.list || [];
  })
}

const getTotal = (shopOrder: ShopOrder) => {
  return shopOrder.carts?.reduce((prev, curr) => {
    prev += prev + ((curr.product?.price || 0) * curr.count);
    return prev;
  }, 0) ?? 0;
}

onMounted(() => {
  getList()
})

</script>

<template>
  <div v-loading="loading">
    <el-table :data="list" border>
      <el-table-column prop="id" label="订单ID" align="center" />
      <el-table-column prop="userId" label="下单用户" align="center">
        <template #default="{ row }">{{ row.user?.username }}</template>
      </el-table-column>
      <el-table-column prop="carts" label="购买商品" align="center">
        <template #default="{ row }">
          <order-product :data="row.carts" />
        </template>
      </el-table-column>
      <el-table-column prop="total" label="总价" align="center">
        <template #default="{ row }">
          <el-tag type="danger">￥{{ getTotal(row) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createdAt" label="创建时间" align="center" />
      <el-table-column prop="updatedAt" label="更新时间" align="center" />
    </el-table>
  </div>
</template>