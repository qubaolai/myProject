<template>
  <div id="main-wrap">
    <div class="main-content">
      <div class="content">
        <router-view />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "layoutMain"
};
</script>
<style lang="scss" scoped>
@import "@/styles/config.scss";
#main-wrap {
  height: 100vh;
  position: fixed;
  left: $navMenu;
  top: 75px;
  right: 0;
  bottom: 0;
  border: 30px solid #f7f7f7;
  border-bottom: none;
  box-sizing: border;
}
.open {
  #main-wrap {
    left: $navMenu;
  }
}
.close {
  #main-wrap {
    left: 64px;
  }
}
.main-content {
  width: 100%;
  height: 100%;
  // padding-top: $layoutHeader + 30;
  padding-right: 30px;
  @include webkit(box-sizing, border-box);
  @include webkit(transition, all 0.3s ease 0s);
}
// .open {
//   .main-content {
//     padding-left: $navMenu - 40;
//   }
// }
// .close {
//   .main-content {
//     padding-left: $navMenuMin + 30;
//   }
// }
.content {
  width: 100%;
  height: 100%;
  // padding-top: 20px;
  background-color: #fff;
  @include webkit(box-sizing, border-box);
}
</style>
