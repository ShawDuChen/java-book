import request from '@/utils/request'
import { Category } from 'app'

export const fetchAll = (name?: string) => {
  return request<Category>({
    url: '/category/all',
    method: 'get',
    params: { name }
  })
}

export const createCategory = (category: Partial<Category>) => {
  return request<Category>({
    url: '/category/create',
    method: 'post',
    data: {
      model: category
    }
  })
}

export const updateCategory = (category: Partial<Category>) => {
  return request<Category>({
    url: '/category/update',
    method: 'post',
    data: {
      model: category
    }
  })
}

export const deleteCategory = (id: number) => {
  return request<Category>({
    url: '/category/delete',
    method: 'get',
    params: {
      id
    }
  })
}

export const getCategory = (id: number) => {
  return request<Category>({
    url: '/category/get',
    method: 'get',
    params: {
      id
    }
  })
}

