// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vuetify/dist/vuetify.min.css'
import Vuetify from 'vuetify'
import Notifications from 'vue-notification'
import icon from 'vue-awesome/components/Icon'
import 'vue-awesome/icons'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

Vue.component('icon',icon);
Vue.use(Vuetify)
Vue.use(BootstrapVue);
Vue.use(Notifications);
Vue.use(Vuetify, {
  iconfont: 'mdi' // 'md' || 'mdi' || 'fa' || 'fa4'
})

Vue.prototype.$http = axios
Vue.config.productionTip = false
// axios.defaults.baseURL = "/api";


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
