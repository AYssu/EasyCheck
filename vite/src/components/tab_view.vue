<template>
<el-tabs
    class="tab-view"
    type="card"
    v-model="active_tab"
    closable
    @tab-remove="tab_remove"
    @tab-click="tab_click">
  <el-tab-pane v-for="(item ,index) in tab_list" :key="index" :name="item.path" :label="item.title" ></el-tab-pane>
</el-tabs>
</template>
<style scoped lang="scss">
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
import {TabsPaneContext} from "element-plus";
import router from "@/router";
const tabs_status_data = tabs_status();

const active_tab = ref('');
const tab_list =  computed(()=>{
  return tabs_status_data.get_tab_list();
})

const add_tab = () => {
  const tab:Tab = {
    title: route.meta.title as string,
    path: route.path
  }
  tabs_status_data.set_tab_list(tab)
}

const route = useRoute();
watch(()=>route.path, ()=>{
  add_tab()
  active_tab.value = route.path
})

const tab_remove = (tab_name: string) => {
  console.log("delete tab:", tab_name)
  const tabs = tab_list.value;
  let active_tab_name = active_tab.value;
  if (tab_name === active_tab_name)
  {
    tabs.forEach((item, index)=>{
      if (item.path === tab_name)
      {

        const next_tab = tabs[index+1] || tabs[index-1];
        if (next_tab)
        {
          active_tab_name = next_tab.path;
        }
      }
    })
  }
  console.log("active_tab_name:", active_tab_name)
  active_tab.value = active_tab_name
  router.push({path: active_tab_name})
  tabs_status_data.tab_list = tabs.filter(item=>item.path !== tab_name)

}


const tab_click = (tab: TabsPaneContext) => {
  console.log(tab.props)
  router.push({path: tab.props.name as string})
}
onMounted(()=> {
  active_tab.value = route.path
  add_tab()
})
</script>