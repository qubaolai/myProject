<template>
  <div class="adminBody">
    <!-- 一行 -->
    <el-row :gutter="20">
      <el-col :span="16">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
    <!-- 二行 -->
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <div class="mainBody">
            <div>{{ user.username }}</div>
            <div>欢迎登录到医院人事管理系统</div>
            <div>登录时间:{{ nowTime }}</div>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
    <!-- 三行 -->
    <el-row :gutter="20">
      <el-col :span="8">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content bg-purple">
          <div class="buttonType">
            <el-button type="primary" @click="signinWork()">签到</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="7">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
    <!-- 四行 -->
    <el-row :gutter="20">
      <el-col :span="4">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="16">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { getNowDate } from "@/utils/dateUtil.js";
import { singin } from "@/api/user/signin.js";
export default {
  name: "console",
  data: () => {
    return {
      //页面显示内容start
      user: {
        username: "",
        userRole: ""
      },
      nowTime: ""
      //页面显示内容end
      //功能参数Start
      //功能参数end
    };
  },
  mounted() {
    this.nowTime = getNowDate();
    const user = JSON.parse(sessionStorage.getItem("user"));
    this.user.username = user.name;
    this.user.userRole = user.role;
  },
  methods: {
    signinWork() {
      singin().then(responce => {
        console.log(responce);
        this.$message({
          message: responce.data.msg,
          type: "warning"
        });
      });
      // this.$message({
      //   message: "签到成功" + "\n" + "签到时间为:" + " " + getNowDate(),
      //   type: "success"
      // });
    }
  }
};
</script>
<style lang="scss" scoped>
.adminBody {
  background-color: rgb(236, 236, 236);
  line-height: normal;
  font-weight: bold;
  letter-spacing: normal;
  text-align: justify;
}
.el-button--primary {
  margin: 100px;
  width: 170px;
  line-height: 30px;
  text-align: 30px;
}
.el-row {
  margin-bottom: 10px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 20vh;
}
.row-bg {
  padding: 10px 0;
}
</style>
