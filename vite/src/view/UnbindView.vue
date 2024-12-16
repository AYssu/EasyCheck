<template>
	<div class="unbind-vue" :style="{ backgroundImage: 'url(' + (phone_bool ? phone_background : background) + ')' }">
		<el-container style="height: 100vh">
			<!--      顶部 logo-->
			<el-header>
				<div style="max-width: 150px"><logo_view /></div>
			</el-header>
			<!--      主体-->
			<el-main
				style="
					display: flex; /* 启用Flexbox布局 */
					flex-direction: column;
					justify-content: center; /* 水平居中 */
					align-items: center; /* 垂直居中 */
				"
			>
				<!--        卡片-->
				<el-card class="unbind-card">
					<p>解绑卡密</p>
					<el-input v-model="key" class="el-input" size="large" placeholder="请输入需要解绑的卡密" :prefix-icon="Search" />
					<el-button type="success" class="unbind-button">立即解绑</el-button>
				</el-card>
			</el-main>
			<!--      底部-->
			<el-footer></el-footer>
		</el-container>
	</div>
</template>
<style scoped lang="less">
.unbind-vue {
	width: 100%;
	height: 100vh;
	background-repeat: no-repeat; /* 设置背景图片不重复 */
	background-position: bottom center; /* 设置背景图片在底部居中显示 */

	.unbind-card {
		width: 35%;
		text-align: center;
		color: #808080; /* 字体颜色设置为灰色 */
		padding: 15px 15px 20px 15px;

		.el-input {
			margin: 30px 0;
		}

		.unbind-button {
			background-color: #2dce89; /* 按钮背景颜色 */
			color: white; /* 按钮文字颜色 */
			width: 100%; /* 按钮宽度增加 */
			border-radius: 5px; /* 改变按钮圆角 */
			letter-spacing: 1px;
		}
		.unbind-button:hover {
			background-color: rgba(45, 206, 137, 0.8); /* 鼠标悬停时背景颜色变淡 */
		}
	}
}
/* 媒体查询，针对手机端适配 */
@media only screen and (max-width: 1200px) {
	.unbind-vue {
		background-size: cover;
	}

	.unbind-card {
		min-width: 300px;
	}

	.unbind-card .el-input,
	.unbind-card .unbind-button {
		width: 100%; /* 手机端输入框和按钮宽度100% */
	}
}
</style>
<script lang="ts" setup>
import phone_size from '@/utils/phone_size.ts';
import { onUnmounted, ref } from 'vue';
import { Search } from '@element-plus/icons-vue';
import background from '@/assets/svg/unbind_background.svg';
import phone_background from '@/assets/svg/unbind_phone_backgroud.svg';
import Logo_view from '@/components/logo_view.vue';

// 引入手机端适配
const { phone_bool, remove_phone_size } = phone_size();
// 存储卡密变量
const key = ref('');

onUnmounted(() => {
	remove_phone_size();
});
</script>
