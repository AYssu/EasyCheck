<script setup lang="ts">
// 引入图片
import default_icon from '@/assets/default.jpg'
// 引入数据
import {user_token} from "@/stores/token.ts";
import {Expand, Fold} from "@element-plus/icons-vue";
const user_data  = user_token() as any
console.log(user_data)

import {aside_status} from "@/stores/aside.ts";
import breadcrumb from "@/components/breadcrumb.vue";
import {computed} from "vue";
import phone_size from "@/utils/phone_size.ts";
import {onUnmounted} from "vue";
const {phone_bool,remove_phone_size} = phone_size();

onUnmounted(() => {
  remove_phone_size()
})
const aside_data = aside_status()

const change_aside_status = () => {
  aside_data.toggleStatus()
}
// 这边需要注意的就是这个username 原本都是小驼峰的 但是后端的login vo 重写了spring security 的user方法 然后自己定义的userName 就没了

const UNKNOWN_USERNAME = '未知用户名';
const UNKNOWN_EMAIL = '未知邮箱';

const top_user_info = computed(() => {
  const username = user_data?.token?.username || UNKNOWN_USERNAME;
  const userEmail = user_data?.token?.userEmail || UNKNOWN_EMAIL;
  return !phone_bool.value?`${username} | ${userEmail} |`:`${username}`;
});


</script>

<template>
  <div class="toolbar">
    <div  class="bread_menu">
      <el-icon size="20" style="cursor: pointer" class="el-icon-s-fold">
        <component v-if="phone_bool" @click="change_aside_status" :is="aside_data.status?Expand:Fold"/>
        <component v-else @click="change_aside_status" :is="aside_data.status?Fold:Expand"/>
      </el-icon>
      <breadcrumb  v-if="!phone_bool"/>
    </div>
    <div class="user_info">
      <el-dropdown>
        <el-image :src="default_icon" style="width: 20px;height:20px;border: 1px solid rgb(128,128,128);border-radius: 100%;margin-right: 8px" />
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>修改资料</el-dropdown-item>
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <span>{{top_user_info}}</span>
      <el-tag v-if="user_data.token.level===1" size="small" round effect="light" type="info" style="margin-left: 5px;margin-right: 8px"><span >小萌新</span></el-tag>
      <el-tag v-else-if="user_data.token.level===2" size="small" round effect="light" type="warning" style="margin-left: 5px;margin-right: 8px"><span>进阶用户</span></el-tag>
      <el-tag v-else-if="user_data.token.level===3" size="small" round effect="light" type="primary" style="margin-left: 5px;margin-right: 8px"><span>核心用户</span></el-tag>
      <el-tag v-else-if="user_data.token.level===4" size="small" round effect="light" type="success" style="margin-left: 5px;margin-right: 8px"><span>资深用户</span></el-tag>
      <el-tag v-else-if="user_data.token.level===5" size="small" round effect="light" type="danger" style="margin-left: 5px;margin-right: 8px">
      <el-icon>
        <svg  class="icon" viewBox="0 0 1024 1024"  xmlns="http://www.w3.org/2000/svg"  width="200" height="200"><path d="M976.394 400.901L811.794 86.08a43.357 43.357 0 0 0-38.415-23.265h-532.67a43.357 43.357 0 0 0-38.416 23.265L37.693 400.9a43.35 43.35 0 0 0 5.845 48.704l430.935 490.412a43.35 43.35 0 0 0 65.127 0l430.935-490.412a43.35 43.35 0 0 0 5.86-48.704zM792.2 421.471L539.217 701.685a43.336 43.336 0 0 1-64.346 0L221.873 421.471a43.35 43.35 0 1 1 64.36-58.097l220.81 244.574 220.811-244.574a43.35 43.35 0 1 1 64.346 58.097z" ></path></svg>
      </el-icon>
        <span style="font-size: 0.65rem;"   >超级管理员</span>
      </el-tag>
    </div>
  </div>

</template>

<style scoped lang="scss">
.toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
  width: 100%;
}

.bread_menu {
  display: flex;
  align-items: center;
  margin-left: 20px;
}


@media only screen and (max-width: 1200px) {
  .toolbar {
    height: 40px;
  }
  .bread_menu {
    margin-left: 5px;
  }
  span {
    font-size: 10px;
  }


}
</style>