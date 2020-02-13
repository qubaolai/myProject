<template>
  <div class="basic-grey">
    <div
      style="margin-bottom: 10px; background-color: #fff; width: 99%; padding: 3px; border-radius: 5px; padding-left: 8px; height: 70px;"
    >
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="grid-content bg-purple rowStyle">
            <span>部门名称:</span>
            <el-input
              class="formStyle"
              placeholder="部门名称"
              v-model="form.departmentName"
              clearable
            ></el-input>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple rowStyle">
            <span>领导姓名:</span>
            <el-input
              class="formStyle"
              placeholder="领导姓名"
              v-model="form.mangerName"
              clearable
            ></el-input>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple rowStyle">
            <span>电话:</span>
            <el-input
              class="formStyle"
              placeholder="电话"
              v-model="form.departmentTel"
              clearable
            ></el-input>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple rowStyle">
            <el-button
              class="buttonStyle"
              type="primary"
              :disabled="disable"
              @click="submitForm()"
              >查询</el-button
            >
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
        <div>
          <el-table
            v-loading="loading"
            height="400"
            v-show="adminShow"
            :data="pageData"
            style="width: 100%; padding-left: 50px;padding-right: 50px;"
          >
            <el-table-column type="index"> </el-table-column>
            <el-table-column label="部门名称" width="300">
              <template slot-scope="scope">
                <span>{{ scope.row.departmentName }}</span>
              </template>
            </el-table-column>
            <el-table-column label="领导姓名" width="300">
              <template slot-scope="scope">
                <span>{{ scope.row.manageName }}</span>
              </template>
            </el-table-column>
            <el-table-column label="部门电话" width="300">
              <template slot-scope="scope">
                <span>{{ scope.row.departmentTel }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              style="margin-right: 0;float: center;"
            >
              <template slot-scope="scope">
                <el-button size="mini" @click="handleEdit(scope.row)"
                  >编辑</el-button
                >
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.row)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>

          <el-table
            v-show="userShow"
            :data="pageData"
            style="width: 100%; padding-left: 50px;padding-right: 50px;"
          >
            <el-table-column type="index"> </el-table-column>
            <el-table-column label="部门名称" width="400">
              <template slot-scope="scope">
                <span>{{ scope.row.departmentName }}</span>
              </template>
            </el-table-column>
            <el-table-column label="领导姓名" width="350">
              <template slot-scope="scope">
                <span>{{ scope.row.manageName }}</span>
              </template>
            </el-table-column>
            <el-table-column label="部门电话" width="350">
              <template slot-scope="scope">
                <span>{{ scope.row.departmentTel }}</span>
              </template>
            </el-table-column>
          </el-table>
        </div>
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

    <el-dialog
      title="编辑部门"
      :visible.sync="dialogFormVisible"
      :append-to-body="true"
    >
      <el-form>
        <el-form-item
          label="部门名称:"
          :label-width="formLabelWidth"
          style="margin-bottom: 10px;"
        >
          <el-input
            style="width: 250px;"
            v-model="dialogForm.departmentName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="部门电话:"
          style="margin-bottom: 10px;"
          :label-width="formLabelWidth"
        >
          <el-input
            style="width: 250px;"
            v-model="dialogForm.departmentTel"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="部门领导:" :label-width="formLabelWidth">
          <el-select
            v-model="dialogForm.manageName"
            clearable
            placeholder="请选择部门领导"
            style="width:250px;"
          >
            <el-option
              v-for="item in manageList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateDept()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { getDeptList } from "@/api/department/getDepartments.js";
import { deleteDept } from "@/api/department/deleteDepartment.js";
import { updateDept } from "@/api/department/updateDepartment.js";
import { getEmps } from "@/api/user/getEmployees.js";
export default {
  name: "userList",
  data() {
    return {
      loading: false,
      adminShow: true,
      userShow: false,
      //弹层
      dialogFormVisible: false,
      formLabelWidth: "240px",
      //分页
      //显示数据总数
      sumNum: 0,
      //每页数量
      pageSize: 10,
      //当前页
      currentPage: 1,
      //分页
      //查询按钮状态
      disable: false,
      //页面表单start
      form: {
        departmentName: "",
        manageName: "",
        departmentTel: ""
      },
      //弹层数据
      dialogForm: {
        id: "",
        departmentName: "",
        departmentTel: "",
        manageName: ""
      },
      manageList: [],
      //查询数据容器
      tableData: []
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
    submitForm() {
      this.loading = true;
      this.tableData = [];
      getDeptList(this.form).then(response => {
        this.loading = false;
        const data = response.data;
        if (data.code !== 200) {
          this.$message({
            message: data.msg,
            type: "warning"
          });
        }
        if (data.code == 200) {
          const tableInfo = data.data;
          this.sumNum = tableInfo.length;
          for (let i = 0; i < tableInfo.length; i++) {
            const table = {
              id: "",
              departmentName: "",
              manageName: "",
              departmentTel: ""
            };
            table.id = tableInfo[i].id;
            table.departmentName = tableInfo[i].name;
            if (tableInfo[i].employee != null && tableInfo[i].employee != "") {
              table.manageName = tableInfo[i].employee.name;
            } else {
              table.manageName = "无";
            }
            table.departmentTel = tableInfo[i].telephone;
            this.tableData.push(table);
          }
        }
      });
    },
    //删除按钮
    handleDelete(table) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteDept(table.id).then(response => {
            const data = response.data;
            if (data.code == 200) {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              this.submitForm();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    //编辑按钮
    handleEdit(table) {
      this.dialogFormVisible = true;
      this.dialogForm.id = table.id;
      this.dialogForm.departmentName = table.departmentName;
      this.dialogForm.departmentTel = table.departmentTel;
      this.dialogForm.manageName = table.manageName;
      //查询所有员工
      this.manageList = [];
      getEmps().then(response => {
        const data = response.data.data;
        for (let i = 0; i < data.length; i++) {
          const marage = {
            value: "",
            label: ""
          };
          marage.value = data[i].id;
          marage.label = data[i].name;
          this.manageList.push(marage);
        }
      });
    },
    updateDept() {
      this.dialogFormVisible = false;
      updateDept(this.dialogForm).then(response => {
        const data = response.data;
        if (data.code == 200) {
          this.$message({
            type: "success",
            message: "修改成功!"
          });
          this.submitForm();
        }
      });
    },
    reset() {}
  },
  created: function() {
    const user = JSON.parse(sessionStorage.getItem("user"));
    if (user.role == 0) {
      this.adminShow = true;
      this.userShow = false;
    } else {
      this.adminShow = false;
      this.userShow = true;
    }
    this.submitForm();
  },
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
<style lang="scss" scoped>
.main-content[data-v-5c886d6e] {
  width: 100%;
  padding-top: 3px;
  padding-left: 3px;
  padding-right: 3px;
}
</style>
<style lang="scss" scoped>
.page {
  float: left;
  position: fixed;
  right: 20%;
  bottom: 20px;
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
  padding: 16px 7px 25px 10px;
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
.el-row {
  margin-top: 16px;
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
