// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import vueSwiper from 'vue-awesome-swiper'
/* 样式的话，我这里有用到分页器，就在全局中引入了样式 */
import 'swiper/dist/css/swiper.css'

Vue.use(vueSwiper)
Vue.prototype.axios = axios
Vue.config.productionTip = false
Vue.prototype.goRoute = function(page) {
  this.$router.push(page)
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  data: function() {
    return {
      // 后台服务器地址，因为调用了跨域，在跨域配置中配置了服务器地址，所以直接使用api代替
      serverSrc: 'api',
      // 当前登录人姓名
      username: '',
      // 当前是否已经登录
      hasEnter: false
    }
  },
  router,
  components: { App },
  template: '<App/>'
})
