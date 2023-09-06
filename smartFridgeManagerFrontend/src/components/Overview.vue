<template>
    <div class="background p-5">
        <h1> Welcome to your SmartFridgeManager! </h1>
        <AddItemForm :items="items" @getItems="getItems" />
        <Stock :items="items" @getItems="getItems" />
    </div>
</template>

<script setup lang="ts">
import axios from "axios";
import Stock from "./Stock.vue"
import AddItemForm from "./AddItemForm.vue"
import { ref, onMounted } from "vue";
import type { Item } from "./Item";

onMounted(() => {
    getItems()
})

const items = ref<Item[]>([])

async function getItems() {
    await axios.get("http://localhost:8080/items")
        .then(response => { items.value = response.data })
        .catch(error => { console.error("error fetching data: ", error) }
        )
    console.log("Items sind neu geladen")
}
</script>

<style scoped>
.background {
    background-color: lightblue
}
</style>