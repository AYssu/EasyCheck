<template>
  <div>
    <div style="margin-top: 10px">
      <div v-if="phone_bool" style="display:flex;justify-content: space-between;margin-right: 10px" >
          <div style="display: flex;align-items: center;margin-left: 10px;margin-bottom: 20px">
            <div style="background-color: rgb(128,128,128);width: 5px;height: 10px;margin-right: 5px"></div>
            <el-text size="small">用户列表</el-text>
          </div>
        <div>
          <el-button color="#42b983"  size="small" :icon="Search" plain>搜索</el-button>
        </div>
      </div>
      <el-form :size="phone_bool?'small':''" style="margin-left: 10px;max-width: calc(100% - 20px); " :inline="!phone_bool">

        <el-form-item label="用户邮箱">
          <el-input  placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="归属程序">
          <el-input  placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="会员等级" style="min-width: 240px">
          <el-select  clearable placeholder="请选择">
            <el-option label="测试" value="1"/>
            <el-option label="开发" value="2"/>
            <el-option label="发布" value="3"/>
            <el-option label="稳定" value="4"/>
          </el-select>
        </el-form-item>

        <el-form-item v-if="!phone_bool">
          <el-button color="#42b983" plain :icon="Search" type="primary" >搜索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-card shadow="never" style="margin: 0 5px;box-sizing: border-box">
      <el-table
          :data="tableData"
          :default-sort="{ prop: 'date', order: 'descending' }"
          :highlight-current-row="true"
          :size="phone_bool?'small':''"
      >
        <template #empty>
          <el-empty></el-empty>
        </template>
        <el-table-column label="UID" prop="openUserId" width="60px"/>
        <el-table-column label="用户邮箱" prop="openEmail" width="190">
          <template #default="scope">
            <el-link style="font-size: 0.8rem" type="success">{{ scope.row.openEmail }}</el-link>
          </template>
        </el-table-column>
        <el-table-column label="会员等级" prop="openVip" width="100">
          <template #default="scope">
            <el-tag
                :effect="tagEffect"
                :round="tagRound"
                :size="tagSize"
                :type="getVipType(scope.row.openVip)"
                style="margin-left: 5px; margin-right: 8px">
              {{ getVipLabel(scope.row.openVip) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="归属程序" prop="projectName" width="120">
          <template #default="scope">
            <el-tag type="primary">{{ scope.row.projectName }}</el-tag>
          </template>
        </el-table-column>

        <el-table-column align="left" label="会员到期" prop="openSvipTime" width="200">
          <template #default="scope">
            <el-text :size="phone_bool?'small':''" style="margin-left: 5px" type="danger">
              {{ scope.row.openSvipTime == null ? "未激活" : scope.row.openSvipTime }}
            </el-text>
          </template>
        </el-table-column>

        <el-table-column label="注册日期" prop="openCreateTime" sortable width="140"/>

        <el-table-column label="操作" width="500">
          <template #default>
            <el-button plain size="small" type="primary">通用编辑</el-button>
            <el-button plain size="small" type="success">余额管理</el-button>
            <el-button plain size="small" type="warning">会员等级</el-button>
            <el-button plain size="small" type="danger">拉黑用户</el-button>
            <el-button plain size="small" type="danger">删除用户</el-button>

          </template>
        </el-table-column>
      </el-table>

    </el-card>
    <div style="margin-top: 20px;display: flex;justify-content: flex-end;margin-right: 20px">
      <el-pagination
          v-model:current-page="currentPage2"
          v-model:page-size="pageSize2"
          :background="background"
          :disabled="disabled"
          :page-sizes="[10, 20, 30, 40]"
          :size="size"
          :total="total"
          layout="sizes, prev, pager, next"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, onUnmounted, ref} from "vue";
import {ComponentSize} from "element-plus";
import {get_project_user_list_services} from "@/api/project.ts";
import phone_size from "@/utils/phone_size.ts";
import {Search} from "@element-plus/icons-vue";
const {phone_bool,remove_phone_size} = phone_size();

onUnmounted(()=>{
  remove_phone_size()
})
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
  1: {label: '小萌新', type: 'info'},
  2: {label: '进阶用户', type: 'warning'},
  3: {label: '核心用户', type: 'primary'},
  4: {label: '资深用户', type: 'success'},
  5: {label: '无敌大佬', type: 'danger'},
  default: {label: '加载中', type: 'info'}
};

const getVipType = (level: number) => {
  return vipLevels[level] ? vipLevels[level].type : vipLevels.default.type;
}

const getVipLabel = (level: number) => {
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

const get_user_list = async () => {
  const params = {
    currentPage: currentPage2.value,
    pageSize: pageSize2.value
  }
  const result = await get_project_user_list_services(params);
  try {
    tableData.value = result.data.data.items
    total.value = result.data.data.total
  } catch (e) {
    console.log(e)
  }
}

onMounted(() => {
  get_user_list()
})
</script>