<template>
  <a-config-provider
      :theme="{
        token: {
          colorPrimary: '#17926c',
        },
      }"
  >
  </a-config-provider>
  <el-container class="layout-container-demo">

    <el-container>
      <el-aside v-if="!phone_bool" style="max-width: 250px" width="200px">
        <div>
          <logo_view v-if="show_aside&&!phone_bool"/>
          <div style="width: 100%;display: flex;justify-content: center;margin-top: 18px;margin-bottom: 10px">
            <img v-if="!show_aside||phone_bool" :src="icon_translate" alt="icon" style="width: 25px;height: 25px">
          </div>
          <el-menu :collapse="!show_aside||phone_bool"
                   :default-active="active_menu"
                   :default-openeds="['1', '2']"
                   class="el-menu-vertical-demo"
                   router
                   @close="handleClose"
                   @open="handleOpen">

            <el-menu-item v-if="!show_aside_error" index="1"  route="/home/table" >
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1546" t="1730291296708"
                       version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M647 457.415a45 45 0 0 1 45 45v405a45 45 0 0 1-45 45h-45a45 45 0 0 1-45-45v-405a45 45 0 0 1 45-45h45z m-225 180a45 45 0 0 1 45 45v225a45 45 0 0 1-45 45H377a45 45 0 0 1-45-45v-225a45 45 0 0 1 45-45h45z m-225-90a45 45 0 0 1 45 45v315a45 45 0 0 1-45 45H152a45 45 0 0 1-45-45v-315a45 45 0 0 1 45-45h45z m675-225a45 45 0 0 1 45 45v540a45 45 0 0 1-45 45h-45a45 45 0 0 1-45-45v-540a45 45 0 0 1 45-45h45zM939.5 62l-90 208.35L797.075 206l-233.37 189.135-228.6-24.615L115.28 457.55a22.5 22.5 0 0 1-27.36-9l-1.8-3.645a22.5 22.5 0 0 1 9-27.36l3.6-1.845 230.175-91.08 221.4 23.85 218.385-177.21L709.55 98.81 939.5 62z"
                        fill="#515151" p-id="1547"></path>
                  </svg>
                </el-icon>
                <span>数据统计</span>
              </template>
            </el-menu-item>
            <el-sub-menu v-if="show_aside_error" index="1" >
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1546" t="1730291296708"
                       version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M647 457.415a45 45 0 0 1 45 45v405a45 45 0 0 1-45 45h-45a45 45 0 0 1-45-45v-405a45 45 0 0 1 45-45h45z m-225 180a45 45 0 0 1 45 45v225a45 45 0 0 1-45 45H377a45 45 0 0 1-45-45v-225a45 45 0 0 1 45-45h45z m-225-90a45 45 0 0 1 45 45v315a45 45 0 0 1-45 45H152a45 45 0 0 1-45-45v-315a45 45 0 0 1 45-45h45z m675-225a45 45 0 0 1 45 45v540a45 45 0 0 1-45 45h-45a45 45 0 0 1-45-45v-540a45 45 0 0 1 45-45h45zM939.5 62l-90 208.35L797.075 206l-233.37 189.135-228.6-24.615L115.28 457.55a22.5 22.5 0 0 1-27.36-9l-1.8-3.645a22.5 22.5 0 0 1 9-27.36l3.6-1.845 230.175-91.08 221.4 23.85 218.385-177.21L709.55 98.81 939.5 62z"
                        fill="#515151" p-id="1547"></path>
                  </svg>
                </el-icon>
                <span>数据统计</span>
              </template>
              <el-menu-item index="/home/table">项目数据</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1385" t="1730291279546"
                       version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M512 56.88888853c251.35786667 0 455.11111147 203.7532448 455.11111147 455.11111147s-203.7532448 455.11111147-455.11111147 455.11111147S56.88888853 763.35786667 56.88888853 512 260.64213333 56.88888853 512 56.88888853z m97.53031147 227.55555627c-34.816-0.09102187-68.26666667 13.5395552-92.91093334 37.9107552a128.75093333 128.75093333 0 0 0-38.38862293 91.88693333v196.67626667c0 36.0220448-29.9235552 65.60426667-66.30968853 65.60426667-36.4316448 0-67.62951147-29.58222187-67.62951147-66.90133334 0-37.2736 29.9235552-65.536 66.3552-65.536 8.64711147 0.06826667 16.95288853-3.2995552 23.07413333-9.32977813 6.09848853-6.05297813 9.48906667-14.29048853 9.4208-22.82382187a31.67573333 31.67573333 0 0 0-9.4435552-22.80106666 32.40391147 32.40391147 0 0 0-23.09688853-9.32977814c-68.6307552 2.36657813-123.7219552 56.86613333-126.06577813 124.74595627A130.20728853 130.20728853 0 0 0 410.6012448 739.5555552a131.7091552 131.7091552 0 0 0 93.0019552-37.9335104 128.75093333 128.75093333 0 0 0 38.34311147-91.9552V413.01333333c0-36.0220448 29.9235552-65.5587552 66.30968853-65.5587552 36.40888853 0 66.28693333 29.58222187 66.28693333 65.5587552 0 36.0220448-29.9235552 65.58151147-64.98986666 65.58151147h-1.29706667a32.40391147 32.40391147 0 0 0-23.07413333 9.32977707 31.67573333 31.67573333 0 0 0-9.4435552 22.77831146c-0.09102187 8.55608853 3.32231147 16.7936 9.4208 22.84657814 6.144 6.05297813 14.44977813 9.3980448 23.09688853 9.32977706a131.7091552 131.7091552 0 0 0 92.9564448-37.9335104A128.75093333 128.75093333 0 0 0 739.5555552 413.01333333a126.5891552 126.5891552 0 0 0-37.7059552-91.27253333 129.4563552 129.4563552 0 0 0-85.85671147-37.18257813L609.53031147 284.4444448z"
                        fill="#515151" p-id="1386"></path>
                  </svg>
                </el-icon>
                <span>项目管理</span>
              </template>
              <el-menu-item-group>
                <template #title>项目</template>
                <el-menu-item index="/home/project_list">项目列表</el-menu-item>
                <el-menu-item index="/home/user_list">用户列表</el-menu-item>

              </el-menu-item-group>

              <el-menu-item-group>
                <template #title>项目</template>

              </el-menu-item-group>
              <el-sub-menu index="2-4">
                <template #title>项目管理</template>
                <el-menu-item index="/home/manage/card_list">卡密列表</el-menu-item>
                <el-menu-item index="/home/manage/user_authentic">激活权限</el-menu-item>
                <el-menu-item index="/home/manage/user_authority">用户认证</el-menu-item>
              </el-sub-menu>
            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1224" t="1730291254624"
                       version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M875.459375 247.371875L560.984375 66.5375c-30.20625001-17.371875-67.74375001-17.371875-97.959375 0L148.53124999 247.371875C118.2875 264.771875 99.5 297.190625 99.5 331.990625v361.6875c0 34.809375 18.7875 67.2375 49.040625 84.61875l314.484375 180.83437501c15.103125 8.690625 32.034375 13.03125001 48.975 13.03124999 16.93124999 0 33.871875-4.340625 48.984375-13.03124999l314.475-180.83437501c30.253125-17.38125001 49.040625-49.809375 49.040625-84.61875v-361.6875c0-34.809375-18.7875-67.2375-49.040625-84.61875zM323.1875 512.984375c0-105.43125001 85.4625-190.89375001 190.89375001-190.89375s190.89375001 85.4625 190.89374999 190.89375-85.4625 190.89375001-190.89375001 190.89375S323.1875 618.415625 323.1875 512.984375z"
                        fill="#515151" p-id="1225"></path>
                  </svg>
                </el-icon>
                <span>项目设置</span>
              </template>
              <el-menu-item-group>
                <template #title>系统设置</template>
                <el-menu-item index="3-1">全局设置</el-menu-item>
                <el-menu-item index="3-2">用户设置</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="系统代理">
                <el-menu-item index="3-3">代理分配</el-menu-item>
                <el-menu-item index="3-4">代理统计</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
            <el-menu-item v-if="!show_aside_error" index="4">
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1063" version="1.1" viewBox="0 0 1024 1024"
                       width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M505.173 68.267c244.313 0 442.368 198.055 442.368 442.368S749.486 953.003 505.173 953.003 62.805 754.948 62.805 510.635 260.86 68.267 505.173 68.267z m8.192 404.138c-22.621 0-40.96 18.339-40.96 40.96v172.032c0 22.622 18.339 40.96 40.96 40.96 22.622 0 40.96-18.338 40.96-40.96V513.365c0-22.621-18.338-40.96-40.96-40.96z m0-141.994c-22.621 0-40.96 18.338-40.96 40.96 0 22.621 18.339 40.96 40.96 40.96 22.622 0 40.96-18.339 40.96-40.96 0-22.622-18.338-40.96-40.96-40.96z"
                        fill="#515151" p-id="1064"></path>
                  </svg>
                </el-icon>
                <span>关于我们</span>
              </template>
            </el-menu-item>
            <el-sub-menu v-if="show_aside_error" index="4">
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1063" version="1.1" viewBox="0 0 1024 1024"
                       width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M505.173 68.267c244.313 0 442.368 198.055 442.368 442.368S749.486 953.003 505.173 953.003 62.805 754.948 62.805 510.635 260.86 68.267 505.173 68.267z m8.192 404.138c-22.621 0-40.96 18.339-40.96 40.96v172.032c0 22.622 18.339 40.96 40.96 40.96 22.622 0 40.96-18.338 40.96-40.96V513.365c0-22.621-18.338-40.96-40.96-40.96z m0-141.994c-22.621 0-40.96 18.338-40.96 40.96 0 22.621 18.339 40.96 40.96 40.96 22.622 0 40.96-18.339 40.96-40.96 0-22.622-18.338-40.96-40.96-40.96z"
                        fill="#515151" p-id="1064"></path>
                  </svg>
                </el-icon>
                <span>关于我们</span>
              </template>
              <el-menu-item >关于我们</el-menu-item>

            </el-sub-menu>

          </el-menu>
        </div>
      </el-aside>
      <el-container>
        <el-header  :style="phone_bool?'font-size: 14px;position: fixed;width: 100%;background-color: white;z-index: 999':'font-size: 12px;'">
          <top_bar/>
        </el-header>
        <div v-if="phone_bool" style="height: 40px;"></div>
        <tab_view v-if="!phone_bool"/>
        <el-main>

          <router-view  v-slot="{ Component }">
            <transition mode="out-in" name="scale">
              <keep-alive :include="keepAliveList"  >
              <component :is="Component"  />
              </keep-alive>
            </transition>
          </router-view>

        </el-main>
      </el-container>
    </el-container>

    <div>
      <el-drawer :lock-scroll="false" v-if="phone_bool"  size="250px" :with-header="false"  :direction="'ltr'" :title="null" :show-close="false" v-model="show_aside_error" @close="aside_data.toggleStatus()">
        <div>
          <logo_view/>
          <el-menu :collapse="false"
                   :default-active="active_menu"
                   :default-openeds="['1', '2']"
                   class="el-menu-vertical-demo"
                   router
                   @close="handleClose"
                   @open="handleOpen">

            <el-menu-item index="1"  route="/home/table" >
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1546" t="1730291296708"
                       version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M647 457.415a45 45 0 0 1 45 45v405a45 45 0 0 1-45 45h-45a45 45 0 0 1-45-45v-405a45 45 0 0 1 45-45h45z m-225 180a45 45 0 0 1 45 45v225a45 45 0 0 1-45 45H377a45 45 0 0 1-45-45v-225a45 45 0 0 1 45-45h45z m-225-90a45 45 0 0 1 45 45v315a45 45 0 0 1-45 45H152a45 45 0 0 1-45-45v-315a45 45 0 0 1 45-45h45z m675-225a45 45 0 0 1 45 45v540a45 45 0 0 1-45 45h-45a45 45 0 0 1-45-45v-540a45 45 0 0 1 45-45h45zM939.5 62l-90 208.35L797.075 206l-233.37 189.135-228.6-24.615L115.28 457.55a22.5 22.5 0 0 1-27.36-9l-1.8-3.645a22.5 22.5 0 0 1 9-27.36l3.6-1.845 230.175-91.08 221.4 23.85 218.385-177.21L709.55 98.81 939.5 62z"
                        fill="#515151" p-id="1547"></path>
                  </svg>
                </el-icon>
                <span>数据统计</span>
              </template>
            </el-menu-item>
            <el-sub-menu index="2">
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1385" t="1730291279546"
                       version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M512 56.88888853c251.35786667 0 455.11111147 203.7532448 455.11111147 455.11111147s-203.7532448 455.11111147-455.11111147 455.11111147S56.88888853 763.35786667 56.88888853 512 260.64213333 56.88888853 512 56.88888853z m97.53031147 227.55555627c-34.816-0.09102187-68.26666667 13.5395552-92.91093334 37.9107552a128.75093333 128.75093333 0 0 0-38.38862293 91.88693333v196.67626667c0 36.0220448-29.9235552 65.60426667-66.30968853 65.60426667-36.4316448 0-67.62951147-29.58222187-67.62951147-66.90133334 0-37.2736 29.9235552-65.536 66.3552-65.536 8.64711147 0.06826667 16.95288853-3.2995552 23.07413333-9.32977813 6.09848853-6.05297813 9.48906667-14.29048853 9.4208-22.82382187a31.67573333 31.67573333 0 0 0-9.4435552-22.80106666 32.40391147 32.40391147 0 0 0-23.09688853-9.32977814c-68.6307552 2.36657813-123.7219552 56.86613333-126.06577813 124.74595627A130.20728853 130.20728853 0 0 0 410.6012448 739.5555552a131.7091552 131.7091552 0 0 0 93.0019552-37.9335104 128.75093333 128.75093333 0 0 0 38.34311147-91.9552V413.01333333c0-36.0220448 29.9235552-65.5587552 66.30968853-65.5587552 36.40888853 0 66.28693333 29.58222187 66.28693333 65.5587552 0 36.0220448-29.9235552 65.58151147-64.98986666 65.58151147h-1.29706667a32.40391147 32.40391147 0 0 0-23.07413333 9.32977707 31.67573333 31.67573333 0 0 0-9.4435552 22.77831146c-0.09102187 8.55608853 3.32231147 16.7936 9.4208 22.84657814 6.144 6.05297813 14.44977813 9.3980448 23.09688853 9.32977706a131.7091552 131.7091552 0 0 0 92.9564448-37.9335104A128.75093333 128.75093333 0 0 0 739.5555552 413.01333333a126.5891552 126.5891552 0 0 0-37.7059552-91.27253333 129.4563552 129.4563552 0 0 0-85.85671147-37.18257813L609.53031147 284.4444448z"
                        fill="#515151" p-id="1386"></path>
                  </svg>
                </el-icon>
                <span>项目管理</span>
              </template>
              <el-menu-item-group>
                <template #title>项目</template>
                <el-menu-item index="/home/project_list">项目列表</el-menu-item>
                <el-menu-item index="/home/user_list">用户列表</el-menu-item>

              </el-menu-item-group>

              <el-menu-item-group>
                <template #title>项目</template>

              </el-menu-item-group>
              <el-sub-menu index="2-4">
                <template #title>项目管理</template>
                <el-menu-item index="/home/manage/card_list">卡密列表</el-menu-item>
                <el-menu-item index="/home/manage/user_authentic">激活权限</el-menu-item>
                <el-menu-item index="/home/manage/user_authority">用户认证</el-menu-item>
              </el-sub-menu>
            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1224" t="1730291254624"
                       version="1.1" viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M875.459375 247.371875L560.984375 66.5375c-30.20625001-17.371875-67.74375001-17.371875-97.959375 0L148.53124999 247.371875C118.2875 264.771875 99.5 297.190625 99.5 331.990625v361.6875c0 34.809375 18.7875 67.2375 49.040625 84.61875l314.484375 180.83437501c15.103125 8.690625 32.034375 13.03125001 48.975 13.03124999 16.93124999 0 33.871875-4.340625 48.984375-13.03124999l314.475-180.83437501c30.253125-17.38125001 49.040625-49.809375 49.040625-84.61875v-361.6875c0-34.809375-18.7875-67.2375-49.040625-84.61875zM323.1875 512.984375c0-105.43125001 85.4625-190.89375001 190.89375001-190.89375s190.89375001 85.4625 190.89374999 190.89375-85.4625 190.89375001-190.89375001 190.89375S323.1875 618.415625 323.1875 512.984375z"
                        fill="#515151" p-id="1225"></path>
                  </svg>
                </el-icon>
                <span>项目设置</span>
              </template>
              <el-menu-item-group>
                <template #title>系统设置</template>
                <el-menu-item index="3-1">全局设置</el-menu-item>
                <el-menu-item index="3-2">用户设置</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="系统代理">
                <el-menu-item index="3-3">代理分配</el-menu-item>
                <el-menu-item index="3-4">代理统计</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
            <el-menu-item index="4">
              <template #title>
                <el-icon>
                  <svg class="icon" height="200" p-id="1063" version="1.1" viewBox="0 0 1024 1024"
                       width="200" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M505.173 68.267c244.313 0 442.368 198.055 442.368 442.368S749.486 953.003 505.173 953.003 62.805 754.948 62.805 510.635 260.86 68.267 505.173 68.267z m8.192 404.138c-22.621 0-40.96 18.339-40.96 40.96v172.032c0 22.622 18.339 40.96 40.96 40.96 22.622 0 40.96-18.338 40.96-40.96V513.365c0-22.621-18.338-40.96-40.96-40.96z m0-141.994c-22.621 0-40.96 18.338-40.96 40.96 0 22.621 18.339 40.96 40.96 40.96 22.622 0 40.96-18.339 40.96-40.96 0-22.622-18.338-40.96-40.96-40.96z"
                        fill="#515151" p-id="1064"></path>
                  </svg>
                </el-icon>
                <span>关于我们</span>
              </template>
            </el-menu-item>

          </el-menu>
        </div>
      </el-drawer>
    </div>
  </el-container>
</template>

<style>

</style>
<style lang="scss" scoped>

.el-menu {
  .is-active
  {
    color: #42b983 !important;
  }
  border-right: none;
}

:deep( .el-drawer__body ){
  padding: 0 !important;
}
// 淡出淡入
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

// 滑动
.slide-enter-active,
.slide-leave-active {
  transition: all 0.75s ease-out;
}

.slide-enter-to {
  position: absolute;
  right: 0;
}

.slide-enter {
  position: absolute;
  right: -100%;
}

.slide-leave-to {
  position: absolute;
  left: -100%;
}

.slide-leave {
  position: absolute;
  left: 0;
}

// 设置滑动过渡必须给每个组件设定宽度
.wrapper {
  width: 100%;
  // min-height: 100vh;
  // background: chartreuse;
}

// 缩放
.scale-enter-active,
.scale-leave-active {
  transition: all 0.5s ease;
}

.scale-enter-from,
.scale-leave-to {
  opacity: 0;
  transform: scale(0.9);
}


@media only screen and (max-width: 1200px) {
  .el-header {
    height: 40px;
    padding: 0 5px 0 5px;
  }
}

</style>
<script lang="ts" setup>
import logo_view from "@/components/logo_view.vue"
import top_bar from '@/components/top_bar.vue'

import {useRouter} from "vue-router";
import {computed, onUnmounted, ref, watch} from "vue";
import {aside_status} from "@/stores/aside.ts";
import icon_translate from '@/assets/icon.png'
import tab_view from "@/components/tab_view.vue";
import {tabs_status} from "@/stores/tabs/tabs.ts";
import phone_size from "@/utils/phone_size.ts";
const {phone_bool,remove_phone_size} = phone_size();

onUnmounted(() => {
  remove_phone_size()
})
const router = useRouter()

const aside_data = aside_status()

// 获取当前路由
const active_menu = computed(() => {
  return router.currentRoute.value.path
})

// 缓存路由 判断哪些属于该缓存的页面
const tabs_data =tabs_status()
const keepAliveList = computed(() => {
  return tabs_data.tab_list
      .map(item => {
        const matchedRoute = router.getRoutes().find(r => r.path === item.path);
        if (matchedRoute && matchedRoute.name) {
          return matchedRoute.name as string;
        }
        return null;
      })
      .filter(name => name !== null); // 过滤掉 null 值
});


// 监听侧边栏状态
const show_aside = ref(true);
const show_aside_error = ref(false);
watch(() => aside_data.status, (new_value: boolean) => {
  if (!new_value) {
    setTimeout(() => {
      show_aside_error.value = true
      show_aside.value = false
    }, 200)

  } else {
    show_aside_error.value = false
    show_aside.value = true
  }
})

watch(() => router.currentRoute.value.path, () => {
  show_aside_error.value = false
})

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}


const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}


</script>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  color: var(--el-text-color-primary);
}

.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
}

.layout-container-demo .el-menu {
  border-right: none;
}

.layout-container-demo .el-main {
  padding: 0;
}

.logo_view_style {
  visibility: hidden;
  height: 100px;
  width: 0;
}

.logo_view_style_visible {
  visibility: visible;
  width: 100%;
}
</style>
