<template>
  <div class="container">
    <el-row :gutter="2">
      <el-col :sm="12" :xs="24">
        <el-col :sm="24" v-for="item in imagesLeft">
          <figure class="effect-bubba">
            <img :src="HOST + '/images/' + item.src" class="img-responsive">
            <figcaption>
              <label>选择购买</label>
              <!--<h2>Test</h2>-->
              <!--<p>description</p>-->
              <a @click="chooseAnImage(item.id)"></a>
            </figcaption>
          </figure>
        </el-col>
      </el-col>
      <el-col :sm="12" :xs="24">
        <el-col :sm="24" v-for="item in imagesRight">
          <figure class="effect-bubba">
            <img :src="HOST + '/images/' + item.src" class="img-responsive">
            <figcaption>
              <label>选择购买</label>
              <!--<h2>Test</h2>-->
              <!--<p>description</p>-->
              <a @click="chooseAnImage(item.id)"></a>
            </figcaption>
          </figure>
        </el-col>
      </el-col>
    </el-row>
    <el-dialog
            title="选择购买"
            width="500px"
            :visible.sync="dialogVisible">
      <el-form :model="orderInfo" :rules="rules" ref="orderInfo" label-width="100px">
        <el-form-item label="收件人姓名" prop="recipient">
          <el-input v-model="orderInfo.recipient"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="phoneNumber">
          <el-input v-model="orderInfo.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="邮寄地址" prop="address">
          <el-input v-model="orderInfo.address"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button type="primary" size="small" @click="confirmSelect">确定</el-button>
          <el-button size="small" @click="cancelSelect">取消</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
  import { get, post } from "../tools/http";

  const HOST = process.env.VUE_APP_API_BASEURL;
  const cookies = require('js-cookie');

  const windowSize = document.body.clientWidth;

  export default {
    name: "Gallery",
    data() {
      return {
        HOST: HOST,
        dialogVisible: false,

        nextPage: 0,
        totalPages: null,
        pageSize: 10,

        images: [],

        imagesLeft: [],
        imagesRight: [],

        orderInfo: {
          imageid: '',
          recipient: '',
          phoneNumber: '',
          address: ''
        },

        rules: {
          recipient: [
            {required: true, message: '请填写收件人姓名！', trigger: 'blur'}
          ],
          phoneNumber: [
            {required: true, message: '请填写收件人手机号码！', trigger: 'blur'}
          ],
          address: [
            {required: true, message: '请填写收件地址！', trigger: 'blur'}
          ]
        }
      }
    },
    created() {
      // 滚动事件触发
      window.onscroll = () => {
        if (this.getScrollTop() + this.getClientHeight() === this.getScrollHeight()) {
          console.log('下拉刷新了');
          if (this.totalPages !== null && this.nextPage >= this.totalPages) {
            return;
          }
          this.getImages(this.nextPage, this.pageSize);
        }
      }

      this.getImages(this.nextPage, this.pageSize);

    },
    beforeDestroy() {
      window.onscroll = null;
    },
    methods: {
      getImages(page, size) {
        get(`api/image/list?page=${page}&size=${size}`)
          .then(res => {
            if (res.success) {
              const currentPage = res.data.currentPage;
              const totalPages = res.data.totalPages;
              const images = res.data.images;

              if (currentPage == this.nextPage) {
                this.nextPage = currentPage + 1;
                this.images = this.images.concat(images);
                if (windowSize < 768) {
                  this.imagesLeft.concat(images);
                } else {
                  let index = this.images.length + 1;
                  for (let i = 0; i < images.length; i++) {
                    if ((index + i) % 2 === 0) {
                      this.imagesRight.push(images[i]);
                    } else {
                      this.imagesLeft.push(images[i]);
                    }
                  }
                }
              }
              this.totalPages = totalPages;

              console.log(this.images);
            } else {
              this.$message.error(res.message);
            }
          })
      },
      chooseAnImage(index) {
        if (!cookies('token')) {
          this.$router.push({name: 'passport'});
        }

        this.orderInfo.imageid = index;
        this.orderInfo.recipient = '';
        this.orderInfo.phoneNumber = '';
        this.orderInfo.address = '';
        this.dialogVisible = true;
      },
      confirmSelect() {
        this.$refs['orderInfo'].validate((valid) => {
          if (valid) {
            post('api/image/order', this.orderInfo)
              .then(res => {
                if (res.success) {
                  this.$message.success('订购成功!');
                  // TODO: 支付跳转
                } else {
                  this.$message.error(res.message);
                }
              })
          }
        });
      },
      cancelSelect() {
        this.dialogVisible = false;
      },

      // 获取滚动条
      getScrollTop() {
        let scrollTop = 0;
        if (document.documentElement && document.documentElement.scrollTop) {
          scrollTop = document.documentElement.scrollTop;
        } else if (document.body) {
          scrollTop = document.body.scrollTop;
        }
        return scrollTop;
      },

      // 获取当前可视范围的高度
      getClientHeight() {
        let clientHeight = 0;
        if (document.body.clientHeight && document.documentElement.clientHeight) {
          clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);
        } else {
          clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);
        }
        return clientHeight;
      },

      // 获取文档完整的高度
      getScrollHeight() {
        return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
      }
    }
  }
</script>

<style scoped>
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

  figure {
    position: relative;
    overflow: hidden;
    margin: 0 1px 1px 0;
    background: #726FB9;
    text-align: center;
    cursor: pointer;


  }

  figure.effect-bubba {
    background: #000;
    margin-bottom: 4px;
  }

  figure.effect-bubba img {
    width: 100%;
    opacity: 0.8;
    -webkit-transition: opacity 0.35s;
    transition: opacity 0.35s;
  }

  figure.effect-bubba:hover img {
    opacity: 0.4;
  }

  .img-responsive {
    display: block;
    max-width: 100%;
    height: auto;
  }

  figure figcaption {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }

  figure figcaption > a {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }

  figure figcaption > a {
    z-index: 1000;
    text-indent: 200%;
    white-space: nowrap;
    font-size: 0;
    opacity: 0;
  }

  figure figcaption label {
    position: relative;
    top: 48%;
    font-size: 18px;
    font-weight: bold;
    color: white;
    opacity: 0;
    -webkit-transition: -webkit-transform 0.35s;
    transition: transform 0.35s;
    -webkit-transform: translate3d(0, -20px, 0);
    transform: translate3d(0, -20px, 0);
    /*-webkit-transform: translate3d(0,0,0);*/
    /*transform: translate3d(0,0,0);*/
  }

  figure:hover figcaption label {
    opacity: 1;
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
  }

  figure.effect-bubba:hover h2, figure.effect-bubba:hover p {
    opacity: 1;
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
  }

  figure.effect-bubba h2 {
    color: #fff;
    font-size: 15px;
    font-weight: 700;
    text-transform: uppercase;
    padding-top: 30%;
    -webkit-transition: -webkit-transform 0.35s;
    transition: transform 0.35s;
    -webkit-transform: translate3d(0, -20px, 0);
    transform: translate3d(0, -20px, 0);
  }

  figure.effect-bubba:hover p {
    opacity: 1;
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
  }

  figure.effect-bubba p {
    color: #fff;
    font-size: 13px;
    font-weight: 500;
    padding: 20px 2.5em;
    opacity: 0;
    -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
    transition: opacity 0.35s, transform 0.35s;
    -webkit-transform: translate3d(0, 20px, 0);
    transform: translate3d(0, 20px, 0);
  }

  figure.effect-bubba:hover figcaption::before, figure.effect-bubba:hover figcaption::after {
    opacity: 1;
    -webkit-transform: scale(1);
    transform: scale(1);
  }

  figure.effect-bubba figcaption::before {
    border-top: 1px solid #fff;
    border-bottom: 1px solid #fff;
    -webkit-transform: scale(0, 1);
    transform: scale(0, 1);
  }

  figure.effect-bubba figcaption::after {
    border-right: 1px solid #fff;
    border-left: 1px solid #fff;
    -webkit-transform: scale(1, 0);
    transform: scale(1, 0);
  }

  figure.effect-bubba figcaption::before, figure.effect-bubba figcaption::after {
    position: absolute;
    top: 30px;
    right: 30px;
    bottom: 30px;
    left: 30px;
    content: '';
    opacity: 0;
    -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
    transition: opacity 0.35s, transform 0.35s;
  }
</style>