<template>
  <div id="main">
    <el-container id="container">
      <el-header>易验证-EasyCheck</el-header>
      <el-main>
        <div class="main">
          <el-text>
            欢迎使用易验证，一个基于vue3+element-plus+vite的web应用。
          </el-text>
          <el-row>
            <el-col :span="24">
              <el-button type="primary" @click="login_dialog_show=true">登录</el-button>
              <el-button type="primary" @click="to_register">注册</el-button>
            </el-col>
          </el-row>
        </div>
      </el-main>
      <el-footer>
        <div class="footer">
          <el-text type="info">Copyright © 2023-2024 2024-2025</el-text>
          <el-text type="info">All Rights Reserved</el-text>
        </div>
      </el-footer>
    </el-container>
    <el-dialog v-model="login_dialog_show" :show-close="false" :close-on-press-escape="true" width="40%">
      <template #header>
        <div class="input_dialog_head">
          <el-text size="large">用户登录</el-text>
        </div>
      </template>
      <el-form ref="loginForm" :model="login_form"  :rules="login_rules" label-width="60px" >
        <el-form-item label="账号" prop="username">
          <el-input v-model="login_form.username" type="text" placeholder="请输入账号" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="login_form.password" type="password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-checkbox v-model="login_form.agree">同意协议</el-checkbox>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="to_login">登录</el-button>
          <el-button @click="()=>{
            login_dialog_show = false;
            register_dialog_show = true;
          }">没有账号？前往注册</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
    <el-dialog v-model="register_dialog_show" :show-close="false" :close-on-press-escape="true" width="40%">
      <template #header>
        <div class="input_dialog_head">
          <el-text size="large">用户注册</el-text>
        </div>
      </template>
      <el-form ref="loginForm" :model="register_form"  :rules="register_rules" label-width="60px" >
        <el-form-item label="账号" prop="username">
          <el-input v-model="register_form.username" type="text" placeholder="请输入账号" clearable></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="register_form.email" type="text" placeholder="请输入邮箱" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="register_form.password" type="password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>

        <el-form-item>
          <el-checkbox v-model="register_form.agree">同意协议</el-checkbox>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="to_login">注册</el-button>
          <el-button @click="()=>{
            register_dialog_show = false;
              login_dialog_show = true;

          }">已有账号？前往登录</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref} from 'vue';

// 显示登录弹窗的变量
const login_dialog_show = ref(false);
const register_dialog_show = ref(false);

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
  agree: false
})

// 表单验证规则

const login_rules = ref({
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { min: 2, max: 10, message: '账号长度在 2 到 10 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 18, message: '密码长度在 6 到 18 个字符', trigger: 'blur' }
  ],
});

// 注册表单校验规则
const register_rules = ref({
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { min: 2, max: 10, message: '账号长度在 2 到 10 个字符', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 18, message: '密码长度在 6 到 18 个字符', trigger: 'blur' }
  ],
});


// 登录方法
const to_login = () => {
  console.log('点击登录');
};

// 注册方法
const to_register = () => {
  // 执行跳转到注册页面的逻辑
  console.log('点击注册');
};
</script>

<style scoped lang="scss">
#main {
  display: flex;
  height: 100vh; /* Full height of the viewport */
  align-items: center; /* Vertically center the content */
  justify-content: center; /* Horizontally center the content */
}

#container {
  width: 100vw;
  height: 100vh;
  text-align: center;
}

.main {
  width: 100%;
  height: 100%;
  display: grid;
  place-items: center; /* Center the content */
}

.footer {
  width: 100%;
  position: absolute;
  bottom: 0;
  margin-bottom: 5px;
}

.input_dialog_head {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>