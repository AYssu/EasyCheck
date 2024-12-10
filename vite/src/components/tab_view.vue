<template>
	<el-tabs
		v-loading.fullscreen.lock="fullscreen_loading"
		v-model="active_tab"
		class="tab-view"
		closable
		type="card"
		@tab-remove="tab_remove"
		@tab-click="tab_click"
	>
		<el-tab-pane v-for="(item, index) in tab_list" :key="index" :label="item.title" :name="item.path"></el-tab-pane>
	</el-tabs>
</template>

<style lang="scss" scoped>
:deep(.el-tabs__header) {
	box-sizing: border-box;
	margin: 0 5px 0 5px !important; // 修正了Opx为0px
}

:deep(.el-tabs__item) {
	height: 26px !important;
	line-height: 26px !important;
	text-align: center !important;
	margin: 0 3px !important;
	color: #495060;
	border: none !important;
	font-size: 13px !important;
	padding: 0 10px !important; // 修正了0xp为0px
}

:deep(.el-tabs__nav) {
	border: none !important;
}

:deep(.is-active) {
	border: 1px solid #42b983 !important;
	border-radius: 10px;
	background-color: #42b983 !important;
	color: #fff !important;
}

:deep(.el-tabs__item:hover) {
	color: #495060 !important;
}

:deep(.is-active:hover) {
	color: #fff !important;
}

:deep(.el-tabs__nav-next) {
	line-height: 26px !important;
}

@media only screen and (max-width: 1200px) {
	:deep(.el-tabs__item) {
		height: 16px !important;
		line-height: 16px !important;
		margin: 0 2px !important;
		font-size: 11px !important;
		padding: 8px 6px !important; // 修正了0xp为0px
	}
}
</style>
<script lang="ts" setup>
import { Tab, tabs_status } from '@/stores/tabs/tabs.ts';
import { computed, onMounted, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import { ElMessage, TabsPaneContext } from 'element-plus';
import router from '@/router';
import { message } from 'ant-design-vue';

// 获取tabs状态 路由的store
const tabs_status_data = tabs_status();
const active_tab = ref('');
const fullscreen_loading = ref(false);
const route = useRoute();

/**
 * tab列表
 * @return tab列表
 */
const tab_list = computed(() => {
	return tabs_status_data.get_tab_list();
});

/**
 * 添加tab
 */
const add_tab = () => {
	const tab: Tab = {
		title: route.meta.title as string,
		path: route.path,
	};
	tabs_status_data.set_tab_list(tab);
};

/**
 * 删除tab
 * @param tab_name
 */
const tab_remove = (tab_name: string) => {
	console.log('delete tab:', tab_name);
	const tabs = tab_list.value;

	// 不能删除首页 初始进入首页会载入table
	if (tab_name === '/home/table') {
		ElMessage.warning('不能删除首页');
		return;
	}
	let active_tab_name = active_tab.value;
	if (tab_name === active_tab_name) {
		tabs.forEach((item, index) => {
			if (item.path === tab_name) {
				const next_tab = tabs[index + 1] || tabs[index - 1];
				if (next_tab) {
					active_tab_name = next_tab.path;
				}
			}
		});
	}
	console.log('active_tab_name:', active_tab_name);
	active_tab.value = active_tab_name;
	router.push({ path: active_tab_name });

	// 更新对应路由的keepAlive属性为false
	const routes = router.getRoutes();
	const route_to_deactivate = routes.find((r) => r.path === tab_name);
	if (route_to_deactivate) {
		console.log('routeToDeactivate:', route_to_deactivate);
		route_to_deactivate.meta.keepAlive = false;
	}

	tabs_status_data.tab_list = tabs.filter((item) => item.path !== tab_name);
};

/**
 * tab点击事件
 * @param tab
 */
const tab_click = async (tab: TabsPaneContext) => {
	if (tab.props.name === active_tab.value) return;
	fullscreen_loading.value = true;
	const old_route = route.name;
	await router.push({ path: tab.props.name as string });
	if (old_route === route.name) {
		message.error('切换失败,刷新浏览器');
		window.location.reload();
	}
	fullscreen_loading.value = false;
};

// 初始化路由路径和激活路由样式
onMounted(() => {
	active_tab.value = route.path;
	document.title = route.meta.title as string;
	add_tab();
});

watch(
	() => route.path,
	() => {
		add_tab();
		document.title = route.meta.title as string;
		active_tab.value = route.path;
	}
);
</script>
