import request from '@/utils/request'
import { Product } from 'app'

export const fetchAll = (params?: Partial<Product>) => {
  return request<Product>({
    url: '/product/all',
    method: 'get',
    params: params
  })
}

export const createProduct = (product: Partial<Product>) => {
  return request<Product>({
    url: '/product/create',
    method: 'post',
    data: {
      model: product
    }
  })
}

export const updateProduct = (product: Partial<Product>) => {
  return request<Product>({
    url: '/product/update',
    method: 'post',
    data: {
      model: product
    }
  })
}

export const deleteProduct = (id: number) => {
  return request<Product>({
    url: '/product/delete',
    method: 'get',
    params: {
      id
    }
  })
}

export const getProduct = (id: number) => {
  return request<Product>({
    url: '/product/get',
    method: 'get',
    params: {
      id
    }
  })
}

export const fetchNewList = (page = 1, limit = 20) => {
  return request<Product>({
    url: '/product/list',
    method: 'get',
    params: {
      page,
      limit
    }
  })
}

export const fetchHotList = () => {
  return request<Product>({
    url: '/product/hotList',
    method: 'get'
  })
}
