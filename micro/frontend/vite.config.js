import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'
import { fileURLToPath } from 'url'

const __filename = fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api/flights': {
        target: 'http://localhost:8081',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api\/flights/, '/flights')
      },
      '/api/hotels': {
        target: 'http://localhost:8082',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api\/hotels/, '/hotels')
      },
      '/api/travelorder': {
        target: 'http://localhost:8083',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api\/travelorder/, '/travelorder')
      }
    }
  },
  test: {
    globals: true,
    environment: 'happy-dom',
    setupFiles: path.resolve(__dirname, './src/setupTests.js'),
  }
})
