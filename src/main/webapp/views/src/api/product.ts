import request from '@/utils/request'
import { Product } from 'app'

export const fetchAll = () => {
  return request<Product>({
    url: '/product/all',
    method: 'get'
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

