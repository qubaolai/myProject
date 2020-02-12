import Vue from "vue";
import VueRouter from "vue-router";
import layout from "../views/layout/layout.vue";

Vue.use(VueRouter);
export const fixedRouter = [
  {
    path: "/",
    name: "Login",
    hidden: true,
    meta: {
      name: "登录",
      roles: ["0", "1"]
    },
    component: () => import("../views/login/index.vue")
  }
];
// 需要权限判断展示的路由
export const permissionRouter = [
  {
    path: "/console",
    name: "Console",
    redirect: "index",
    hidden: false,
    meta: {
      name: "控制台",
      roles: ["0", "1"],
      icon: "console"
    },
    component: layout,
    children: [
      {
        path: "/index",
        name: "Index",
        hidden: false,
        meta: {
          name: "首页",
          roles: ["0", "1"]
        },
        component: () => import("../views/console/index.vue")
      },
      {
        path: "/signlog",
        name: "Signlog",
        hidden: false,
        meta: {
          name: "签到记录",
          roles: ["0", "1"]
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
          name: "员工列表",
          roles: ["0", "1"]
        },
        component: () => import("../views/user/userList.vue")
      },
      {
        path: "/insetUser",
        name: "InsetUser",
        // hidden: false,
        meta: {
          name: "添加员工",
          roles: ["0"]
        },
        component: () => import("../views/user/insertUser.vue")
      },
      {
        path: "/schedulingUser",
        name: "SchedulingUser",
        // hidden: false,
        meta: {
          name: "员工调度",
          roles: ["0"]
        },
        component: () => import("../views/user/schedulingUser.vue")
      },
      {
        path: "/moveLog",
        name: "MoveLog",
        // hidden: false,
        meta: {
          name: "调度记录",
          roles: ["0", "1"]
        },
        component: () => import("../views/user/moveLog.vue")
      }
    ]
  },
  {
    path: "/department",
    name: "Department",
    hidden: false,
    meta: {
      name: "部门管理",
      roles: ["0", "1"],
      icon: "department"
    },
    component: layout,
    children: [
      {
        path: "/departmentList",
        name: "DepartmentList",
        // hidden: false,
        meta: {
          name: "部门列表",
          roles: ["0", "1"]
        },
        component: () => import("../views/department/departmentList.vue")
      },
      {
        path: "/insertDepartment",
        name: "InsertDepartment",
        // hidden: false,
        meta: {
          name: "添加部门",
          roles: ["0"]
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
      roles: ["0"],
      icon: "holiday"
    },
    component: layout,
    children: [
      {
        path: "/approvalHoliday",
        name: "ApprovalHoliday",
        // hidden: false,
        meta: {
          name: "请假审批",
          roles: ["0"]
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
      roles: ["0", "1"],
      icon: "systemConfig"
    },
    component: layout,
    children: [
      {
        path: "/updatePassword",
        name: "UpdatePassword",
        // hidden: false,
        meta: {
          name: "修改密码",
          roles: ["0", "1"]
        },
        component: () => import("../views/system/updatePassword.vue")
      }
    ]
  }
];
export default new VueRouter({
  routes: fixedRouter
});
