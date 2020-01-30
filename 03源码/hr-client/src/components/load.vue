<template>
  <div class="md-cont" v-show="showstate">
    <div class="md-wrapper">
      <div class="md-title">
        <slot name="tlt">标题</slot>
      </div>
      <div class="md-text">
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
      <div class="footer">
        <el-button type="primary" @click="tocancel" plain>取消</el-button>
        <el-button type="primary" @click="took" plain>确定</el-button>
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
      value: ""
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
  width: 400px;
  height: 200px;
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
}
.md-text {
  font-size: 0.25rem;
  text-align: center;
  line-height: 0.4rem;
  padding: 0.2rem 0.5rem;
}
.footer {
  display: flex;
  line-height: 0.88rem;
  color: #488bf1;
  font-size: 0.32rem;
  margin-top: 30px;
  padding-left: 32%;
}
</style>
