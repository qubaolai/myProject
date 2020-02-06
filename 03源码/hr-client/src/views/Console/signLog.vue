<template>
  <div class="basic-grey">
    <div
      style="margin-bottom: 10px; background-color: #fff; width: 99%; padding: 3px; border-radius: 5px;"
    >
      <el-row :gutter="20" style="position: relative;left: 9%;">
        <el-col :span="7">
          <div class="grid-content bg-purple rowStyle">
            <span>姓名:</span>
            <el-input
              class="formStyle"
              placeholder="员工姓名"
              v-model="form.empName"
              clearable
            ></el-input>
          </div>
        </el-col>
        <el-col :span="7">
          <div class="grid-content bg-purple rowStyle">
            <span>编号:</span>
            <el-input
              class="formStyle"
              placeholder="员工编号"
              v-model="form.employeeNumber"
              clearable
            ></el-input>
          </div>
        </el-col>
        <el-col :span="7">
          <div class="grid-content bg-purple rowStyle">
            <span>部门:</span>
            <el-select
              class="formStyle"
              v-model="form.departmentNumber"
              filterable
              clearable
              placeholder="请选择部门"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" style="position: relative; left: 9%;">
        <el-col :span="6">
          <div class="grid-content bg-purple" style="width: 120%;">
            <span>签到时间:</span>
            <el-date-picker
              class="timeStyle"
              v-model="signTime"
              type="daterange"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </div>
        </el-col>
        <el-col :span="6">
          <div
            class="grid-content bg-purple rowStyle"
            style="margin-left: 50px; margin-top: 4px;"
          >
            <span>签到类型:</span>
            <el-radio class="radio" v-model="form.signType" label="0"
              >上班</el-radio
            >
            <el-radio class="radio" v-model="form.signType" label="1"
              >下班</el-radio
            >
            <el-radio class="radio" v-model="form.signType" label="2"
              >加班</el-radio
            >
          </div>
        </el-col>
        <el-col :span="3" style="margin-left: 40px;">
          <div class="grid-content bg-purple rowStyle">
            <el-button
              class="buttonStyle"
              type="primary"
              :disabled="disable"
              @click="submitForm()"
              >查询</el-button
            >
          </div>
        </el-col>
        <el-col :span="3">
          <div class="grid-content bg-purple1">
            <el-button class="buttonStyle" type="primary" @click="reset()"
              >重置</el-button
            >
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="line"></div>
    <div style="height: 200%; background-color: #fff;">
      <el-row>
        <el-col :span="24"
          ><div class="grid-content bg-purple-dark"></div
        ></el-col>
        <div class="block">
          <el-pagination
            class="page"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[2, 5, 10]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="sumNum"
          >
          </el-pagination>
        </div>
      </el-row>
    </div>
  </div>
</template>
<script>
export default {
  name: "userList",
  data() {
    return {
      //分页
      //显示数据总数
      sumNum: 0,
      //每页数量
      pageSize: 10,
      //当前页
      currentPage: 1,
      //分页
      //页面表单start
      form: {
        empName: "",
        employeeNumber: "",
        departmentNumber: "",
        signType: ""
      },
      options: [
        {
          value: "选项1",
          label: "黄金糕"
        },
        {
          value: "选项2",
          label: "双皮奶"
        },
        {
          value: "选项3",
          label: "蚵仔煎"
        },
        {
          value: "选项4",
          label: "龙须面"
        },
        {
          value: "选项5",
          label: "北京烤鸭"
        }
      ],
      //入职日期数组 绑定在日期选择器
      signTime: [],
      //查询按钮控制
      disable: false
      //查询数据容器
    };
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
    },
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //提交查询参数
    submitForm() {},
    reset() {},
    init() {}
  },
  created: function() {},
  computed: {
    //自动计算页面数据展示
    pageData: function() {
      return this.tableData.slice(
        (this.currentPage - 1) * this.pageSize,
        this.currentPage * this.pageSize
      );
    }
  }
};
</script>
<style>
.main-content[data-v-5c886d6e] {
  width: 100%;
  padding-top: 3px;
  padding-left: 3px;
  padding-right: 3px;
}
.rowStyle {
  width: 105%;
}
</style>
<style lang="scss" scoped>
.page {
  float: left;
  position: fixed;
  right: 20%;
  bottom: 20px;
}
.buttonStyle {
  margin-top: 2px;
  margin-left: 70px;
  width: 80px;
  height: 36px;
  line-height: 12px;
}
.timeStyle {
  width: 75%;
  margin-left: 10px;
}
.line {
  margin: 0 8px 9px 0px;
  height: 3px;
  background-color: #fff;
}
.basic-grey {
  width: 99%;
  margin-left: auto;
  margin-right: auto;
  height: 100vh;
  padding: 25px 0 25px 10px;
  font: 14px;
  color: rgb(99, 97, 97);
  background-color: rgb(245, 243, 243);
  text-shadow: 1px 1px 1px #fff;
  border-radius: 5px;
}
.basic-grey > span {
  float: left;
  width: 20%;
  text-align: right;
  padding-right: 10px;
  margin-top: 10px;
  color: rgb(85, 85, 85);
}
.formStyle {
  width: auto;
  margin-left: 10px;
}
.radio {
  margin-top: 9px;
  margin-left: 10px;
  color: rgb(85, 85, 85);
}
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  margin-right: 9px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
