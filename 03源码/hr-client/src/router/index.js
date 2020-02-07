import Vue from "vue";
import VueRouter from "vue-router";
import layout from "../views/layout/layout.vue";

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
      component: () => import("../views/login/index.vue")
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
          component: () => import("../views/console/index.vue")
        },
        {
          path: "/signlog",
          name: "Signlog",
          hidden: false,
          meta: {
            name: "签到记录"
          },
          component: () => import("../views/console/signLog.vue")
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
          component: () => import("../views/user/userList.vue")
        },
        {
          path: "/insetUser",
          name: "InsetUser",
          // hidden: false,
          meta: {
            name: "添加员工"
          },
          component: () => import("../views/user/insertUser.vue")
        },
        {
          path: "/schedulingUser",
          name: "SchedulingUser",
          // hidden: false,
          meta: {
            name: "员工调度"
          },
          component: () => import("../views/user/schedulingUser.vue")
        }
      ]
    },
    {
      path: "/department",
      name: "Department",
      hidden: false,
      meta: {
        name: "部门管理",
        icon: "department"
      },
      component: layout,
      children: [
        {
          path: "/departmentList",
          name: "DepartmentList",
          // hidden: false,
          meta: {
            name: "部门列表"
          },
          component: () => import("../views/department/departmentList.vue")
        },
        {
          path: "/insertDepartment",
          name: "InsertDepartment",
          // hidden: false,
          meta: {
            name: "添加部门"
          },
          component: () => import("../views/department/insertDepartment.vue")
        }
      ]
    },
    {
      path: "/holiday",
      name: "Holiday",
      hidden: false,
      meta: {
        name: "请假管理",
        icon: "holiday"
      },
      component: layout,
      children: [
        {
          path: "/approvalHoliday",
          name: "ApprovalHoliday",
          // hidden: false,
          meta: {
            name: "请假审批"
          },
          component: () => import("../views/holiday/approvalHoliday.vue")
        }
      ]
    },
    {
      path: "/systemConfig",
      name: "SystemConfig",
      hidden: false,
      meta: {
        name: "系统设置",
        icon: "systemConfig"
      },
      component: layout,
      children: [
        {
          path: "/updatePassword",
          name: "UpdatePassword",
          // hidden: false,
          meta: {
            name: "修改密码"
          },
          component: () => import("../views/system/updatePassword.vue")
        }
      ]
    },
    {
      path: "/test",
      name: "Test",
      hidden: false,
      meta: {
        name: "测试",
        icon: "systemConfig"
      },
      component: layout,
      children: [
        {
          path: "/test",
          name: "Test",
          // hidden: false,
          meta: {
            name: "测试"
          },
          component: () => import("../views/Test/test.vue")
        }
      ]
    }
  ]
});
