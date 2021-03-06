import Vue from 'vue'
import 'knacss/css/knacss.css';
import 'font-awesome/css/font-awesome.css';
import './palette.css';
import './grillade.css';
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
