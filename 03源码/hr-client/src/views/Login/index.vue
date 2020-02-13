<template>
  <div id="login">
    <div class="login-wrap">
      <ul class="menu-tab" style="height: 200px;"></ul>
      <!-- 表单 -->
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        class="login-form"
        size="medium"
      >
        <el-form-item prop="username" class="item-from">
          <label>用户名</label>
          <el-input v-model="ruleForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" class="item-from">
          <label>密码</label>
          <el-input
            type="password"
            v-model="ruleForm.password"
            autocomplete="off"
            maxlength="20"
            minlength="6"
          ></el-input>
        </el-form-item>
        <el-form-item class="item-from">
          <el-button
            :v-loading="loading"
            type="primary"
            :disabled="loginButtonState"
            @click="submitForm('ruleForm')"
            class="login-btn block"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import { login } from "@/api/login.js";
import { stripscript, validatePwd } from "@/utils/validate";
export default {
  name: "login",
  data: function() {
    var validateUsername = (rule, value, callback) => {
      if (value === "" || value === " ") {
        callback(new Error("请输入用户名"));
      } else {
        callback();
      }
    };
    // 验证密码
    var validatePassword = (rule, value, callback) => {
      // 过滤特殊字符
      this.ruleForm.password = stripscript(value);
      value = this.ruleForm.password;
      // 正则表达式验证密码(加特定字符验证)
      if (value === "" || value === " ") {
        callback(new Error("请输入密码"));
      } else if (!validatePwd(value)) {
        callback(new Error("密码为6位字母和数字组合"));
      } else {
        callback();
      }
    };
    return {
      // 显示验证码区域和确认密码
      user: {
        name: "",
        role: ""
      },
      ruleForm: {
        username: "asd",
        password: "asd123",
        confirmPassword: "",
        age: ""
      },
      //登录按钮状态声明
      loginButtonState: false,
      loading: false,
      //校验规则
      rules: {
        username: [{ validator: validateUsername, trigger: "blur" }],
        password: [{ validator: validatePassword, trigger: "blur" }]
      }
    };
  },
  created() {},
  mounted() {},
  methods: {
    submitForm(formName) {
      this.loginButtonState = true;
      this.loading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          const formParam = {
            username: this.ruleForm.username,
            password: this.ruleForm.password
          };
          login(formParam)
            .then(response => {
              const data = response.data;
              if (data.code === 200) {
                this.user.name = data.data.user.name;
                this.user.role = data.data.user.role;
                //将数据存储在sessionStorage
                sessionStorage.setItem("user", JSON.stringify(this.user));
                //将token保存
                const token = data.data.token;
                if (token !== null) {
                  window.sessionStorage.setItem("token", token);
                  sessionStorage[token];
                }
                this.$router.push({
                  path: "/console"
                  // query: { user: data.data.user }
                });
              } else if (data.code === 400) {
                this.$message({
                  message: "用户名或密码错误!",
                  type: "warning"
                });
                this.loginButtonState = false;
              } else {
                this.$message({
                  message: data.msg,
                  type: "warning"
                });
                this.loginButtonState = false;
              }
            })
            .catch(() => {
              this.loginButtonState = false;
            });
        }
      });
    },
    reset() {
      this.ruleForm.username = "";
      this.ruleForm.password = "";
    }
  }
};
</script>
<style lang="scss" scoped>
#login {
  height: 100vh;
  background-color: #344a5f;
}
.login-wrap {
  width: 330px;
  margin: auto;
}
.menu-tab {
  text-align: center;
  li {
    display: inline-block;
    width: 88px;
    line-height: 36px;
    font-size: 14px;
    color: #fff;
    border-radius: 2px;
    cursor: pointer;
  }
  .current {
    margin-top: 16vh;
    background-color: rgba(0, 0, 0, 0.1);
  }
}
.login-form {
  label {
    display: block;
    margin-bottom: 3px;
    font-size: 14px;
    color: #fff;
  }
  .item-from {
    margin-bottom: 13px;
  }
  .block {
    display: block;
    width: 100%;
  }
  .login-btn {
    margin-top: 19px;
  }
}
</style>
<!--
密码加密：
1、在前端预先加密一次
登录的密码：123456（普通字符串）
经过加密后：sha1('123456') == '541216ad5s4f5ds1f5asd4f65asd4' （加密后的字符串）


2、后台加密
接收到字符串：'541216ad5s4f5ds1f5asd4f65asd4'
后台再次加密：md5('541216ad5s4f5ds1f5asd4f65asd4') == '8f9qwersd3g165y4d1sf3s1f6aew4'（最终的加密后的密码）
最终新的字符串写入数据库：8f9qwersd3g165y4d1sf3s1f6aew4


3、登录
用户名与加密后的密码进行匹配，成功则登录，失败则提示
-->
