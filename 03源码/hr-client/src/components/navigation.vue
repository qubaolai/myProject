<template>
  <div id="nav-wrap">
    <div id="hrImg">
      <svg-icon iconClass="hrImg" class="hrImg" />
      <label>HR医院</label>
    </div>
    <el-menu
      default-active="1-4-1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      :collapse="isCollapse"
      background-color="transparent"
      text-color="#fff"
      router
    >
      <!-- 一级菜单 -->
      <template v-for="(item, index) in routers">
        <el-submenu v-if="!item.hidden" :key="item.id" :index="index + ''">
          <!-- 子菜单 -->
          <template slot="title">
            <!-- <i :class="item.meta.icon"></i> -->
            <!-- 自定义图标组件 -->
            <svg-icon :iconClass="item.meta.icon" :className="item.meta.icon" />
            <span slot="title">{{ item.meta.name }}</span>
          </template>
          <el-menu-item-group>
            <el-menu-item
              style="text-indent: 36px;"
              v-for="subItem in item.children"
              :key="subItem.id"
              :index="subItem.path"
              >{{ subItem.meta.name }}</el-menu-item
            >
          </el-menu-item-group>
        </el-submenu>
      </template>
    </el-menu>
  </div>
</template>
<script>
export default {
  name: "navigation",
  data: function() {
    //声明路由对象
    return {
      isCollapse: false,
      routers: this.$router.options.routes
    };
  },
  methods: {
    handleOpen() {
      // console.log(key, keyPath);
      console.log(this.routers);
    },
    handleClose() {
      // console.log(key, keyPath);
    }
  }
};
</script>
<style lang="scss" scoped>
@import "@/styles/config.scss";
#nav-wrap {
  position: fixed;
  top: 0;
  left: 0;
  width: $navMenu;
  height: 100vh;
  background-color: #344a5f;
}
svg {
  font-size: 18px;
  margin-left: 14px;
  margin-right: 10px;
  padding-bottom: 4px;
}
#hrImg {
  margin-top: 20px;
  margin-left: 35px;
  margin-bottom: 30px;
  color: #fff;
  label {
    margin-top: -30px;
  }
  svg {
    margin-bottom: -10px;
    font-size: 30px;
  }
}
</style>
