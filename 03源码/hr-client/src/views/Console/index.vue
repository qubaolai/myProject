<template>
  <div>
    <div class="main">
      <div style="margin-bottom: 30px;">
        <h2>欢迎登录到XX医院人事管理系统</h2>
      </div>
      <div>
        <h2>
          {{ nowTimes.yy }}年{{ nowTimes.mm }}月{{ nowTimes.dd }}日&nbsp;{{
            nowTimes.hou
          }}时{{ nowTimes.min }}分{{ nowTimes.sec }}秒
        </h2>
      </div>
    </div>
    <div class="clild-left">
      <el-button type="primary" @click="signinWork()">签到</el-button>
    </div>
    <div class="clild-right">
      <el-button type="primary" @click="loading = true">请假</el-button>
    </div>
    <Modal
      type="confirm"
      @took="applyHoliday"
      @tocancel="cancelfall"
      :showstate="loading"
      ref="childModal"
    >
      <span slot="tlt"></span>
    </Modal>
  </div>
</template>
<script>
import { singin } from "@/api/attendance/signin.js";
import { apply } from "@/api/lea/apply.js";
import { formatDate } from "@/utils/dateUtil.js";
import Modal from "@/components/load.vue";
export default {
  name: "console",
  components: {
    Modal
  },
  data: () => {
    return {
      //页面显示内容start
      user: {
        username: "",
        userRole: ""
      },
      nowTimes: {
        yy: "00",
        mm: 0,
        dd: "00",
        hou: "00",
        min: "00",
        sec: "00"
      },
      //页面显示内容end
      //功能参数Start
      //请假弹窗
      loading: false
      //功能参数end
    };
  },
  mounted() {
    setInterval(() => {
      this.setNowTimes();
    }, 1000);
    const user = JSON.parse(sessionStorage.getItem("user"));
    this.user.username = user.name;
    this.user.userRole = user.role;
  },
  methods: {
    signinWork() {
      singin().then(responce => {
        this.$message({
          message: responce.data.msg,
          type: "success"
        });
      });
    },
    applyHoliday() {
      //获取子组件的值
      const value = this.$refs.childModal.value;
      const textarea = this.$refs.childModal.textarea;
      const applyType = this.$refs.childModal.applyType;
      const form = {
        startTime: formatDate(value[0]),
        endTime: formatDate(value[1]),
        reason: textarea,
        type: applyType
      };
      apply(form).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.loading = false;
          this.$message({
            message: "申请成功!",
            type: "success"
          });
        } else {
          this.$message(data.msg);
        }
      });
    },
    setNowTimes() {
      let myDate = new Date();
      this.nowTimes.yy = myDate.getFullYear();
      this.nowTimes.mm = myDate.getMonth() + 1;
      this.nowTimes.dd = String(
        myDate.getDate() < 10 ? "0" + myDate.getDate() : myDate.getDate()
      );
      this.nowTimes.hou = String(
        myDate.getHours() < 10 ? "0" + myDate.getHours() : myDate.getHours()
      );
      this.nowTimes.min = String(
        myDate.getMinutes() < 10
          ? "0" + myDate.getMinutes()
          : myDate.getMinutes()
      );
      this.nowTimes.sec = String(
        myDate.getSeconds() < 10
          ? "0" + myDate.getSeconds()
          : myDate.getSeconds()
      );
    },
    cancelfall() {
      this.loading = false;
    }
  },
  created: function() {
    this.setNowTimes();
  },
  beforeDestory: function() {
    //清除定时器
    if (this.timer) {
      clearInterval(this.timer); //在Vue实例销毁前，清除定时器
    }
  }
};
</script>
<style lang="scss" scoped>
.main {
  text-align: center;
  position: absolute;
  left: 50%;
  top: 25%;
  transform: translate(-50%, -50%);
  font-family: Arial, Helvetica, sans-serif;
  font-size: 36px;
  line-height: 36px;
  color: #7c7b7b;
  text-align: left;
}
.clild-left {
  position: absolute;
  left: 30%;
  top: 45%;
}
.clild-right {
  position: absolute;
  left: 60%;
  top: 45%;
}
.el-button--primary {
  width: 170px;
  line-height: 30px;
  text-align: 30px;
}
</style>
