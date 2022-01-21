// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
//import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.css'
// or import all icons if you don't care about bundle size
import 'vue-awesome/icons'
import Icon from 'vue-awesome/components/Icon'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.component('v-icon', Icon)
Vue.use(VueAxios, axios)
Vue.config.productionTip = false

Vue.prototype.$hostname = 'http://localhost:8080'
Vue.prototype.$profile = 'http://localhost:8081'
Vue.prototype.$main = 'http://localhost:8089'
Vue.prototype.$recommendation = 'http://localhost:8081'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
