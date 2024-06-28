import request from '@/utils/request'
import { User } from 'app'

export function login(data: Partial<User>) {
  return request<User>({
    url: '/auth/login',
    method: "post",
    data: {
      model: data
    }
  })
}

export function register(data: Partial<User>) {
  return request({
    url: '/auth/register',
    method: "post",
    data: {
      model: data
    }
  })
}
 