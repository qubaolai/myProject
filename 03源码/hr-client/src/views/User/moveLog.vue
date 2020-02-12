<template>
  <div class="basic-grey">
    <div style="background-color: #fff;margin-bottom: 10px;">
      <el-row
        :gutter="20"
        style="padding-top: 10px;margin-bottom: 10px;position: relative;left: 5%;"
      >
        <el-col :span="6">
          <div class="grid-content bg-purple rowStyle">
            <span>姓名:</span>
            <el-cascader
              class="formStyle"
              :options="empInfos"
              v-model="form.empName"
              style="width: 204px;"
            >
              <template slot-scope="{ node, data }">
                <span>{{ data.label }}</span>
                <span v-if="!node.isLeaf">({{ data.children.length }}人)</span>
              </template>
            </el-cascader>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple rowStyle">
            <span>编号:</span>
            <el-input
              class="formStyle"
              placeholder="员工编号"
              v-model="form.empNum"
              clearable
            ></el-input>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple rowStyle">
            <span>调动前部门:</span>
            <el-select
              class="formStyle"
              v-model="form.deptNum"
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
        <el-col :span="6">
          <div class="grid-content bg-purple1">
            <el-button class="buttonStyle" type="primary" @click="query()"
              >查询</el-button
            >
          </div>
        </el-col>
      </el-row>
      <el-row
        :gutter="20"
        style="padding-bottom: 10px;position: relative;left: 5%;"
      >
        <el-col :span="6">
          <div class="grid-content bg-purple  rowStyle">
            <span>时间:</span>
            <el-date-picker
              class="formStyle"
              style="width: 204px;"
              v-model="form.moveDate"
              type="daterange"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple rowStyle">
            <span>方式:</span>
            <el-radio class="radio" v-model="form.moveType" label="0"
              >部门</el-radio
            >
            <el-radio class="radio" v-model="form.moveType" label="1"
              >职位</el-radio
            >
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple rowStyle">
            <span>调动前职位:</span>
            <el-select
              class="formStyle"
              v-model="form.position"
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
        <el-col :span="6">
          <div class="grid-content bg-purple1">
            <el-button class="buttonStyle" type="primary" @click="reset()"
              >重置</el-button
            >
          </div>
        </el-col>
      </el-row>
    </div>
    <!-- 数据 -->
    <div
      style="width: 100%; height: 3px; background-color: #fff;margin-bottom: 6px;"
    ></div>
    <div style="height: 200%; background-color: #fff">
      <el-row>
        <div>
          <el-table :data="pageData" style="width: 100%" height="450">
            <el-table-column
              prop="moveDate"
              label="调度日期"
              width="180"
              sortable
            >
            </el-table-column>
            <el-table-column label="调度信息">
              <el-table-column prop="name" label="姓名" width="120">
              </el-table-column>
              <el-table-column label="部门">
                <el-table-column prop="deptBefore" label="调度前" width="140">
                </el-table-column>
                <el-table-column prop="deptAfter" label="调度后" width="140">
                </el-table-column>
              </el-table-column>
              <el-table-column label="职位">
                <el-table-column
                  prop="positionBefore"
                  label="调度前"
                  width="140"
                >
                </el-table-column>
                <el-table-column
                  prop="positionAfter"
                  label="调度后"
                  width="140"
                >
                </el-table-column>
              </el-table-column>
              <el-table-column prop="moveType" label="调度类型">
              </el-table-column>
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
import { initPage } from "@/api/page/init.js";
import formData from "@/store/data.js";
import { formatDate } from "@/utils/dateUtil.js";
import { getMoveLog } from "@/api/user/schedulingUser.js";
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
        deptNum: "",
        position: "",
        moveDate: "",
        moveType: ""
      },
      empInfos: [],
      tableData: []
    };
  },
  methods: {
    query() {
      this.tableData = [];
      if (this.form.moveDate != null && this.form.moveDate != "") {
        this.form.moveDate[0] = formatDate(this.form.moveDate[0]);
        this.form.moveDate[1] = formatDate(this.form.moveDate[1]);
      }
      getMoveLog(this.form).then(response => {
        const data = response.data;
        if (data.code === 200) {
          const dataList = data.data;
          this.sumNum = dataList.length;
          for (let i = 0; i < dataList.length; i++) {
            const table = {
              moveDate: "",
              name: "",
              deptBefore: "",
              deptAfter: "",
              positionBefore: "",
              positionAfter: "",
              moveType: ""
            };
            table.moveDate = dataList[i].moveTime;
            table.name = dataList[i].eName;
            table.deptBefore =
              dataList[i].beforeDept == null ? "无" : dataList[i].beforeDept;
            table.deptAfter =
              dataList[i].afterDept == null ? "无" : dataList[i].afterDept;
            table.positionBefore =
              dataList[i].beforePosition == null
                ? "无"
                : dataList[i].beforePosition;
            table.positionAfter =
              dataList[i].afterPosition == null
                ? "无"
                : dataList[i].afterPosition;
            if (dataList[i].mType === 0) {
              table.moveType = "部门调动";
            }
            if (dataList[i].mType === 1) {
              table.moveType = "职位调动";
            }
            if (dataList[i].mType === 2) {
              table.moveType = "部门与职位同时调动";
            }
            this.tableData.push(table);
          }
        }
      });
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
    reset() {
      this.form.empName = "";
      this.form.empNum = "";
      this.form.deptNum = "";
      this.form.position = "";
      this.form.moveDate = "";
      this.form.moveType = "";
    },
    //页面加载完成调用的初始化方法
    init() {
      //页面初始化 发送请求获取所有部门和职称
      initPage();
      this.initData = formData;
    }
  },
  created: function() {
    this.initPage();
    this.init();
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
  left: 10%;
  width: 100px;
}
.rowSty {
  margin-top: 10px;
  margin-bottom: 10px;
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
.basic-grey {
  height: 100vh;
  color: rgb(99, 97, 97);
  background-color: rgb(245, 243, 243);
  text-shadow: 1px 1px 1px #fff;
  border-radius: 5px;
}
</style>
