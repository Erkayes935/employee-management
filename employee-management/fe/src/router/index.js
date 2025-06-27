import { createRouter, createWebHistory } from 'vue-router'

import Home from '../views/Home.vue'
import Form from '../views/Form.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/add', component: () => import('../views/Form.vue')},
  { path: '/edit/:id', component: () => import('../views/Form.vue')}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
