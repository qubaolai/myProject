<template>
  <div>
    <div class="aft-box">
      <div class="aft-flex aft-pd">
        <div class="aft-btn aft-blue" @click="confirms">confirm</div>
      </div>
    </div>
    <Modal
      type="confirm"
      @took="okfall2"
      @tocancel="cancelfall"
      :showstate="showc"
    >
      <span slot="tlt">假期时间:</span>
    </Modal>
  </div>
</template>
<script>
import Modal from "@/components/load.vue";
export default {
  name: "container",
  components: {
    Modal
  },
  data() {
    return {
      showa: false,
      showc: false
    };
  },
  methods: {
    confirms() {
      this.showc = true;
    },
    okfall() {
      this.showa = false;
    },
    okfall2() {
      this.showc = false;
      alert("tijiao");
    },
    cancelfall() {
      this.showc = false;
    }
  }
};
</script>
<style>
.aft-box {
  display: flex;
  line-height: 0.65rem;
  font-size: 0.26rem;
  color: #333;
  padding: 0.5rem 0;
}
.aft-flex {
  flex: 1;
}
.aft-pd {
  padding: 0.5rem 0.1rem;
}
.aft-btn {
  display: block;
  line-height: 0.88rem;
  text-align: Center;
  color: #fff;
  border-radius: 0.12rem;
  background: #ffb63b;
}
.aft-blue {
  background: #488bf1;
}
</style>
