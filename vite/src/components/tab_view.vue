<template>
<el-tabs
    class="tab-view"
    type="card"
    closable
    @tab-remove="tab_remove"
    @tab-click="tab_click">
  <el-tab-pane v-for="(item ,index) in tab_list" :key="index" :name="item.path" :label="item.title" ></el-tab-pane>
</el-tabs>
</template>
<style scoped lang="scss">

</style>
<script lang="ts" setup>
import {Tab, tabs_status} from "@/stores/tabs/tabs.ts";
import {computed, ref, watch} from "vue";
import {useRoute} from "vue-router";
const tabs_status_data = tabs_status();

const active_tab = ref('');
const tab_list =  computed(()=>{
  return tabs_status_data.get_tab_list();
})

const route = useRoute();
watch(()=>route.path, ()=>{
  const tab:Tab = {
    title: route.meta.title as string,
    path: route.path
  }
  tabs_status_data.set_tab_list(tab)
}
)
const tab_remove = (tab: any) => {

}
const tab_click = (tab: any) => {

}
</script>