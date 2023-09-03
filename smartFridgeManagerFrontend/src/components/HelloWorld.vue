<script setup lang="ts">
import TestComponent from './TestComponent.vue';

defineProps<{
  msg: string
}>()

</script>

<template>
  <TestComponent />
</template>

<style scoped></style>
