import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import user from '@/components/user'
import hello from '@/components/HelloWorld'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(Router)
Vue.use(ElementUI)

export default new Router({
  routes: [
    
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/',
      name: 'user',
      component: user
    },
    {
      path: '/',
      name: 'hello',
      component: hello
    },
  ]
})
