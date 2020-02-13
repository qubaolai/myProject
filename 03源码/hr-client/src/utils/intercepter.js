import axios from "axios";
import router from "@/router";
import { Message } from "element-ui";
import { setData } from "@/store/data.js";
const BASEURL = process.env.NODE_ENV === "production" ? "" : "/api";
//创建axios 赋给变量intercept
const intercept = axios.create({
  baseURL: BASEURL,
  timeout: 10000
});
// 添加请求拦截器
intercept.interceptors.request.use(
  function(config) {
    // 在发送请求之前做些什么
    //如果不是登录请求,在请求头添加token
    const url = config.url;
    if (url !== "/user/login") {
      const token = window.sessionStorage.getItem("token");
      if (token !== null) {
        config.headers["token"] = token;
      } else {
        config.headers["token"] = "";
      }
    }
    return config;
  },
  function(error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

// 添加响应拦截器
intercept.interceptors.response.use(
  function(response) {
    const data = response.data;
    if (data.code === 500) {
      Message.error("系统异常!");
    } else if (data.msg === "initPage") {
      setData(data.data);
    } else if (data.code === 302) {
      router.push({ name: "Login" });
    } else {
      //返回response
      return response;
    }
  },
  function(error) {
    debugger;
    // location.replace("/");
    // 对响应错误做点什么
    if (error.response.status === 500) {
      //请求状态500
      router.push({ name: "Login" });
      Message.error("无法连接服务器");
    }
  }
);
export default intercept;
