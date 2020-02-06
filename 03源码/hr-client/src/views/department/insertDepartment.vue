<template>
  <div class="main">
    <el-input
      placeholder="请输入部门名称"
      v-model="form.deptName"
      clearable
      class="inputStyle"
    >
    </el-input>
    <el-input
      placeholder="请输入部门电话"
      v-model="form.deptTel"
      clearable
      class="inputStyle"
    >
    </el-input>
    <el-input
      placeholder="请输入职位"
      v-model="form.position"
      clearable
      class="inputStyle"
    >
    </el-input>
    <el-select
      v-model="form.manageName"
      filterable
      placeholder="请选择部门领导"
      style="display: block;"
      class="selectStyle"
    >
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>
    <el-button type="primary" plain @click="commitForm()">提交</el-button>
    <el-button type="primary" plain @click="reset()">重置</el-button>
  </div>
</template>
<script>
import { getEmps } from "@/api/user/getEmployees.js";
import { insertDept } from "@/api/department/insertDepartment.js";
export default {
  name: "insertDept",
  data() {
    return {
      form: {
        deptName: "",
        deptTel: "",
        manageName: "",
        position: ""
      },
      options: []
    };
  },
  methods: {
    initPage() {
      getEmps().then(response => {
        const data = response.data.data;
        for (let i = 0; i < data.length; i++) {
          const option = {
            value: "",
            label: ""
          };
          option.value = data[i].id;
          option.label = data[i].name;
          this.options.push(option);
        }
      });
    },
    commitForm() {
      insertDept(this.form).then(response => {
        const data = response.data;
        if (data.code == 200) {
          this.$message({
            message: "添加成功!",
            type: "success"
          });
          this.reset();
        } else {
          this.$message({
            message: data.msg,
            type: "warning"
          });
        }
      });
    },
    reset() {
      this.form.deptName = "";
      this.form.deptTel = "";
      this.form.position = "";
      this.form.manageName = "";
    }
  },
  created: function() {
    this.initPage();
  }
};
</script>
<style lang="scss" scoped>
.main {
  text-align: center;
  position: absolute;
  left: 50%;
  top: 30%;
  transform: translate(-50%, -50%);
}
.inputStyle {
  margin-bottom: 20px;
}
.selectStyle {
  margin-bottom: 30px;
}
</style>
