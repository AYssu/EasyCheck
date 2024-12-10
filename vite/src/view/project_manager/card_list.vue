<template>
	<div>
		<div style="margin-bottom: 20px">
			<el-input placeholder="请输入搜索内容" style="width: 40%; margin-right: 10px">
				<template #prepend>
					<el-button :icon="Search"></el-button>
				</template>
			</el-input>

			<el-button type="primary" plain>添加卡密</el-button>
		</div>
		<el-table :data="tableData" :default-sort="{ prop: 'date', order: 'descending' }">
			<el-table-column prop="id" width="100" label="卡密ID" />
			<el-table-column prop="project_name" width="120" label="归属程序">
				<template #default="scope">
					<el-tag type="primary">{{ scope.row.project_name }}</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="date" label="创建日期" sortable width="120" />
			<el-table-column prop="type" label="卡密类型" width="120">
				<template #default="scope">
					<el-tag type="info">{{ scope.row.type }}</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="key" label="卡密" width="150">
				<template #default="scope">
					<el-link type="warning">{{ scope.row.key }}</el-link>
				</template>
			</el-table-column>

			<el-table-column prop="introduction" label="卡密备注" width="100" show-overflow-tooltip>
				<template #default="scope">
					<el-text type="info" style="margin-left: 5px">{{ scope.row.introduction }}</el-text>
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template #default>
					<el-button type="primary" size="small" plain>通用编辑</el-button>
					<el-button type="danger" size="small" plain>删除卡密</el-button>
				</template>
			</el-table-column>
		</el-table>
		<div style="margin-top: 20px; display: flex; justify-content: flex-end; margin-right: 20px">
			<el-pagination
				v-model:current-page="currentPage2"
				v-model:page-size="pageSize2"
				:page-sizes="[20, 50, 80, 400]"
				:size="size"
				:disabled="disabled"
				:background="background"
				layout="sizes, prev, pager, next"
				:total="1000"
				@size-change="handleSizeChange"
				@current-change="handleCurrentChange"
			/>
		</div>
	</div>
</template>

<script lang="ts" setup>
import { Search } from '@element-plus/icons-vue';
import { ComponentSize } from 'element-plus';
import { ref } from 'vue';

interface Card {
	id: number; // 卡密编号
	project_name: string; // 项目名称
	date: string; // 创建日期
	type: string; // 卡密类型
	key: string; // 卡密
	introduction: string; // 卡密备注
}

const tableData: Card[] = [
	{
		id: 1,
		project_name: '项目1',
		date: '2023-05-01',
		type: '体验会员卡',
		key: '123456789',
		introduction: '这是一条简单的卡密备注',
	},
];

const currentPage2 = ref(5);
const pageSize2 = ref(20);
const size = ref<ComponentSize>('default');
const background = ref(false);
const disabled = ref(false);

const handleSizeChange = (val: number) => {
	console.log(`${val} items per page`);
};
const handleCurrentChange = (val: number) => {
	console.log(`current page: ${val}`);
};
</script>
