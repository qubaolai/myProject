<template>
  <div>
    <el-tooltip content="Bottom center" placement="bottom" effect="light">
      <div slot="content">
        <el-image
          style="width: 100px; height: 100px"
          :src="url"
          :preview-src-list="srcList"
        >
        </el-image>
        <el-link type="primary">修改密码</el-link>
        <el-link type="primary">更换头像</el-link>
      </div>
      <img src="@/assets/images/face.jpg" alt="" />
    </el-tooltip>
  </div>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
      url: "@/assets/images/face.jpg",
      srcList: ["@/assets/images/face.jpg", "@/assets/images/face.jpg"]
    };
  }
};
</script>
