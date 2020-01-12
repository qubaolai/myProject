<template>
  <div>
    <el-table :data="dataPage" style="width: 100%">
      <el-table-column prop="date" label="日期" width="180"> </el-table-column>
      <el-table-column prop="name" label="姓名" width="180"> </el-table-column>
      <el-table-column prop="address" label="地址"> </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[2, 3, 4, 5]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="sumNum"
    >
    </el-pagination>
  </div>
</template>
<script>
export default {
  name: "console",
  data: () => {
    return {
      //总数
      sumNum: 6,
      //每页数量
      pageSize: 3,
      //当前页
      currentPage: 1,
      tableData: []
    };
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      console.log(`当前页: ${val}`);
    }
  },
  computed: {
    dataPage: function() {
      return this.tableData.slice(
        (this.currentPage - 1) * this.pageSize,
        this.currentPage * this.pageSize
      );
      //   return this.msg
      //     .split("")
      //     .reverse()
      //     .join("");
    }
  }
};
</script>
<style lang="scss" scoped></style>
