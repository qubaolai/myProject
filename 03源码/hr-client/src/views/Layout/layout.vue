<template>
  <div>
    <Layoutheader />
    <Layoutmain />
    <Layoutnavigation />
    asd
  </div>
</template>
<script>
import Layoutheader from "@/components/header";
import Layoutmain from "@/components/main";
import Layoutnavigation from "@/components/navigation";
export default {
  name: "layout",
  components: { Layoutheader, Layoutmain, Layoutnavigation }
};
</script>
<style lang="scss" scoped></style>
