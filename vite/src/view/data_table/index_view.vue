<template>
	<div class="echarts-box">
		<div class="top_menu_bar">
			<el-card class="top_menu_card">
				<div class="left_info">
					<img :src="default_icon" alt="icon" class="top_icon" />
					<div class="top_menu_text">
						<div class="top_menu_title">
							<div>
								<span style="font-weight: bold; padding-bottom: 0">{{ say_hello }}</span>
							</div>
							<el-text size="small" type="info"
								>寄语:
								{{ text }}
							</el-text>
						</div>
					</div>
				</div>
			</el-card>
			<div v-if="!phone_bool" class="top_menu_right">
				<el-card class="top_menu_card">
					<div class="top_menu_list">
						<img :src="project_icon" alt="" class="center_icon" />
						<el-text size="small" style="margin-top: 10px" type="info">项目编辑</el-text>
					</div>
				</el-card>
				<el-card class="top_menu_card">
					<div class="top_menu_list">
						<img :src="edit_icon" alt="" class="center_icon" />
						<el-text size="small" style="margin-top: 10px" type="info">项目编辑</el-text>
					</div>
				</el-card>
				<el-card class="top_menu_card">
					<div class="top_menu_list">
						<img :src="tips_icon" alt="" class="center_icon" />
						<el-text size="small" style="margin-top: 10px" type="info">项目编辑</el-text>
					</div>
				</el-card>
				<el-card class="top_menu_card">
					<div class="top_menu_list">
						<img :src="data_icon" alt="" class="center_icon" />
						<el-text size="small" style="margin-top: 10px" type="info">项目编辑</el-text>
					</div>
				</el-card>
			</div>
		</div>

		<div class="bottom_menu_bar">
			<el-card style="min-height: calc(100vh - 300px); margin: 0 10px 10px 10px; box-shadow: none">
				<div class="center_static">
					<el-col>
						<el-statistic :value="268500">
							<template #title>
								注册用户
								<el-icon :size="12" style="margin-left: 4px">
									<svg
										class="icon"
										height="200"
										p-id="1636"
										t="1730361885932"
										version="1.1"
										viewBox="0 0 1024 1024"
										width="200"
										xmlns="http://www.w3.org/2000/svg"
									>
										<path
											d="M926.26863 957.936375l-55.567573 0C859.981924 816.071344 768.64366 697.151907 642.9264 647.223774c-39.952944 19.377326-84.538395 30.569226-131.806955 30.569226-47.273676 0-91.879594-11.192923-131.830491-30.569226-125.716236 49.928133-217.054501 168.847569-227.756237 310.713624l-55.584969 0c9.907651-151.502529 99.44899-280.826943 226.671533-346.280678C251.660158 555.227524 205.943954 467.874013 205.943954 369.606942c0-170.193217 136.641053-308.165592 305.176514-308.165592 168.520111 0 305.160141 137.973398 305.160141 308.165592 0 98.267071-45.716204 185.620581-116.67328 242.049778C826.829873 677.110455 916.358933 806.434869 926.26863 957.936375zM760.786715 369.606942c0-139.254577-111.795179-252.137531-249.666246-252.137531-137.89665 0-249.693876 112.882953-249.693876 252.137531 0 139.248438 111.797225 252.144694 249.693876 252.144694C648.991536 621.751636 760.786715 508.85538 760.786715 369.606942z"
											fill="#272636"
											p-id="1637"
										></path>
									</svg>
								</el-icon>
							</template>
						</el-statistic>
					</el-col>
					<el-col>
						<el-statistic :value="138">
							<template #title>
								<div style="display: inline-flex; align-items: center">
									今日活跃
									<el-icon :size="12" style="margin-left: 4px">
										<svg
											class="icon"
											height="200"
											p-id="3463"
											t="1730362025621"
											version="1.1"
											viewBox="0 0 1024 1024"
											width="200"
											xmlns="http://www.w3.org/2000/svg"
										>
											<path
												d="M384.9216 877.1584c6.8096 24.9856 42.1888 25.1904 49.3056 0.256L613.8368 248.832l129.4848 474.7776a25.6 25.6 0 0 0 47.5648 4.7104l95.5392-190.6688h86.528a25.6 25.6 0 1 0 0-51.2h-102.2976a25.6 25.6 0 0 0-22.8864 14.1312l-72.704 144.9984-135.9872-498.688c-6.8096-24.9856-42.1888-25.1904-49.3056-0.256L410.1632 775.168 280.6784 300.4416a25.6 25.6 0 0 0-47.616-4.7104L137.6768 486.4H51.2a25.6 25.6 0 0 0 0 51.2h102.2464a25.6 25.6 0 0 0 22.8864-14.1312l72.6016-144.9472 135.9872 498.6368z"
												fill="#666666"
												p-id="3464"
											></path>
										</svg>
									</el-icon>
								</div>
							</template>
						</el-statistic>
					</el-col>
					<el-col>
						<el-statistic :value="outputValue">
							<template #title>
								剩余制卡
								<el-icon style="vertical-align: -0.125em">
									<svg
										t="1733582079617"
										class="icon"
										viewBox="0 0 1024 1024"
										version="1.1"
										xmlns="http://www.w3.org/2000/svg"
										p-id="2458"
										width="200"
										height="200"
									>
										<path
											d="M731.648993 0c-161.313604 0-292.566047 131.252443-292.566048 292.566047 0 60.067467 18.248807 115.929296 49.407092 162.447298L5.59149 937.911892a18.267093 18.267093 0 1 0 25.855524 25.855524L109.946142 885.268289l133.355262 133.355261a18.230522 18.230522 0 0 0 25.855524 0 18.267093 18.267093 0 0 0 0-25.855524L135.801667 859.412764 201.373032 793.841399l96.784505 96.784506a18.230522 18.230522 0 0 0 25.855525 0 18.267093 18.267093 0 0 0 0-25.855525L227.228556 767.985875 292.799922 702.414509l133.355261 133.355262a18.230522 18.230522 0 0 0 25.855525 0 18.267093 18.267093 0 0 0 0-25.855525L318.655446 676.558985l192.398747-192.398747C564.740063 545.873388 643.623183 585.132095 731.648993 585.132095c161.313604 0 292.566047-131.252443 292.566047-292.566048S892.962597 0 731.648993 0z m0 548.561339c-141.163118 0-255.995292-114.832174-255.995292-255.995292S590.485875 36.570756 731.648993 36.570756s255.995292 114.832174 255.995291 255.995291-114.832174 255.995292-255.995291 255.995292z"
											fill=""
											p-id="2459"
										></path>
									</svg>
								</el-icon>
							</template>
						</el-statistic>
					</el-col>
					<el-col>
						<el-statistic :value="100">
							<template #title>
								<div style="display: inline-flex; align-items: center">
									剩余项目
									<el-icon :size="12" style="margin-left: 4px"> </el-icon>
								</div>
							</template>
							<template #suffix>
								<el-icon style="vertical-align: -0.125em">
									<svg
										class="icon"
										height="200"
										p-id="14571"
										t="1730362349496"
										version="1.1"
										viewBox="0 0 1024 1024"
										width="200"
										xmlns="http://www.w3.org/2000/svg"
									>
										<path
											d="M751.4 957.2c-113.5 0-205.8-92.3-205.8-205.8V545.7h205.8c113.5 0 205.8 92.3 205.8 205.8 0 113.4-92.3 205.7-205.8 205.7zM617 751.4c0 74.1 60.3 134.4 134.4 134.4 74.1 0 134.4-60.3 134.4-134.4 0-74.1-60.3-134.4-134.4-134.4H617v134.4zM272.6 957.2c-113.5 0-205.8-92.3-205.8-205.8 0-113.5 92.3-205.8 205.7-205.8h205.8v205.8c0.1 113.5-92.2 205.8-205.7 205.8z m0-340.2c-74.1 0-134.4 60.3-134.4 134.4 0 74.1 60.3 134.4 134.4 134.4 74.1 0 134.4-60.3 134.4-134.4V617H272.6z m273-138.7V272.6c0-113.5 92.3-205.8 205.8-205.8 113.5 0 205.8 92.3 205.8 205.8 0 55-21.4 106.6-60.3 145.5-38.9 38.9-90.5 60.3-145.5 60.3H545.6z m205.8-340.1c-74.1 0-134.4 60.3-134.4 134.4V407h134.4c74.1 0 134.4-60.3 134.4-134.4 0-74.1-60.3-134.4-134.4-134.4zM272.6 478.3c-54.9 0-106.6-21.4-145.5-60.3-38.9-38.9-60.3-90.5-60.3-145.5 0-113.5 92.3-205.8 205.8-205.8 113.5 0 205.8 92.3 205.8 205.8v205.8H272.6z m0-340.1c-74.1 0-134.4 60.3-134.4 134.4 0 74.1 60.3 134.4 134.4 134.4H407V272.6c0-74.1-60.3-134.4-134.4-134.4z"
											fill="#333333"
											p-id="14572"
										></path>
									</svg>
								</el-icon>
							</template>
						</el-statistic>
					</el-col>
				</div>

				<v-chart :option="option" autoresize class="chart"></v-chart>
			</el-card>
			<el-card style="min-height: calc(100vh - 300px); margin: 0px 10px 10px 10px; box-shadow: none">
				<div class="demo-collapse">
					<el-text size="default" style="font-weight: bold" type="info"> 系统公告 </el-text>
					<el-collapse v-model="activeName" accordion style="margin-top: 10px">
						<el-collapse-item name="1" title="2024年12月2日 这是一条普通的系统公告">
							<div>Operation feedback: enable the users to clearly perceive their operations by style updates and interactive effects;</div>
							<div>Visual feedback: reflect current state by updating or rearranging elements of the page.</div>
						</el-collapse-item>
						<el-collapse-item name="2" title="2024年12月2日 元宵节活动即将开始">
							<div>Operation feedback: enable the users to clearly perceive their operations by style updates and interactive effects;</div>
							<div>Visual feedback: reflect current state by updating or rearranging elements of the page.</div>
						</el-collapse-item>
						<el-collapse-item name="3" title="2024年12月2日 了解易验的工作流?">
							<el-text type="info"> </el-text>
						</el-collapse-item>
						<el-collapse-item name="4" title="2024年12月2日 给新伙伴的一封信">
							<div>Decision making: giving advices about operations is acceptable, but do not make decisions for the users;</div>
							<div>
								Controlled consequences: users should be granted the freedom to operate, including canceling, aborting or terminating current
								operation.
							</div>
						</el-collapse-item>
					</el-collapse>
				</div>
			</el-card>
		</div>
	</div>
</template>
<script lang="ts" setup>
import { computed, onMounted, ref } from 'vue';
import * as echarts from 'echarts/core';
import { TitleComponent, TooltipComponent, GridComponent, VisualMapComponent, LegendComponent } from 'echarts/components';
import { LineChart } from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';
import { useTransition } from '@vueuse/core';

import { user_token } from '@/stores/token.ts';

const user_data = user_token() as any;

import default_icon from '@/assets/default.jpg';
import project_icon from '@/assets/svg/project.svg';
import data_icon from '@/assets/svg/data.svg';
import tips_icon from '@/assets/svg/tips.svg';
import edit_icon from '@/assets/svg/edit.svg';
import phone_size from '@/utils/phone_size.ts';
import { onUnmounted } from 'vue';
const { phone_bool, remove_phone_size } = phone_size();

onUnmounted(() => {
	remove_phone_size();
});
const source = ref(0);
const outputValue = useTransition(source, {
	duration: 1500,
});

source.value = 100000;
echarts.use([TitleComponent, TooltipComponent, GridComponent, VisualMapComponent, LineChart, CanvasRenderer, UniversalTransition, LegendComponent]);

const activeName = ref('1');

function getRandomGreeting() {
	const greetings = ['祝你好运连连', '祝你开心每一天', '祝你心想事成', '祝你万事如意', '祝你笑口常开'];
	return greetings[Math.floor(Math.random() * greetings.length)];
}

const getGreetingTime = () => {
	const now = new Date();
	const hour = now.getHours();
	if (hour < 5) {
		return '深夜好';
	} else if (hour < 7) {
		return '凌晨好';
	} else if (hour < 12) {
		return '早上好';
	} else if (hour < 14) {
		return '中午好';
	} else if (hour < 18) {
		return '下午好';
	} else if (hour < 21) {
		return '傍晚好';
	} else if (hour < 24) {
		return '晚上好';
	} else {
		return '你好'; // 以防万一，虽然实际上不会执行到这里
	}
};

const message = !phone_bool.value
	? '愿你的每一天都充满阳光，每一步都走得坚定。在这个忙碌的世界里，愿你找到属于自己的宁静与快乐。祝福你，陌生人，愿好运常伴你左右。🌟'
	: '在这个忙碌的世界里，愿你找到属于自己的宁静与快乐。🌟';
const text = ref('');

// 递归调用 实现打字效果
const type = (index: number) => {
	if (index < message.length) {
		text.value = message.slice(0, index + 1);
		setTimeout(() => type(index + 1), 20); // 100毫秒打一个字
	}
};

onMounted(() => {
	type(0);
});

const say_hello = computed(() => {
	const greetingTime = getGreetingTime();
	const randomGreeting = getRandomGreeting();
	return `${greetingTime} ${user_data.token.username}，${randomGreeting}`;
});
const colorList = ['#9E87FF', '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF'];
const option = ref({
	title: {
		text: '数据预览',
		textStyle: {
			fontSize: 12,
			fontWeight: 400,
		},
		left: '0',
		top: '5%',
	},
	legend: {
		icon: 'circle',
		top: '5%',
		right: '5%',
		itemWidth: 6,
		itemGap: 20,
		textStyle: {
			color: '#556677',
		},
	},
	tooltip: {
		trigger: 'axis',
		axisPointer: {
			label: {
				show: true,
				backgroundColor: '#fff',
				color: '#556677',
				borderColor: 'rgba(0,0,0,0)',
				shadowColor: 'rgba(0,0,0,0)',
				shadowOffsetY: 0,
			},
			lineStyle: {
				width: 0,
			},
		},
		backgroundColor: '#fff',
		textStyle: {
			color: '#5c6c7c',
		},
		padding: [10, 10],
		extraCssText: 'box-shadow: 1px 0 2px 0 rgba(163,163,163,0.5)',
	},
	grid: {
		top: '15%',
	},
	xAxis: [
		{
			type: 'category',
			data: ['周一', '周二', '周三', '周四', '周五', '周六', '周天'],
			axisLine: {
				lineStyle: {
					color: '#DCE2E8',
				},
			},
			axisTick: {
				show: false,
			},
			axisLabel: {
				interval: 0,
				textStyle: {
					color: '#556677',
				},
				// 默认x轴字体大小
				fontSize: 12,
				// margin:文字到x轴的距离
				margin: 15,
			},
			axisPointer: {
				label: {
					// padding: [11, 5, 7],
					padding: [0, 0, 10, 0],

					// 这里的margin和axisLabel的margin要一致!
					margin: 15,
					// 移入时的字体大小
					fontSize: 12,
					backgroundColor: {
						type: 'linear',
						x: 0,
						y: 0,
						x2: 0,
						y2: 1,
						colorStops: [
							{
								offset: 0,
								color: '#fff', // 0% 处的颜色
							},
							{
								// offset: 0.9,
								offset: 0.86,
								/*
0.86 = （文字 + 文字距下边线的距离）/（文字 + 文字距下边线的距离 + 下边线的宽度）

            */
								color: '#fff', // 0% 处的颜色
							},
							{
								offset: 0.86,
								color: '#33c0cd', // 0% 处的颜色
							},
							{
								offset: 1,
								color: '#33c0cd', // 100% 处的颜色
							},
						],
						global: false, // 缺省为 false
					},
				},
			},
			boundaryGap: false,
		},
	],
	yAxis: [
		{
			type: 'value',
			axisTick: {
				show: false,
			},
			axisLine: {
				show: true,
				lineStyle: {
					color: '#DCE2E8',
				},
			},
			axisLabel: {
				textStyle: {
					color: '#556677',
				},
			},
			splitLine: {
				show: false,
			},
		},
		{
			type: 'value',
			position: 'right',
			axisTick: {
				show: false,
			},
			axisLabel: {
				textStyle: {
					color: '#556677',
				},
				formatter: '{value}',
			},
			axisLine: {
				show: true,
				lineStyle: {
					color: '#DCE2E8',
				},
			},
			splitLine: {
				show: false,
			},
		},
	],
	series: [
		{
			name: phone_bool.value ? '制卡' : '卡密制作',
			type: 'line',
			data: [10, 10, 30, 12, 15, 3, 7],
			symbolSize: 1,
			symbol: 'circle',
			smooth: true,
			yAxisIndex: 0,
			showSymbol: false,
			lineStyle: {
				width: 5,
				color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
					{
						offset: 0,
						color: '#9effff',
					},
					{
						offset: 1,
						color: '#9E87FF',
					},
				]),
				shadowColor: 'rgba(158,135,255, 0.3)',
				shadowBlur: 10,
				shadowOffsetY: 20,
			},
			itemStyle: {
				normal: {
					color: colorList[0],
					borderColor: colorList[0],
				},
			},
		},
		{
			name: phone_bool.value ? '注册' : '用户注册',
			type: 'line',
			data: [5, 12, 11, 14, 25, 16, 10],
			symbolSize: 1,
			symbol: 'circle',
			smooth: true,
			yAxisIndex: 0,
			showSymbol: false,
			lineStyle: {
				width: 5,
				color: new echarts.graphic.LinearGradient(1, 1, 0, 0, [
					{
						offset: 0,
						color: '#73DD39',
					},
					{
						offset: 1,
						color: '#73DDFF',
					},
				]),
				shadowColor: 'rgba(115,221,255, 0.3)',
				shadowBlur: 10,
				shadowOffsetY: 20,
			},
			itemStyle: {
				normal: {
					color: colorList[1],
					borderColor: colorList[1],
				},
			},
		},
		{
			name: phone_bool.value ? '登录' : '卡密/用户登录',
			type: 'line',
			data: [150, 120, 170, 140, 500, 160, 110],
			symbolSize: 1,
			yAxisIndex: 1,
			symbol: 'circle',
			smooth: true,
			showSymbol: false,
			lineStyle: {
				width: 5,
				color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
					{
						offset: 0,
						color: '#fe9a',
					},
					{
						offset: 1,
						color: '#fe9a8b',
					},
				]),
				shadowColor: 'rgba(254,154,139, 0.3)',
				shadowBlur: 10,
				shadowOffsetY: 20,
			},
			itemStyle: {
				normal: {
					color: colorList[2],
					borderColor: colorList[2],
				},
			},
		},
	],
});
</script>

<style lang="scss" scoped>
.el-col {
	text-align: center;
}
.top_menu_card {
	display: flex;
	align-items: center;
	margin: 10px 10px 10px 10px;
	justify-content: center;
	height: 140px;
	box-shadow: none;
}

.top_menu_list {
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;

	img {
		width: 40px;
		height: 40px;
	}

	span {
		text-align: center;
	}
}

.top_menu_text {
	height: 100%;
	display: flex;
	flex-direction: row;
}

.top_menu_title {
	width: 100%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	margin-left: 20px;
}

.left_info {
	height: 100px;
	width: 100%;
	display: flex;
	align-items: center;
}

.echarts-box {
	min-height: calc(100vh - 100px);
}

.chart {
	width: 100%;
	height: 50vh;
}

.top_menu_bar {
	display: grid;
	grid-template-columns: 1fr 1fr;
}

.top_menu_right {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(80px, 1fr));
}

.bottom_menu_bar {
	display: grid;
	grid-template-columns: 1fr 1fr;
}

.demo-collapse {
	margin-top: 16px;
}

.top_icon {
	width: 60px;
	height: 60px;
	border-radius: 100%;
	margin-right: 8px;
}
.center_icon {
	width: 30px;
	height: 30px;
}

.center_static {
	display: grid;
	align-items: center;
	grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
}

@media only screen and (max-width: 1200px) {
	.top_menu_bar {
		display: grid;
		grid-template-columns: 1fr; /* 第二行平铺4个 */
	}

	.top_menu_card {
		height: 120px;
	}
	.bottom_menu_bar {
		display: grid;
		grid-template-columns: 1fr;
	}

	.center_icon {
		width: 30px !important;
	}
	.top_icon {
		width: 40px;
		height: 40px;
	}
	.top_menu_title {
		span {
			font-size: 12px;
		}
	}

	.center_static {
		grid-template-columns: repeat(auto-fit, minmax(120px, 2fr));
	}
}
</style>
