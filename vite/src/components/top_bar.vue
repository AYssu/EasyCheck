<script setup lang="ts">
// 引入图片
import default_icon from '@/assets/default.jpg';
// 引入数据
import { user_token } from '@/stores/token.ts';
import { Expand, Fold, Sunny } from '@element-plus/icons-vue';
const user_data = user_token() as any;
console.log(user_data);
import { theme_status } from '@/stores/theme.ts';
const dark_theme = computed(() => {
	return theme_status().dark;
});

import { useDark, useToggle } from '@vueuse/core';

const isDark = useDark();
const toggle = useToggle(isDark);
theme_status().setStatus(isDark.value);

const theme_dark = computed(() => {
	return dark_theme.value ? theme.darkAlgorithm : theme.defaultAlgorithm;
});

watch(
	() => dark_theme.value,
	(new_v) => {
		if (isDark.value != new_v) {
			toggle();
		}
	}
);

import { aside_status } from '@/stores/aside.ts';
import breadcrumb from '@/components/breadcrumb.vue';
import { computed, ref, watch } from 'vue';
import phone_size from '@/utils/phone_size.ts';
import { onUnmounted } from 'vue';
import { theme } from 'ant-design-vue';

const { phone_bool, remove_phone_size } = phone_size();

onUnmounted(() => {
	remove_phone_size();
});
const aside_data = aside_status();

const change_aside_status = () => {
	aside_data.toggleStatus();
};
// 这边需要注意的就是这个username 原本都是小驼峰的 但是后端的login vo 重写了spring security 的user方法 然后自己定义的userName 就没了

const UNKNOWN_USERNAME = '未知用户名';
const UNKNOWN_EMAIL = '未知邮箱';

const top_user_info = computed(() => {
	const username = user_data?.token?.username || UNKNOWN_USERNAME;
	const userEmail = user_data?.token?.userEmail || UNKNOWN_EMAIL;
	return !phone_bool.value ? `${username} | ${userEmail} |` : `${username}`;
});
</script>

<template>
	<a-config-provider
		:theme="{
			algorithm: theme_dark,
			token: {
				colorPrimary: '#17926c',
			},
		}"
	>
	</a-config-provider>
	<div class="toolbar">
		<div class="bread_menu">
			<el-icon size="20" style="cursor: pointer" class="el-icon-s-fold">
				<component v-if="phone_bool" @click="change_aside_status" :is="aside_data.status ? Expand : Fold" />
				<component v-else @click="change_aside_status" :is="aside_data.status ? Fold : Expand" />
			</el-icon>
			<breadcrumb v-if="!phone_bool" />
		</div>
		<div class="user_info">
			<el-badge :show-zero="true" :value="12" style="margin: 0 15px; text-align: center; display: flex; align-items: center; justify-content: center">
				<el-icon size="20">
					<svg
						t="1733671814743"
						class="icon"
						viewBox="0 0 1024 1024"
						version="1.1"
						xmlns="http://www.w3.org/2000/svg"
						p-id="10926"
						width="200"
						height="200"
					>
						<path
							d="M824.5 826.7H221.7c-17.3 0-33.4-8.6-43.1-22.9-9.7-14.4-11.6-32.5-5.1-48.6l49.2-121.5V449.1c0-38.9 7.7-76.7 22.9-112.4 14.6-34.4 35.5-65.3 62.1-91.9 26.6-26.6 57.5-47.5 91.9-62.1 35.7-15.2 73.5-22.9 112.4-22.9h0.7c38.9 0 76.7 7.7 112.4 22.9 34.4 14.6 65.3 35.5 91.9 62.1 26.6 26.6 47.5 57.5 62.1 91.9 15.2 35.7 22.9 73.5 22.9 112.4v182.3l67.5 117.4c9.4 16.3 9.3 35.7-0.1 52-9.3 16.2-26.2 25.9-44.9 25.9z m-585-64h564.2L738 648.5V449.1c0-59.9-23.5-116.4-66.2-159.1-42.7-42.7-99.2-66.2-159.1-66.2h-0.7c-59.9 0-116.4 23.5-159.1 66.2-42.7 42.7-66.2 99.2-66.2 159.1v197.1l-47.2 116.5z"
							p-id="10927"
						></path>
						<path
							d="M432.8 232.8c-13.5 0-25.4-9.7-27.8-23.4-1.1-6.2-1.6-12.5-1.6-18.7 0-59.5 48.4-108 108-108s108 48.4 108 108c0 6.2-0.6 12.5-1.6 18.7-2.7 15.2-17.1 25.5-32.4 23l-56.8-9.2c-11.4-1.9-23-1.9-34.5 0l-56.8 9.2c-1.5 0.3-3 0.4-4.5 0.4z m78.5-67.5c8.8 0 17.6 0.7 26.3 2.1l21.2 3.4c-7.8-18.5-26.1-31.6-47.5-31.6s-39.7 13.1-47.5 31.6l21.2-3.4c8.8-1.4 17.6-2.1 26.3-2.1zM658 946H376.7c-17.6 0-32-14.4-32-32s14.4-32 32-32H658c17.6 0 32 14.4 32 32s-14.4 32-32 32z"
							p-id="10928"
						></path>
					</svg>
				</el-icon>
			</el-badge>
			<el-switch
				:size="phone_bool ? 'small' : ''"
				border-color="grey"
				:inactive-action-icon="Sunny"
				v-model="dark_theme"
				@click="theme_status().toggleStatus()"
			>
				<template #active-action>
					<el-icon>
						<svg
							t="1733667116144"
							class="icon"
							viewBox="0 0 1024 1024"
							version="1.1"
							xmlns="http://www.w3.org/2000/svg"
							p-id="4443"
							width="200"
							height="200"
						>
							<path
								d="M450.29 957.85c-155.33 0-302.37-70.8-393.39-189.39-9.23-12.11-13.89-27.06-13.15-42.04 1.65-35.51 33.25-63.22 68.63-61.61 4.09 0.21 7.76 0.3 11.15 0.3 229.61 0 416.44-174.4 416.44-388.82 0-41.55-7.01-82.44-20.83-121.49-10.07-28.5-12.55-47.99 2.97-69.43 1.83-2.5 4.14-4.59 6.79-6.17 3.79-2.2 37.56-21.26 76.51-8.93 197.28 62.52 329.86 236.03 329.86 431.77 0 251.35-217.54 455.81-484.98 455.81z"
								fill="#fff"
								p-id="4444"
							></path>
						</svg>
					</el-icon>
				</template>
				<template #inactive-action>
					<el-icon>
						<svg
							t="1733749930495"
							class="icon"
							viewBox="0 0 1024 1024"
							version="1.1"
							xmlns="http://www.w3.org/2000/svg"
							p-id="34063"
							width="200"
							height="200"
						>
							<path
								d="M379.14 861.49c0 17.53 14.87 31.77 33.21 31.77h199.29c18.35 0 33.22-14.24 33.22-31.77s-14.86-31.78-33.22-31.78H412.35c-18.35 0-33.21 14.22-33.21 31.78z m66.42 95.31c0 17.53 14.88 31.77 33.22 31.77h66.44c18.34 0 33.21-14.24 33.21-31.77S563.56 925 545.22 925h-66.44c-18.34 0-33.22 14.24-33.22 31.8zM213.06 512c0 157.94 133.83 286 298.94 286s298.94-128 298.94-286S677.1 226.05 512 226.05 213.06 354.07 213.06 512zM478.78 67.2v63.54c0 17.55 14.87 31.76 33.22 31.76s33.22-14.21 33.22-31.76V67.2c0-17.55-14.89-31.77-33.22-31.77s-33.22 14.22-33.22 31.77zM977 432.56h-66.42c-18.34 0-33.22 14.23-33.22 31.77s14.88 31.77 33.22 31.77H977c18.34 0 33.22-14.22 33.22-31.77s-14.87-31.77-33.22-31.77z m-863.6 0H47c-18.35 0-33.22 14.23-33.22 31.77S28.64 496.1 47 496.1h66.42c18.35 0 33.22-14.22 33.22-31.77s-14.88-31.77-33.23-31.77z m714.14-251.44l-47 44.93a30.82 30.82 0 0 0 0 44.93 34.28 34.28 0 0 0 47 0l47-44.93a30.8 30.8 0 0 0 0-44.93 34.3 34.3 0 0 0-47 0z m-631.09 0a34.27 34.27 0 0 0-47 0 30.77 30.77 0 0 0 0 44.93l47 44.93a34.28 34.28 0 0 0 47 0 30.8 30.8 0 0 0 0-44.93z"
								fill="#231815"
								p-id="34064"
							></path>
						</svg>
					</el-icon>
				</template>
			</el-switch>
			<el-image
				:src="default_icon"
				style="width: 20px; height: 20px; border: 1px solid rgb(128, 128, 128); border-radius: 100%; margin: 0 7px 0 7px"
			/>
			<span>{{ top_user_info }}</span>
			<el-tag v-if="user_data.token.level === 1" size="small" round effect="light" type="info" style="margin-left: 5px; margin-right: 8px"
				><span>小萌新</span></el-tag
			>
			<el-tag v-else-if="user_data.token.level === 2" size="small" round effect="light" type="warning" style="margin-left: 5px; margin-right: 8px"
				><span>进阶用户</span></el-tag
			>
			<el-tag v-else-if="user_data.token.level === 3" size="small" round effect="light" type="primary" style="margin-left: 5px; margin-right: 8px"
				><span>核心用户</span></el-tag
			>
			<el-tag v-else-if="user_data.token.level === 4" size="small" round effect="light" type="success" style="margin-left: 5px; margin-right: 8px"
				><span>资深用户</span></el-tag
			>
			<el-tag v-else-if="user_data.token.level === 5" size="small" round effect="light" type="danger" style="margin-left: 5px; margin-right: 8px">
				<el-icon>
					<svg class="icon" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="200" height="200">
						<path
							d="M976.394 400.901L811.794 86.08a43.357 43.357 0 0 0-38.415-23.265h-532.67a43.357 43.357 0 0 0-38.416 23.265L37.693 400.9a43.35 43.35 0 0 0 5.845 48.704l430.935 490.412a43.35 43.35 0 0 0 65.127 0l430.935-490.412a43.35 43.35 0 0 0 5.86-48.704zM792.2 421.471L539.217 701.685a43.336 43.336 0 0 1-64.346 0L221.873 421.471a43.35 43.35 0 1 1 64.36-58.097l220.81 244.574 220.811-244.574a43.35 43.35 0 1 1 64.346 58.097z"
						></path>
					</svg>
				</el-icon>
				<span style="font-size: 0.65rem">超级管理员</span>
			</el-tag>
		</div>
	</div>
</template>

<style scoped lang="scss">
:deep(.is-checked .el-switch__core) {
	background-color: black !important;
	border-color: gray !important;
}
:deep(.el-switch__core .el-switch__action) {
	background-color: transparent;
}
:deep(.el-badge__content) {
	font-size: 9px;
	font-weight: bold;
	padding: 0 6px;
}
.user_info {
	display: flex;
	justify-content: center;
	align-items: center;
}
.toolbar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	height: 60px;
	width: 100%;
}

.bread_menu {
	display: flex;
	align-items: center;
	margin-left: 20px;
}

@media only screen and (max-width: 1200px) {
	.toolbar {
		height: 40px;
	}
	.bread_menu {
		margin-left: 5px;
	}
	span {
		font-size: 10px;
	}
}
</style>
