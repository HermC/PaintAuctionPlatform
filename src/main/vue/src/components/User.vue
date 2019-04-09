<template>
  <div class="container">
    <el-tabs>
      <el-tab-pane name="address" label="收货地址">

      </el-tab-pane>
      <el-tab-pane>

      </el-tab-pane>
    </el-tabs>
    <el-row>
      <h3>用户信息</h3>
      <el-form ref="userInfo" :model="userInfo" label-width="100px">

      </el-form>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "User",
    data() {
      return {
        orderInfo: {}
      }
    },
    created() {

    },
    methods: {

    }
  }
</script>

<style scoped>

</style>