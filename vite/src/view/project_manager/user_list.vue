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


    </div>
    <el-table
        :data="tableData"
        :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <el-table-column prop="id"  width="100" label="用户ID" />
      <el-table-column prop="project_name"  width="120" label="归属程序">
        <template #default="scope">
          <el-tag type="primary">{{ scope.row.project_name }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="date" label="注册日期" sortable width="120" />
      <el-table-column prop="name" label="用户昵称" width="120" />
      <el-table-column prop="email" label="用户邮箱"  width="150">
        <template #default="scope">
          <el-link type="success">{{scope.row.email}}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="money" label="用户余额"  width="100">
        <template #default="scope">
          <el-text type="danger" style="margin-left: 5px">{{scope.row.money}} ¥</el-text>
        </template>
      </el-table-column>
      <el-table-column prop="level" label="会员等级" width="100">
        <template #default="scope">
          <el-tag v-if="scope.row.level===1" size="small" round effect="light" type="info" style="margin-left: 5px;margin-right: 8px">小萌新</el-tag>
          <el-tag v-else-if="scope.row.level===2" size="small" round effect="light" type="warning" style="margin-left: 5px;margin-right: 8px">进阶用户</el-tag>
          <el-tag v-else-if="scope.row.level===3" size="small" round effect="light" type="primary" style="margin-left: 5px;margin-right: 8px">核心用户</el-tag>
          <el-tag v-else-if="scope.row.level===4" size="small" round effect="light" type="success" style="margin-left: 5px;margin-right: 8px">资深用户</el-tag>
          <el-tag v-else-if="scope.row.level===5" size="small" round effect="light" type="danger" style="margin-left: 5px;margin-right: 8px">超级管理员</el-tag>


        </template>
      </el-table-column>
      <el-table-column  label="操作">
        <template #default>
          <el-button type="primary" size="small" plain >通用编辑</el-button>
          <el-button type="success" size="small" plain >余额管理</el-button>
          <el-button type="warning" size="small" plain >会员等级</el-button>
          <el-button type="danger" size="small" plain >拉黑用户</el-button>
          <el-button type="danger" size="small" plain >删除用户</el-button>

        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px;display: flex;justify-content: flex-end;margin-right: 20px">
      <el-pagination
          v-model:current-page="currentPage2"
          v-model:page-size="pageSize2"
          :page-sizes="[20, 50, 80, 400]"
          :size="size"
          :disabled="disabled"
          :background="background"
          layout="sizes, prev, pager, next"
          :total="1000"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>

import {Search} from "@element-plus/icons-vue";
import {ref} from "vue";
import {ComponentSize} from "element-plus";

interface User {
  id: number
  project_name: string
  date: string
  name: string
  email:string
  money: number
  level: number
  introduction: string
}

const tableData: User[] = [
  {
    id: 1,
    project_name: '未来之役',
    date: '2016-05-02',
    name: '王小虎',
    email:'123456@qq.com',
    money: 100,
    level: 1,
    introduction: 'Vue is a progressive framework for building user interfaces.'
  },
    {
      id: 2,
      project_name: '王者荣耀',
      date: '2016-05-04',
      name: '王小虎',
      email:'123456@qq.com',
      money: 100,
      level: 2,
      introduction: 'Vue is a progressive framework for building user interfaces.'
    },
]

const currentPage2 = ref(5)
const pageSize2 = ref(20)
const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)

const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
}
</script>