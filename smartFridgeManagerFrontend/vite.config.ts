import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Replace with your API server URL
        changeOrigin: true, // Required for CORS when targeting a different origin
        secure: false, // If your API server doesn't use HTTPS
        rewrite: (path) => path.replace(/^\/api/, ''), // Remove /api from the request URL
      },
    },
  },
})
