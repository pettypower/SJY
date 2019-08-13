<template>

<div class="shadow">
<div class="login-block" v-show="blockShow"></div>
<div class="header">
    <div class="head">
        <a href="/cn" class="logo" title="蜗牛二手货车官网" alt="蜗牛二手货车官网">
            <img src="../assets/logo2.png" alt="">
        </a>
        <div class="city"><span id="atCity" data-value="">全国</span><i></i>
            <div class="cities" id="cities">
                <p></p>
                <div class="area-box">
                    <div class="area-city" id="js-cities-pos">
                        <div class="area-city-word">
                            <div class="area-line rm">
                                <span class="area how-city">常用城市</span>
                                <a class="province-item" href="/cn" rrc-event-name="cn" data-value="963258">全国</a>
                            </div>
                        </div>
                        <!--seo优化-->
                        <div class="area-city-letter">
                            <div class="area-line" v-for='item in citys' :key='item.name'>
                                <span class="area">{{item.name}}</span>
                                <a :data-value="city.cityCode" v-for='city in item.city' :key='city.cityCode' class="province-item selected" href="/ak" rrc-event-name="">{{city.cityName}}</a>
                            </div>
                        </div>
                        <div class="area-city-letter">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="phone"></div>
        <div class="login" id="login" @click="showRegister()"><span class="loginbtn">注册</span>
        </div>
        <div class="login" id="login" @click="showLogin()" @mouseout="hideExit()"><i class="loginicon" style="background-position: 0px 0px;"></i>
            <span class="loginbtn" v-if="!this.$root.hasEnter">点击登录</span>
            <span class="suc_login" @mouseover="showExit()" v-else>{{this.$root.username}}</span>
            <p id="exitbtn" @mouseover="showExit()" @mouseout="hideExit()" v-if="exitShow">退出</p></div>
        <div class="nav">
            <ul>
                <li url-value="index" v-bind:class="index"><router-link  tag='a' :to="'/index'" >首页</router-link></li>
                <li url-value="buyList" v-bind:class="buyList"><router-link  tag='a' :to="'/buy'" >我要买车</router-link></li>
                <li url-value="buyList"><router-link  tag='a' :to="'/index'" >快速卖车</router-link></li>
                <li url-value="buyList"><router-link  tag='a' :to="'/index'" >自主卖车</router-link></li>
                <li url-value="buyList"><router-link  tag='a' :to="'/index'" >蜗估车</router-link></li>
                <li url-value="buyList"><router-link  tag='a' :to="'/index'" >以旧换新</router-link></li>
              </ul>
        </div>
    </div>
</div>
<div class="authenticate-mark"></div>
<div class="login-content" v-show="registerShow">
    <div class="login-left-main">
        <h4>快速注册</h4>
        <ul class="form-login">
           <li><input type="text" placeholder="请输入账号" maxlength="16" class="getinput" v-model="registerName" value=""></li>
            <p class="tel-prompt"></p>
            <li><input type="password" placeholder="请输入密码" maxlength="16" class="getinput" v-model="registerPassword" value=""></li>
            <p class="veri-prompt"></p>
            <li>
                <button type="button" id="immediatelybtn" @click="register()">立即注册</button>
            </li>
            <p></p>
        </ul>
        <p class="foot-tag">注册视为同意蜗牛二手货车服务条款</p>
    </div>
    <div class="login-right-main">
        <span id="close-login" @click="hideRegister()"><img id="close-block" src="../../static/images/close.png"></span>
        <p class="right-loginone">扫码下载APP</p>
        <p class="right-login-two">车源更新不错过</p>
        <p class="right-login-three"><img src="../../static/images/ewm2.jpg" alt="蜗牛二手货车"></p>
        <p><span>更多车源等你来</span></p>
    </div>
</div>
<div class="login-content" v-show="loginShow">
    <div class="login-left-main">
        <h4>快速登录</h4>
        <ul class="form-login">
           <li><input type="text" placeholder="请输入账号" maxlength="16" class="getinput" v-model="loginName" value=""></li>
            <p class="tel-prompt"></p>
            <li><input type="password" placeholder="请输入密码" maxlength="16" class="getinput" v-model="loginPassword" value=""></li>
            <p class="veri-prompt"></p>
            <li>
                <button type="button" id="immediatelybtn" @click="login()">立即登录</button>
            </li>
            <p></p>
        </ul>
        <p class="foot-tag">登录视为同意蜗牛二手货车服务条款</p>
    </div>
    <div class="login-right-main">
        <span id="close-login" @click="hideLogin()"><img id="close-block" src="../../static/images/close.png"></span>
        <p class="right-loginone">扫码下载APP</p>
        <p class="right-login-two">车源更新不错过</p>
        <p class="right-login-three"><img src="../../static/images/ewm2.jpg" alt="蜗牛二手货车"></p>
        <p><span>更多车源等你来</span></p>
    </div>
</div>
</div>
</template>

<script>
import vPinyin from '../../static/js/vue-py'
export default {
  name: 'commonHead',
  data() {
    return {
      // 判断登录窗口是否展示
      loginShow: false,
      // 判断遮罩层是否展示
      blockShow: false,
      // 判断注册窗口是否展示
      registerShow: false,
      // 判断退出按钮是否展示
      exitShow: false,
      registerName: '',
      registerPassword: '',
      loginName: '',
      loginPassword: '',
      citys: []
    }
  },
  props: ['index', 'buyList'],
  methods: {
    // 获取城市信息方法
    getCitys() {
      let _this = this
      this.axios.post(this.$root.serverSrc + 'AreaInfo/page', {
        headers: {
          'Content-Type': 'application/json'
        },
        areaLevel: 'city'
      }).then(function(response) {
        let data = response.data.data
        let citysInfo = data.content
        // 遍历所有城市，判断是否其拼音首字母已经存入数组对象，如果没存入，则存入该首字母
        for (let i = 0; i < citysInfo.length; i++) {
          let exitFlag = false
          let firstWord = vPinyin.initialTreatment(citysInfo[i].name)
          for (var j = 0; j < _this.citys.length; j++) {
            if (firstWord === _this.citys[j].name) {
              _this.citys[j].city.push({cityName: citysInfo[i].name, cityCode: citysInfo[i].adcode})
              exitFlag = true
            }
          }
          if (exitFlag) {
            continue
          }
          _this.citys.push({
            name: firstWord,
            city: [{cityName: citysInfo[i].name, cityCode: citysInfo[i].adcode}]
          })
        }
        // 将数组按字母顺序排序
        _this.citys.sort((a, b) => {
          return a.name.charCodeAt() - b.name.charCodeAt()
        })
      }).catch(function(error) {
        console.log(error)
      })
    },
    // 展示注册窗口
    showRegister() {
      this.registerShow = true
      this.blockShow = true
    },
    // 展示登录窗口
    showLogin() {
      this.loginShow = true
      this.blockShow = true
    },
    // 隐藏登录窗口
    hideLogin() {
      this.loginShow = false
      this.blockShow = false
    },
    // 隐藏注册窗口
    hideRegister() {
      this.registerShow = false
      this.blockShow = false
    },
    // 显示退出按钮
    showExit() {
      this.exitShow = true
    },
    // 隐藏退出按钮
    hideExit() {
      this.exitShow = false
    },
    // 注册
    register() {
      let _this = this
      this.axios.post(this.$root.serverSrc + 'userInfo/save', {
        headers: {
          'Content-Type': 'application/json'
        },
        userName: this.registerName,
        loginName: this.registerName,
        loginPassword: this.registerPassword,
        phone: ''
      }).then(function(response) {
        let data = response.data
        if (data.status) {
          alert(data.message)
        } else {
          alert(data.message)
        }
        _this.hideRegister()
      }).catch(function(error) {
        console.log(error)
      })
    },
    // 登录
    login() {
      let _this = this
      this.axios.post(this.$root.serverSrc + 'api/login', {
        headers: {
          'Content-Type': 'application/json'
        },
        loginName: this.loginName,
        loginPassword: this.loginPassword
      }).then(function(response) {
        let data = response.data
        if (data.status) {
          alert(data.message)
          debugger
          _this.$root.username = data.data.userName
          _this.$root.hasEnter = true
        } else {
          alert(data.message)
        }
        _this.hideLogin()
      }).catch(function(error) {
        console.log(error)
      })
    }
  },
  created() {
    // 画面初始化默认加载信息
    this.getCitys()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped>
@import url("../../static/css/common.css")
</style>
