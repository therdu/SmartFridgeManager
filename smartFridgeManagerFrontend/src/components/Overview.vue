<template>
    <h1> Welcome at your SmartFridgeManager! </h1>
    <Stock />
    <AddItemForm />
</template>

<script setup lang="ts">
import { ref } from "vue";
import Stock from "./Stock.vue"
import AddItemForm from "./AddItemForm.vue"
</script>

<style scoped>
.flex {
    display: flex
}
</style>