<template>
  <div id="layout" :class="navMenu ? 'close' : 'open'">
    <Layoutheader />
    <Layoutmain />
    <Layoutnavigation />
  </div>
</template>
<script>
import Layoutheader from "@/components/header";
import Layoutmain from "@/components/main";
import Layoutnavigation from "@/components/navigation";
export default {
  name: "layout",
  data: () => {
    return {};
  },
  components: { Layoutheader, Layoutmain, Layoutnavigation },
  computed: {
    navMenu: function() {
      return this.$store.state.isCollapse;
    }
  }
};
</script>
<style lang="scss" scoped></style>
