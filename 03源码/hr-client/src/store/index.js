import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //导航栏状态
    isCollapse: JSON.parse(sessionStorage.getItem("isCollapse")) || false
  },
  getters: {
    isCollapse: state => state.isCollapse
  },
  mutations: {
    SET_STATUS(state) {
      state.isCollapse = !state.isCollapse;
      sessionStorage.setItem("isCollapse", JSON.stringify(state.isCollapse));
    }
  },
  actions: {},
  modules: {}
});
