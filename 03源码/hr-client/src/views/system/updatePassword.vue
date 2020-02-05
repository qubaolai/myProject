<template>
  <div class="main">
    <div>
      <el-form :inline="true" :model="form" class="demo-form-inline">
        <div class="inputDiv">
          <span class="w2">旧密码:</span>
          <el-form-item prop="oldPassword">
            <el-input
              :type="show.old ? 'text' : 'password'"
              v-model="form.oldPassword"
              placeholder="请输入旧密码"
            >
              <img
                :src="show.old ? closeeye : openeye"
                slot="suffix"
                class="imgStyle"
                alt=""
                @click="show.old = !show.old"
              />
            </el-input>
          </el-form-item>
        </div>
        <div class="inputDiv">
          <span class="w2">新密码:</span>
          <el-form-item prop="newPassword">
            <el-input
              :type="show.new ? 'text' : 'password'"
              v-model="form.newPassword"
              placeholder="请输入新密码"
            >
              <img
                :src="show.new ? closeeye : openeye"
                slot="suffix"
                class="imgStyle"
                alt=""
                @click="show.new = !show.new"
              />
            </el-input>
          </el-form-item>
        </div>
        <div class="inputDiv">
          <span class="w2" style="margin-left: -14px;">确认密码:</span>
          <el-form-item prop="confirmPassword">
            <el-input
              :type="show.check ? 'text' : 'password'"
              v-model="form.confirmPassword"
              placeholder="请确认密码"
            >
              <img
                :src="show.check ? closeeye : openeye"
                slot="suffix"
                class="imgStyle"
                alt=""
                @click="show.check = !show.check"
              />
            </el-input>
          </el-form-item>
        </div>
      </el-form>
    </div>
    <el-button type="primary" plain @click="commitForm()">确认</el-button>
    <el-button type="primary" plain @click="reset()">重置</el-button>
  </div>
</template>
<script>
import { updatePassword } from "@/api/user/updatePassword.js";
import router from "@/router";
export default {
  name: "insertDept",
  data() {
    return {
      form: {
        oldPassword: "",
        newPassword: "",
        confirmPassword: ""
      },
      show: {
        old: false,
        new: false,
        check: false
      },
      openeye: require("@/icons/imgs/close.png"),
      closeeye: require("@/icons/imgs/open.png"),
      options: []
    };
  },
  methods: {
    commitForm() {
      updatePassword(this.form).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.$alert("请重新登录!", "修改成功", {
            confirmButtonText: "重新登录",
            showClose: false
          }).then(() => {
            router.push({ name: "Login" });
          });
        } else {
          this.$message({
            message: data.msg,
            type: "warning"
          });
        }
      });
    },
    reset() {
      this.form.oldPassword = "";
      this.form.newPassword = "";
      this.form.confirmPassword = "";
    }
  },
  created: function() {}
};
</script>
<style lang="scss" scoped>
.imgStyle {
  width: 20px;
  height: 20px;
  margin-top: 8px;
}
span {
  display: inner-block;
  margin-right: 2px;
}
.w2 {
  width: 25%;
  margin-left: 5px;
  letter-spacing: 3px;
  vertical-align: middle;
  position: relative;
  top: 8px;
}
.inputDiv {
  margin-bottom: 10px;
}
.main {
  font-size: 14px;
  text-align: center;
  position: absolute;
  left: 50%;
  top: 30%;
  transform: translate(-50%, -50%);
}
.inputStyle {
  width: 300px;
  margin-bottom: 20px;
}
.selectStyle {
  margin-bottom: 30px;
}
</style>
