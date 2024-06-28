import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router'
import "@/styles/index.scss"
import ElementPlus from 'element-plus'
import { createPinia } from 'pinia'

createApp(App)
  .use(router)
  .use(ElementPlus)
  .use(createPinia())
  .mount('#app')
