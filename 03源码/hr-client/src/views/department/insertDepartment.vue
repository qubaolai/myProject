<template>
  <div class="main">
    <div style="margin-top: 10px;">
      <el-page-header
        @back="goBack"
        content="添加员工"
        style="margin-left:10px;width: 40%;"
      >
      </el-page-header>
    </div>
    <div class="leftMain">
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: 8px;">
            <span class="w2">姓名</span>
            <span>:</span>
            <el-input
              style="width: 75%; margin-left: 5px;"
              placeholder="请输入内容"
              v-model="form.name"
              clearable
            >
            </el-input></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div
            class="grid-content bg-purple"
            style="margin-left:5px;margin-top:-10;"
          >
            <span>员工编号:</span>
            <el-input
              style="width: 76%; margin-left: 5px;"
              placeholder="请输入内容"
              v-model="form.username"
              clearable
            >
            </el-input></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div
            class="grid-content bg-purple"
            style="margin-left:5px; margin-top:-10;"
          >
            <span>联系方式:</span>
            <el-input
              style="width: 76%; margin-left: 5px;"
              placeholder="请输入内容"
              v-model="form.telephone"
              clearable
            >
            </el-input></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -10px;">
            <span style="padding-top: 10px;" class="w2">性别</span>
            <span style="padding-top: 10px;">:</span>
            <el-radio
              v-model="form.gender"
              label="1"
              style="margin-left: 5px; margin-right: 5px;padding-top: 10px;"
              >男</el-radio
            >
            <el-radio v-model="form.gender" label="2">女</el-radio>
          </div></el-col
        >
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -10px;">
            <span class="w2">生日</span>
            <span>:</span>
            <el-date-picker
              v-model="form.birthday"
              type="date"
              placeholder="选择日期"
              style="width: 74%; margin-left: 5px; margin-right: 5px;"
            >
            </el-date-picker></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -10px;">
            <span class="w2">地址</span>
            <span>:</span>
            <el-cascader
              style="width: 74%; margin-left: 5px; margin-right: 5px;"
              v-model="form.address"
              :options="options"
              :props="{ expandTrigger: 'hover' }"
              @change="handleChange"
            ></el-cascader></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -10px;">
            <span class="w2">部门</span>
            <span>:</span>
            <el-select
              v-model="form.departmentNumber"
              clearable
              placeholder="请选择"
              style="width: 74%; margin-left: 5px; margin-right: 5px;"
            >
              <el-option
                v-for="item in departments"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -10px;">
            <span class="w2">职称</span>
            <span>:</span>
            <el-select
              v-model="form.positionNumber"
              clearable
              placeholder="请选择"
              style="width: 74%; margin-left: 5px; margin-right: 5px;"
            >
              <el-option
                v-for="item in positions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -10px;">
            <span class="w2">学历</span>
            <span>:</span>
            <el-select
              v-model="form.education"
              clearable
              placeholder="请选择"
              style="width: 74%; margin-left: 5px; margin-right: 5px;"
            >
              <el-option
                v-for="item in educations"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div
            class="grid-content bg-purple"
            style="width: 74%;margin-left: 10%; margin-top: 5px;"
          >
            <el-button style="margin-right: 50px;">默认按钮</el-button>
            <el-button>默认按钮</el-button>
          </div></el-col
        >
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
        <el-table-column label="员工编号" width="130">
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
              @click="handleDelete(scope.$index, scope.row)"
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
export default {
  name: "test",
  data: () => {
    return {
      form: {
        name: "",
        username: "",
        telephone: "",
        gender: "1",
        birthday: "",
        address: "",
        education: "",
        departmentNumber: "",
        positionNumber: ""
      },
      input: "",
      value: [],
      options: [
        {
          value: "zhinan",
          label: "指南",
          children: [
            {
              value: "shejiyuanze",
              label: "设计原则",
              children: [
                {
                  value: "yizhi",
                  label: "一致"
                },
                {
                  value: "fankui",
                  label: "反馈"
                }
              ]
            }
          ]
        },
        {
          value: "zujian",
          label: "组件",
          children: [
            {
              value: "basic",
              label: "Basic",
              children: [
                {
                  value: "layout",
                  label: "Layout 布局"
                },
                {
                  value: "color",
                  label: "Color 色彩"
                }
              ]
            },
            {
              value: "form",
              label: "Form",
              children: [
                {
                  value: "radio",
                  label: "Radio 单选框"
                },
                {
                  value: "checkbox",
                  label: "Checkbox 多选框"
                }
              ]
            }
          ]
        }
      ],
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
          departmentNumber: "骨伤外科",
          positionNumber: "主治医师"
        }
      ],
      departments: [],
      positions: [],
      educations: []
    };
  },
  methods: {
    goBack() {
      router.back(-1);
    },
    handleChange(value) {
      console.log(value);
    }
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
// .bg-purple-dark {
//   background: #99a9bf;
// }
// .bg-purple {
//   background: #d3dce6;
// }
// .bg-purple-light {
//   background: #e5e9f2;
// }
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 5px 0;
  background-color: #f9fafc;
}
</style>
