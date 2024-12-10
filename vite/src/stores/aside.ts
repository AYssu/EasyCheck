import { defineStore } from 'pinia';
import { ref } from 'vue';

export const aside_status = defineStore('easy_aside_status', () => {
	const status = ref(true); // 初始状态为 true

	const setStatus = (newStatus: boolean) => {
		status.value = newStatus;
	};

	const toggleStatus = () => {
		status.value = !status.value;
	};

	return {
		status,
		setStatus,
		toggleStatus,
	};
});
