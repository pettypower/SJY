import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import commonHead from '@/components/commonHead'
import commonFoot from '@/components/commonFoot'
import index from '@/components/index'
import buy from '@/components/buy'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/commonHead',
      name: 'commonHead',
      component: commonHead
    },
    {
      path: '/commonFoot',
      name: 'commonFoot',
      component: commonFoot
    },
    {
      path: '/index',
      name: 'index',
      component: index
    },
    {
      path: '/buy',
      name: 'buy',
      component: buy
    }
  ]
})
