import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store";
//完整引入
// 引入ElementUI组件库
import ElementUI from 'element-ui';
// 引入ElementUI全部样式
import 'element-ui/lib/theme-chalk/index.css';
import '../src/assets/gloable.css';
import request from "@/utils/request";
import qs from 'qs'

Vue.config.productionTip = false
//应用ElementUI    small 或 mini
Vue.use(ElementUI, {size:"mini"});
Vue.prototype.request = request;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
