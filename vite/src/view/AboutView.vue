<template>
  <el-collapse-transition class="transition-box ">
    <div v-show="show" class="transition-box-list">
      <div class="item" @click="()=>{handleSelect(1);show=!show}">
        <span class="item-title">网站首页</span>
        <el-icon>
          <ArrowRight/>
        </el-icon>
      </div>
      <div class="item" @click="()=>{handleSelect(2);show=!show}">
        <span class="item-title">特点简绍</span>
        <el-icon>
          <ArrowRight/>
        </el-icon>
      </div>
      <div class="item" @click="()=>{handleSelect(3);show=!show}">
        <span class="item-title">开发者后台</span>
        <el-icon>
          <ArrowRight/>
        </el-icon>
      </div>
      <div class="item" @click="()=>{handleSelect(4);show=!show}">
        <span class="item-title">用户使用协议</span>
        <el-icon>
          <ArrowRight/>
        </el-icon>
      </div>
      <div class="item" @click="()=>{handleSelect(5);show=!show}">
        <span class="item-title">接口文档</span>
        <el-icon>
          <ArrowRight/>
        </el-icon>
      </div>
    </div>
  </el-collapse-transition>

  <main class="main">
    <header class="top-title-header">
      <div class="header-container" style="display:flex;justify-content: space-between;align-items: center;">
        <div class="grid-content left-icon">
          <el-image :src="icon" class="icon-image"></el-image>
          <span class="text-title">{{ content.title }}</span>
        </div>
        <div class="grid-content self-hidden-sm-and-down" style="display: flex; align-items: flex-end;">
          <el-menu
              :ellipsis=false
              active-text-color="#53e3a6"
              background-color="#FFFFFF01"
              class="el-menu-popper-demo"
              default-active="1"
              mode="horizontal"
              style="background: transparent;"
              text-color="#fff"
              @select="handleSelect"
          >
            <el-menu-item index="1" style="min-width: 130px">
              网站首页
            </el-menu-item>
            <el-menu-item index="2" style="min-width: 130px">
              特点简绍
            </el-menu-item>
            <el-menu-item index="3" style="min-width: 130px">
              开发者后台
            </el-menu-item>
            <el-menu-item index="4" style="min-width: 130px">
              用户使用协议
            </el-menu-item>
            <el-menu-item index="5" style="min-width: 130px">
              接口文档
            </el-menu-item>
          </el-menu>
        </div>
        <div class="hamburger">
          <div class="hamburger-box">
            <img :src="hamburger" alt="" srcset="" style="width: 30px" @click="show = !show">
          </div>
        </div>
      </div>
    </header>

    <div class="main-show" @click="show = false">
      <div class="center-box">
        <div class="main-show-content-title">
          <span class="main-show-content-title-text">{{ content.title }}</span>
        </div>
        <div class="main-show-content-message">
        <span class="main-show-content-message-text">
          {{ content.content }}
        </span>
        </div>
        <div class="main-show-content-message">
          <span class="main-show-content-message-text2">
          {{ content.content2 }}
        </span>
        </div>
        <div class="main-show-content-message">
          <span class="main-show-content-message-text2">
          {{ content.content3 }}
        </span>
        </div>
        <div class="main-show-content-button">

          <button class="main-show-content-button-button" type="button" @click="login_dialog_show=true">开发者登录
          </button>
          <button class="main-show-content-button-button" type="button" @click="register_dialog_show=true">开发者注册
          </button>
          <button class="main-show-content-button-button" style="background: white;color: rgb(128,128,128)"
                  type="button">用户登录
          </button>


        </div>
      </div>
    </div>

    <div ref="dom" class="bottom-content" @click="show = false">
      <el-card v-for="(item,index) in bottom_message" :key="index" class="el-card-list" style="height: 300px">
        <div class="el-card-item">
          <div class="el-card-item-title">
            <img :src="item.icon" :srcset="item.icon" alt="" style="width: 50px">
            <span class="el-card-item-title-title">{{ item.title }}</span>
          </div>
          <div class="el-card-item-title-content">
            <span class="el-card-item-title-content-content">{{ item.content }}</span>
          </div>
        </div>
      </el-card>

    </div>

    <footer class="el-footer-box">
      <div class="footer-box-content">
        <el-text> Powered by 阿夜 | Copyright © 2013-2024</el-text>
      </div>
    </footer>
  </main>

  <div class="background">
  </div>
  <div class="wrapper">
    <div class="container">
    </div>
    <ul class="bg-bubbles">
      <li v-for="() in 10" key="index"></li>
    </ul>
  </div>

  <a-modal v-model:open="login_dialog_show"
           title="用户登录"
           :width="phone_bool ? '90%' : '40%'">

    <template #footer>
      <div >
        <a-button type="default" @click="()=>{login_dialog_show = false;register_dialog_show = true;}"
                  style="margin-right: 20px">没有账号？前往注册
        </a-button>
        <a-button type="primary" :loading="confirmLoading" @click="to_login">登录</a-button>
      </div>
    </template>
    <el-form ref="loginForm" :model="login_form" :rules="login_rules" label-width="60px" style="margin-top: 20px">
      <el-form-item label="账号" prop="username">
        <el-input v-model="login_form.username" clearable placeholder="请输入账号" type="text"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="login_form.password" placeholder="请输入密码" show-password type="password"
                  @keyup.enter="to_login"></el-input>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="login_form.agree">同意协议</el-checkbox>
      </el-form-item>

    </el-form>

  </a-modal>
  <a-modal
      v-model:open="register_dialog_show"
      title="用户注册"
      :width="phone_bool ? '90%' : '40%'">
    <template #footer>
      <div >
        <a-button type="default" @click="()=>{register_dialog_show = false;login_dialog_show = true;}"
                  style="margin-right: 20px">已有账号？前往登录
        </a-button>
        <a-button type="primary" :loading="confirmLoading" @click="to_login">注册</a-button>
      </div>
    </template>
    <el-form ref="loginForm" label-position="top" :model="register_form" :rules="register_rules" label-width="60px" style="margin-top: 20px">
      <el-form-item label="账号" prop="username">
        <el-input v-model="register_form.username" clearable placeholder="请输入账号" type="text"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="register_form.email" clearable placeholder="请输入邮箱" type="text"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="code" >
        <el-input v-model="register_form.code" clearable placeholder="请输入验证码" type="text">
          <template #append>
            <el-button type="primary" :loading="send_loading" @click="send_code(register_form.email)">{{ send_text }}</el-button>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="register_form.password" placeholder="请输入密码" show-password type="password"
                  @keyup.enter="to_register"></el-input>
      </el-form-item>

      <el-form-item>
        <el-checkbox v-model="register_form.agree">同意协议</el-checkbox>
      </el-form-item>

    </el-form>

  </a-modal>
  <el-backtop :bottom="20" :right="20"/>
</template>


<script lang="ts" setup>
import 'element-plus/theme-chalk/display.css'
import {ref} from 'vue';
import {ElMessage} from "element-plus";
import {user_login_services, user_register_code_services, user_register_services} from "@/api/user.ts";
import {ElCollapseTransition} from 'element-plus'
// 显示登录弹窗的变量
const login_dialog_show = ref(false);
const register_dialog_show = ref(false);
const confirmLoading = ref(false);
// 引入token的store
import {user_token} from "@/stores/token.ts";

const user_token_data = user_token();


import router from "@/router/index.ts";

// 登录表单
const login_form = ref({
  username: '',
  password: '',
  agree: false
})

// 注册表单
const register_form = ref({
  username: '',
  password: '',
  email: '',
  code: '',
  agree: false
})

// 表单验证规则
const login_rules = ref({
  username: [
    {required: true, message: '请输入账号', trigger: 'blur'},
    {min: 2, max: 10, message: '账号长度在 2 到 10 个字符', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 18, message: '密码长度在 6 到 18 个字符', trigger: 'blur'}
  ],
});

// 注册表单校验规则
const register_rules = ref({
  username: [
    {required: true, message: '请输入账号', trigger: 'blur'},
    {min: 2, max: 10, message: '账号长度在 2 到 10 个字符', trigger: 'blur'}
  ],
  email: [
    {required: true, message: '请输入邮箱', trigger: 'blur'},
    {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
  ],
  code: [
    {required: true, message: '请输入验证码', trigger: 'blur'},
    {min: 6, max: 6, message: '验证码长度为6位', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 18, message: '密码长度在 6 到 18 个字符', trigger: 'blur'}
  ],
});
const send_loading = ref(false);
const send_text = ref("发送验证码");

const validateEmail = (email: string): boolean => {
  const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
  return emailRegex.test(email);
};

const send_code = async (email: string)=> {
  if (!validateEmail(email))
    return message.error("邮箱格式不正确")
  send_loading.value = true;
  send_text.value = "发送中..."
  message.loading("请等待片刻,邮箱酱在全力发送...")
  const result = await user_register_code_services(email);
  try {
    if (result.data.code === 200)
    {
      message.success(result.data.message);
      send_text.value = "再次发送";
      send_loading.value = false;
    }else {
      message.error(result.data.message);
      send_loading.value = false;
      send_text.value = "发送验证码";
    }
  }catch (e)
  {
    console.log(e)
  }
}
// 登录方法
const to_login = async () => {
  if (!login_form.value.agree) {
    ElMessage.error('请先同意协议');
    return;
  }

  confirmLoading.value = true
  // 执行登录的逻辑
  const login_request: any = await user_login_services(login_form.value);

  if (login_request.data.code === 200) {
    ElMessage.success(login_request.data.message)
    confirmLoading.value = false
    // 清除token
    user_token_data.remove_token();
    user_token_data.set_token(login_request.data.data);

    await router.push({name: 'home'});

  } else {
    confirmLoading.value = false
    ElMessage.error(login_request.data.message)
  }
};


// 注册方法
const to_register = async () => {
  // 执行跳转到注册页面的逻辑
  if (!register_form.value.agree) {
    ElMessage.error('请先同意协议');
    return;
  }
  confirmLoading.value = true
  const register_request: any = await user_register_services(register_form.value);

  if (register_request.data.code === 200) {
    confirmLoading.value = false
    ElMessage.success(register_request.data.message);
    register_dialog_show.value = false;
    login_form.value.username = register_form.value.username;
    login_form.value.password = register_form.value.password;
    login_form.value.agree = true;

    login_dialog_show.value = true;
  } else
    confirmLoading.value = false
    ElMessage.error(register_request.data.message);

};

import icon from "@/assets/icon.png"
import safe from "@/assets/index/safe.svg";
import fast from "@/assets/index/fast.svg";
import lock from "@/assets/index/lock.svg";
import hamburger from "@/assets/index/hamburger.svg"
import {ArrowRight} from "@element-plus/icons-vue";
import {message} from "ant-design-vue";
import phone_size from "@/utils/phone_size.ts";
import {onUnmounted} from "vue";
const {phone_bool,remove_phone_size} = phone_size();

onUnmounted(() => {
  remove_phone_size()
})
const show = ref(false)

// 侧边栏选择事件
const handleSelect = (view: any) => {
  if (view == 2)
    jump();
  if (view == 3)
    login_dialog_show.value = true;
  if (view == 5)
    window.open("https://apifox.com/apidoc/shared-8e7afe9d-8d2a-41b8-adc0-444f3752be7d")
}

// 跳转 指定dom
const dom = ref<any>(null);

// 滚动到指定dom
const jump = () => {
  let height = document.documentElement.scrollTop || document.body.scrollTop;
  let domHeight = dom.value.offsetTop;
  let S = Number(height) - Number(domHeight);
  if (S < 0) {
    //下滚
    S = Math.abs(S);	//Math.abs返回一个数的绝对值
    window.scrollBy({top: S, behavior: "smooth"});
  } else if (S == 0) {
    window.scrollBy({top: 0, behavior: "smooth"});
  } else {
    S = -S
    window.scrollBy({top: S, behavior: "smooth"});
  }
}

// 底部信息
const content = ref({
  title: "易验证 Alpha",
  content: "Vue3 SpringBoot3 Element Plus",
  content2: "极快部署 极简对接 极易使用 容器部署",
  content3: "支持Java C++ 等主流语言对接使用 提供完整对接案例 实现用户简洁对接"
});
const bottom_message = ref([{
  icon: safe,
  title: "传输安全",
  content: "采用混合BASE64加密验证，多种加密防止数据被篡改，双向签名，数据一次性，防止抓包，API接口采用程序唯一性，支持用户注册，校验，用户身份认证，单码/次数卡可为用户进行充值，一卡多用。"
}, {
  icon: fast,
  title: "快速上手",
  content: "采用高级语言特性，编辑最简易示例，不失功能的基础上针对多系统，多平台进行网络验证源码编写，运行用户自定义开发示例，支持拓展开发，云变量热更新，轻松实现用户免登录更新，云端监控，智能通知。"
}, {
  icon: lock,
  title: "安全服务",
  content: "用户信息专属保管，不会泄露任何用户相关信息，私有服务器部署，安全可靠。"
}]);


</script>


<style lang="scss" scoped>

.transition-box {
  position: fixed;
  top: 60px;
  width: calc(100% - 20px);
  border-radius: 4px;
  box-sizing: border-box;
  padding: 10px 20px;
  margin-left: 10px;
  z-index: 99;
  background: #ffffff;
  color: grey;
  display: none;

  .transition-box-list {
    width: 100%;
  }
}

.item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin-top: 10px;
  padding-bottom: 10px;
  box-sizing: border-box;
  border-bottom: 1px solid #f3f3f3;
}

.item:last-child {
  margin-bottom: 10px;
  border: none;
}

.main {
  width: 100%;

  .top-title-header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 60px;
    z-index: 999;

    background-color: rgb(80, 163, 162, 0.2);
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
    backdrop-filter: blur(0.1rem);

    .header-container {
      height: 100%;
      width: 100%;
    }

    .grid-content {
      height: 100%;
    }


    .left-icon {
      display: flex;
      align-items: center;
      justify-content: flex-start;
      padding-left: 30px;
      box-sizing: border-box;

      .icon-image {
        width: 30px;
        height: 30px;
      }

      .text-title {
        margin-left: 8px;
        color: white;
        font-size: 1.1rem;
        font-weight: bold;
      }
    }

  }

  .hamburger {
    display: none;
  }

  .el-menu-popper-demo {
    border-bottom-color: transparent;
  }

  .main-show {

    color: white;
    box-sizing: border-box;
    width: 100%;
    height: 100vh;
    padding: 0 20vw;
    backdrop-filter: blur(1px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    .main-show-content-title, .main-show-content-message, .main-show-content-button {
      margin-top: 10px;
    }

    @keyframes slideInFromLeft {
      from {
        transform: translateX(-100px); /* 开始时，元素位于屏幕左侧之外 */
        opacity: 0; /* 开始时，元素完全透明 */
      }
      to {
        transform: translateX(0); /* 结束时，元素位于正常位置 */
        opacity: 1; /* 结束时，元素完全不透明 */
      }
    }

    .main-show-content-title-text {
      font-size: 3rem;
      font-weight: bold;
      background-image: linear-gradient(to right, #7375cc, #427551, #35b25e);
      background-clip: text;
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
      /* transition属性可能会干扰动画，根据需要决定是否需要 */
      transition: transform 0.1s ease, font-size 0.3s ease;
    }

    .main-show-content-title-text:hover {
      font-size: 3.2rem;
    }

    .main-show-content-message-text {
      font-size: 1.5rem;

      color: #7375cc;
    }

    .main-show-content-button-button {
      margin-top: 10px;
      background: #42b983;
      animation: slideInFromLeft 2s ease-in-out; /* 移除了 forwards，除非您需要保持动画结束状态 */

      color: white;
      padding: 10px 15px;
      border-radius: 10px;
      border: transparent 1px solid;
      font-weight: bold;
      letter-spacing: 2px;
      margin-right: 15px;
    }

    .main-show-content-button-button:hover {
      border: #6ac987 1px solid;
    }
  }

  @keyframes moveUp {
    0%, 100% {
      transform: translateY(0);
    }
    50% {
      transform: translateY(-10px);
    }
  }

  .bottom-content {
    width: 100%;
    margin-top: 40px;
    display: grid;
    grid-gap: 16px;
    grid-template-columns: 1fr 1fr 1fr;


    padding: 0 10px;
    box-sizing: border-box;

    .el-card-list {
      border-radius: 15px;
      margin: 0 10px;
      opacity: 0.7;
      transition: transform 1s ease-in-out; /* 平滑过渡效果 */
      transform: translateY(0); /* 默认状态在原位 */

      .el-card-item-title {
        display: flex;
        align-items: center;

        margin-bottom: 10px;

        .el-card-item-title-title {
          margin-left: 20px;
          font-weight: bold;
          font-size: 1.4rem;
          color: #2c2c2c;
        }

        .el-card-item-title-content-content {
          margin-top: 10px;
        }

      }
    }


    .el-card-list:hover {
      animation: moveUp 1s ease-in-out forwards; /* 鼠标悬停时触发动画，持续时间1秒，向前填充模式 */
      opacity: 1; /* 鼠标悬停时改变透明度 */
      box-shadow: 3px 3px 10px 1px rgb(115, 204, 157), /* 右下方的阴影 */
      -3px -3px 10px 1px rgb(115, 204, 157), /* 左上方的阴影 */
      3px -3px 10px 1px rgb(115, 204, 157), /* 右上方的阴影 */
      -3px 3px 10px 1px rgb(115, 204, 157); /* 左下方的阴影 */
    }

    .el-card-list:not(:hover) {
      transition: transform 1s ease-in-out, opacity 1s ease-in-out; /* 鼠标移开时平滑恢复 */
    }
  }

  .el-footer-box {
    box-sizing: border-box;
    padding: 0 10px;
    margin-top: 300px;

    .footer-box-content {
      width: 100%;
      height: 100px;
      border-radius: 10px;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      color: #fbfffb;
      opacity: 0.7;
    }
  }
}


@media only screen and (max-width: 1200px) {
  .self-hidden-sm-and-down {
    display: none !important
  }
  .main {
    .bottom-content {
      grid-template-columns: 1fr;
    }


    .el-footer-box {
      margin-top: 0;
    }
  }

  .transition-box {
    display: block;
  }

  .hamburger {
    display: flex !important;
    align-items: center;
    justify-content: flex-end;
    padding-right: 20px;
    box-sizing: border-box;
    opacity: 0.3;
  }

  .main-show-content-title-text {
    font-size: 2.4rem !important;
  }


  .main-show-content-button-button {
    margin-top: 10px;
    background: #35b25e;
    animation: slideInFromLeft 2s ease-in-out; /* 移除了 forwards，除非您需要保持动画结束状态 */

    color: white;
    padding: 8px 10px !important;
    border-radius: 10px;
    border: transparent 1px solid;
    font-weight: bold;
    letter-spacing: 1px !important;
    margin-right: 15px;
    font-size: small;

  }

  .el-card-item-title {
    img {
      width: 40px !important;
      height: 40px !important;
    }
  }
  .el-card-item-title-title {
    margin-left: 10px !important;
    font-weight: bold;
    font-size: 1.1rem !important;
    color: #2c2c2c;

  }

  .el-card-item-title-content-content {
    margin-top: 10px;
    font-size: 0.9rem !important;
  }
}

@media only screen and (min-width: 768px) {
  .banner-brand__wrapper .banner-brand__content .title {
    font-size: 4.5rem !important;
    line-height: 1 !important;
  }
}
</style>

<style lang="less" scoped>

.wrapper {
  background: #50a3a2;

  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  z-index: -9999;

  &.form-success {
    .container {
      h1 {
        transform: translateY(85px);
      }
    }
  }
}

.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 80px 0;
  height: 400px;
  text-align: center;

  h1 {
    font-size: 40px;
    transition-duration: 1s;
    transition-timing-function: ease-in;
    font-weight: 200;
  }
}


.bg-bubbles {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;

  z-index: 1;

  li {
    position: absolute;
    list-style: none;
    display: block;
    width: 40px;
    height: 40px;
    background-color: fade(white, 15%);
    bottom: -160px;

    -webkit-animation: square 25s infinite;
    animation: square 25s infinite;

    -webkit-transition-timing-function: linear;
    transition-timing-function: linear;

    &:nth-child(1) {
      left: 10%;
    }

    &:nth-child(2) {
      left: 20%;

      width: 80px;
      height: 80px;

      animation-delay: 2s;
      animation-duration: 17s;
    }

    &:nth-child(3) {
      left: 25%;
      animation-delay: 4s;
    }

    &:nth-child(4) {
      left: 40%;
      width: 60px;
      height: 60px;

      animation-duration: 22s;

      background-color: fade(white, 25%);
    }

    &:nth-child(5) {
      left: 70%;
    }

    &:nth-child(6) {
      left: 80%;
      width: 120px;
      height: 120px;

      animation-delay: 3s;
      background-color: fade(white, 20%);
    }

    &:nth-child(7) {
      left: 32%;
      width: 160px;
      height: 160px;

      animation-delay: 1s;
    }

    &:nth-child(8) {
      left: 55%;
      width: 20px;
      height: 20px;

      animation-delay: 15s;
      animation-duration: 40s;
    }

    &:nth-child(9) {
      left: 25%;
      width: 10px;
      height: 10px;

      animation-delay: 2s;
      animation-duration: 40s;
      background-color: fade(white, 30%);
    }

    &:nth-child(10) {
      left: 90%;
      width: 160px;
      height: 120px;

      animation-delay: 11s;
    }
  }
}

@-webkit-keyframes square {
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-700px) rotate(600deg);
  }
}

@keyframes square {
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-700px) rotate(600deg);
  }
}
</style>
