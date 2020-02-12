import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //导航栏状态
    isCollapse: false
  },
  getters: {
    isCollapse: state => state.isCollapse
  },
  mutations: {
    SET_STATUS(state) {
      state.isCollapse = !state.isCollapse;
    }
  },
  actions: {},
  modules: {}
});
