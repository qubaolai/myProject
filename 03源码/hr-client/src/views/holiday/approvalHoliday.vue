<template>
  <div class="main">
    <div class="leftMain">
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: 8px;">
            <span class="w2">姓名</span>
            <span>:</span>
            <el-input
              style="width: 75%; margin-left: 5px;"
              placeholder="请输入内容"
              v-model="form.empName"
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
              v-model="form.empNum"
              clearable
            >
            </el-input></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div
            class="grid-content bg-purple"
            style="margin-left:5px;margin-top: -10px;"
          >
            <span>开始日期:</span>
            <el-date-picker
              style="width: 76%; margin-left: 5px; font-size: 10px;"
              v-model="form.startTime"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div
            class="grid-content bg-purple"
            style="margin-left:5px;margin-top: -10px;"
          >
            <span>结束日期:</span>
            <el-date-picker
              style="width: 76%; margin-left: 5px;"
              v-model="form.endTime"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -10px;">
            <span class="w2">部门</span>
            <span>:</span>
            <el-select
              v-model="form.deptNum"
              clearable
              placeholder="请选择"
              style="width: 75%; margin-left: 5px; margin-right: 5px;"
            >
              <el-option
                v-for="item in initData.formData.departments"
                :key="item.value"
                :label="item.label"
                :value="item.label"
              >
              </el-option>
            </el-select></div
        ></el-col>
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div class="grid-content bg-purple" style="margin-top: -10px;">
            <span style="padding-top: 10px;" class="w2">状态</span>
            <span style="padding-top: 10px;">:</span>
            <el-radio
              v-model="form.status"
              label="0"
              style="margin-left: 5px; margin-right: 5px;padding-top: 10px;"
              >未审批</el-radio
            >
            <el-radio
              v-model="form.status"
              label="1"
              style="margin-left: 5px; margin-right: 8px;padding-top: 10px;"
              >已同意</el-radio
            >
            <el-radio v-model="form.status" label="2">未同意</el-radio>
          </div></el-col
        >
      </el-row>
      <el-row :gutter="8">
        <el-col :span="18"
          ><div
            class="grid-content bg-purple"
            style="width: 100%;margin-left: 53%; margin-top: 5px;"
          >
            <el-button @click="getData()">查询</el-button>
            <el-button @click="reset()">重置</el-button>
          </div></el-col
        >
      </el-row>
    </div>
    <div class="rightMain">
      <el-table
        :data="tableData"
        max-height="550"
        height="550"
        style="width: 100%"
      >
        <el-table-column fixed prop="empName" label="姓名" width="80">
        </el-table-column>
        <el-table-column prop="empNum" label="员工编号" width="130">
        </el-table-column>
        <el-table-column prop="deptName" label="部门" width="90">
        </el-table-column>
        <el-table-column prop="startTime" label="开始时间" width="110">
        </el-table-column>
        <el-table-column prop="endTime" label="结束时间" width="110">
        </el-table-column>
        <el-table-column
          prop="reason"
          label="原因"
          width="150"
        ></el-table-column>
        <el-table-column
          prop="status"
          label="状态"
          width="80"
        ></el-table-column>
        <el-table-column
          prop="notes"
          label="审批意见"
          width="100"
        ></el-table-column>
        <el-table-column fixed="right" label="操作" width="105">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="agree(scope.row)"
              type="text"
              size="small"
              :disabled="scope.row.status != '未审批'"
            >
              同意
            </el-button>
            <el-button
              @click.native.prevent="noagree(scope.row)"
              type="text"
              size="small"
              :disabled="scope.row.status != '未审批'"
            >
              不同意
            </el-button>
          </template>
        </el-table-column>
      </el-table>
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
    </div>
  </div>
</template>
<script>
import { initPage } from "@/api/page/init.js";
import formData from "@/store/data.js";
import { getLeasByConditions } from "@/api/lea/getLeasByConditions.js";
import { approvalHoliday } from "@/api/lea/approvalHoliday.js";
export default {
  name: "insertEmployee",
  data: () => {
    return {
      //分页
      //显示数据总数
      sumNum: 0,
      //每页数量
      pageSize: 10,
      //当前页
      currentPage: 1,
      initData: formData,
      form: {
        empName: "",
        empNum: "",
        startTime: "",
        endTime: "",
        deptNum: "",
        status: ""
      },
      tableData: [
        {
          leaId: "",
          empName: "王小虎",
          empNum: "wangxiaohu",
          deptNmae: "脑外科",
          startTime: "2016-05-03",
          endTime: "2016-05-03",
          reason: "就是不想上班",
          status: "同意"
        }
      ],
      //页面绑定的部门列表
      departments: []
    };
  },
  methods: {
    getData() {
      getLeasByConditions(this.form).then(response => {
        const data = response.data;
        if (data.code === 200) {
          const tableList = data.data;
          for (let i = 0; i < tableList.length; i++) {
            if (tableList[i].status === 0) {
              tableList[i].status = "未审批";
            }
            if (tableList[i].status === 1) {
              tableList[i].status = "已同意";
            }
            if (tableList[i].status === 2) {
              tableList[i].status = "未同意";
            }
          }
          this.tableData = tableList;
        } else {
          this.$message({
            type: "error",
            message: data.msg
          });
        }
      });
    },
    //同意按钮
    agree(rowData) {
      const param = {
        status: 1,
        leaId: rowData.id
      };
      approvalHoliday(param).then(response => {
        const data = response.data;
        if (data.code === 200) {
          this.$message({
            type: "success",
            message: "审批成功!"
          });
          this.getData();
        }
      });
    },
    //不同意
    noagree(rowData) {
      this.$prompt("请输入不通过原因", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /^[\s\S]*.*[^\s][\s\S]*$/,
        inputErrorMessage: "审批原因不可为空!"
      })
        .then(({ value }) => {
          const param = {
            status: 2,
            leaId: rowData.id,
            notes: value
          };
          approvalHoliday(param).then(response => {
            const data = response.data;
            if (data.code === 200) {
              this.$message({
                type: "success",
                message: "审批成功!"
              });
              this.getData();
            } else {
              this.$message({
                type: "error",
                message: data.msg
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入"
          });
        });
    },
    reset() {
      this.form.empName = "";
      this.form.empNum = "";
      this.form.startTime = "";
      this.form.endTime = "";
      this.form.deptNum = "";
      this.form.status = "";
    },
    //页面加载完成调用的初始化方法
    init() {
      //页面初始化 发送请求获取所有部门和职称
      initPage();
      this.initData = formData;
    },
    handleSizeChange(val) {
      this.pageSize = val;
    },
    handleCurrentChange(val) {
      this.currentPage = val;
    }
  },
  created: function() {
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
<style lang="scss" scoped>
.page {
  position: fixed;
  bottom: 10px;
  left: 50%;
}
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
  width: 35%;
  float: left;
  padding-left: 10px;
  box-flex: 1;
  margin-right: 1%;
  padding-top: 10px;
}
.rightMain {
  margin-top: 20px;
  width: 63%;
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
