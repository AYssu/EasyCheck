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
      <el-button type="primary" plain @click="drawer = true" >添加项目</el-button>

    </div>
    <el-table
        :data="tableData"
        :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <template #empty>
        <el-empty :image-size="200"/>
      </template>
      <el-table-column prop="projectId"  width="65" label="编号" />

      <el-table-column prop="projectUpdateUrl" label="项目图标" width="100">
        <template #default="row:any">
          <div style="width: 100%;display: flex;justify-content: center;align-items: center;">
            <el-image
                style="width: 40px; height: 40px;border-radius: 5px"
                :src="row.row.projectIcon"
                fit="contain"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="projectName" label="项目名称" width="130">
        <template #default="scope">
          <el-tag :type="scope.row.tagType">{{ scope.row.projectName }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="projectCreateTime" label="添加日期" sortable width="130" />

      <el-table-column prop="projectKey" label="项目密钥" width="210">
        <template #default="scope">
          <el-text type="warning" @click="copy_text(scope.row.projectKey)" style="margin-left: 5px">{{scope.row.projectKey}}</el-text>
          <el-button type="primary" size="small" plain style="margin-left: 7px" @click="copy_text(scope.row.projectKey)">复制</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="projectBase64" label="BASE64" sortable width="640" show-overflow-tooltip >
        <template #default="scope">
          <el-text type="info" @click="copy_text(scope.row.projectBase64)" style="margin-left: 5px">{{scope.row.projectBase64}}</el-text>

          <el-button type="primary" size="small" plain style="margin-left: 7px" @click="copy_text(scope.row.projectBase64)">复制</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="projectStatus" label="运行状态" width="100">
        <template #default="scope:any">
          <el-switch
              v-model="scope.row.projectStatus"
              :active-value="0"
              :inactive-value="1"
              style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
              inline-prompt
              active-text="运行"
              inactive-text="暂停"
              @click="update_project_status(scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column prop="projectMessage" label="项目简介"  width="200">
        <template #default="scope">
          <el-text type="info"  style="margin-left: 5px">{{scope.row.projectMessage==""?"暂无介绍...":scope.row.projectMessage}}</el-text>
        </template>
      </el-table-column>
      <el-table-column  label="操作" width="460">
        <template #default>
          <el-button type="primary" size="small" plain >通用编辑</el-button>
          <el-button type="success" size="small" plain >公告编辑</el-button>
          <el-button type="info" size="small" plain >远程设置</el-button>
          <el-button type="warning" size="small" plain >更新设置</el-button>
          <el-button type="danger" size="small" plain >删除应用</el-button>

        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        style="justify-content: flex-end;margin-right: 10px;margin-top: 10px"

        v-model:current-page="search_params.currentPage"
        v-model:page-size="search_params.pageSize"
        :page-sizes="[10, 20, 30, 40]"
        :size="size"
        :disabled="disabled"
        :background="background"
        layout="sizes, prev, pager, next"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />

    <el-drawer v-model="drawer" title="创建程序" :with-header="false" direction="ttb">
      <el-form label-width="80px" :model="create_project_params" label-position="right" style="margin: 30px" :rules = "create_rules">
        <el-form-item label="项目名称" prop="projectName">
          <el-input placeholder="请输入项目名称" v-model="create_project_params.projectName" />
        </el-form-item>
        <el-form-item label="项目简介" prop="projectMessage">
          <el-input @keyup.enter="create_project_click" placeholder="请输入项目简介" v-model="create_project_params.projectMessage" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" plain @click="create_project_click">创建</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script lang="ts" setup>

import {Search} from "@element-plus/icons-vue";
import {onMounted, ref, UnwrapRef} from "vue";
import {create_project_services, get_project_list_services} from "@/api/project.ts";
import {Action, ComponentSize, ElMessage, ElMessageBox} from "element-plus";
const drawer = ref(false);



const total = ref(0)
const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)


interface Project {
  projectId : number
  projectCreateTime: string
  projectKey  : string
  projectName: string
  projectMessage: string
  tagType: string
}

// 创建程序的参数
interface Project_from {
  projectName: string
  projectMessage: string
}

interface Search {
  currentPage: number
  pageSize: number
  projectName: string|null
}

const create_rules = ref({
  projectName: [
    { required: true, message: '请输入项目名称', trigger: 'blur' },
    { min: 2, max: 5, message: '长度在 2 到 10 个字符', trigger: 'blur' }
  ],
  projectMessage: [
    { required: true, message: '请输入项目简介', trigger: 'blur' },
    { min: 2, max: 255, message: '长度在 2 到 255 个字符', trigger: 'blur' }
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
  tableData.value = list_result.data.data.items.map((item:Project) => ({
    ...item,
    tagType: randomType(),
    projectIcon: "https://www.loliapi.com/acg/pp/" // 随机头像 在线API 多久失效就难说了
  }));
  total.value = list_result.data.data.total;
}

onMounted(()=>{
  // 初始化数据
  get_project_list(search_params.value);
})


const randomType = () => {
  const types = ['info', 'success', 'warning', 'danger'];
  return types[Math.floor(Math.random() * 4)];
}

const create_project_click = async () => {
  const crate_success = await create_project_services(create_project_params.value);
  if (crate_success.data.code===200)
  {
    ElMessage.success(crate_success.data.message)
    drawer.value = false;
    create_project_params.value = {
      projectName: "",
      projectMessage: ""
    }
    await get_project_list(search_params.value);
  }else {
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


const update_project_status = async (row: Project) => {
  console.log(row)
}

// 复制文本 在不支持复制的浏览器上，使用此方法
const  old_copy = (text:string)=> {
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
const copy_text =  (key: string) => {
  // 检查 key 的长度，如果超过20个字符，则截断并添加省略号
  const displayKey = key.length > 40 ? key.substring(0, 40) + '...' : key;

  ElMessageBox.alert(displayKey, '复制文本', {
    confirmButtonText: '确定',
    callback: (action:Action) => {
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