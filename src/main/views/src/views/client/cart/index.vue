<script lang="ts" setup>
import { deleteCart, fetchAll, updateCart } from '@/api/cart';
import { createOrder } from '@/api/shopOrder';
import ProductCard from '@/components/product-card.vue';
import { useBool } from '@/hooks';
import { readUser } from '@/utils/cache';
import { Minus, Plus, ShoppingCart } from '@element-plus/icons-vue';
import { Cart, ShopOrder } from 'app';
import { ElMessage, ElMessageBox, ElTable, ElTableColumn, ElTag, ElButton, ElSpace } from 'element-plus'
import { debounce } from 'lodash';
import { computed, onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const { bool: loading, toggle } = useBool();
const user = readUser();
const list = ref<Cart[]>([]);
const router = useRouter();

const fetchList = async () => {
  const res = await fetchAll(user.id);
  list.value = res.list || [];
}

const selections = ref<Cart[]>([]);

const handleCurrentChange = (list: Cart[]) => {
  selections.value = list;
}

const selectTotal = computed(() => {
  return selections.value.reduce((total, item) => {
    return total + item.count * (item.product?.price || 0);
  }, 0).toFixed(2);
})

const remove = (row: Cart) => {
  ElMessageBox.confirm("是否移除该商品？", "提示", {
    type: 'warning'
  }).then(() => {
    toggle();
    deleteCart(row.id).then(() => {
      ElMessage.success('移除成功~');
      fetchList();
    }).finally(() => toggle())
  }).catch(() => {
    ElMessage.info('取消操作~');
  })
}

const getTotal = (row: Cart) => {
  return (row.count * (row.product?.price || 0)).toFixed(2)
}

const createPayOrder = () => {
  ElMessageBox.confirm("确认创建订单，并打开支付窗口吗？", "提示", { type: 'warning' })
    .then(() => {
      confirm()
    }).catch(() => {
      ElMessage.info('已取消创建订单~')
    })
}

const confirm = () => {
  const ids = selections.value.map(item => item.id)
  toggle();
  createOrder(ids, user.id!).then((res) => {
    ElMessage.success('创建订单成功，为您打开付款页面~');
    fetchList()
    toPayOrder(res.data);
  }).finally(() => toggle())
}

const toPayOrder = (order: ShopOrder) => {
  router.push(`/c/pay?id=${order.id}`)
}

const handleCountChange = debounce((row: Cart, offset: number) => {
  toggle()
  updateCart({ ...row, count: row.count + offset}).then(() => {
    fetchList();
  }).finally(() => toggle())
}, 200)

onMounted(() => {
  fetchList();
})
</script>

<template>
  <div v-loading="loading">
    <el-table :data="list" @selection-change="handleCurrentChange">
      <el-table-column type="selection" align="center" />
      <el-table-column prop="product" label="商品详情">
        <template #default="{ row }">
          <ProductCard :data="row.product" :onlyshow="true" />
        </template>
      </el-table-column>
      <el-table-column prop="createdAt" label="加入购物车时间" align="center" />
      <el-table-column prop="count" label="数量" align="center" width="100px">
        <template #default="{ row }">
          <el-space>
            <el-button circle :icon="Minus" size="small" :disabled="row.count === 1" @click="handleCountChange(row, -1)" />
            <el-tag type="danger" effect="dark">{{ row.count }}</el-tag>
            <el-button circle :icon="Plus" size="small" @click="handleCountChange(row, +1)" />
          </el-space>
        </template>
      </el-table-column>
      <el-table-column porp="total" label="总价" align="center">
        <template #default="{ row }">
          <el-tag type="danger" effect="dark">￥{{ getTotal(row) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="action" label="操作" width="80px" align="center">
        <template #default="{ row }">
          <el-button type="danger" link @click="remove(row)">移除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div v-show="selections.length" class="selection">
      <div class="info">
        <h1 class="price">￥ {{ selectTotal }}</h1>
        <el-button
          type="danger"
          size="large"
          :icon="ShoppingCart"
           @click="createPayOrder"
        >提交订单</el-button>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.selection {
  position: fixed;
  bottom: 0;
  left: 0;
  height: 60px;
  width: 100%;
  border-top: 1px solid #dcdfe6;
  background-color: #fff;
  z-index: 9;
  .info {
    padding: 0 16px;
    height: 60px;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .price {
      color: #f56c6c;
    }
  }
}
</style>
