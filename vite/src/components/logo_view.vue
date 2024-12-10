<template>
	<div class="logo_container">
		<img :src="logo" alt="logo" class="object-cover" />
		<div class="logo-text">
			<span class="logo-text-title">Easy Verify</span>
			<span class="logo-text-message">network manager</span>
		</div>
	</div>
</template>

<script lang="ts" setup>
import logo from '@/assets/icon.png';
</script>

<style lang="scss" scoped>
.logo_container {
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 20px 0px 0 0px;
}

.object-cover {
	width: 40px;
	height: 40px;
}

.logo-text {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.logo-text-title {
	font-size: 0.8rem;
	color: #17926c;
	font-weight: bold;
}

.logo-text-message {
	font-size: 0.7rem;
	color: #17926c;
	font-style: oblique;
}
</style>
