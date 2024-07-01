import axios from 'axios'
import type { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios'
import { ElMessage } from 'element-plus'
import { getToken } from './cache';
import router from '@/router';

export type ResponseData<T = null> = {
  code: number
  message: string
  data: T
  list?: T[]
  token?: string;
};

console.log(import.meta.env)

const serve = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_URL,
  timeout: 60 * 1000
})

serve.interceptors.request.use(
  (config) => {
    const token = getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

serve.interceptors.response.use(
  (response: AxiosResponse<ResponseData>) => {
    const data = response.data;
    if (data.code !== 200) {
      ElMessage.error(data.message);
      return Promise.reject(data.message);
    }
    return response;
  },
  (error: AxiosError) => {
    const { status, statusText } = error.response || {};
    if (status === 401) {
      ElMessage.error('认证失败，请重新登录')
      router.push('/login');
    } else {
      ElMessage.error(`[${status}]: ${statusText || 'Server Error'}`)
    }
    return Promise.reject(error)
  }
)

function request<T = null>(config: AxiosRequestConfig) {
  return new Promise<ResponseData<T>>((resolve, reject) => {
    serve(config).then(res => {
      resolve(res.data as ResponseData<T>)
    }).catch(err => {
      reject(err)
    })
  })
}

export default request;
