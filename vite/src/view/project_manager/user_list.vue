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
      <template #empty>
        <el-empty></el-empty>
      </template>
      <el-table-column prop="openUserId"  width="100" label="用户ID" />
      <el-table-column prop="projectName"  width="120" label="归属程序">
        <template #default="scope">
          <el-tag type="primary">{{ scope.row.projectName }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="openCreateTime" label="注册日期" sortable width="140" />
      <el-table-column prop="openEmail" label="用户邮箱"  width="170">
        <template #default="scope">
          <el-link type="success">{{scope.row.openEmail}}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="openSvipTime" align="left" label="会员到期"  width="200">
        <template #default="scope">
          <el-text type="danger" style="margin-left: 5px">{{scope.row.openSvipTime==null?"未激活":scope.row.openSvipTime}}</el-text>
        </template>
      </el-table-column>
      <el-table-column prop="openVip" label="会员等级" width="100">
        <template #default="scope">
          <el-tag
              :size="tagSize"
              :round="tagRound"
              :effect="tagEffect"
              :type="getVipType(scope.row.openVip)"
              style="margin-left: 5px; margin-right: 8px">
            {{ getVipLabel(scope.row.openVip) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column  label="操作" width="500">
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
          :page-sizes="[10, 20, 30, 40]"
          :size="size"
          :disabled="disabled"
          :background="background"
          layout="sizes, prev, pager, next"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>

import {Search} from "@element-plus/icons-vue";
import {onMounted, ref} from "vue";
import {ComponentSize} from "element-plus";
import {get_project_user_list_services} from "@/api/project.ts";
interface Project_User {
  openUserId: number
  projectName: string
  openCreateTime: string
  openEmail: string
  openVip: number
  openSvipTime: string
}

const tableData = ref<Project_User[]>();

const currentPage2 = ref(1)
const pageSize2 = ref(10)
const total = ref(0)
const size = ref<ComponentSize>('small')
const background = ref(false)
const disabled = ref(false)


const tagSize = ref('small');
const tagRound = ref(true);
const tagEffect = ref('light');

const vipLevels: any = {
  1: { label: '小萌新', type: 'info' },
  2: { label: '进阶用户', type: 'warning' },
  3: { label: '核心用户', type: 'primary' },
  4: { label: '资深用户', type: 'success' },
  5: { label: '无敌大佬', type: 'danger' },
  default: { label: '加载中', type: 'info' }
};

const  getVipType = (level:number) => {
  return vipLevels[level] ? vipLevels[level].type : vipLevels.default.type;
}

const  getVipLabel = (level:number) => {
  return vipLevels[level] ? vipLevels[level].label : vipLevels.default.label;
}
const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
  get_user_list()
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
  get_user_list()
}

const get_user_list = async ()=>{
  const params = {
    currentPage:currentPage2.value,
    pageSize: pageSize2.value
  }
  const result = await get_project_user_list_services(params);
  try {
    tableData.value = result.data.data.items
    total.value = result.data.data.total
  }catch (e)
  {
    console.log(e)
  }
}

onMounted(()=>{
  get_user_list()
})
</script>