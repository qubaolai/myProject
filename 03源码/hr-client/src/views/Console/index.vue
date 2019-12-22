<template>
  <div class="adminBody">
    <!-- 一行 -->
    <el-row :gutter="20">
      <el-col :span="16">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
    <!-- 二行 -->
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <div class="mainBody">
            <div>{{ role }}</div>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
    <!-- 三行 -->
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple" justify align>
          <div class="buttonType">
            <el-button type="primary">主要按钮</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple">
          <div class="buttonType">
            <el-button type="primary">主要按钮</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
    <!-- 四行 -->
    <el-row :gutter="20">
      <el-col :span="4">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="16">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  name: "console",
  data: () => {
    return {
      //页面显示内容start
      role: ""
      //页面显示内容end
    };
  },
  mounted() {
    this.role = JSON.parse(window.localStorage.getItem("user")).name;
    console.log(this.role);
  }
};
</script>
<style lang="scss" scoped>
.adminBody {
  background-color: rgb(236, 236, 236);
}
.el-button--primary {
  margin: 100px;
  width: 170px;
  line-height: 30px;
  text-align: 30px;
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
.grid-content {
  border-radius: 4px;
  min-height: 20vh;
}
.row-bg {
  padding: 10px 0;
}
</style>
