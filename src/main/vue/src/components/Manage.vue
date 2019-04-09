<template>
  <el-container>
    <el-header class="nav-bar" style="height: 100px">
      <nav class="nav-bar-default">
        <div class="container">
          <div class="nav-bar-header">
            <router-link class="nav-bar-brand" :to="{ name: 'index' }">
              公益售卖
            </router-link>
            <span>管理后台</span>
            <div class="nav-bar-user">
              <el-menu mode="horizontal">
                <el-menu-item index="3" @click="logout">
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
        <div class="container">
          <el-tabs :active-name="activeName">
            <el-tab-pane label="上传画作" name="add">
              <el-upload class="upload"
                         ref="imageUpload"
                         name="image"
                         :headers="{ Authorization: token }"
                         :action="HOST + '/api/upload/image'"
                         :on-success="onUploadSuccess"
                         :on-error="onUploadError"
                         drag
                         multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件</div>
              </el-upload>
              <br>
              <el-button type="primary" size="small" @click="submitImageList">提交</el-button>
            </el-tab-pane>
            <el-tab-pane label="删除画作" name="delete">

            </el-tab-pane>
          </el-tabs>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import { post } from "../tools/http";

  const cookies = require('js-cookie');

  const HOST = process.env.VUE_APP_API_BASEURL;
  export default {
    name: "Manage",
    data() {
      return {
        activeName: 'add',

        uploadImageList: [],

        HOST: HOST,

        token: '',
      }
    },
    created() {
      this.token = cookies('token');
      if (!this.token) {
        this.$router.push({name: 'passport'});
      }
    },
    methods: {
      onUploadSuccess(res, file, fileList) {
        console.log(file);
        if (res.success) {
          this.uploadImageList.push(res.data.imageName);
        } else {
          this.$message.error(res.message);
          for (let i = 0; i < fileList.length; i++) {
            if (file.name === fileList[i].name) {
              fileList.splice(i, 1);
              break;
            }
          }
          // file.status = 'fail';
          // fileList.push(file);
        }
      },
      onUploadError(error, file, fileList) {
        console.log(error);
        this.$message.error(JSON.parse(error.message));
      },

      submitImageList() {
        console.log(this.uploadImageList);
        post('api/image/new', this.uploadImageList)
          .then(res => {
            if (res.success) {
              this.$message.success('提交成功!');
              this.uploadImageList = [];
              this.$refs['imageUpload'].clearFiles();
            } else {
              this.$message.error(res.message);
            }
          })
      },

      logout() {
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

  @media (min-width: 768px) {
    .container > .nav-bar-header {
      margin-right: 0;
      margin-left: 0;
    }
  }

  @media (min-width: 1200px) {
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
    margin-right: auto;
    margin-left: auto;
  }

  .container {
    min-height: 60px;
  }

  .nav-bar-header > a.nav-bar-brand {
    font-size: 40px;
    text-decoration: none;
    color: black;
  }

  .nav-bar-user {
    float: right;
    height: 60px;
  }

  .upload {
    width: 360px;
  }
</style>