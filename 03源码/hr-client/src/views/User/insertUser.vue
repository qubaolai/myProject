<template>
  <div class="mainStyle">
    <div class="leftMain">
      <el-row>
        <el-col :span="23"
          ><div class="grid-content bg-purple">
            <div class="spanStyle">
              <span>员工编号</span>
            </div>
            <span>&nbsp;:</span>
            <el-input
              class="inputStyle"
              @blur="check()"
              placeholder="员工编号"
              v-model="input"
              clearable
            >
            </el-input></div
        ></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"
          ><div class="grid-content bg-purple">
            <div class="spanStyle">
              <span>员工姓名</span>
            </div>
            <span>&nbsp;:</span>
            <el-input
              class="inputStyle"
              @blur="check()"
              placeholder="员工姓名"
              v-model="input"
              clearable
            ></el-input></div
        ></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"
          ><div class="grid-content bg-purple">
            <div class="spanStyle">
              <span>联系方式</span>
            </div>
            <span>&nbsp;:</span>
            <el-input
              class="inputStyle"
              @blur="check()"
              placeholder="联系方式"
              v-model="input"
              clearable
            ></el-input></div
        ></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"
          ><div class="grid-content bg-purple">
            <div class="spanStyle">
              <span>邮箱</span>
            </div>
            <span>&nbsp;:</span>
            <el-input
              class="inputStyle"
              @blur="check()"
              placeholder="邮箱"
              v-model="input"
              clearable
            ></el-input></div
        ></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"
          ><div class="grid-content bg-purple">
            <div class="spanStyle">
              <span>地址</span>
            </div>
            <span>&nbsp;:</span>
            <el-input
              class="inputStyle"
              @blur="check()"
              placeholder="地址"
              v-model="input"
              clearable
            ></el-input></div
        ></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"><div class="grid-content bg-purple"></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"><div class="grid-content bg-purple"></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"><div class="grid-content bg-purple"></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"><div class="grid-content bg-purple"></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"><div class="grid-content bg-purple"></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="23"><div class="grid-content bg-purple"></div></el-col>
      </el-row>
    </div>
    <div class="rightMain">
      <div style="margin-top: 20px;">
        <el-table :data="tableData" style="width: 100%" height="750">
          <el-table-column label="日期" width="140">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.date }}</span>
            </template>
          </el-table-column>
          <el-table-column label="姓名" width="115">
            <template slot-scope="scope">
              <el-popover trigger="hover" placement="top">
                <p>姓名: {{ scope.row.name }}</p>
                <p>住址: {{ scope.row.address }}</p>
                <p>姓名: {{ scope.row.name }}</p>
                <p>住址: {{ scope.row.address }}</p>
                <p>姓名: {{ scope.row.name }}</p>
                <p>住址: {{ scope.row.address }}</p>
                <p>姓名: {{ scope.row.name }}</p>
                <p>住址: {{ scope.row.address }}</p>
                <p>姓名: {{ scope.row.name }}</p>
                <p>住址: {{ scope.row.address }}</p>
                <div slot="reference" class="name-wrapper">
                  <el-tag size="medium">{{ scope.row.name }}</el-tag>
                </div>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column label="姓名" width="115">
            <template slot-scope="scope">
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.name }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="姓名" width="115">
            <template slot-scope="scope">
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.name }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="姓名" width="115">
            <template slot-scope="scope">
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.name }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)"
                >编辑</el-button
              >
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "insertUser",
  data() {
    return {
      input: "",
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        }
      ]
    };
  },
  methods: {
    check() {
      alert(123);
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    }
  }
};
</script>
<style>
.el-table th > .cell {
  display: inline-block;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  position: relative;
  vertical-align: middle;
  padding-left: 54px;
  padding-right: 0px;
  width: 100%;
}
</style>
<style lang="scss" scoped>
.spanStyle {
  width: 70px;
  margin: auto;
  text-align: justify;
  text-align-last: justify;
  display: inline-block;
}
.spanStyle span {
  font-size: 15px;
  color: #373738;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-align: justify;
}
.inputStyle {
    margin-left: 4px;
  width: 300px;
}
.mainStyle {
  padding-left: 34px;
  padding-top: 20px;
  background-color: brown;
  margin-bottom: 3px;
  height: 1000px;
}
.leftMain {
  width: 780px;
  height: 800px;
  float: left;
  background-color: aqua;

  text-align: justify;
  font-size: 15px;
}
.rightMain {
  width: 780px;
  height: 800px;
  float: left;
  background-color: blue;
}
.el-row {
  margin-bottom: 10px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  margin-left: 30px;
  margin-top: 20px;
  margin-bottom: -5px;
  height: 45px;
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
