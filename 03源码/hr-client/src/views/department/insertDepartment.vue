<template>
  <div class="main">
    <el-form
      :model="form"
      status-icon
      :rules="rules"
      ref="form"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="部门名称:" prop="deptName" class="formItem">
        <el-input
          placeholder="请输入部门名称"
          v-model="form.deptName"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="部门电话:" prop="deptTel" class="formItem">
        <el-input placeholder="请输入部门电话" v-model="form.deptTel" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="所属职位:" prop="position" class="formItem">
        <el-input placeholder="请输入职位" v-model="form.position" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="部门领导:" prop="manageName" class="formItem">
        <el-select
          v-model="form.manageName"
          filterable
          clearable
          placeholder="请选择部门领导"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="submitForm()">提交</el-button>
        <el-button type="primary" plain @click="reset()">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { getEmps } from "@/api/user/getEmployees.js";
import { insertDept } from "@/api/department/insertDepartment.js";
export default {
  name: "insertDept",
  data() {
    return {
      rules: {
        deptName: [
          { required: true, message: "请输入部门名称", trigger: "blur" },
          {
            pattern: /[\u4e00-\u9fa5]{2,8}$/,
            message: "长度在 2 到 8 个汉字",
            trigger: "blur"
          }
        ],
        deptTel: [
          { required: true, message: "请输入部门电话", trigger: "blur" },
          {
            pattern: /^(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}/,
            message: "格式为固定电话",
            trigger: "blur"
          }
        ],
        manageName: [
          { required: true, message: "请选择部门领导", trigger: "change" }
        ],
        position: [
          { required: true, message: "请输入部门所属职位", trigger: "blur" },
          {
            pattern: /[\u4e00-\u9fa5]{2,8}$/,
            message: "长度在 2 到 8 个汉字",
            trigger: "blur"
          }
        ]
      },
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
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
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
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
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
.formBody {
  position: relative;
  left: -30%;
}
.formItem {
  margin-bottom: 20px;
}
.main {
  text-align: left;
  position: absolute;
  left: 50%;
  top: 30%;
  transform: translate(-50%, -50%);
}
</style>
