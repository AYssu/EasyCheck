<template>
	<el-breadcrumb class="pink" :separator-icon="ArrowRight">
		<el-breadcrumb-item v-for="(item, index) in tabs" :key="index">{{ item.meta.title }}</el-breadcrumb-item>
	</el-breadcrumb>
</template>

<script lang="ts" setup>
import { ArrowRight } from '@element-plus/icons-vue';
import { onMounted, ref, Ref, watch } from 'vue';
import { RouteLocationMatched } from 'vue-router';

const tabs: Ref<RouteLocationMatched[]> = ref([]);

import { useRoute } from 'vue-router';
const use_route = useRoute();
const get_breadcrumb = () => {
	let matched = use_route.matched.filter((item) => item.meta && item.meta.title);
	const first = matched[0];
	if (first && first.path !== '/home') {
		matched = [{ path: '/home', meta: { title: 'homepage' } } as any].concat(matched);
	}
	tabs.value = matched;
};

onMounted(() => {
	get_breadcrumb();
});

watch(
	() => use_route.path,
	() => {
		get_breadcrumb();
	}
);
</script>

<style scoped lang="scss">
.pink {
	margin-left: 10px;
}
</style>
