<template>
  <el-container class="layout-container-demo">

    <el-container>
      <el-aside style="max-width: 200px" width="auto">
        <div >
          <logo_view v-if="show_aside"/>
          <div style="width: 100%;display: flex;justify-content: center;margin-top: 18px;margin-bottom: 10px">
            <img v-if="!show_aside" :src="icon_translate" alt="icon" style="width: 25px;height: 25px">
          </div>
          <el-menu :collapse="!show_aside"
                   :default-active="active_menu"
                   :default-openeds="['1', '2']"
                   class="el-menu-vertical-demo"
                   router
                   @close="handleClose"
                   @open="handleOpen">
            <el-sub-menu index="1">
              <template #title>
                <el-icon>
                  <DataLine/>
                </el-icon>
                <span>数据统计</span>
              </template>
              <el-menu-item index="/home/table">项目数据</el-menu-item>

            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <el-icon>
                  <Promotion/>
                </el-icon>
                <span>项目管理</span>
              </template>
              <el-menu-item-group>
                <template #title>项目</template>
                <el-menu-item index="/home/project_list">项目列表</el-menu-item>
                <el-menu-item index="/home/user_list">用户列表</el-menu-item>

              </el-menu-item-group>

              <el-menu-item-group>
                <template #title>项目</template>

              </el-menu-item-group>
              <el-sub-menu index="2-4">
                <template #title>项目管理</template>
                <el-menu-item index="/home/manage/card_list">卡密列表</el-menu-item>
                <el-menu-item index="/home/manage/user_authentic">激活权限</el-menu-item>
                <el-menu-item index="/home/manage/user_authority">用户认证</el-menu-item>
              </el-sub-menu>
            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <el-icon>
                  <setting/>
                </el-icon>
                <span>项目设置</span>
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
        </div>
      </el-aside>
      <el-container>
        <el-header style="font-size: 12px">
          <top_bar/>
        </el-header>
        <tab_view/>
        <el-main>
          <el-scrollbar>
            <router-view v-slot="{ Component }">
              <transition mode="out-in" name="scale">
                <component :is="Component"/>
              </transition>
            </router-view>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped>

// 淡出淡入
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

// 滑动
.slide-enter-active,
.slide-leave-active {
  transition: all 0.75s ease-out;
}

.slide-enter-to {
  position: absolute;
  right: 0;
}

.slide-enter {
  position: absolute;
  right: -100%;
}

.slide-leave-to {
  position: absolute;
  left: -100%;
}

.slide-leave {
  position: absolute;
  left: 0;
}

// 设置滑动过渡必须给每个组件设定宽度
.wrapper {
  width: 100%;
  // min-height: 100vh;
  // background: chartreuse;
}

// 缩放
.scale-enter-active,
.scale-leave-active {
  transition: all 0.5s ease;
}

.scale-enter-from,
.scale-leave-to {
  opacity: 0;
  transform: scale(0.9);
}


</style>
<script lang="ts" setup>
import {DataLine, Promotion, Setting} from '@element-plus/icons-vue'
import logo_view from "@/components/logo_view.vue"
import top_bar from '@/components/top_bar.vue'

import {useRouter} from "vue-router";
import {computed, ref, watch} from "vue";

const router = useRouter()
import {aside_status} from "@/stores/aside.ts";

const aside_data = aside_status()

import icon_translate from '@/assets/icon_translate.png'
import tab_view from "@/components/tab_view.vue";

// 获取当前路由
const active_menu = computed(() => {
  return router.currentRoute.value.path
})

// 监听侧边栏状态
const show_aside = ref(true);
watch(() => aside_data.status, (new_value: boolean) => {
  if (!new_value) {
    setTimeout(() => {
      show_aside.value = false
    }, 200)

  } else {
    show_aside.value = true
  }
})


const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}


const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
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

.logo_view_style {
  visibility: hidden;
  height: 100px;
  width: 0;
}

.logo_view_style_visible {
  visibility: visible;
  width: 100%;
}
</style>
