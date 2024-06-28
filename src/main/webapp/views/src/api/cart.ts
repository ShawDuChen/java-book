import request from '@/utils/request'
import { Cart } from 'app'

export const fetchAll = () => {
  return request<Cart>({
    url: '/cart/all',
    method: 'get'
  })
}

export const createCart = (cart: Partial<Cart>) => {
  return request<Cart>({
    url: '/cart/create',
    method: 'post',
    data: {
      model: cart
    }
  })
}

export const updateCart = (cart: Partial<Cart>) => {
  return request<Cart>({
    url: '/cart/update',
    method: 'post',
    data: {
      model: cart
    }
  })
}

export const deleteCart = (id: number) => {
  return request<Cart>({
    url: '/cart/delete',
    method: 'get',
    params: {
      id
    }
  })
}

export const getCart = (id: number) => {
  return request<Cart>({
    url: '/cart/get',
    method: 'get',
    params: {
      id
    }
  })
}


export const createOrder = (ids: number[], userId: number) => {
  return request<Cart>({
    url: '/cart/createOrder',
    method: 'post',
    data: {
      ids,
      userId,
    }
  })
}
