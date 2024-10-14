<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-input
          placeholder="请输入搜索内容"
          v-model="search_params.projectName"
          style="width: 40%;margin-right: 10px"
          @keyup.enter="get_project_list(search_params)"
      >
        <template #prepend>
          <el-button @click="get_project_list(search_params)" :icon="Search"></el-button>
        </template>
      </el-input>
      <el-button type="primary" plain>添加项目</el-button>

    </div>
    <el-table
        :data="tableData"
        :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <el-table-column prop="projectId"  width="100" label="项目编号" />
      <el-table-column prop="projectName" label="项目名称" width="130">
        <template #default="scope">
          <el-tag :type="scope.row.tagType">{{ scope.row.projectName }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="projectCreateTime" label="添加日期" sortable width="130" />

      <el-table-column prop="projectKey" label="项目密钥" width="180">
        <template #default="scope">
          <el-text type="warning" style="margin-left: 5px">{{scope.row.projectKey}}</el-text>
        </template>
      </el-table-column>
      <el-table-column prop="projectMessage" label="项目简介"  width="200">
        <template #default="scope">

          <el-text type="info"  style="margin-left: 5px">{{scope.row.projectMessage==""?"暂无介绍...":scope.row.projectMessage}}</el-text>
        </template>
      </el-table-column>
      <el-table-column  label="操作" width="600">
        <template #default>
          <el-button type="primary" size="small" plain >通用编辑</el-button>
          <el-button type="success" size="small" plain >公告编辑</el-button>
          <el-button type="info" size="small" plain >远程设置</el-button>
          <el-button type="warning" size="small" plain >更新设置</el-button>
          <el-button type="danger" size="small" plain >删除应用</el-button>

        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>

import {Search} from "@element-plus/icons-vue";
import {onMounted, ref, UnwrapRef} from "vue";
import {get_project_list_services} from "@/api/project.ts";

interface Project {
  projectId : number
  projectCreateTime: string
  projectKey  : string
  projectName: string
  projectMessage: string
  tagType: string
}

interface Search {
  currentPage: number
  pageSize: number
  projectName: string|null
}
const search_params = ref<Search>({
  currentPage: 1,
  pageSize: 20,
  projectName: null
})
const tableData = ref<Project[]>([]);

// 初始化数据
const get_project_list = async (value: UnwrapRef<Search>) => {
  const list_result = await get_project_list_services(value);
  console.log(list_result)
  tableData.value = list_result.data.data.items;
  tableData.value = list_result.data.data.items.map((item:Project) => ({
    ...item,
    tagType: randomType(),
  }));
}

onMounted(()=>{
  // 初始化数据
  get_project_list(search_params.value);
})


const randomType = () => {
  const types = ['info', 'success', 'warning', 'danger'];
  return types[Math.floor(Math.random() * 4)];
}
</script>