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
      const token = window.localStorage.getItem("token");
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
    Message.error(error);
    // 对响应错误做点什么
  }
);
// function setDept(data) {
//   const dept = data["dept"];
//   // const position = data["option"];
//   formData.departments = [];
//   formData.options = [];
//   const department = {
//     value: "",
//     name: ""
//   };
//   for (let i = 0; i < dept.length; i++) {
//     // formData.departments[i] = data[i].name;
//     department.value = dept[i].id;
//     department.name = dept[i].name;
//     formData.departments.push(department);
//   }
//   // for (let i = 0; i < position.length; i++) {
//   //   formData.options[i].value = position[i].id;
//   //   formData.options[i].name =
//   // }
// }
export default intercept;
