<script lang="ts" setup>
import { fetchAll } from '@/api/shopOrder';
import { useBool } from '@/hooks';
import { Product, ShopOrder, User } from 'app';
import { ElTable, ElTableColumn } from 'element-plus'
import { onMounted, ref, reactive } from 'vue';
import { OrderProduct } from '@/components';
import { fetchAll as fetchUserAll } from '@/api/user.ts'
import { useRouter } from 'vue-router';

const list = ref<Array<Partial<ShopOrder>>>([])
const { bool: loading } = useBool()

const getList = () => {
  fetchAll(queryForm.userId).then(res => {
    list.value = res.list || [];
  })
}

const getTotal = (shopOrder: ShopOrder) => {
  return shopOrder.carts?.reduce((prev, curr) => {
    prev += prev + ((curr.product?.price || 0) * curr.count);
    return prev;
  }, 0) ?? 0;
}

const userList = ref<User[]>([])
const getUserList = () => {
  fetchUserAll().then((res) => {
    userList.value = res.list || []
  })
}

const queryForm = reactive<Partial<ShopOrder>>({})

const router = useRouter();
const check = (row: Product) => {
  router.push({
    name: "productDetail",
    params: {
      id: row.id
    }
  })
}

onMounted(() => {
  getUserList();
  getList()
})

</script>

<template>
  <div v-loading="loading">
    <el-form
      :model="queryForm"
      :inline="true"
    >
      <el-form-item prop="userId">
        <el-select v-model="queryForm.userId" placeholder="下单用户" clearable filterable style="width:120px;">
          <el-option v-for="item in userList" :key="item.id" :value="item.id" :label="item.username" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="list" border>
      <el-table-column prop="id" label="订单ID" align="center" width="80px" />
      <el-table-column prop="userId" label="下单用户" align="center">
        <template #default="{ row }">{{ row.user?.username }}</template>
      </el-table-column>
      <el-table-column prop="carts" label="购买商品" align="center">
        <template #default="{ row }">
          <order-product :data="row.carts" @check="check" />
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
      <el-table-column prop="createdAt" label="下单时间" align="center" width="180px" />
      <el-table-column prop="updatedAt" label="订单更新时间" align="center" width="180px" />
    </el-table>
  </div>
</template>