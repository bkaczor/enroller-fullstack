import Vue from 'vue';
import App from './App.vue';
// dodanie vue-resource do projektu
import VueResource from "vue-resource";

// uzycie jakiejs biblioteki
Vue.use(VueResource);
Vue.http.options.root = '/api';
new Vue({
    el: '#app',
    render: h => h(App)
});
