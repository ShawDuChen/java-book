import request from '@/utils/request'
import { User } from 'app'

export function login(data: Partial<User>, loginType?: string) {
  return request<User>({
    url: '/auth/login',
    method: "post",
    data: {
      model: data,
      loginType,
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
 
export function verify(data: Partial<User>) {
  return request<User>({
    url: '/auth/verify',
    method: "post",
    data: {
      model: data
    }
  })
}

export function resetPassword(data: Partial<User>) {
  return request({
    url: '/auth/resetPassword',
    method: "post",
    data: {
      model: data
    }
  })
}
