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
            v-model="form.positionName"
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
            v-model="inTime"
            type="daterange"
            unlink-panels
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
            :data="pageData"
            style="width: 100%"
            height="400"
            :default-sort="{ prop: 'date', order: 'descending' }"
          >
            <el-table-column prop="empName" label="姓名" width="165">
            </el-table-column>
            <el-table-column prop="deptName" label="部门" width="165">
            </el-table-column>
            <el-table-column prop="mangName" label="领导姓名" width="165">
            </el-table-column>
            <el-table-column prop="education" label="学历" width="150">
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
          :page-sizes="[2, 5, 10]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="sumNum"
        >
        </el-pagination>
      </div>
    </el-row>
  </div>
</template>
<script>
import { getEmpList } from "@/api/user/getEmployees.js";
import formData from "@/store/data.js";
import { initPage } from "@/api/page/init.js";
import { formatDate } from "@/utils/dateUtil.js";
export default {
  name: "userList",
  data() {
    return {
      //分页
      //总数
      sumNum: 0,
      //每页数量
      pageSize: 2,
      //当前页
      currentPage: 1,
      //分页
      //页面表单start
      form: {
        pageNo: 1,
        pageSize: 10,
        empName: "姓名",
        employeeNumber: "zxc",
        mangerName: "姓名2",
        sex: "1",
        education: "本科",
        departmentName: "外科",
        positionName: "",
        intimeStart: "",
        intimeEnd: ""
      },
      inTime: [],
      initData: formData,
      dataLength: 0,
      //页面表单end
      //按钮控制
      disable: false,
      tableData: []
    };
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      console.log(`当前页: ${val}`);
    },
    //提交查询参数
    submitForm() {
      if (this.form.intimeStart !== "" && this.form.intimeEnd !== "") {
        this.form.intimeStart = formatDate(this.inTime[0]);
        this.form.intimeEnd = formatDate(this.inTime[1]);
      }
      this.form.sex = this.form.sex === "1" ? "男" : "女";
      getEmpList(this.form).then(response => {
        this.form.sex = this.form.sex === "男" ? "1" : "2";
        //设置每页条数
        this.form.pageSize = this.pageSize;
        //设置页数
        this.form.pageNo = this.currentPage;
        const data = response.data;
        if (data.code === 400) {
          this.tableData = [];
          this.$message("数据为空!");
        } else {
          const tableList = data.data.list;
          this.tableData = [];
          this.sumNum = tableList.length;
          for (let i = 0; i < tableList.length; i++) {
            const table = {
              empName: "",
              deptName: "",
              mangName: "",
              inTime: "",
              education: "",
              option: "",
              telephone: "",
              address: ""
            };
            table.empName = tableList[i].name;
            table.deptName = tableList[i].department.name;
            table.mangName = tableList[i].employee.name;
            table.inTime = tableList[i].inTime;
            table.education = tableList[i].education;
            table.option = tableList[i].position.name;
            table.telephone = tableList[i].telephone;
            table.address = tableList[i].address;
            this.tableData.push(table);
          }
        }
      });
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
      this.form.intimeStart = "";
      this.form.intimeEnd = "";
    },
    init() {
      initPage();
      this.initData = formData;
    }
  },
  created: function() {
    this.init();
  },
  computed: {
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
