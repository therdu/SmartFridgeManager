<template>
    1234ksgklhfkjüoewaij
    <v-btn @click="testAxios">
        GO
    </v-btn>
    {{ items }}
</template>

<script setup lang="ts">
import axios from "axios";
import { ref } from "vue";

const items = ref([])

async function testAxios() {
    axios.get("http://localhost:8080/items")
        .then(response => { items.value = response.data })
        .catch(error => { console.error("error fetching data: ", error) }
        )
    console.log(items.value)
}
</script>