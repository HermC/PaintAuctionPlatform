import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import router from './router'

Vue.config.productionTip = false;

new Vue({
  router,
  template: '<App/>',
  render: h => h(App),
}).$mount('#app');
