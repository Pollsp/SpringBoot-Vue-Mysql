import Vue from 'vue'
import App from './App.vue'

import './plugins/element.js'

import router from './router'

import './assets/icon/iconfont.css'


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')



import VueKinesis from 'vue-kinesis'

Vue.use(VueKinesis)


import request from '@/utils/request'
Vue.prototype.$request=request