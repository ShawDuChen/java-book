<script lang="ts" setup>
import { fetchAll } from '@/api/shopOrder';
import { useBool } from '@/hooks';
import { readUser } from '@/utils/cache';
import { ShopOrder } from 'app';
import { onMounted, ref } from 'vue';
import { ElTable, ElTableColumn } from 'element-plus'
import { OrderProduct } from '@/components';
import { useRouter } from 'vue-router';

const { bool: loading, toggle } = useBool()
const user = readUser();
const list = ref<ShopOrder[]>([]);

const fetchList = () => {
  toggle()
  fetchAll(user.id).then((res) => {
    list.value = res.list || [];
  }).finally(() => toggle())
}

const getTotal = (shopOrder: ShopOrder) => {
  return shopOrder.carts?.reduce((prev, curr) => {
    prev += prev + ((curr.product?.price || 0) * curr.count);
    return prev;
  }, 0) ?? 0;
}

const router = useRouter();
const toPay = (shopOrder: ShopOrder) => {
  router.push(`/c/pay?id=${shopOrder.id}`)
}

onMounted(() => {
  fetchList();
})

</script>

<template>
  <div v-loading="loading">
    <el-table :data="list" border>
      <el-table-column prop="id" label="订单ID" align="center" width="80px" />
      <el-table-column prop="carts" label="购买商品" align="center">
        <template #default="{ row }">
          <order-product :data="row.carts" />
        </template>
      </el-table-column>
      <el-table-column prop="total" label="总价" align="center" width="120px">
        <template #default="{ row }">
          <el-tag type="danger">￥{{ getTotal(row) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="paid" label="已付款" align="center" width="80px">
        <template #default="{ row }">
          <el-tag :type="row.paid ? 'success' : 'danger'">{{  row.paid ? '是' : '否' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createdAt" label="订单创建时间" align="center" width="180px" />
      <el-table-column prop="updatedAt" label="付款时间" align="center" width="180px">
        <template #default="{ row }">
          <span v-if="row.paid">{{ row.updatedAt }}</span>
          <el-button v-else type="success" link @click="toPay(row)">去付款</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
