<template>
  <div class="md-cont" v-show="showstate">
    <div class="md-wrapper">
      <div class="md-title">
        <slot name="tlt">标题</slot>
      </div>
      <div class="md-text">
        <span class="spanStyle">类型:</span>
        <div
          class="block"
          style="margin-top: 15px;     position: relative;
    left: -46px;"
        >
          <el-select v-model="applyType" clearable placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </div>
      </div>
      <div class="md-text">
        <span class="spanStyle">时间:</span>
        <div class="block" style="margin-top: 15px;">
          <el-date-picker
            v-model="value"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </div>
      </div>
      <div class="md-text">
        <span class="spanStyle">原因:</span>
        <div class="block" style="margin-top: 15px;">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入请假原因"
            v-model="textarea"
          ></el-input>
        </div>
      </div>
      <div class="footer">
        <el-button type="primary" @click="took" plain>确定</el-button>
        <el-button type="primary" @click="tocancel" plain>取消</el-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "modal",
  props: ["showstate"],
  data: () => {
    return {
      value: "",
      textarea: "",
      options: [
        {
          value: "0",
          label: "事假"
        },
        {
          value: "1",
          label: "病假"
        }
      ],
      applyType: ""
    };
  },
  methods: {
    tocancel: function() {
      this.$emit("tocancel");
    },
    took: function() {
      this.$emit("took");
    }
  }
};
</script>
<style scoped>
.spanStyle {
  font-size: 16px;
  display: inline-block;
  position: relative;
  left: -195px;
  top: 40px;
}
.md-cont {
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  z-index: 500;
  background: rgba(0, 0, 0, 0.3);
  text-align: center;
  overflow: hidden;
  white-space: nowrap;
}
.md-cont:after {
  content: "";
  display: inline-block;
  width: 0;
  height: 100%;
  visibility: hidden;
  vertical-align: middle;
}
.md-wrapper {
  width: 600px;
  height: 320px;
  display: inline-block;
  vertical-align: middle;
  background: #fff;
  color: #333333;
  font-size: 24px;
  padding-top: 0.2rem;
  border-radius: 0.1rem;
  max-width: 100%;
}
.md-title {
  font-size: 18px;
  text-align: left;
  line-height: 18px;
  position: relative;
  left: 40px;
  top: 45px;
}
.md-text {
  font-size: 0.25rem;
  text-align: center;
  line-height: 0.4rem;
  padding: 0.2rem 9.5rem;
}
.footer {
  display: flex;
  line-height: 0.88rem;
  color: #488bf1;
  font-size: 0.32rem;
  margin-top: 30px;
  padding-left: 38%;
}
</style>
