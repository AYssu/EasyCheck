<template>
  <div>
    <div style="margin-bottom: 20px;margin-top: 10px">
      <el-autocomplete
          v-model="search_params.projectName"
          :fetch-suggestions="querySearch"
          clearable
          placeholder="请输入项目名称"
          style="width: 40%;margin-right: 10px"
          @select="get_project_list(search_params)"
          @keyup.enter="get_project_list(search_params)"
      >
        <template #prepend>
          <el-button :icon="Search" @click="get_project_list(search_params)"></el-button>
        </template>
      </el-autocomplete>
      <el-button plain type="primary" @click="drawer = true">添加项目</el-button>

    </div>
    <div class="box_card_list">

      <a-card v-for="(item,index) in tableData" :key="index" hoverable style="width: 240px">
        <template #cover>
          <img alt="example" src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"/>
        </template>
        <template #actions>
          <setting-outlined/>
          <edit-outlined @click="on_click_open_show(item)"/>
          <ellipsis-outlined/>
        </template>
        <a-card-meta>
          <template #avatar>
            <a-avatar :src="item.projectIcon"/>
          </template>
          <template #title>
            <div>
              <el-text>{{ item.projectName }}</el-text>
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
                <el-text size="small" type="info">创建时间: {{ item.projectCreateTime }}</el-text>
              </div>
            </div>
          </template>
        </a-card-meta>
      </a-card>
    </div>

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
        <a-button key="submit" :loading="loading" type="primary" @click="handleOk">创建</a-button>
      </template>
      <el-form :label-position="'left'" :model="create_project_params" :rules="create_rules" label-width="80px"
               style="margin: 30px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="create_project_params.projectName" placeholder="请输入项目名称"/>
        </el-form-item>
        <el-form-item label="项目简介" prop="projectMessage">
          <el-input v-model="create_project_params.projectMessage" placeholder="请输入项目简介"/>
        </el-form-item>
      </el-form>
    </a-modal>

    <a-drawer
        :closable="false"
        :open="a_drawer"
        placement="right"
        title="项目详情"
        width="640"
        @close="()=>a_drawer=false">
      <template #extra>
        <a-button style="margin-right: 8px" @click="a_drawer = false">取消</a-button>
        <a-button type="primary" @click="on_update_project()">更新数据</a-button>
      </template>
      <a-form :model="show_project_info" layout="vertical">
        <a-form-item label="项目名称">
          <el-input v-model="show_project_info.projectName" placeholder="请输入项目名称">
          </el-input>
        </a-form-item>
        <a-form-item>
          <template #label>
            <span>项目key</span>
            <el-icon size="15" style="margin-left: 4px" @click="click_reset_key(show_project_info.projectId)">
              <svg class="icon" data-spm-anchor-id="a313x.search_index.0.i8.1cfb3a81XkHwer" height="200" p-id="4887"
                   t="1731297185428" version="1.1"
                   viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                <path
                    d="M943.8 484.1c-17.5-13.7-42.8-10.7-56.6 6.8-5.7 7.3-8.5 15.8-8.6 24.4h-0.4c-0.6 78.3-26.1 157-78 223.3-124.9 159.2-356 187.1-515.2 62.3-31.7-24.9-58.2-54-79.3-85.9h77.1c22.4 0 40.7-18.3 40.7-40.7v-3c0-22.4-18.3-40.7-40.7-40.7H105.5c-22.4 0-40.7 18.3-40.7 40.7v177.3c0 22.4 18.3 40.7 40.7 40.7h3c22.4 0 40.7-18.3 40.7-40.7v-73.1c24.2 33.3 53 63.1 86 89 47.6 37.3 101 64.2 158.9 79.9 55.9 15.2 113.5 19.3 171.2 12.3 57.7-7 112.7-24.7 163.3-52.8 52.5-29 98-67.9 135.3-115.4 37.3-47.6 64.2-101 79.9-158.9 10.2-37.6 15.4-76 15.6-114.6h-0.1c-0.3-11.6-5.5-23.1-15.5-30.9zM918.7 135.2h-3c-22.4 0-40.7 18.3-40.7 40.7V249c-24.2-33.3-53-63.1-86-89-47.6-37.3-101-64.2-158.9-79.9-55.9-15.2-113.5-19.3-171.2-12.3-57.7 7-112.7 24.7-163.3 52.8-52.5 29-98 67.9-135.3 115.4-37.3 47.5-64.2 101-79.9 158.8-10.2 37.6-15.4 76-15.6 114.6h0.1c0.2 11.7 5.5 23.2 15.4 30.9 17.5 13.7 42.8 10.7 56.6-6.8 5.7-7.3 8.5-15.8 8.6-24.4h0.4c0.6-78.3 26.1-157 78-223.3 124.9-159.2 356-187.1 515.2-62.3 31.7 24.9 58.2 54 79.3 85.9h-77.1c-22.4 0-40.7 18.3-40.7 40.7v3c0 22.4 18.3 40.7 40.7 40.7h177.3c22.4 0 40.7-18.3 40.7-40.7V175.8c0.1-22.3-18.2-40.6-40.6-40.6z"
                    p-id="4888"></path>
              </svg>
            </el-icon>
          </template>
          <el-input v-model="show_project_info.projectKey" placeholder="请输入项目key" readonly>
            <template #append>
              <el-button @click="copy_text(show_project_info.projectKey)">复制</el-button>
            </template>
          </el-input>
        </a-form-item>
        <a-form-item label="自定义BASE64">
          <el-input v-model="show_project_info.projectBase64" placeholder="请输入项目名称" readonly>
            <template #append>
              <el-button @click="copy_text(show_project_info.projectBase64)">复制</el-button>
            </template>
          </el-input>
        </a-form-item>
        <a-form-item >
          <template #label>
            <span>项目绑定密钥</span>
            <el-icon size="15" style="margin-left: 4px" @click="help_show">
              <svg t="1731378682079" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1594" width="200" height="200"><path d="M878.08 731.274667a32 32 0 0 1-54.88-32.938667A360.789333 360.789333 0 0 0 874.666667 512c0-200.298667-162.368-362.666667-362.666667-362.666667S149.333333 311.701333 149.333333 512s162.368 362.666667 362.666667 362.666667a360.789333 360.789333 0 0 0 186.314667-51.445334 32 32 0 0 1 32.928 54.88A424.778667 424.778667 0 0 1 512 938.666667C276.362667 938.666667 85.333333 747.637333 85.333333 512S276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667c0 78.293333-21.152 153.568-60.586667 219.274667zM650.666667 437.333333c0 65.898667-46.72 120.853333-109.194667 135.082667V608a32 32 0 0 1-64 0v-64a32 32 0 0 1 32-32C552.266667 512 586.666667 478.4 586.666667 437.333333s-34.4-74.666667-77.194667-74.666666c-26.773333 0-51.082667 13.248-65.173333 34.624a73.088 73.088 0 0 0-8.522667 17.717333 32 32 0 0 1-60.885333-19.690667c3.797333-11.754667 9.173333-22.933333 15.978666-33.237333 25.856-39.253333 70.186667-63.413333 118.613334-63.413333C587.274667 298.666667 650.666667 360.576 650.666667 437.333333zM512 736a32 32 0 1 1 0-64 32 32 0 0 1 0 64z" fill="#000000" p-id="1595"></path></svg>
            </el-icon>
          </template>
          <el-input v-model="show_project_info.bindKey" placeholder="项目绑定密钥" readonly>
            <template #append>
              <el-button @click="copy_text(show_project_info.bindKey)">复制</el-button>
            </template>
          </el-input>
        </a-form-item>
        <a-form-item label="项目简绍">
          <el-input v-model='show_project_info.projectMessage' maxlength="100" placeholder="请输入项目名称" show-word-limit
                    type="textarea"/>
        </a-form-item>
      </a-form>
    </a-drawer>
  </div>
</template>

<script lang="ts" setup>

import {Search} from "@element-plus/icons-vue";
import {createVNode, onMounted, ref, UnwrapRef} from "vue";
import {
  create_project_services,
  get_project_list_services,
  update_project_normal_info_services, update_project_reset_key_services,
  update_project_status_services
} from "@/api/project.ts";
import {ComponentSize, ElMessage} from "element-plus";

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
  projectBase64: "",
  bindKey: ""
});
import {SettingOutlined, EditOutlined, EllipsisOutlined} from '@ant-design/icons-vue';
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
  bindKey: string
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

const handleOk = async () => {
  loading.value = true;
  await create_project_click()
  loading.value = false;
};

const handleCancel = () => {
  drawer.value = false;
};


const on_update_project = async () => {
  const params = {
    projectId: show_project_info.value.projectId,
    projectName: show_project_info.value.projectName,
    projectMessage: show_project_info.value.projectMessage
  }

  try {
    const result = await update_project_normal_info_services(params)
    if (result.data.code === 200) {
      message.success(result.data.message)
      await get_project_list(search_params.value)
      a_drawer.value = false
    } else {
      message.error(result.data.message)
    }
  } catch (e) {
    console.log(e)
  }

}
const on_click_open_show = (data: any) => {
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


import {ExclamationCircleOutlined} from '@ant-design/icons-vue';

const update_project_status = (row: Project) => {
  if (row.projectStatus == 0) {
    Modal.confirm({
      title: `确定要禁用项目${row.projectName}吗？`,
      okText: '禁用',
      okType: 'danger',
      cancelText: '取消',
      icon: createVNode(ExclamationCircleOutlined),
      content: createVNode('div', {style: 'color:red;'}, '禁用后程序用户无法登录，卡密不可使用'),
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
      class: 'test',
    });
  } else {
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
      if (navigator.clipboard) {
        navigator.clipboard.writeText(key).then(() => {
          message.success('复制成功');
        }).catch((error) => {
          message.error('复制失败，请手动复制');
          console.error('复制失败:', error);
        });
      } else {
        old_copy(key);
      }
    },
    onCancel: () => {
    }
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

// 重置密钥返回结构体
interface reset_data {
  success: boolean
  projectBase64: string
  projectKey: string
}

const click_reset_key = async (pid: number) => {
  Modal.warning({
    title: '重置项目key',
    content: '确定要重置项目key吗？',
    okText: '确定',
    okCancel: true,
    cancelText: '取消',
    onOk: async () => {
      const update_result = await update_project_reset_key_services(pid);
      try {
        const project_reset_data = update_result.data.data as reset_data;
        if (update_result.data.code === 200) {
          if (project_reset_data.success)
          {
            message.success(update_result.data.message)
            show_project_info.value.projectBase64 = project_reset_data.projectBase64
            show_project_info.value.projectKey = project_reset_data.projectKey
            await get_project_list(search_params.value);
          }else {
            message.error("重置错误")
          }
        } else {
          message.error(update_result.data.message)
        }
      }catch (e){
        console.log(e)
      }
    },
  })
}

const help_show = () => {
  const contentVNode = createVNode('div', {}, [
    '1.该密钥程序创建就已决定，无法改变',
    createVNode('br'),
    '2.绑定密钥即为当前程序生成一个用户',
    createVNode('br'),
    '3.绑定密钥后，使用用户登录不需要卡密',
    createVNode('br'),
    '4.绑定密钥后，用户无法解绑，请谨慎使用',
  ]);

  Modal.info({
    title: "绑定密钥帮助",
    content: contentVNode,
    okText: "确定",
    cancelText: "取消",
    okCancel: true,
    onOk: () => {
    }
  });
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