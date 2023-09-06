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

// get all items from backend
async function getItems() {
    await axios.get("http://localhost:8080/items")
        .then(response => { items.value = response.data })
        .catch(error => { console.error("error fetching data: ", error) }
        )
    //parse all dates to correct format
    items.value.forEach(item => {
        item.purchaseDate == null ? null : (item.purchaseDate = new Date(item.purchaseDate).toISOString().slice(0, 10));
        item.bestBeforeDate == null ? null : (item.bestBeforeDate = new Date(item.bestBeforeDate).toISOString().slice(0, 10));
        item.openingDate == null ? null : (item.openingDate = new Date(item.openingDate).toISOString().slice(0, 10));
    })
}
</script>

<style scoped>
.background {
    background-color: lightblue;
    min-height: 100vh;
}
</style>