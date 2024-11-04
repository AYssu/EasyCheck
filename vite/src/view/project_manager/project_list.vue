<template>
  <div>
    <div style="margin-bottom: 20px;margin-top: 10px">
        <el-autocomplete
            v-model="search_params.projectName"
            :fetch-suggestions="querySearch"
            clearable
            placeholder="请输入项目名称"
            style="width: 40%;margin-right: 10px"
            @keyup.enter="get_project_list(search_params)"
            @select="get_project_list(search_params)"
        >
          <template #prepend>
            <el-button :icon="Search" @click="get_project_list(search_params)"></el-button>
          </template>
        </el-autocomplete>
      <el-button plain type="primary" @click="drawer = true">添加项目</el-button>

    </div>
    <div class="box_card_list">

      <a-card v-for="(item,index) in tableData" :key="index" hoverable style="width: 240px" @click="on_click_open_show(item)">
        <template #cover>
          <img alt="example" src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"/>
        </template>
        <template #actions>
          <setting-outlined  />
          <edit-outlined  />
          <ellipsis-outlined  />
        </template>
        <a-card-meta  >
          <template #avatar>
            <a-avatar :src="item.projectIcon" />
          </template>
          <template #title>
            <div >
              <el-text >{{ item.projectName }}</el-text>
              <el-switch
                  v-model="item.projectStatus"
                  :active-value="0"
                  :disabled="true"
                  :inactive-value="1"
                  active-text="启用"
                  inactive-text="禁用"
                  inline-prompt
                  style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949;position: absolute;right: 5px;top: 0"
                  @click="update_project_status(item as Project)"
              />
            </div>
          </template>
          <template #description>
            <div>
              <div>
                <el-text type="info" size="small">创建时间: {{ item.projectCreateTime }}</el-text>
              </div>
            </div>
          </template>
        </a-card-meta>
      </a-card>
    </div>

<!--    <el-table-->
<!--        :data="tableData"-->
<!--        :default-sort="{ prop: 'date', order: 'descending' }"-->
<!--    >-->
<!--      <template #empty>-->
<!--        <el-empty :image-size="200"/>-->
<!--      </template>-->
<!--      <el-table-column label="编号" prop="projectId" width="65"/>-->

<!--      <el-table-column label="项目图标" prop="projectUpdateUrl" width="100">-->
<!--        <template #default="row:any">-->
<!--          <div style="width: 100%;display: flex;justify-content: center;align-items: center;">-->
<!--            <el-image-->
<!--                :src="row.row.projectIcon"-->
<!--                fit="contain"-->
<!--                style="width: 40px; height: 40px;border-radius: 5px"-->
<!--            />-->
<!--          </div>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="项目名称" prop="projectName" width="130">-->
<!--        <template #default="scope">-->
<!--          <el-tag :type="scope.row.tagType">{{ scope.row.projectName }}</el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="添加日期" prop="projectCreateTime" sortable width="130"/>-->

<!--      <el-table-column label="项目密钥" prop="projectKey" width="210">-->
<!--        <template #default="scope">-->
<!--          <el-text style="margin-left: 5px" type="warning" @click="copy_text(scope.row.projectKey)">-->
<!--            {{ scope.row.projectKey }}-->
<!--          </el-text>-->
<!--          <el-button plain size="small" style="margin-left: 7px" type="primary"-->
<!--                     @click="copy_text(scope.row.projectKey)">复制-->
<!--          </el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="BASE64" prop="projectBase64" show-overflow-tooltip sortable width="640">-->
<!--        <template #default="scope">-->
<!--          <el-text style="margin-left: 5px" type="info" @click="copy_text(scope.row.projectBase64)">-->
<!--            {{ scope.row.projectBase64 }}-->
<!--          </el-text>-->

<!--          <el-button plain size="small" style="margin-left: 7px" type="primary"-->
<!--                     @click="copy_text(scope.row.projectBase64)">复制-->
<!--          </el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="运行状态" prop="projectStatus" width="100">-->
<!--        <template #default="scope:any">-->
<!--          <el-switch-->
<!--              v-model="scope.row.projectStatus"-->
<!--              :active-value="0"-->
<!--              :disabled="true"-->
<!--              :inactive-value="1"-->
<!--              active-text="运行"-->
<!--              inactive-text="暂停"-->
<!--              inline-prompt-->
<!--              style="&#45;&#45;el-switch-on-color: #13ce66; &#45;&#45;el-switch-off-color: #ff4949"-->
<!--              @click="update_project_status($event,scope.row)"-->
<!--          />-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="项目简介" prop="projectMessage" width="200">-->
<!--        <template #default="scope">-->
<!--          <el-text style="margin-left: 5px" type="info">-->
<!--            {{ scope.row.projectMessage == "" ? "暂无介绍..." : scope.row.projectMessage }}-->
<!--          </el-text>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="操作" width="460">-->
<!--        <template #default>-->
<!--          <el-button plain size="small" type="primary">通用编辑</el-button>-->
<!--          <el-button plain size="small" type="success">公告编辑</el-button>-->
<!--          <el-button plain size="small" type="info">接口列表</el-button>-->
<!--          <el-button plain size="small" type="warning">更新设置</el-button>-->
<!--          <el-button plain size="small" type="danger">删除应用</el-button>-->

<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->
<!--    -->
<!--    -->
    <el-pagination
        v-model:current-page="search_params.currentPage"

        v-model:page-size="search_params.pageSize"
        :background="background"
        :disabled="disabled"
        :page-sizes="[10, 20, 30, 40]"
        :size="size"
        :total="total"
        layout=" prev, pager, next"
        style="justify-content: flex-end;margin-right: 10px;margin-top: 10px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />

    <a-modal v-model:open="drawer" aria-hidden="false" title="创建程序" @ok="handleOk">
      <template #footer>
        <a-button key="back" @click="handleCancel">取消</a-button>
        <a-button key="submit" type="primary" :loading="loading" @click="handleOk">创建</a-button>
      </template>
      <el-form :label-position="'left'" :model="create_project_params" :rules="create_rules"  label-width="80px" style="margin: 30px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="create_project_params.projectName" placeholder="请输入项目名称"/>
        </el-form-item>
        <el-form-item label="项目简介" prop="projectMessage">
          <el-input v-model="create_project_params.projectMessage" placeholder="请输入项目简介"/>
        </el-form-item>
      </el-form>
    </a-modal>

    <a-drawer
        width="640"
        placement="right"
        :closable="false"
        :open="a_drawer"
        title="项目详情"
        @close="()=>a_drawer=false">
      <template #extra>
        <a-button style="margin-right: 8px" @click="a_drawer = false">取消</a-button>
        <a-button type="primary" @click="on_update_project()">更新数据</a-button>
      </template>
      <a-form layout="vertical" :model="show_project_info"  >
        <a-form-item  label="项目名称">
          <el-input   v-model="show_project_info.projectName" placeholder="请输入项目名称">
          </el-input>
        </a-form-item>
        <a-form-item label="程序密钥">
          <el-input  v-model="show_project_info.projectKey"  readonly placeholder="请输入项目key" >
            <template #append>
              <el-button @click="copy_text(show_project_info.projectKey)">复制</el-button>
            </template>
          </el-input>
        </a-form-item>
        <a-form-item label="自定义BASE64">
          <el-input readonly v-model="show_project_info.projectBase64"  placeholder="请输入项目名称">
            <template #append>
              <el-button @click="copy_text(show_project_info.projectBase64)">复制</el-button>
            </template>
          </el-input>
        </a-form-item>
        <a-form-item label="项目简绍">
          <el-input type="textarea"  v-model = 'show_project_info.projectMessage' show-word-limit maxlength="100" placeholder="请输入项目名称" />
        </a-form-item>
      </a-form>
    </a-drawer>
  </div>
</template>

<script lang="ts" setup>

import {Search} from "@element-plus/icons-vue";
import {createVNode, onMounted, ref, UnwrapRef} from "vue";
import {create_project_services, get_project_list_services, update_project_status_services} from "@/api/project.ts";
import {Action, ComponentSize, ElMessage, ElMessageBox} from "element-plus";

const drawer = ref<boolean>(false);
const a_drawer = ref<boolean>(false);

const total = ref(0)
const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)
const show_project_info = ref<Project>({
  projectId: 0,
  projectCreateTime: "",
  projectKey: "",
  projectName: "",
  projectMessage: "",
  tagType: "",
  projectStatus: 0,
  projectIcon: "",
  projectBase64: ""
});
import { SettingOutlined, EditOutlined, EllipsisOutlined } from '@ant-design/icons-vue';
import {message, Modal} from "ant-design-vue";


export interface Project {
  projectId: number
  projectCreateTime: string
  projectKey: string
  projectName: string
  projectMessage: string
  tagType: string
  projectStatus: number
  projectIcon: string
  projectBase64: string
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
const loading = ref<boolean>(false);

const handleOk =async () => {
  loading.value = true;
  await create_project_click()
  loading.value = false;
};

const handleCancel = () => {
  drawer.value = false;
};


const on_update_project = ()=> {

}
const on_click_open_show = (data:any)=> {
  // 这样就不会修改原本的table_data咯
  show_project_info.value = JSON.parse(JSON.stringify(data));
  a_drawer.value = true
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
  restaurants.value = list_result.data.data.names.map((item: string) => ({
    value: item,
  }))
}



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


import { ExclamationCircleOutlined } from '@ant-design/icons-vue';

const update_project_status = ( row: Project) => {
  if (row.projectStatus == 0)
  {
    Modal.confirm({
      title: `确定要禁用项目${row.projectName}吗？`,
      okText: '禁用',
      okType: 'danger',
      cancelText: '取消',
      icon: createVNode(ExclamationCircleOutlined),
      content: createVNode('div', { style: 'color:red;' }, '禁用后程序用户无法登录，卡密不可使用'),
      onOk : async () => {
        const update_result = await update_project_status_services(row.projectId);
        if (update_result.data.code === 200) {
          ElMessage.success(update_result.data.message)
          await get_project_list(search_params.value);
        } else {
          ElMessage.error(update_result.data.message)
        }
      },
      onCancel() {
        console.log('Cancel');
      },
      class: 'test',
    });
  }else {
    Modal.success({
      maskClosable: true,
      title: '提示',
      okText: '启用',
      cancelText: '取消',
      okCancel: true,
      content: `确定要启用项目${row.projectName}吗？`,
      onOk: async () => {
        const update_result = await update_project_status_services(row.projectId);
        if (update_result.data.code === 200) {
          ElMessage.success(update_result.data.message)
          await get_project_list(search_params.value);
        } else {
          ElMessage.error(update_result.data.message)
        }
      },
      onCancel() {
        console.log('Cancel');
      },
    })
  }

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
      message.success('复制成功');
    } else {
      message.error('复制失败，请手动复制');
    }
  } catch (err) {
    message.error('复制失败，请手动复制');
    console.error('无法复制文本: ', err);
  }

  document.body.removeChild(textArea);
}
// 复制项目key
const copy_text = (key: string) => {
  // 检查 key 的长度，如果超过20个字符，则截断并添加省略号
  const displayKey = key.length > 40 ? key.substring(0, 40) + '...' : key;

  Modal.success({
    title: '复制文本',
    content: `${displayKey}`,
    okText: '复制',
    cancelText: '取消',
    okCancel: true,
    onOk: () => {
      // 复制文本到剪贴板
      navigator.clipboard.writeText(key).then(() => {
        message.success('复制成功');
      }).catch(() => {
        message.error('复制失败，请手动复制');
      });
    },
    onCancel: () => {}
  })
};


interface RestaurantItem {
  value: string
}

const restaurants = ref<RestaurantItem[]>([])

const querySearch = (queryString: string, cb: any) => {
  const results = queryString
      ? restaurants.value.filter(createFilter(queryString))
      : restaurants.value
  // call callback function to return suggestions
  cb(results)
}
const createFilter = (queryString: string) => {
  return (restaurant: RestaurantItem) => {
    return (
        restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
    )
  }
}


onMounted(() => {
  // 初始化数据
  get_project_list(search_params.value);

})

</script>

<style lang="scss" scoped>
:where(.ant-card-meta-detail >div:not(:last-child)) {
  margin-bottom: 0 !important;
}
.box_card_list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(230px, 1fr));
  gap: 10px;
}

.box_card_list .ant-card {
  width: calc(33.333% - 10px); /* 每行三张卡片，减去间距 */
  margin: 5px; /* 卡片之间的间距 */
  transition: transform 0.3s ease-in-out; /* 可选，为hover添加动画效果 */
}

.box_card_list .ant-card:hover {
  transform: translateY(-5px); /* 可选，hover时卡片上移 */
}
</style>