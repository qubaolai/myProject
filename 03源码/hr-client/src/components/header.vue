<template>
  <div id="header-wrap">
    <div class="pull-left header-icon">
      <svg-icon iconClass="menu" class="menu" />
    </div>
    <div class="pull-right">
      <div class="user-info pull-left">
        <img src="@/assets/images/face.jpg" alt />
        {{ user.username }}
      </div>
      <div class="header-icon pull-left" @click="exit()">
        <svg-icon iconClass="logout" class="logout" />
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import { logout } from "@/api/user/logout.js";
export default {
  data: () => {
    return {
      //页面显示内容start
      user: {
        username: "",
        userRole: ""
      }
      //页面显示内容end
    };
  },
  mounted() {
    const user = JSON.parse(sessionStorage.getItem("user"));
    if (user === null) {
      this.$message({
        message: "请登录!",
        type: "warning"
      });
      router.push({ name: "Login" });
    }
    this.user.username = user.name;
    this.user.userRole = user.role;
  },
  methods: {
    exit() {
      logout().then(response => {
        const data = response.data;
        if (data.code === 200) {
          window.sessionStorage.clear();
          router.push({ name: "Login" });
        } else {
          this.$message({
            message: data.msg,
            type: "warning"
          });
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
@import "@/styles/config.scss";
#header-wrap {
  position: fixed;
  top: 0;
  left: $navMenu;
  right: 0;
  height: 75px;
  background-color: #fff;
  box-shadow: 0 3px 16px 0 rgba(0, 0, 0, 0.1);
  line-height: 75px;
}
.header-icon {
  padding: 0 32px;
  svg {
    margin-bottom: -8px;
    font-size: 25px;
    cursor: pointer;
  }
}
.user-info {
  height: 100%;
  padding: 0 32px;
  border-right: 1px solid #c5c3c3;
  + .header-icon {
    padding: 0 28px;
  }
  img {
    display: inline-block;
    margin-bottom: -12px;
    margin-right: 18px;
    border-radius: 50px;
  }
}
</style>
