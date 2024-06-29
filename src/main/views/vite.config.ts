import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'

const prefix = '/book_war_exploded'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: process.env.NODE_ENV === 'production' ? `${prefix}/views/` : '/',
  resolve: {
    alias: {
      "@": resolve(__dirname, './src')
    }
  },
  server: {
    proxy: {
      '/api': {
        target: `http://localhost:8080${prefix}`,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  },
  build: {
    outDir: '../webapp/views'
  }
})
