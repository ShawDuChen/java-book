<script lang="ts" setup>
import { getProduct } from '@/api/product';
import { useBool } from '@/hooks';
import { Product, Comment, Rating } from 'app';
import { ElCard, ElTable, ElTableColumn, ElRow, ElCol } from 'element-plus'
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { productComments } from '@/api/comment'
import { productRatings } from '@/api/rating'

const { bool: loading, toggle } = useBool();

const product = ref<Partial<Product>>({});
const comments = ref<Comment[]>([]);
const ratings = ref<Rating[]>([])

const route = useRoute();

onMounted(() => {
  toggle()
  const productId = parseInt(`${route.params.id}`);
  Promise.all([
    getProduct(productId).then(res => {
      product.value = res.data;
    }),
    productComments(productId).then(res => {
      comments.value = res.list || []
    }),
    productRatings(productId).then(res => {
      ratings.value = res.list || []
    })
  ]).finally(() => toggle())
})
</script>

<template>
  <div v-loading="loading">
    <el-card>
      <template #header>
        <h1>{{product.name + `(${product.code})`}}</h1>
      </template>
      <el-row :gutter="20">
        <el-col :span="24" :md="12" :lg="8" class="lh-12">
          单价：￥{{product.price}}
        </el-col>
        <el-col :span="24" :md="12" :lg="8" class="lh-12">
          描述：{{product.description}}
        </el-col>
        <el-col :span="24" :md="12" :lg="8" class="lh-12">
          所属类别：{{product.category?.name}}
        </el-col>
        <el-col :span="24" :md="12" :lg="8" class="lh-12">
          销量：{{product.sellCount}}
        </el-col>
        <el-col :span="24" :md="12" :lg="8" class="lh-12">
          评分：{{product.score}}
        </el-col>
        <el-col :span="24" :md="12" :lg="8" class="lh-12">
          上架时间：{{product.createdAt}}
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="24" :lg="12">
          <h3>商品评论({{comments.length}})</h3>
          <el-table :data="comments" border>
            <el-table-column prop="content" label="评论内容" />
            <el-table-column prop="userId" label="评论人">
              <template #default="{row}">
                {{ row.user?.username }}
              </template>
            </el-table-column>
            <el-table-column prop="createdAt" label="评论时间" />
          </el-table>
        </el-col>
        <el-col :span="24" :lg="12">
          <h3>商品评分({{ratings.length}})</h3>
          <el-table :data="ratings" border>
            <el-table-column prop="score" label="评分" />
            <el-table-column prop="userId" label="评分人">
              <template #default="{row}">
                {{ row.user?.username }}
              </template>
            </el-table-column>
            <el-table-column prop="createdAt" label="评分时间" />
          </el-table>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>
