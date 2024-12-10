import { defineStore } from 'pinia';
import { ref } from 'vue';

export const theme_status = defineStore('easy_theme_status', () => {
	const dark = ref(false); // 初始状态为 false

	const setStatus = (newStatus: boolean) => {
		dark.value = newStatus;
	};

	const toggleStatus = () => {
		dark.value = !dark.value;
	};

	return {
		dark,
		setStatus,
		toggleStatus,
	};
});
