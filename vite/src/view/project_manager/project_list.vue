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
    <el-empty v-if="tableData.length === 0"></el-empty>
    <div class="box_card_list">
      <a-card v-for="(item,index) in tableData" :key="index" hoverable style="width: 240px">
        <template #cover>
          <el-image :alt="item.projectName" :src="project_background"/>
        </template>
        <template #actions>
          <setting-outlined/>
          <edit-outlined @click="on_click_open_show(item)"/>
          <el-dropdown trigger="click">
            <ellipsis-outlined/>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>
                  <el-icon style="color: #515151">
                    <svg class="icon" height="200" p-id="9275" t="1731552005213"
                         version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                      <path
                          d="M512 64c247.424 0 448 200.576 448 448S759.424 960 512 960 64 759.424 64 512 264.576 64 512 64z m0 72c-207.66 0-376 168.34-376 376s168.34 376 376 376 376-168.34 376-376-168.34-376-376-376z"
                          fill="#333333" p-id="9276"></path>
                      <path
                          d="M511.552 274.003l0.185-0.002 0.158-0.001h0.21a36.364 36.364 0 0 1 0.426 0.004L512 274c0.206 0 0.41 0.002 0.615 0.005l0.043 0.001 0.182 0.003a36.207 36.207 0 0 1 1.746 0.082l0.101 0.008c0.15 0.01 0.299 0.023 0.448 0.036l0.203 0.018c0.095 0.008 0.19 0.017 0.284 0.027l0.277 0.03c0.118 0.011 0.236 0.025 0.354 0.039l0.126 0.016c0.154 0.017 0.307 0.037 0.46 0.057l0.05 0.009a35.674 35.674 0 0 1 8.426 2.211l0.054 0.022 0.015 0.006 0.222 0.09 0.101 0.042 0.195 0.08c0.245 0.103 0.49 0.208 0.734 0.317l0.025 0.011 0.112 0.05a35.694 35.694 0 0 1 1.168 0.55l0.134 0.068c0.128 0.063 0.254 0.127 0.38 0.192l0.132 0.068a35.817 35.817 0 0 1 8.11 5.767l168.889 157.898 0.431 0.41c14.106 13.623 14.723 36.094 1.28 50.473-13.578 14.523-36.359 15.29-50.883 1.711L548 392.94V726c0 19.882-16.118 36-36 36-19.683 0-35.677-15.797-35.995-35.405L476 726V392.939L367.586 494.297c-14.524 13.579-37.305 12.812-50.883-1.711-13.443-14.379-12.826-36.85 1.28-50.473l0.431-0.41 168.89-157.898a35.844 35.844 0 0 1 8.11-5.767l0.117-0.06a33.602 33.602 0 0 1 1.35-0.66 35.758 35.758 0 0 1 10.791-3.06l0.024-0.002c0.15-0.02 0.301-0.036 0.453-0.052l0.164-0.017 0.345-0.034 0.23-0.02c0.132-0.012 0.265-0.022 0.398-0.032l0.112-0.008-0.112 0.008a36.32 36.32 0 0 1 1.452-0.08c0.042 0 0.083-0.002 0.125-0.003 0.122-0.004 0.244-0.008 0.366-0.01h0.015l0.128-0.003h0.033l0.083-0.002h0.064z"
                          fill="#333333" p-id="9277"></path>
                    </svg>
                  </el-icon>
                  <el-text size="small">更新管理</el-text>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-icon style="color: #515151">
                    <svg class="icon" height="200" p-id="14603" t="1731552056423"
                         version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                      <path
                          d="M774.447309 893.981872 246.411717 893.981872c-64.806461 0-117.349037-52.542376-117.349037-117.349237L129.06268 248.580654c0-64.789473 52.542776-117.331649 117.349037-117.331649l528.035592 0c64.806861 0 117.349237 52.542176 117.349237 117.331649l0 528.052181C891.796546 841.439696 839.25437 893.981872 774.447309 893.981872zM833.138516 248.580654c0-32.386042-26.288177-58.657031-58.691207-58.657031L246.411717 189.923624c-32.386042 0-58.674619 26.271188-58.674619 58.657031l0 528.052181c0 32.386042 26.288576 58.675018 58.674619 58.675018l528.035592 0c32.403031 0 58.691207-26.289176 58.691207-58.675018L833.138516 248.580654zM715.789279 717.958816l-410.703543 0c-16.218404 0-29.337109-13.135094-29.337109-29.337109 0-16.219004 13.118506-29.338109 29.337109-29.338109l410.703543 0c16.185027 0 29.338109 13.119105 29.338109 29.338109C745.127388 704.823722 731.974305 717.958816 715.789279 717.958816zM715.789279 365.94668c-16.235992 0-29.33691-13.153082-29.33691-29.355097 0-16.219004 13.101117-29.319521 29.33691-29.319521 16.185027 0 29.338109 13.100518 29.338109 29.319521C745.127388 352.793798 731.974305 365.94668 715.789279 365.94668zM587.796458 541.93556 305.085736 541.93556c-16.218404 0-29.337109-13.136094-29.337109-29.320121 0-16.219004 13.118506-29.337509 29.337109-29.337509l282.710522 0c16.202015 0 29.338109 13.118506 29.338109 29.337509C617.134367 528.799466 603.963497 541.93556 587.796458 541.93556zM510.446102 365.94668c-16.235992 0-29.33651-13.153082-29.33651-29.355097 0-16.219004 13.100518-29.319521 29.33651-29.319521 16.203014 0 29.320121 13.100518 29.320121 29.319521C539.766023 352.793798 526.649116 365.94668 510.446102 365.94668zM305.085736 365.94668c-16.218404 0-29.337109-13.153082-29.337109-29.355097 0-16.219004 13.118506-29.319521 29.337109-29.319521 16.236991 0 29.337509 13.100518 29.337509 29.319521C334.423445 352.793798 321.322727 365.94668 305.085736 365.94668z"
                          fill="#262535" p-id="14604"></path>
                    </svg>
                  </el-icon>
                  <el-text size="small">公告管理</el-text>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-icon style="color: #515151">
                    <svg class="icon" height="200" p-id="19136" t="1731552116654"
                         version="1.1" viewBox="0 0 1246 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                      <path
                          d="M303.339977 664.942675l106.011591-276.040855h39.350086l112.971638 276.040855h-41.612716l-32.193289-83.618933h-115.431018l-30.311864 83.618933h-38.784428z m79.647033-113.352842h93.579424l-28.811641-76.44984q-13.182279-34.775639-19.576668-57.242078a351.974224 351.974224 0 0 1-14.879251 52.716819zM590.853841 664.942675V388.90182h104.130165q27.483576 0 41.99392 2.631537 20.339076 3.393945 34.074715 12.89945t22.134424 26.647387a84.578091 84.578091 0 0 1 8.37419 37.653114q0 35.20603-22.404955 59.590786t-80.962803 24.384757H627.387936v112.221527h-36.534095z m36.534095-144.796021h71.322031q35.402781 0 50.269735-13.182278t14.916142-37.087457q0-17.314038-8.755394-29.660127a40.936387 40.936387 0 0 0-23.068988-16.281098q-9.222676-2.45938-34.087012-2.459381H627.387936v98.670341zM854.278074 664.942675V388.90182h36.534095v276.040855h-36.534095z"
                          fill="#231815" p-id="19137"></path>
                      <path
                          d="M914.938691 915.270707H623.108614a32.857322 32.857322 0 0 1 0-65.702347h291.830077c146.443805 0 265.613082-119.144683 265.613081-265.613081 0-133.728808-99.912328-247.020166-232.411446-263.522609l-23.364114-2.914365-4.734307-23.081285c-27.213044-132.560603-145.361678-228.722376-280.922724-228.722376-158.138159 0-286.788347 128.650188-286.788347 286.788347 0 3.209491 0.073781 6.62803 0.221344 10.772086l1.340362 37.739192-37.616223-3.910415a230.751365 230.751365 0 0 0-23.745317-1.22969c-125.071789 0-226.828653 101.756863-226.828653 226.828653s101.756863 226.828653 226.828653 226.828652h79.032189a32.857322 32.857322 0 0 1 0 65.702347h-79.032189C131.232537 915.270707 0.049188 784.03817 0.049188 622.739707c0-159.540006 128.379656-289.653525 287.255629-292.481812A352.515287 352.515287 0 0 1 863.205624 80.434035a353.941728 353.941728 0 0 1 115.775332 178.464938c153.858837 30.12741 267.223976 166.008176 267.223976 325.080899a331.290835 331.290835 0 0 1-331.266241 331.290835z"
                          fill="#231815" p-id="19138"></path>
                      <path
                          d="M698.033638 1023.999914a32.734353 32.734353 0 0 1-22.195908-8.607831L532.505041 883.913607l135.684016-141.315997a32.857322 32.857322 0 0 1 47.39226 45.498537l-89.115648 92.829313 93.776174 86.016829a32.857322 32.857322 0 0 1-22.208205 57.057625z"
                          fill="#231815" p-id="19139"></path>
                    </svg>
                  </el-icon>
                  <el-text size="small">接口管理</el-text>
                </el-dropdown-item>
                <el-dropdown-item @click="handle_edit_variable(item.projectId,item.projectName)">
                  <el-icon style="color: #515151">
                    <svg class="icon" height="200" p-id="23610" t="1731552163584"
                         version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                      <path
                          d="M127.78104098 789.54900543v-144.98828641c0-32.10454914-25.89076543-59.03094518-59.03094517-59.03094519h-19.67698173V522.35630617h19.67698173c32.10454914 0 59.03094518-25.89076543 59.03094517-59.03094519v-144.98828641c0-65.24472889 52.81716148-118.06189037 118.06189038-118.06189038H284.16126419v78.70792692h-39.35396344c-21.74824297 0-39.35396347 17.6057205-39.35396347 39.35396346v160.52274568c0 34.17581037-21.74824297 64.20909827-53.8527921 74.56540444 32.10454914 10.35630617 53.8527921 40.38959408 53.8527921 74.56540445v160.52274568c0 21.74824297 17.6057205 39.35396347 39.35396347 39.35396345H284.16126419v78.70792691h-39.35396344c-64.20909827 1.03563061-117.02625975-51.78153086-117.02625977-117.02625975zM896.21895902 644.56071902v144.98828641c0 65.24472889-52.81716148 118.06189037-118.06189038 118.06189038H739.83873581v-78.70792692h39.35396344c21.74824297 0 39.35396347-17.6057205 39.35396347-39.35396346V629.02625975c0-34.17581037 21.74824297-64.20909827 53.8527921-74.56540444-32.10454914-10.35630617-53.8527921-40.38959408-53.8527921-74.56540445V319.37270518c0-21.74824297-17.6057205-39.35396347-39.35396347-39.35396345H739.83873581V201.31081482h39.35396344c65.24472889 0 118.06189037 52.81716148 118.06189038 118.06189036v144.98828643c0 32.10454914 25.89076543 59.03094518 59.03094519 59.03094519h19.67698171v63.17346765h-19.67698171c-34.17581037-1.03563061-60.06657581 25.89076543-60.0665758 57.99531457z"
                          p-id="23611"></path>
                      <path
                          d="M562.77696916 270.4184459l75.84958641 24.59622717-176.36789412 543.76821191-75.8392301-24.59622717z"
                          p-id="23612"></path>
                    </svg>
                  </el-icon>
                  <el-text size="small">变量管理</el-text>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-icon style="color: #515151">
                    <svg class="icon" height="200" p-id="5610" t="1731551752877"
                         version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                      <path
                          d="M606.1 386.9c17.4 0 31.6 14.1 31.6 31.6v315.9c0 17.4-14.1 31.6-31.6 31.6s-31.6-14.1-31.6-31.6v-316c0-17.4 14.1-31.5 31.6-31.5z m-189.6 0c17.4 0 31.6 14.1 31.6 31.6v315.9c0 17.4-14.1 31.6-31.6 31.6-17.4 0-31.6-14.1-31.6-31.6v-316c0-17.4 14.2-31.5 31.6-31.5z m-126.4-63.2V766c0 34.9 28.3 63.2 63.2 63.2h315.9c34.9 0 63.2-28.3 63.2-63.2V323.7H290.1z m158-126.4c-34.9 0-63.2 28.3-63.2 63.2h252.7c0-34.9-28.3-63.2-63.2-63.2H448.1zM226.9 323.7h-63.2c-17.4 0-31.6-14.1-31.6-31.6 0-17.4 14.1-31.6 31.6-31.6h158v-31.6c0-52.3 42.4-94.8 94.8-94.8h189.6c52.3 0 94.8 42.4 94.8 94.8v31.6h158c17.4 0 31.6 14.1 31.6 31.6 0 17.4-14.1 31.6-31.6 31.6h-63.2V766c0 69.8-56.6 126.4-126.4 126.4h-316c-69.8 0-126.4-56.6-126.4-126.4V323.7z"
                          p-id="5611"></path>
                    </svg>
                  </el-icon>
                  <el-text size="small">刪除程序</el-text>
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
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
        <a-form-item>
          <template #label>
            <span>项目绑定密钥</span>
            <el-icon size="15" style="margin-left: 4px" @click="help_show">
              <svg class="icon" height="200" p-id="1594" t="1731378682079"
                   version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                <path
                    d="M878.08 731.274667a32 32 0 0 1-54.88-32.938667A360.789333 360.789333 0 0 0 874.666667 512c0-200.298667-162.368-362.666667-362.666667-362.666667S149.333333 311.701333 149.333333 512s162.368 362.666667 362.666667 362.666667a360.789333 360.789333 0 0 0 186.314667-51.445334 32 32 0 0 1 32.928 54.88A424.778667 424.778667 0 0 1 512 938.666667C276.362667 938.666667 85.333333 747.637333 85.333333 512S276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667c0 78.293333-21.152 153.568-60.586667 219.274667zM650.666667 437.333333c0 65.898667-46.72 120.853333-109.194667 135.082667V608a32 32 0 0 1-64 0v-64a32 32 0 0 1 32-32C552.266667 512 586.666667 478.4 586.666667 437.333333s-34.4-74.666667-77.194667-74.666666c-26.773333 0-51.082667 13.248-65.173333 34.624a73.088 73.088 0 0 0-8.522667 17.717333 32 32 0 0 1-60.885333-19.690667c3.797333-11.754667 9.173333-22.933333 15.978666-33.237333 25.856-39.253333 70.186667-63.413333 118.613334-63.413333C587.274667 298.666667 650.666667 360.576 650.666667 437.333333zM512 736a32 32 0 1 1 0-64 32 32 0 0 1 0 64z"
                    fill="#000000" p-id="1595"></path>
              </svg>
            </el-icon>
          </template>
          <el-input v-model="show_project_info.bindKey" placeholder="项目绑定密钥" readonly>
            <template #append>
              <el-button @click="copy_text(show_project_info.bindKey)">复制</el-button>
            </template>
          </el-input>
        </a-form-item>
        <a-form-item label="项目简绍">
          <el-input v-model='show_project_info.projectMessage' maxlength="100" placeholder="请输入项目名称"
                    show-word-limit
                    type="textarea"/>
        </a-form-item>
      </a-form>
    </a-drawer>

    <a-modal v-model:open="json_show" :closable="true" :title="choose_project_variable.title" width="50%">
      <Codemirror
          ref="cmRef"
          v-model:value="code"
          :border="true"
          :height="'500px'"
          :options="cmOptions"
          @ready="onReady"
      >
      </Codemirror>

      <template #footer>
        <el-row justify="end">
          <el-button type="warning" :loading="format_loading"  @click="format_json">
            <el-icon>
              <svg class="icon" height="200" p-id="1585" t="1731564695662" version="1.1"
                   viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                <path
                    d="M607.456 302.88c13.024 8.672 18.24 21.44 13.696 33.44l-156.288 410.56c-4.576 12-18.208 21.44-35.808 24.768-17.6 3.328-36.416 0-49.44-8.64-13.024-8.704-18.24-21.44-13.696-33.472l156.288-410.56c4.576-12.032 18.24-21.44 35.808-24.8 17.6-3.296 36.448 0 49.44 8.704z m103.52-33.312a45.92 45.92 0 0 1 65.824 0.704l201.504 209.248c8.736 8.96 13.664 21.248 13.696 34.08 0 12.864-4.864 25.152-13.504 34.112l-201.6 210.88c-18.144 18.752-47.2 19.168-65.792 0.896a48.96 48.96 0 0 1-14.496-34.304 49.056 49.056 0 0 1 13.504-34.688l168.896-176.704-168.704-175.168a48.768 48.768 0 0 1-13.344-28.224l-0.32-6.464a48.96 48.96 0 0 1 14.336-34.368z m-463.808 0.704a45.888 45.888 0 0 1 65.856-0.704c9.024 8.928 14.24 21.312 14.368 34.368 0.128 13.056-4.832 25.6-13.664 34.624L144.96 513.792l168.896 176.704c7.36 7.616 11.936 17.6 13.184 28.256l0.32 6.464a48.96 48.96 0 0 1-14.464 34.272 45.92 45.92 0 0 1-65.856-0.96l-201.44-210.784A49.056 49.056 0 0 1 32 513.6c0-12.832 4.96-25.12 13.632-34.048z"
                    fill="#FFF" p-id="1586"></path>
              </svg>
            </el-icon>
            <el-text style="color: white">格式化</el-text>
          </el-button>
          <el-button type="primary" :loading="update_variable_loading" @click="update_variable">更新变量</el-button>
        </el-row>
      </template>
    </a-modal>


  </div>
</template>

<script lang="ts" setup>
import {Search} from "@element-plus/icons-vue";
import {createVNode, onMounted, reactive, ref, UnwrapRef} from "vue";
import {
  create_project_services,
  get_project_list_services, get_project_variable_services, set_project_variable_services,
  update_project_normal_info_services, update_project_reset_key_services,
  update_project_status_services
} from "@/api/project.ts";
import {ComponentSize, ElMessage} from "element-plus";

import project_background from '@/assets/svg/project_background.svg'

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
import {Editor, EditorConfiguration} from "codemirror";
import Codemirror, {CmComponentRef} from "codemirror-editor-vue3";

import jsonlint from "jsonlint-mod-fixed";
// language json or js
import "codemirror/mode/javascript/javascript.js";
import "codemirror/addon/lint/lint.css";
import "codemirror/addon/lint/lint.js";
import "codemirror/addon/lint/json-lint";

const cmRef = ref<CmComponentRef>();
(window as any).jsonlint = jsonlint;

const json_show = ref<boolean>(false)

const code = ref();

interface Project_Variable {
  pid: number
  title: string
}
const choose_project_variable = ref<Project_Variable>({
  pid: 0,
  title: ''
})
const cmOptions: EditorConfiguration = reactive({
  mode: "application/json",
  lineNumbers: true,
  lineWiseCopyCut: true,
  gutters: ["CodeMirror-lint-markers"],
  lint: true,
});

const cm_instance = ref<Editor | null>(null);
const onReady = (cm: Editor) => {
  cm_instance.value = cm;
};

defineExpose({
  setTheme: (theme: string) => {
    cm_instance.value?.setOption("theme", theme);
  },
});


const handle_edit_variable = async (pid: number,title:string) => {
  const result = await get_project_variable_services(pid)
  choose_project_variable.value.pid = pid;
  choose_project_variable.value.title = title
  try {
    if (result.data.code === 200) {
      const result_data = JSON.stringify(result.data.data, null, 2);
      if (result_data == "null") {
        const example_data = {
          "字符串示例": "我是字符串",
          "int": 123,
          "float": 123.456,
          "bool": true,
          "null": null,
          "注意事项": "只支持基本数据类型，该示例为例子,仅供参考!实际变量数据为空!"
        }
        code.value = JSON.stringify(example_data, null, 2)
      } else
        code.value = result_data
      json_show.value = true
    } else {
      message.error(result.data.message)
    }
  } catch (e) {
    console.log(e)
  }
}

const format_loading = ref(false)


const format_json = async () => {
  try {
    format_loading.value = true
    const result =  JSON.parse(code.value)
    setTimeout(()=>{
      code.value = JSON.stringify(result, null, 2)
      format_loading.value = false
    }, 1000)
  } catch (e) {
    message.error("JSON 格式错误");
    format_loading.value = false
  }
}
const update_variable_loading = ref(false)

const update_variable = async () => {
  update_variable_loading.value = true
  try {
    const params = {
      pid: choose_project_variable.value.pid,
      json: JSON.parse(code.value)
    }
    const result = await set_project_variable_services(params)
    if (result.data.code === 200) {
      message.success("更新成功")
      update_variable_loading.value = false
    } else {
      message.error(result.data.message)
      update_variable_loading.value = false
    }
  } catch (e) {
    console.log(e)
    message.error("格式化异常,或格式不合法")
  }
}

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
          if (project_reset_data.success) {
            message.success(update_result.data.message)
            show_project_info.value.projectBase64 = project_reset_data.projectBase64
            show_project_info.value.projectKey = project_reset_data.projectKey
            await get_project_list(search_params.value);
          } else {
            message.error("重置错误")
          }
        } else {
          message.error(update_result.data.message)
        }
      } catch (e) {
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