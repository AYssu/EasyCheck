
<template>
    <div class="logo_container">
        <img :src="logo" class="object-cover"  alt="logo"/>
    </div>
</template>

<script setup lang="ts">
import logo from '@/assets/logo.jpg'
</script>

<style scoped lang="scss">
.logo_container {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
.object-cover {
    width: 70%;
    height: 100%;
    object-fit: cover;
    margin-top : 20px;
}
@media only screen and (max-width: 1200px) {
  .object-cover {
    width: 50%;
  }
}
</style>