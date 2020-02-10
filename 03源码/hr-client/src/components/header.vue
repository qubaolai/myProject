<template>
  <div id="header-wrap">
    <div class="pull-left header-icon">
      <svg-icon iconClass="menu" class="menu" />
    </div>
    <div class="pull-right">
      <div class="user-head pull-left">
        <el-tooltip content="更换头像" placement="bottom">
          <el-image
            style="width: 40px; height: 40px; position: relative; left: -18px; top: 14px;"
            :src="url"
            @click="dialogFormVisible = true"
          ></el-image>
        </el-tooltip>
      </div>
      <div class="user-text pull-left">
        {{ user.username }}
      </div>
      <div class="header-icon pull-left" @click="exit()">
        <el-tooltip content="注销" placement="bottom" effect="light">
          <svg-icon iconClass="logout" class="logout" />
        </el-tooltip>
      </div>
    </div>
    <div>
      <el-dialog :visible.sync="dialogFormVisible" :append-to-body="true">
        <el-upload
          class="avatar-uploader"
          action="api/file/uploadFile"
          name="headPhoto"
          :data="{ path: 'D:/img' }"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <el-img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false"
            >确 定</el-button
          >
        </div>
      </el-dialog>
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
      },
      //页面显示内容end
      dialogFormVisible: false,
      imageUrl: "",
      url:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
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
          window.location.href = "/";
          window.sessionStorage.clear();
        } else {
          this.$message({
            message: data.msg,
            type: "warning"
          });
        }
      });
    },
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
.user-head {
  img {
    display: inline-block;
    margin-bottom: -12px;
    margin-right: 18px;
    border-radius: 50px;
  }
}
.user-text {
  height: 100%;
  padding-right: 20px;
  border-right: 1px solid #c5c3c3;
  + .header-icon {
    padding: 0 28px;
  }
}
</style>
