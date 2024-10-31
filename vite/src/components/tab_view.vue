<template>
  <el-tabs
      v-model="active_tab"
      class="tab-view"
      closable
      type="card"
      @tab-remove="tab_remove"
      @tab-click="tab_click">
    <el-tab-pane v-for="(item ,index) in tab_list" :key="index" :label="item.title" :name="item.path"></el-tab-pane>
  </el-tabs>
</template>
<style lang="scss" scoped>
:deep(.el-tabs__header) {
  box-sizing: border-box;
  margin: 0; // 修正了Opx为0px
}

:deep(.el-tabs__item) {
  height: 26px !important;
  line-height: 26px !important;
  text-align: center !important;
  margin: 0 3px !important;
  color: #495060;
  border: none !important;
  font-size: 13px !important;
  padding: 0 10px !important; // 修正了0xp为0px
}

:deep(.el-tabs__nav) {
  border: none !important;
}

:deep(.is-active) {
  border: 1px solid #42b983 !important;
  border-radius: 10px;
  background-color: #42b983 !important;
  color: #fff !important;
}

:deep(.el-tabs__item:hover) {
  color: #495060 !important;
}

:deep(.is-active:hover) {
  color: #fff !important;
}

:deep(.el-tabs__nav-next) {
  line-height: 26px !important;
}
</style>
<script lang="ts" setup>
import {Tab, tabs_status} from "@/stores/tabs/tabs.ts";
import {computed, onMounted, ref, watch} from "vue";
import {useRoute} from "vue-router";
import {ElMessage, TabsPaneContext} from "element-plus";
import router from "@/router";

// 获取tabs状态 路由的store
const tabs_status_data = tabs_status();

const active_tab = ref('');

// 计算属性 获取tabs的列表
const tab_list = computed(() => {
  return tabs_status_data.get_tab_list();
})

// 添加tab
const add_tab = () => {
  const tab: Tab = {
    title: route.meta.title as string,
    path: route.path
  }
  tabs_status_data.set_tab_list(tab)
}

// 监听路由变化
const route = useRoute();
watch(() => route.path, () => {
  add_tab()
  const routes = router.getRoutes();
  const routeToDeactivate = routes.find(r => r.path === route.path);
  if (routeToDeactivate) {
    console.log("routeToDeactivate:", routeToDeactivate)
    // routeToDeactivate.meta.keepAlive = true;
  }

  active_tab.value = route.path
})

// 删除tab
const tab_remove = (tab_name: string) => {
  console.log("delete tab:", tab_name)
  const tabs = tab_list.value;

  // 不能删除首页 初始进入首页会载入table
  if (tab_name === "/home/table") {
    ElMessage.warning("不能删除首页");
    return
  }
  let active_tab_name = active_tab.value;
  if (tab_name === active_tab_name) {
    tabs.forEach((item, index) => {
      if (item.path === tab_name) {
        const next_tab = tabs[index + 1] || tabs[index - 1];
        if (next_tab) {
          active_tab_name = next_tab.path;
        }
      }
    })
  }
  console.log("active_tab_name:", active_tab_name)
  active_tab.value = active_tab_name
  router.push({path: active_tab_name})

  // 更新对应路由的keepAlive属性为false
  const routes = router.getRoutes();
  const routeToDeactivate = routes.find(r => r.path === tab_name);
  if (routeToDeactivate) {
    console.log("routeToDeactivate:", routeToDeactivate)
    routeToDeactivate.meta.keepAlive = false;
  }

  tabs_status_data.tab_list = tabs.filter(item => item.path !== tab_name)

}

// 切换tab
const tab_click = (tab: TabsPaneContext) => {
  console.log(tab.props)
  router.push({path: tab.props.name as string})
}

// 初始化路由路径和激活路由样式
onMounted(() => {
  active_tab.value = route.path
  add_tab()
})
</script>