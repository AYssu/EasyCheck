<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-input
          placeholder="请输入搜索内容"
          style="width: 40%;margin-right: 10px"
      >
        <template #prepend>
          <el-button :icon="Search"></el-button>
        </template>
      </el-input>
      <el-button type="primary" plain>添加项目</el-button>

    </div>
    <el-table
        :data="tableData"
        :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <el-table-column prop="id"  width="100" label="项目编号" />
      <el-table-column prop="name" label="项目名称" width="130">
        <template #default="scope">
          <el-tag type="primary">{{ scope.row.name }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="date" label="添加日期" sortable width="130" />

      <el-table-column prop="key" label="项目密钥" width="180">
        <template #default="scope">
          <el-text type="warning" style="margin-left: 5px">{{scope.row.key}}</el-text>
        </template>
      </el-table-column>
      <el-table-column prop="introduction" label="项目简介"  width="200">
        <template #default="scope">
          <el-text type="info"  style="margin-left: 5px">{{scope.row.introduction}}</el-text>
        </template>
      </el-table-column>
      <el-table-column  label="操作">
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

interface Project {
  id: number
  date: string
  key: string
  name: string
  introduction: string
}

const tableData: Project[] = [
  {
    id: 1,
    date: '2016-05-03',
    key: 'ab127u8q29h20s',
    name: '未来之役',
    introduction: '一款外国游戏',
  },
  {
    id: 2,
    date: '2016-05-02',
    key: 'ab127u8q29h20s',
    name: '和平精英',
    introduction: '击毙你,小逼崽子',
  },
  {
    id:3,
    date: '2016-05-04',
    key: 'ab127u8q29h20s',
    name: '王者荣耀',
    introduction: '公平2V5的推塔游戏',
  },
  {
    id:4,
    date: '2016-05-01',
    key: 'ab127u8q29h20s',
    name: '香肠派对',
    introduction: '香肠打枪枪游戏',
  },
]
</script>