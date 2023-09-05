<template>
    <div class="background p-5">
        <h1> Welcome to your SmartFridgeManager! </h1>
        <AddItemForm />
        <Stock />
    </div>
</template>

<script setup lang="ts">
import Stock from "./Stock.vue"
import AddItemForm from "./AddItemForm.vue"
</script>

<style scoped>
.background {
    background-color: lightblue
}
</style>