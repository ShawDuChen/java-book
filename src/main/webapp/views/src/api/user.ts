import request from '@/utils/request'
import { User } from 'app'

export const fetchAll = () => {
  return request<User>({
    url: '/user/all',
    method: 'get'
  })
}

export const createUser = (user: Partial<User>) => {
  return request<User>({
    url: '/user/create',
    method: 'post',
    data: {
      model: user
    }
  })
}

export const updateUser = (user: Partial<User>) => {
  return request<User>({
    url: '/user/update',
    method: 'post',
    data: {
      model: user
    }
  })
}

export const deleteUser = (id: number) => {
  return request<User>({
    url: '/user/delete',
    method: 'get',
    params: {
      id
    }
  })
}

export const getUser = (id: number) => {
  return request<User>({
    url: '/user/get',
    method: 'get',
    params: {
      id
    }
  })
}

