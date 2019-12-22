import Vue from "vue";
import VueRouter from "vue-router";
import layout from "../views/Layout/layout.vue";

Vue.use(VueRouter);

export default new VueRouter({
  routes: [
    {
      path: "/",
      redirect: "login",
      hidden: true,
      meta: {
        name: "主页"
      }
    },
    {
      path: "/login",
      name: "Login",
      hidden: true,
      meta: {
        name: "登录"
      },
      component: () => import("../views/Login/index.vue")
    },
    {
      path: "/console",
      name: "Console",
      redirect: "index",
      hidden: false,
      meta: {
        name: "控制台",
        icon: "console"
      },
      component: layout,
      children: [
        {
          path: "/index",
          name: "Index",
          hidden: false,
          meta: {
            name: "首页"
          },
          component: () => import("../views/Console/index.vue")
        }
      ]
    },
    {
      path: "/user",
      name: "User",
      hidden: false,
      meta: {
        name: "员工管理",
        icon: "user"
      },
      component: layout,
      children: [
        {
          path: "/userList",
          name: "UserList",
          // hidden: false,
          meta: {
            name: "员工列表"
          },
          component: () => import("../views/User/userList.vue")
        }
      ]
    }
  ]
});
