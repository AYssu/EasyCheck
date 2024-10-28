<script setup lang="ts">
// 引入图片
import default_icon from '@/assets/default.jpg'
// 引入数据
import {user_token} from "@/stores/token.ts";
import {Expand, Fold} from "@element-plus/icons-vue";
const user_data : any  = user_token()
console.log(user_data)

import {aside_status} from "@/stores/aside.ts";
import breadcrumb from "@/components/breadcrumb.vue";
const aside_data = aside_status()

const change_aside_status = () => {
  aside_data.toggleStatus()
}

</script>

<template>
  <div class="toolbar">
    <div class="bread_menu">
      <el-icon size="20" style="cursor: pointer" class="el-icon-s-fold">
        <component @click="change_aside_status" :is="aside_data.status?Fold:Expand"/>
      </el-icon>
      <breadcrumb />
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
      <span>{{ user_data.token.userName }} | {{user_data.token.userEmail}} |</span>
      <el-tag v-if="user_data.token.level===1" size="small" round effect="light" type="info" style="margin-left: 5px;margin-right: 8px">小萌新</el-tag>
      <el-tag v-else-if="user_data.token.level===2" size="small" round effect="light" type="warning" style="margin-left: 5px;margin-right: 8px">进阶用户</el-tag>
      <el-tag v-else-if="user_data.token.level===3" size="small" round effect="light" type="primary" style="margin-left: 5px;margin-right: 8px">核心用户</el-tag>
      <el-tag v-else-if="user_data.token.level===4" size="small" round effect="light" type="success" style="margin-left: 5px;margin-right: 8px">资深用户</el-tag>
      <el-tag v-else-if="user_data.token.level===5" size="small" round effect="light" type="danger" style="margin-left: 5px;margin-right: 8px">超级管理员</el-tag>
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

</style>