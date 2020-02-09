<template>
  <div class="basic-grey">
    <div style="background-color: #fff;">
      <el-row :gutter="5" style="margin-top: 10px;">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <span>员工姓名&nbsp;:&nbsp;</span>
            <el-cascader
              :options="empInfos"
              v-model="form.empName"
              style="width: 240px;"
            >
              <template slot-scope="{ node, data }">
                <span>{{ data.label }}</span>
                <span v-if="!node.isLeaf">({{ data.children.length }}人)</span>
              </template>
            </el-cascader>
          </div></el-col
        >
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <span>员工编号&nbsp;:&nbsp;</span>
            <el-input
              style="width: 200px;"
              placeholder="请输入员工编号"
              v-model="form.empNum"
              clearable
            >
            </el-input></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <span>部门名称&nbsp;:&nbsp;</span>
            <el-select v-model="form.deptName" clearable placeholder="请选择">
              <el-option
                v-for="item in initData.formData.departments"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <span>上班时间&nbsp;:&nbsp;</span>
            <el-time-picker
              value-format="HH:mm:ss"
              style="width: 120px;margin-right: 5px;"
              v-model="form.singInStartTime"
              placeholder="任意时间点"
            >
            </el-time-picker>
            <el-time-picker
              value-format="HH:mm:ss"
              style="width: 120px;"
              v-model="form.singInEndTime"
              placeholder="任意时间点"
            >
            </el-time-picker></div
        ></el-col>
      </el-row>
      <el-row :gutter="5" style="margin-top: 20px;">
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <span>签到日期&nbsp;:&nbsp;</span>
            <el-date-picker
              style="width: 240px;"
              v-model="form.singDate"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker></div
        ></el-col>
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <div style="margin-top: 12px;">
              <span>上班签到&nbsp;:&nbsp;</span>
              <el-radio-group v-model="form.singInType">
                <el-radio :label="0">正常</el-radio>
                <el-radio :label="1">迟到</el-radio>
              </el-radio-group>
            </div>
          </div></el-col
        >
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <div style="margin-top: 12px;">
              <span>下班签到&nbsp;:&nbsp;</span>
              <el-radio-group v-model="form.singOutType">
                <el-radio :label="0">正常</el-radio>
                <el-radio :label="1">早退</el-radio>
                <el-radio :label="2">加班</el-radio>
              </el-radio-group>
            </div>
          </div></el-col
        >
        <el-col :span="6"
          ><div class="grid-content bg-purple">
            <span>下班时间&nbsp;:&nbsp;</span>
            <el-time-picker
              value-format="HH:mm:ss"
              style="width: 120px;margin-right: 5px;"
              v-model="form.singOutStartTime"
              :picker-options="{
                selectableRange: '00:00:00 - 23:59:59'
              }"
              placeholder="任意时间点"
            >
            </el-time-picker>
            <el-time-picker
              value-format="HH:mm:ss"
              style="width: 120px;"
              v-model="form.singOutEndTime"
              :picker-options="{
                selectableRange: '00:00:00 - 23:59:59'
              }"
              placeholder="任意时间点"
            >
            </el-time-picker></div
        ></el-col>
      </el-row>
      <el-row :gutter="0" style="margin-bottom: 6px;">
        <el-col :span="24"
          ><div class="grid-content bg-purple rowSty">
            <el-button type="primary" class="buttonStyle" @click="query()"
              >查询</el-button
            >
            <el-button type="primary" class="buttonStyle" @click="reset()"
              >重置</el-button
            >
          </div></el-col
        >
      </el-row>
    </div>
    <!-- 数据 -->
    <div
      style="width: 100%; height: 3px; background-color: #fff;margin-bottom: 6px;"
    ></div>
    <div style="height: 200%; background-color: #fff">
      <el-row>
        <div>
          <el-table :data="tableData" style="width: 100%;" height="390">
            <el-table-column
              fixed
              prop="singDate"
              sortable
              label="签到日期"
              width="150"
            >
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="150">
            </el-table-column>
            <el-table-column prop="dept" label="部门" width="150">
            </el-table-column>
            <el-table-column prop="singInTime" label="上班时间" width="150">
            </el-table-column>
            <el-table-column prop="singOutTime" label="下班时间" width="150">
            </el-table-column>
            <el-table-column prop="singInType" label="上班签到" width="150">
            </el-table-column>
            <el-table-column prop="singOutType" label="下班签到" width="150">
            </el-table-column>
            <el-table-column v-if="role" fixed="right" label="操作" width="120">
              <template slot-scope="scope">
                <el-button
                  @click.native.prevent="showDialog(scope.row)"
                  type="text"
                  size="small"
                >
                  编辑
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="page">
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
    <!-- 弹层 -->
    <div>
      <el-dialog
        title="修改签到记录"
        :visible.sync="dialogFormVisible"
        :append-to-body="true"
      >
        <el-form :model="form">
          <el-form-item
            label="员工姓名"
            :label-width="formLabelWidth"
            style="margin-bottom: 10px;"
          >
            <el-input
              :disabled="true"
              v-model="dialogForm.employeeName"
              clearable
              style="width: 220px;"
            >
            </el-input>
          </el-form-item>
          <el-form-item
            label="签到日期"
            :label-width="formLabelWidth"
            style="margin-bottom: 10px;"
          >
            <el-input
              :disabled="true"
              v-model="dialogForm.date"
              clearable
              style="width: 220px;"
            >
            </el-input>
          </el-form-item>
          <el-form-item
            label="上班时间"
            :label-width="formLabelWidth"
            style="margin-bottom: 10px;"
          >
            <el-time-picker
              value-format="HH:mm:ss"
              v-model="dialogForm.startDate"
              :picker-options="{
                selectableRange: '00:00:00 - 23:59:59'
              }"
              placeholder="上班时间点"
            >
            </el-time-picker>
          </el-form-item>
          <el-form-item
            label="下班时间"
            :label-width="formLabelWidth"
            style="margin-bottom: 10px;"
          >
            <el-time-picker
              value-format="HH:mm:ss"
              v-model="dialogForm.endDate"
              :picker-options="{
                selectableRange: '00:00:00 - 23:59:59'
              }"
              placeholder="下班时间点"
            >
            </el-time-picker>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitUpdate()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { getEmpInfo } from "@/api/page/getEmpInfo.js";
import { initPage } from "@/api/page/init.js";
import formData from "@/store/data.js";
import { getAttendance } from "@/api/attendance/getAttendance.js";
import { updateAttendance } from "@/api/attendance/updateAttendance.js";
export default {
  data() {
    return {
      initData: formData,
      //分页
      //显示数据总数
      sumNum: 0,
      //每页数量
      pageSize: 10,
      //当前页
      currentPage: 1,
      form: {
        empName: "",
        empNum: "",
        deptName: "",
        singInStartTime: "",
        singInEndTime: "",
        singOutStartTime: "",
        singOutEndTime: "",
        singInType: "",
        singOutType: "",
        singDate: ""
      },
      empInfos: [],
      tableData: [],
      // 弹层数据
      dialogFormVisible: false,
      dialogForm: {
        employeeNumber: "",
        date: "",
        employeeName: "",
        startDate: "",
        endDate: ""
      },
      formLabelWidth: "120px",
      role: ""
    };
  },
  methods: {
    query() {
      getAttendance(this.form).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.tableData = [];
          const dataList = data.data;
          for (let i = 0; i < dataList.length; i++) {
            const table = {
              empId: "",
              singDate: "",
              name: "",
              dept: "",
              singInTime: "",
              singInType: "",
              singOutTime: "",
              singOutType: ""
            };
            table.empId = dataList[i].employeeNumber;
            table.singDate = dataList[i].day;
            table.name = dataList[i].employee.name;
            table.dept = dataList[i].department.name;
            table.singInTime = dataList[i].startTime;
            table.singOutTime = dataList[i].endTime;
            if (dataList[i].startType == 0) {
              table.singInType = "正常";
            }
            if (dataList[i].startType == 1) {
              table.singInType = "迟到";
            }
            if (dataList[i].startType == 2) {
              table.singInType = "缺勤";
            }
            if (dataList[i].endType == 0) {
              table.singOutType = "正常";
            }
            if (dataList[i].endType == 1) {
              table.singOutType = "早退";
            }
            if (dataList[i].endType == 2) {
              table.singOutType = "加班";
            }
            this.tableData.push(table);
          }
        }
      });
    },
    submitUpdate() {
      if (this.dialogForm.startDate != null) {
        this.dialogForm.startDate =
          this.dialogForm.date + " " + this.dialogForm.startDate;
      }
      if (this.dialogForm.endDate != null) {
        this.dialogForm.endDate =
          this.dialogForm.date + " " + this.dialogForm.endDate;
      }
      console.log(this.dialogForm);
      updateAttendance(this.dialogForm).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.dialogFormVisible = false;
          this.$message({
            message: "修改成功!",
            type: "success"
          });
        }
      });
    },
    showDialog(row) {
      this.dialogFormVisible = true;
      this.dialogForm.employeeNumber = row.empId;
      this.dialogForm.date = row.singDate;
      this.dialogForm.employeeName = row.name;
      this.dialogForm.startDate = row.singInTime;
      this.dialogForm.endDate = row.singOutTime;
    },
    initPage() {
      getEmpInfo().then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.empInfos = [];
          const selectData = data.data;
          //第一层数据 循环对象
          for (let key in selectData) {
            //声明第一层数据
            const firstData = {
              value: "",
              label: "",
              children: []
            };
            firstData.value = key;
            firstData.label = selectData[key].name;
            //拿到第二层数据
            const empList = selectData[key].employeeListMap;
            //循环第二层数据
            for (let i = 0; i < empList.length; i++) {
              //获取每个对象的key
              const keys = Object.keys(empList[i]);
              for (let j = 0; j < keys.length; j++) {
                //声明第二层数据
                const secondData = {
                  value: "",
                  label: ""
                };
                secondData.value = empList[i][keys[j]].id;
                secondData.label = empList[i][keys[j]].name;
                firstData.children.push(secondData);
              }
            }
            this.empInfos.push(firstData);
          }
        }
      });
    },
    handleSizeChange(val) {
      this.pageSize = val;
    },
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    reset() {},
    //页面加载完成调用的初始化方法
    init() {
      //页面初始化 发送请求获取所有部门和职称
      initPage();
      this.initData = formData;
    }
  },
  created: function() {
    const user = JSON.parse(sessionStorage.getItem("user"));
    if (user.role == 0) {
      this.role = true;
    }
    this.initPage();
    this.init();
  }
};
</script>
<style>
.main-content[data-v-5c886d6e] {
  padding: 0;
}
</style>
<style lang="scss" scoped>
.page {
  position: fixed;
  bottom: 10px;
  left: 50%;
}
.buttonStyle {
  position: relative;
  left: 80%;
  width: 116px;
}
.rowSty {
  margin-top: 10px;
  margin-bottom: 10px;
}
.basic-grey {
  height: 100vh;
  color: rgb(99, 97, 97);
  background-color: rgb(245, 243, 243);
  text-shadow: 1px 1px 1px #fff;
  border-radius: 5px;
}
</style>
