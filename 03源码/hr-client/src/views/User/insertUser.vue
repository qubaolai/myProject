<template>
  <div class="main">
    <div class="leftMain">
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: 8px;">
            <!-- <span class="w2">姓名</span>
            <span>:</span> -->
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="员工姓名:" prop="name">
                <el-input
                  style="width: 75%;"
                  placeholder="请输入内容"
                  v-model="form.name"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-form></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top:-10;">
            <!-- <span>员工编号:</span> -->
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="员工编号:" prop="username">
                <el-input
                  style="width: 75%;"
                  placeholder="请输入内容"
                  v-model="form.username"
                  clearable
                  @blur="checkUserName"
                >
                </el-input>
              </el-form-item>
            </el-form></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top:-10;">
            <!-- <span>联系方式:</span> -->
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="联系方式:" prop="telephone">
                <el-input
                  style="width: 75%;"
                  placeholder="请输入内容"
                  v-model="form.telephone"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-form></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -10px;">
            <!-- <span style="padding-top: 10px;" class="w2">性别</span>
            <span style="padding-top: 10px;">:</span> -->
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="员工性别:" prop="gender">
                <el-radio
                  v-model="form.gender"
                  label="0"
                  style="margin-left: 5px;padding-top: 10px;"
                  >男</el-radio
                >
                <el-radio v-model="form.gender" label="1">女</el-radio>
              </el-form-item>
            </el-form>
          </div></el-col
        >
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -15px;">
            <!-- <span class="w2">生日</span>
            <span>:</span> -->
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="员工生日:" prop="birthday">
                <el-date-picker
                  v-model="form.birthday"
                  type="date"
                  placeholder="选择日期"
                  style="width: 75%; margin-right: 5px;"
                >
                </el-date-picker>
              </el-form-item>
            </el-form></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple">
            <!-- <span class="w2">地址</span>
            <span>:</span> -->
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="员工地址:" prop="address">
                <el-input
                  style="width: 75%;"
                  placeholder="请输入内容"
                  v-model="form.address"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-form></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple">
            <!-- <span class="w2">部门</span>
            <span>:</span> -->
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="员工部门:" prop="departmentNumber">
                <el-select
                  v-model="form.departmentNumber"
                  clearable
                  placeholder="请选择"
                  style="width: 75%; margin-right: 5px;"
                >
                  <el-option
                    v-for="item in initData.formData.departments"
                    :key="item.value"
                    :label="item.label"
                    :value="item.label"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-form></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple">
            <!-- <span class="w2">职称</span>
            <span>:</span> -->
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="员工职称:" prop="positionNumber">
                <el-select
                  v-model="form.positionNumber"
                  clearable
                  placeholder="请选择"
                  style="width: 75%; margin-right: 5px;"
                >
                  <el-option
                    v-for="item in initData.formData.options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.label"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-form></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple">
            <!-- <span class="w2">学历</span>
            <span>:</span> -->
            <el-form
              :model="form"
              status-icon
              :rules="rules"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item label="员工学历:" prop="education">
                <el-select
                  v-model="form.education"
                  clearable
                  placeholder="请选择"
                  style="width: 75%; margin-right: 5px;"
                >
                  <el-option
                    v-for="item in initData.formData.educations"
                    :key="item.value"
                    :label="item.label"
                    :value="item.label"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-form></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div
            class="grid-content bg-purple"
            style="width: 100%;margin-left: 10%; margin-top: 5px;"
          >
            <el-button @click="addEmp" :disabled="canInsert">添加</el-button>
            <el-button @click="reset">重置</el-button>
            <el-button
              @click="determine"
              v-text="determineStatu === true ? '确定' : '提交'"
            ></el-button></div
        ></el-col>
      </el-row>
    </div>
    <div class="rightMain">
      <el-table :data="tableData" style="width: 100%" height="750">
        <el-table-column label="姓名" width="100" style="margin-left: -20px;">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <p>姓名: {{ scope.row.name }}</p>
              <p>员工编号: {{ scope.row.username }}</p>
              <p>性别: {{ scope.row.gender }}</p>
              <p>联系方式: {{ scope.row.telephone }}</p>
              <p>生日: {{ scope.row.birthday }}</p>
              <p>地址: {{ scope.row.address }}</p>
              <p>部门: {{ scope.row.departmentNumber }}</p>
              <p>职称: {{ scope.row.positionNumber }}</p>
              <p>学历: {{ scope.row.education }}</p>
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.name }}</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="员工编号" width="110">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="mini">{{ scope.row.username }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="性别" width="70">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.gender }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="部门" width="100">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.departmentNumber }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="职称" width="100">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.positionNumber }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              style="margin-left: 3px;"
              @click="handleDelete(scope.$index)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import router from "@/router/index.js";
import { initPage } from "@/api/page/init.js";
import formData from "@/store/data.js";
import { insertEmp } from "@/api/user/insertEmployee.js";
import { checkEmpNum } from "@/api/user/checkEmpNum.js";
export default {
  name: "insertEmployee",
  data: () => {
    return {
      rules: {
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          {
            pattern: /[\u4e00-\u9fa5]{2,8}$/,
            message: "长度在 2 到 8 个汉字",
            trigger: "blur"
          }
        ],
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { pattern: /^[0-9a-zA-Z]+$/, message: "用户名由英文与数字组合" }
        ],
        telephone: [
          { required: true, message: "请输入联系方式", trigger: "blur" },
          {
            pattern: /^1[3|4|5|7|8][0-9]{9}$/,
            message: "联系方式为中国大陆手机号码"
          }
        ],
        gender: [{ required: true, message: "请选择性别", trigger: "blur" }],
        birthday: [
          {
            type: "date",
            required: true,
            message: "请选择时间",
            trigger: "blur"
          }
        ],
        address: [
          { required: true, message: "请输入地址", trigger: "blur" },
          {
            pattern: /[\u4e00-\u9fa5a-zA-Z0-9]{6,20}/,
            message: "长度在 6 到 20 个汉字、字母、数字,且不能包含特殊字符",
            trigger: "blur"
          }
        ],
        departmentNumber: [
          { required: true, message: "请选择员工部门", trigger: "blur" }
        ],
        positionNumber: [
          { required: true, message: "请选择员工职称", trigger: "blur" }
        ],
        education: [
          { required: true, message: "请选择员工学历", trigger: "blur" }
        ]
      },
      //是否允许添加员工
      canInsert: false,
      //按钮功能为提交还是确定
      determineStatu: false,
      //当前数据下标
      tableindex: "",
      //提交表单
      form: {
        name: "",
        username: "",
        telephone: "",
        gender: "0",
        birthday: "",
        address: "",
        education: "",
        departmentNumber: "",
        positionNumber: ""
      },
      //初始化页面的部门和职称等
      initData: formData,
      //展示区已添加的数据
      tableData: [
        {
          name: "王小虎",
          username: "wangxiaoghuang",
          telephone: "13897968646",
          gender: "男",
          birthday: "2016-05-02",
          address: "上海市普陀区金沙江路 1518 弄",
          education: "本科",
          departmentNumber: "骨伤外科",
          positionNumber: "主治医师"
        },
        {
          name: "王小虎",
          username: "wangxiaohu",
          telephone: "13897968646",
          gender: "男",
          birthday: "2016-05-02",
          address: "上海市普陀区金沙江路 1518 弄",
          education: "本科",
          departmentNumber: "ni",
          positionNumber: "主治医师"
        }
      ],
      //页面绑定的部门列表
      departments: [],
      //页面绑定的职称列表
      positions: [],
      //页面绑定的学历列表
      educations: []
    };
  },
  methods: {
    //用于向右侧展示区添加待插入数据
    addEmp() {
      if (this.tableData.length >= 10) {
        this.$message.error("最多添加10条记录!");
      } else {
        const table = {
          name: this.form.name,
          username: this.form.username,
          telephone: this.form.telephone,
          gender: this.form.gender === "0" ? "男" : "女",
          birthday: this.form.birthday,
          address: this.form.address,
          education: this.form.education,
          departmentNumber: this.form.departmentNumber,
          positionNumber: this.form.positionNumber
        };
        this.tableData.push(table);
        this.reset();
      }
    },
    //页面加载完成调用的初始化方法
    init() {
      //页面初始化 发送请求获取所有部门和职称
      initPage();
      this.initData = formData;
    },
    //返回上一个页面
    goBack() {
      router.back(-1);
    },
    //编辑按钮
    handleEdit(index, row) {
      this.canInsert = true;
      this.form.name = row.name;
      this.form.username = row.username;
      this.form.telephone = row.telephone;
      this.form.gender = row.gender == "男" ? "0" : "1";
      this.form.birthday = row.birthday;
      this.form.address = row.address;
      this.form.education = row.education;
      this.form.departmentNumber = row.departmentNumber;
      this.form.positionNumber = row.positionNumber;
      this.tableindex = index;
      this.determineStatu = !this.determineStatu;
    },
    //删除按钮
    handleDelete(index) {
      this.tableData.splice(index, 1);
      if (index == parseInt(this.tableindex)) {
        this.reset();
      }
      if (this.tableData.length === 0) {
        this.reset();
      }
    },
    //提交和确认按钮方法
    determine() {
      //determineStatu为true时,功能为向右侧展示区修改记录
      if (this.determineStatu) {
        this.canInsert = false;
        const table = {
          name: this.form.name,
          username: this.form.username,
          telephone: this.form.telephone,
          gender: this.form.gender === "0" ? "男" : "女",
          birthday: this.form.birthday,
          address: this.form.address,
          education: this.form.education,
          departmentNumber: this.form.departmentNumber,
          positionNumber: this.form.positionNumber
        };
        if (this.tableindex !== "") {
          this.determineStatu = !this.determineStatu;
          this.tableData.splice(this.tableindex, 1, table);
          this.reset();
        }
      } else {
        //向后台发送请求插入数据
        if (this.tableData.length > 0) {
          const options = this.initData.formData.options;
          const department = this.initData.formData.departments;
          for (let i = 0; i < options.length; i++) {
            for (let j = 0; j < this.tableData.length; j++) {
              if (this.tableData[j].positionNumber == options[i].label) {
                this.tableData[j].positionNumber = options[i].value;
              }
            }
          }
          for (let i = 0; i < department.length; i++) {
            for (let j = 0; j < this.tableData.length; j++) {
              if (this.tableData[j].departmentNumber == department[i].label) {
                this.tableData[j].departmentNumber = department[i].value;
              }
            }
          }
          for (let i = 0; i < department.length; i++) {
            for (let j = 0; j < this.tableData.length; j++) {
              if (this.tableData[j].gender == "男") {
                this.tableData[j].gender = 0;
              } else {
                this.tableData[j].gender = 1;
              }
            }
          }
          //声明传入后台的对象参数,springmvc转换json时需要通过key获取值 不能直接传对象数组到后台
          const emplist = {
            employees: []
          };
          emplist.employees = this.tableData;
          this.tableData = [];
          //调用后台api接口
          insertEmp(emplist).then(response => {
            const data = response.data;
            if (data.code === 200) {
              this.$message({
                message: "添加成功",
                type: "success"
              });
            }
          });
          this.reset();
        } else {
          this.$message.error("提交数据为空!");
        }
      }
    },
    reset() {
      //是否允许添加员工
      this.canInsert = false;
      //按钮功能为提交还是确定
      this.determineStatu = false;
      //当前数据下标
      this.tableindex = "";
      this.form.name = "";
      this.form.username = "";
      this.form.telephone = "";
      this.form.gender = "0";
      this.form.birthday = "";
      this.form.address = "";
      this.form.education = "";
      this.form.departmentNumber = "";
      this.form.positionNumber = "";
      this.tableindex = "";
    },
    checkUserName() {
      if (this.form.username !== null && this.form.username !== "") {
        checkEmpNum(this.form.username).then(response => {
          const data = response.data;
          debugger;
          if (data.code !== 200) {
            this.$alert("员工编号重复", {
              confirmButtonText: "确定"
            });
          }
        });
      }
    }
  },
  created: function() {
    this.init();
  }
};
</script>
<style lang="scss" scoped>
span {
  display: inner-block;
  margin-right: 2px;
}
.w2 {
  width: 25%;
  margin-left: 5px;
  letter-spacing: 2em;
  margin-right: -2em;
}
.main {
  width: 100%;
  display: box;
  box-orient: horizontal;
  float: left;
  font-size: 14px;
}
.leftMain {
  width: 40%;
  float: left;
  padding-left: 10px;
  box-flex: 1;
  margin-right: 1%;
  padding-top: 10px;
}
.rightMain {
  margin-top: 20px;
  width: 50%;
  float: left;
  box-flex: 1;
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
  min-height: 36px;
}
.row-bg {
  padding: 5px 0;
  background-color: #f9fafc;
}
</style>
