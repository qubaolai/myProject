import Vue from "vue";
import svg from "./svgIcon.vue";
//自定义全局组件
Vue.component("svg-icon", svg);
const req = require.context("./svg", false, /\.svg$/); // 正则里面表示结尾，读取结尾为.svg的文件
// es6
const requireAll = requireContext => {
  return requireContext.keys().map(requireContext);
};
requireAll(req);
