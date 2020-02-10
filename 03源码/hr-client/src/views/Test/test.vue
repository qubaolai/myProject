<template>
  <!-- <div>
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
  </div> -->
  <div>
    <div>
      <el-upload
        class="avatar-uploader"
        action="api/file/uploadFile"
        name="headPhoto"
        :data="{ path: 'D:/img' }"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </div>
    <div class="demo-image" style="margin-left: 200px;">
      <div class="block">
        <el-image style="width: 100px; height: 100px" :src="url"></el-image>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: "",
      url:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.url = URL.createObjectURL(file.raw);
      console.log(this.url);
    },
    beforeAvatarUpload(file) {
      let isJPG = false;
      if (file.type === "image/jpeg" || file.type === "image/png") {
        isJPG = true;
      }
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    }
  }
};
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
