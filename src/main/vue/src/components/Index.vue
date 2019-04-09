<template>
  <el-container>
    <el-header class="nav-bar" style="min-height: 100px" height="auto">
      <nav class="nav-bar-default">
        <div class="container">
          <div class="nav-bar-header">
            <a class="nav-bar-brand">
              公益售卖
            </a>
            <div class="nav-bar-user">
              <el-menu mode="horizontal" :router="true">
                <el-menu-item index="1" v-if="isGuest" :route="{ name: 'passport' }">
                  登录
                </el-menu-item>
                <el-menu-item index="2" v-if="isGuest" :route="{ name: 'passport' }">
                  注册
                </el-menu-item>
                <el-menu-item index="3" v-if="!isGuest" @click="logout">
                  登出
                </el-menu-item>
              </el-menu>
            </div>
          </div>
        </div>
      </nav>
    </el-header>
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>

</template>
<script>
  const cookies = require('js-cookie');

  export default {
    name: 'Index',
    data() {
      return {
        isGuest: true
      }
    },
    created() {
      console.log('reload');
      let user = cookies('user');
      if (user) {
        this.isGuest = false;
      } else {
        this.isGuest = true;
      }
    },
    methods: {
      logout() {
        console.log('logout');
        cookies.remove('token');
        cookies.remove('user');
        this.$router.push({name: 'passport'});
      }
    }
  }
</script>
<style scoped>
  .nav-bar {
    position: relative;
    margin-bottom: 20px;
  }
  .nav-bar-default {
    min-height: 60px;
    position: relative;
    background-color: transparent;
    padding: 40px 0 0;
    transition: .3s;
    border: 0;
  }
  @media(min-width: 768px) {
    .container > .nav-bar-header {
      margin-right: 0;
      margin-left: 0;
    }
  }
  @media (min-width: 1200px){
    .container {
      width: 1170px;
    }
  }
  @media (min-width: 992px) {
    .container {
      width: 970px;
    }
  }
  .container {
    display: block;
    /*padding-right: 15px;*/
    /*padding-left: 15px;*/
    margin-right: auto;
    margin-left: auto;
  }
  /*.container > .nav-bar-header {*/
    /*margin-right: -15px;*/
    /*margin-left: -15px;*/
  /*}*/
  .container {
    min-height: 60px;
  }
  @media (min-width: 768px) {
    .nav-bar-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .nav-bar-user {
      /*float: right;*/
      display: inline-block;
      height: 60px;
    }
  }
  .nav-bar-header > a.nav-bar-brand {
    font-size: 40px;
    text-decoration: none;
    color: black;
  }
  .nav-bar-user {
    /*float: right;*/
    height: 60px;
  }
</style>