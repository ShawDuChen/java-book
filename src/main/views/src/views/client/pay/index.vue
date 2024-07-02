<script lang="ts" setup>
import { getShopOrder, payShopOrder } from '@/api/shopOrder';
import { ShopOrder } from 'app';
import { computed, onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { ElCard, ElMessage, ElSpace, ElTag, ElEmpty } from 'element-plus';
import QRCode from 'qrcode';
import { useBool } from '@/hooks';

const { bool: loading, toggle } = useBool()

const route = useRoute();
const router = useRouter();

const shopOrder = ref<Partial<ShopOrder>>({})
const paid = ref<boolean>(false)

const fetchOrder = (id: string) => {
  toggle()
  getShopOrder(parseInt(id)).then((res) => {
    shopOrder.value = res.data;
    paid.value = !!res.data.paid
  }).finally(() => toggle())
}

const total = computed(() => {
  const carts = shopOrder.value.carts || [];
  return carts.reduce((pre, cur) => {
    return pre + cur.count * (cur.product?.price || 0);
  }, 0).toFixed(2)
})

const cartList = computed(() => {
  const carts = shopOrder.value.carts || [];
  return carts.map(cart => ({
    name: cart.product?.name,
    price: cart.product?.price,
    count: cart.count,
    id: cart.id
  }))
})

const qrCodeUrl = ref('');
const createQrcode = () => {
  QRCode.toDataURL(`${location.origin}/#/pay?id=${route.query.id}`, { errorCorrectionLevel: 'H' }).then((url) => {
    qrCodeUrl.value = url;
  })
}

const payConfirm = async (success: boolean) => {
  if (success) {
    toggle()
    await payShopOrder(shopOrder.value.id!).finally(() => toggle())
    ElMessage.success('支付成功')
    router.push('/c/order')
    return;
  }
  ElMessage.error('支付失败')
}

onMounted(() => {
  createQrcode();
  fetchOrder(`${route.query.id}`)
})

</script>

<template>
  <div v-loading="loading">
    <template v-if="paid">
      <el-empty description="该订单已支付成功" />
    </template>
    <template v-else>
      <el-card class="pay">
        <template #header>
          <h1 class="price">￥{{ total }}</h1>
        </template>
        <div class="price text-center">请扫描下方二维码进行支付</div>
        <div class="qrcode">
          <img v-show="qrCodeUrl" :src="qrCodeUrl" alt="qrcode" />
        </div>
        <div class="cart-list">
          <el-space>
            <el-tag
              v-for="item in cartList"
              :key="item.id"
              type="primary"
            >{{ item.name }}({{ item.count }}) - ￥{{ item.price }}</el-tag>
          </el-space>
        </div>
        <div class="text-right" style="margin-top: 12px">
          <el-button type="danger" @click="payConfirm(false)">支付失败</el-button>
          <el-button type="primary" @click="payConfirm(true)">支付成功</el-button>
        </div>
      </el-card>
    </template>
  </div>
</template>

<style scoped lang="scss">
.pay {
  margin: 0 auto;
  width: 480px;
  .price {
    color: #f56c6c;
  }
  .qrcode {
    width: 300px;
    height: 300px;
    margin: 0 auto;
    img {
      width: 100%;
      height: 100%;
    }
  }
}
</style>

