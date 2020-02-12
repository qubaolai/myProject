<template>
  <div id="header-wrap">
    <div class="pull-left header-icon" @click="changeNav">
      <svg-icon iconClass="menu" class="menu" />
    </div>
    <div class="pull-right">
      <div class="user-head pull-left">
        <el-tooltip content="更换头像" placement="bottom">
          <img
            style="width: 40px; height: 40px;"
            :src="url"
            @click="dialogFormVisible = true"
          />
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
    <div style="margin-left: 42%;">
      <el-dialog
        :visible.sync="dialogFormVisible"
        :append-to-body="true"
        :close-on-press-escape="false"
        :show-close="false"
        :before-close="cancelUpload"
      >
        <div>
          <el-upload
            style="position: relative;left: 40%;width: 180px;"
            class="avatar-uploader"
            action="api/file/uploadFile"
            accept=".jpg,.jpeg,.png"
            name="headPhoto"
            :data="{ path: 'D:/img' }"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="imgUrl" :src="imgUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancelUpload">取 消</el-button>
          <el-button type="primary" @click="determine">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import { logout } from "@/api/user/logout.js";
import {
  getPhoto,
  deleteFile,
  insertFileInfo
} from "@/api/file/operationFile.js";
export default {
  // watch: {
  //   url: {
  //     handler(newVal) {
  //       if (this.url != newVal) {
  //         this.getUserHeader();
  //       }
  //     }
  //   }
  // },
  data: () => {
    return {
      //页面显示内容start
      user: {
        username: "",
        userRole: ""
      },
      //页面显示内容end
      dialogFormVisible: false,
      imgUrl: "",
      url: "",
      delPath: ""
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
    determine() {
      if (
        this.delPath != null &&
        this.delPath != "" &&
        this.imgUrl != null &&
        this.imgUrl != ""
      ) {
        insertFileInfo(this.delPath).then(response => {
          const data = response.data;
          if (data.code === 200) {
            this.getUserHeader();
            this.dialogFormVisible = false;
            this.imgUrl = "";
          } else {
            this.cancelUpload();
            this.$message({
              message: "头像上传失败!",
              type: "error"
            });
          }
        });
      } else {
        this.$message({
          message: "请选择头像文件!",
          type: "warning"
        });
      }
    },
    cancelUpload() {
      //删除文件
      if (this.delPath != null && this.delPath != "") {
        deleteFile(this.delPath).then(response => {
          const data = response.data;
          if (data.code === 200) {
            this.imgUrl = "";
            this.$message({
              message: "取消上传",
              type: "success"
            });
          }
        });
      }
      this.dialogFormVisible = false;
    },
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
      if (res.code === 200) {
        this.imgUrl = URL.createObjectURL(file.raw);
        this.delPath = res.data;
      } else {
        this.$message({
          message: "头像上传失败!",
          type: "error"
        });
      }
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
    },
    getUserHeader() {
      getPhoto().then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.url = data.data;
          // base64编码的图片
          const fileBase64 = data.data;
          //转换图片文件
          this.url = URL.createObjectURL(this.base64ImgtoFile(fileBase64));
        }
      });
    },
    base64ImgtoFile(dataurl, filename = "file") {
      let arr = dataurl.split(",");
      let mime = arr[0].match(/:(.*?);/)[1];
      let suffix = mime.split("/")[1];
      let bstr = atob(arr[1]);
      let n = bstr.length;
      let u8arr = new Uint8Array(n);
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }
      return new File([u8arr], `${filename}.${suffix}`, {
        type: mime
      });
    },
    changeNav() {
      this.$store.commit("SET_STATUS");
    }
  },
  created: function() {
    this.imgUrl = "";
    this.getUserHeader();
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
.open {
  #header-wrap {
    left: $navMenu;
  }
}
.close {
  #header-wrap {
    left: 64px;
  }
}
</style>
