<template>
  <div class="main">
    <div>
      <el-form :inline="true" :model="form" class="demo-form-inline">
        <div class="inputDiv" style="position: relative;left: 22px;">
          <span class="w2">员工编号:</span>
          <el-form-item prop="empNum">
            <el-input
              type="text"
              style="width: 204px;"
              v-model="form.empNum"
              @blur="getEmpInfo()"
              placeholder="请输入员工编号"
            >
            </el-input>
          </el-form-item>
        </div>
        <div class="inputDiv">
          <span class="w2">调动类型:</span>
          <el-form-item>
            <el-radio v-model="radio" label="0" style="margin-right: 4px;"
              >部门调动</el-radio
            >
            <el-radio v-model="radio" label="1">职位调动</el-radio>
          </el-form-item>
        </div>
        <div class="inputDiv" style="position: relative;left: 46px;">
          <span class="w2" style="margin-left: -14px;">部门:</span>
          <el-form-item prop="deptNum">
            <el-select
              v-model="form.deptNum"
              placeholder="请选择"
              :disabled="radio == '1'"
              @change="getPositions()"
            >
              <el-option
                v-for="item in deptartments"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="inputDiv" style="position: relative;left: 46px;">
          <span class="w2" style="margin-left: -14px;">职位:</span>
          <el-form-item prop="position">
            <el-select v-model="form.position" placeholder="请选择">
              <el-option
                v-for="item in positions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </div>
      </el-form>
    </div>
    <el-button type="primary" plain @click="commitForm()">确认</el-button>
    <el-button type="primary" plain @click="reset()">重置</el-button>
  </div>
</template>
<script>
import { getEmp } from "@/api/user/getEmployees.js";
import { getDepts } from "@/api/department/getDepartments.js";
import { getpositions } from "@/api/position/getPosition.js";
import { schedulingUser } from "@/api/user/schedulingUser.js";
export default {
  name: "insertDept",
  //监听单选按钮的值 值为1时部门默认选中不可更改
  watch: {
    radio: {
      handler(newVal) {
        if (newVal == "1") {
          if (this.form.empNum != "") {
            this.getEmpInfo();
          }
          this.getPositions();
        }
      },
      deep: true
    }
  },
  data() {
    return {
      form: {
        empNum: "",
        deptNum: "",
        postion: "",
        type: ""
      },
      deptartments: [],
      positions: [],
      //输入框员工编号查询的部门id
      deptNum: "",
      radio: "0"
    };
  },
  methods: {
    getPositions() {
      debugger;
      getpositions(this.form.deptNum).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.positions = [];
          const positionList = data.data;
          for (let i = 0; i < positionList.length; i++) {
            const selectData = {
              value: "",
              label: ""
            };
            selectData.value = positionList[i].id;
            selectData.label = positionList[i].name;
            this.positions.push(selectData);
          }
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    getEmpInfo() {
      if (this.form.empNum != "") {
        getEmp(this.form.empNum).then(response => {
          const data = response.data;
          if (data.code === 200) {
            this.form.deptNum = data.data.departmentNumber;
          } else {
            this.$message.error(data.msg);
          }
        });
      }
    },
    commitForm() {
      this.form.type = this.radio;
      schedulingUser(this.form).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.$message({
            message: "调动成功!",
            type: "success"
          });
        } else {
          this.$message({
            message: data.msg,
            type: "warning"
          });
        }
      });
    },
    reset() {},
    init() {
      getDepts().then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.deptartments = [];
          const dept = data.data;
          for (let i = 0; i < dept.length; i++) {
            const selectData = {
              value: "",
              label: ""
            };
            selectData.value = dept[i].id;
            selectData.label = dept[i].name;
            this.deptartments.push(selectData);
          }
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  },
  created: function() {
    this.init();
  }
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
