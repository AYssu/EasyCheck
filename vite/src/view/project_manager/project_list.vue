<template>
  <div>
    <div style="margin-bottom: 20px;margin-top: 10px">
      <el-input
          v-model="search_params.projectName"
          placeholder="请输入项目名称"
          style="width: 40%;margin-right: 10px"
          @keyup.enter="get_project_list(search_params)"
      >
        <template #prepend>
          <el-button :icon="Search" @click="get_project_list(search_params)"></el-button>
        </template>
      </el-input>
      <el-button plain type="primary" @click="drawer = true">添加项目</el-button>

    </div>
    <el-table
        :data="tableData"
        :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <template #empty>
        <el-empty :image-size="200"/>
      </template>
      <el-table-column label="编号" prop="projectId" width="65"/>

      <el-table-column label="项目图标" prop="projectUpdateUrl" width="100">
        <template #default="row:any">
          <div style="width: 100%;display: flex;justify-content: center;align-items: center;">
            <el-image
                :src="row.row.projectIcon"
                fit="contain"
                style="width: 40px; height: 40px;border-radius: 5px"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column label="项目名称" prop="projectName" width="130">
        <template #default="scope">
          <el-tag :type="scope.row.tagType">{{ scope.row.projectName }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="添加日期" prop="projectCreateTime" sortable width="130"/>

      <el-table-column label="项目密钥" prop="projectKey" width="210">
        <template #default="scope">
          <el-text style="margin-left: 5px" type="warning" @click="copy_text(scope.row.projectKey)">
            {{ scope.row.projectKey }}
          </el-text>
          <el-button plain size="small" style="margin-left: 7px" type="primary"
                     @click="copy_text(scope.row.projectKey)">复制
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="BASE64" prop="projectBase64" show-overflow-tooltip sortable width="640">
        <template #default="scope">
          <el-text style="margin-left: 5px" type="info" @click="copy_text(scope.row.projectBase64)">
            {{ scope.row.projectBase64 }}
          </el-text>

          <el-button plain size="small" style="margin-left: 7px" type="primary"
                     @click="copy_text(scope.row.projectBase64)">复制
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="运行状态" prop="projectStatus" width="100">
        <template #default="scope:any">
          <el-switch
              v-model="scope.row.projectStatus"
              :active-value="0"
              :disabled="true"
              :inactive-value="1"
              active-text="运行"
              inactive-text="暂停"
              inline-prompt
              style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
              @click="update_project_status($event,scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column label="项目简介" prop="projectMessage" width="200">
        <template #default="scope">
          <el-text style="margin-left: 5px" type="info">
            {{ scope.row.projectMessage == "" ? "暂无介绍..." : scope.row.projectMessage }}
          </el-text>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="460">
        <template #default>
          <el-button plain size="small" type="primary">通用编辑</el-button>
          <el-button plain size="small" type="success">公告编辑</el-button>
          <el-button plain size="small" type="info">接口列表</el-button>
          <el-button plain size="small" type="warning">更新设置</el-button>
          <el-button plain size="small" type="danger">删除应用</el-button>

        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        v-model:current-page="search_params.currentPage"

        v-model:page-size="search_params.pageSize"
        :background="background"
        :disabled="disabled"
        :page-sizes="[10, 20, 30, 40]"
        :size="size"
        :total="total"
        layout="sizes, prev, pager, next"
        style="justify-content: flex-end;margin-right: 10px;margin-top: 10px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />

    <el-dialog v-model="drawer" :with-header="false"  title="创建程序">
      <el-form :model="create_project_params" :rules="create_rules" label-position="right" label-width="80px"
               style="margin: 30px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="create_project_params.projectName" placeholder="请输入项目名称"/>
        </el-form-item>
        <el-form-item label="项目简介" prop="projectMessage">
          <el-input v-model="create_project_params.projectMessage" placeholder="请输入项目简介"
                    @keyup.enter="create_project_click"/>
        </el-form-item>
        <el-form-item>
          <el-button plain type="primary" @click="create_project_click">创建</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>

import {Search} from "@element-plus/icons-vue";
import {onMounted, ref, UnwrapRef} from "vue";
import {create_project_services, get_project_list_services, update_project_status_services} from "@/api/project.ts";
import {Action, ComponentSize, ElMessage, ElMessageBox} from "element-plus";

const drawer = ref(false);


const total = ref(0)
const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)


interface Project {
  projectId: number
  projectCreateTime: string
  projectKey: string
  projectName: string
  projectMessage: string
  tagType: string
  projectStatus: number
}

// 创建程序的参数
interface Project_from {
  projectName: string
  projectMessage: string
}

interface Search {
  currentPage: number
  pageSize: number
  projectName: string | null
}

const create_rules = ref({
  projectName: [
    {required: true, message: '请输入项目名称', trigger: 'blur'},
    {min: 2, max: 5, message: '长度在 2 到 10 个字符', trigger: 'blur'}
  ],
  projectMessage: [
    {required: true, message: '请输入项目简介', trigger: 'blur'},
    {min: 2, max: 255, message: '长度在 2 到 255 个字符', trigger: 'blur'}
  ]

})
const search_params = ref<Search>({
  currentPage: 1,
  pageSize: 10,
  projectName: null
})

const create_project_params = ref<Project_from>({
  projectName: "",
  projectMessage: ""
})


const tableData = ref<Project[]>([]);

// 初始化数据
const get_project_list = async (value: UnwrapRef<Search>) => {
  const list_result = await get_project_list_services(value);
  console.log(list_result)
  tableData.value = list_result.data.data.items;
  tableData.value = list_result.data.data.items.map((item: Project) => ({
    ...item,
    tagType: randomType(),
    projectIcon: "https://www.loliapi.com/acg/pp/" // 随机头像 在线API 多久失效就难说了
  }));
  total.value = list_result.data.data.total;
}

onMounted(() => {
  // 初始化数据
  get_project_list(search_params.value);
})


const randomType = () => {
  const types = ['info', 'success', 'warning', 'danger'];
  return types[Math.floor(Math.random() * 4)];
}

const create_project_click = async () => {
  const crate_success = await create_project_services(create_project_params.value);
  if (crate_success.data.code === 200) {
    ElMessage.success(crate_success.data.message)
    drawer.value = false;
    create_project_params.value = {
      projectName: "",
      projectMessage: ""
    }
    await get_project_list(search_params.value);
  } else {
    ElMessage.error(crate_success.data.message)
  }
}

const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
  search_params.value.pageSize = val;
  get_project_list(search_params.value);
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
  search_params.value.currentPage = val;
  get_project_list(search_params.value);
}


const update_project_status = (event: any, row: Project) => {
  console.log(event)
  // 确认框
  ElMessageBox.confirm(`确定要${row.projectStatus == 0 ? "禁用" : "启用"}项目${row.projectName}吗？`, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',    type: row.projectStatus == 0 ? "warning" : "success"
  }).then(async (action: Action) => {
    if (action === 'confirm') {
      const update_result = await update_project_status_services(row.projectId);
      if (update_result.data.code === 200) {
        ElMessage.success(update_result.data.message)
        await get_project_list(search_params.value);
      } else {
        ElMessage.error(update_result.data.message)
      }
    }
  });

}

// 复制文本 在不支持复制的浏览器上，使用此方法
const old_copy = (text: string) => {
  const textArea = document.createElement('textarea');
  textArea.value = text;
  textArea.style.top = '0';
  textArea.style.left = '0';
  textArea.style.position = 'fixed'; // 防止在页面上显示
  document.body.appendChild(textArea);
  textArea.focus();
  textArea.select();
  try {
    const successful = document.execCommand('copy');
    if (successful) {
      ElMessage.success('复制成功');
    } else {
      ElMessage.error('复制失败，请手动复制');
    }
  } catch (err) {
    ElMessage.error('复制失败，请手动复制');
    console.error('无法复制文本: ', err);
  }

  document.body.removeChild(textArea);
}
// 复制项目key
const copy_text = (key: string) => {
  // 检查 key 的长度，如果超过20个字符，则截断并添加省略号
  const displayKey = key.length > 40 ? key.substring(0, 40) + '...' : key;

  ElMessageBox.alert(displayKey, '复制文本', {
    confirmButtonText: '确定',
    callback: (action: Action) => {
      if (action === 'confirm') {
        // 使用原始的 key 进行复制，而不是截断后的 displayKey
        if (navigator.clipboard) {
          navigator.clipboard.writeText(key).then(() => {
            ElMessage.success('复制成功');
          }, (err) => {
            ElMessage.error('复制失败，请手动复制');
            console.error('无法复制文本: ', err);
          });
        } else {
          // 浏览器不支持 navigator.clipboard 或不在安全上下文中
          old_copy(key);
        }
      }
    },
    dangerouslyUseHTMLString: true,
    closeOnClickModal: false,
    closeOnPressEscape: false,
    type: 'success',
    showCancelButton: true,
    showConfirmButton: true
  });
};


</script>