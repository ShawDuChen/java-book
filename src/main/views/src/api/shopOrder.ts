import request from '@/utils/request'
import { ShopOrder } from 'app'

export const fetchAll = (userId?: number) => {
  return request<ShopOrder>({
    url: '/shopOrder/all',
    method: 'get',
    params: { userId }
  })
}

export const createShopOrder = (shopOrder: Partial<ShopOrder>) => {
  return request<ShopOrder>({
    url: '/shopOrder/create',
    method: 'post',
    data: {
      model: shopOrder
    }
  })
}

export const updateShopOrder = (shopOrder: Partial<ShopOrder>) => {
  return request<ShopOrder>({
    url: '/shopOrder/update',
    method: 'post',
    data: {
      model: shopOrder
    }
  })
}

export const deleteShopOrder = (id: number) => {
  return request<ShopOrder>({
    url: '/shopOrder/delete',
    method: 'get',
    params: {
      id
    }
  })
}

export const getShopOrder = (id: number) => {
  return request<ShopOrder>({
    url: '/shopOrder/get',
    method: 'get',
    params: {
      id
    }
  })
}

export const createOrder = (ids: number[], userId: number) => {
  return request<ShopOrder>({
    url: '/shopOrder/createOrder',
    method: 'post',
    data: {
      ids,
      userId,
    }
  })
}

export const payShopOrder = (id: number) => {
  return request<ShopOrder>({
    url: '/shopOrder/pay',
    method: 'get',
    params: {
      id
    }
  })
}
