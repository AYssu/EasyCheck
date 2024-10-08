<template>
  <el-container class="layout-container-demo" >
    <el-header style="text-align: right; font-size: 12px">
      <div class="toolbar">
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
    </el-header>

    <el-container>
      <el-aside width="200px">
        <el-scrollbar>
          <el-menu :default-openeds="['1', '2']">
            <el-sub-menu index="1">
              <template #title>
                <el-icon><DataLine /></el-icon>数据统计
              </template>
              <el-menu-item index="1-1">项目数据</el-menu-item>

            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <el-icon><Promotion /></el-icon>项目管理
              </template>
              <el-menu-item-group>
                <template #title>项目</template>
                <el-menu-item index="2-1">项目列表</el-menu-item>
                <el-menu-item index="2-2">用户列表</el-menu-item>

              </el-menu-item-group>

              <el-menu-item-group>
                <template #title>项目</template>

              </el-menu-item-group>
              <el-sub-menu index="2-4">
                <template #title>项目管理</template>
                <el-menu-item index="2-4-1">卡密列表</el-menu-item>
                <el-menu-item index="2-4-2">激活权限</el-menu-item>
                <el-menu-item index="2-4-3">用户认证</el-menu-item>
              </el-sub-menu>
            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <el-icon><setting /></el-icon>项目设置
              </template>
              <el-menu-item-group>
                <template #title>系统设置</template>
                <el-menu-item index="3-1">全局设置</el-menu-item>
                <el-menu-item index="3-2">用户设置</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="系统代理">
                <el-menu-item index="3-3">代理分配</el-menu-item>
                <el-menu-item index="3-4">代理统计</el-menu-item>
              </el-menu-item-group>

            </el-sub-menu>
          </el-menu>
        </el-scrollbar>
      </el-aside>
      <el-container>
        <el-main>
          <el-scrollbar>
            <el-table :data="tableData">
              <el-table-column prop="date" label="Date" width="140" />
              <el-table-column prop="name" label="Name" width="120" />
              <el-table-column prop="address" label="Address" />
            </el-table>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import {DataLine, Promotion, Setting} from '@element-plus/icons-vue'
import default_icon from '@/assets/default.jpg'

import {user_token} from "@/stores/token.ts";
const user_data = user_token()

const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}

const tableData = ref(Array.from({ length: 20 }).fill(item))
</script>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>
