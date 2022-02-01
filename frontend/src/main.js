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

Vue.prototype.$hostname = 'https://wade-339321.ew.r.appspot.com//'
Vue.prototype.$profile = 'https://profile-dot-wade-339321.ew.r.appspot.com/'
Vue.prototype.$main = 'https://main-dot-wade-339321.ew.r.appspot.com/'
Vue.prototype.$recommendation = 'https://recommendations-dot-wade-339321.ew.r.appspot.com/'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
