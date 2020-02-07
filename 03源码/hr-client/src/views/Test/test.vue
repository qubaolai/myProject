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
                v-for="item in departments"
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
              style="width: 120px;margin-right: 5px;"
              v-model="form.singInStartTime"
              :picker-options="{
                selectableRange: '18:30:00 - 20:30:00'
              }"
              placeholder="任意时间点"
            >
            </el-time-picker>
            <el-time-picker
              style="width: 120px;"
              arrow-control
              v-model="form.singInEndTime"
              :picker-options="{
                selectableRange: '18:30:00 - 20:30:00'
              }"
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
              style="width: 120px;margin-right: 5px;"
              v-model="form.singOutStartTime"
              :picker-options="{
                selectableRange: '18:30:00 - 20:30:00'
              }"
              placeholder="任意时间点"
            >
            </el-time-picker>
            <el-time-picker
              style="width: 120px;"
              arrow-control
              v-model="form.singOutEndTime"
              :picker-options="{
                selectableRange: '18:30:00 - 20:30:00'
              }"
              placeholder="任意时间点"
            >
            </el-time-picker></div
        ></el-col>
      </el-row>
      <el-row :gutter="0" style="margin-bottom: 6px;">
        <el-col :span="24"
          ><div class="grid-content bg-purple rowSty">
            <el-button type="primary" class="buttonStyle">查询</el-button>
            <el-button type="primary" class="buttonStyle">重置</el-button>
          </div></el-col
        >
      </el-row>
    </div>
    <div
      style="width: 100%; height: 3px; background-color: #fff;margin-bottom: 6px;"
    ></div>
    <div style="height: 200%; background-color: #fff">
      <el-row>
        <div>
          <el-table :data="tableData" style="width: 100%;" height="390">
            <el-table-column fixed prop="date" label="签到日期" width="150">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="150">
            </el-table-column>
            <el-table-column prop="province" label="部门" width="150">
            </el-table-column>
            <el-table-column prop="city" label="上班时间" width="150">
            </el-table-column>
            <el-table-column prop="address" label="下班时间" width="150">
            </el-table-column>
            <el-table-column prop="city" label="上班签到" width="150">
            </el-table-column>
            <el-table-column prop="address" label="下班签到" width="150">
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
              <template slot-scope="scope">
                <el-button
                  @click.native.prevent="deleteRow(scope.$index, tableData)"
                  type="text"
                  size="small"
                >
                  移除
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
  </div>
</template>

<script>
import { getEmpInfo } from "@/api/page/getEmpInfo.js";
export default {
  data() {
    return {
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
      empInfos: [
        //第一层
        {
          value: "zhinan",
          label: "指南",
          children: [
            //第二层
            {
              value: "shejiyuanze",
              label: "设计原则"
            },
            {
              value: "daohang",
              label: "导航"
            }
          ]
        }
      ],
      departments: [
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
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1517 弄",
          zip: 200333
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1519 弄",
          zip: 200333
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1516 弄",
          zip: 200333
        }
      ]
    };
  },
  methods: {
    handleClick() {},
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
            console.log(firstData);
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
    }
  },
  created: function() {
    this.initPage();
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
