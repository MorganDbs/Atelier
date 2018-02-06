// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import interceptors from './plugins/Interceptors'
import BootstrapVue from 'bootstrap-vue'
import Vue2Leaflet from 'vue2-leaflet';


Vue.component('v-map', Vue2Leaflet.Map);
Vue.component('v-tilelayer', Vue2Leaflet.TileLayer);
Vue.component('v-marker', Vue2Leaflet.Marker);

Vue.use(BootstrapVue);

require('../node_modules/bootstrap/dist/css/bootstrap.css')
require('../node_modules/leaflet/dist/leaflet.css')


Vue.config.productionTip = false
Vue.use(interceptors)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  interceptors,
  components: { App },
  template: '<App/>'
})
