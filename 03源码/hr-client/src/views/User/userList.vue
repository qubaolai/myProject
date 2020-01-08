<template>
  <div class="basic-grey">
    <el-row :gutter="20">
      <el-col :span="5">
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
      <el-col :span="5">
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
      <el-col :span="5">
        <div class="grid-content bg-purple rowStyle">
          <span>领导:</span>
          <el-input
            class="formStyle"
            placeholder="领导姓名"
            v-model="form.mangerName"
            clearable
          ></el-input>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content bg-purple rowStyle">
          <span>员工性别:</span>
          <el-radio class="radio" v-model="form.sex" label="1">男</el-radio>
          <el-radio class="radio" v-model="form.sex" label="2">女</el-radio>
        </div>
      </el-col>
      <el-col :span="4">
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
    </el-row>
    <el-row :gutter="20">
      <el-col :span="5">
        <div class="grid-content bg-purple rowStyle">
          <span>部门:</span>
          <el-select
            class="formStyle"
            v-model="form.departmentName"
            filterable
            clearable
            placeholder="请选择部门"
          >
            <el-option
              v-for="item in initData.formData.departments"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content bg-purple rowStyle">
          <span>学历:</span>
          <el-select
            class="formStyle"
            v-model="form.education"
            filterable
            clearable
            placeholder="请选择"
          >
            <el-option
              v-for="item in initData.formData.educations"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content bg-purple rowStyle">
          <span>职称:</span>
          <el-select
            class="formStyle"
            v-model="form.option"
            clearable
            placeholder="请选择"
          >
            <el-option
              v-for="item in initData.formData.options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content bg-purple" style="width: 120%;">
          <span>入职时间:</span>
          <el-date-picker
            class="timeStyle"
            v-model="form.intime"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple1">
          <el-button class="buttonStyle" type="primary" @click="reset()"
            >重置</el-button
          >
        </div>
      </el-col>
    </el-row>
    <dir class="line"></dir>
    <el-row>
      <el-col :span="24"
        ><div class="grid-content bg-purple-dark">
          <el-table
            :data="tableData"
            style="width: 100%"
            :default-sort="{ prop: 'date', order: 'descending' }"
          >
            <el-table-column prop="empName" label="姓名" width="165">
            </el-table-column>
            <el-table-column prop="deptName" label="部门" width="165">
            </el-table-column>
            <el-table-column prop="mangName" label="领导姓名" width="165">
            </el-table-column>
            <el-table-column prop="education" label="学历" width="165">
            </el-table-column>
            <el-table-column prop="option" label="职称" width="165">
            </el-table-column>
            <el-table-column prop="telephone" label="电话" width="165">
            </el-table-column>
            <el-table-column prop="address" label="地址"> </el-table-column>
            <el-table-column
              prop="inTime"
              label="入职日期"
              sortable
              width="165"
            >
            </el-table-column>
          </el-table></div
      ></el-col>
      <div class="block">
        <el-pagination
          class="page"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="100"
        >
        </el-pagination>
      </div>
    </el-row>
  </div>
</template>
<script>
import { getEmpList } from "@/api/user/getEmployees.js";
import formData from "@/store/data.js";
export default {
  name: "userList",
  data() {
    return {
      //分页start
      currentPage: 1,
      //分页end
      //页面表单start
      form: {
        empName: "",
        employeeNumber: "",
        mangerName: "",
        input: "",
        sex: "1",
        education: "",
        departmentName: "",
        option: "",
        intime: "",
        initData: formData
      },
      //页面表单end
      //按钮控制
      disable: false,
      tableData: [
        {
          empName: "王小虎",
          deptName: "儿科",
          mangName: "王大虎",
          inTime: "2016-05-01",
          education: "本科",
          option: "老中医",
          telephone: "13865748493",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          empName: "王小虎",
          deptName: "儿科",
          mangName: "王大虎",
          inTime: "2016-05-03",
          education: "本科",
          option: "老中医",
          telephone: "13865748493",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          empName: "王小虎",
          deptName: "儿科",
          mangName: "王大虎",
          inTime: "2016-05-02",
          education: "本科",
          option: "老中医",
          telephone: "13865748493",
          address: "上海市普陀区金沙江路 1518 弄"
        }
      ]
    };
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    submitForm() {
      console.log(formData);
      console.log(getEmpList);
      console.log(this.initData);
      alert("tijiao");
    },
    reset() {
      this.disable = false;
      this.form.empName = "";
      this.form.employeeNumber = "";
      this.form.mangerName = "";
      this.form.sex = "1";
      this.form.input = "";
      this.form.education = "";
      this.form.departmentName = "";
      this.form.option = "";
      this.form.intime = "";
    },
    init() {
      this.initData = formData;
    }
  },
  created: function() {
    //页面加载完成发送请求 请求下拉框数据数据
    this.init();
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
  margin-left: 14px;
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
