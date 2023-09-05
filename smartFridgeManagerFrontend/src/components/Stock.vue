<template>
    <v-card title="Your current stock">
        <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line hide-details></v-text-field>
        <v-data-table items-per-page="10" :headers="headers" :items="items" item-value="name" class="elevation-1"
            :sort-by="[{ key: 'purchaseDate', order: 'asc' }]" :search="search" mobile-breakpoint="800">

            <template v-slot:item="row">
                <tr class="text-left">
                    <td cols="5">{{ row.item.raw.name }}</td>
                    <td cols="2">{{ row.item.raw.purchaseDate }}</td>
                    <td cols="2">{{ row.item.raw.bestBeforeDate }}</td>
                    <td cols="2">{{ row.item.raw.openingDate }}</td>
                    <td cols="1">
                        <v-icon size="small" class="me-2" @click="editItem(row.item.raw)">
                            mdi-pencil
                        </v-icon>
                        <v-icon size="small" @click="deleteItem(row.item.raw)">
                            mdi-delete
                        </v-icon>
                    </td>
                </tr>
            </template>
        </v-data-table>
        <v-card-actions>
            <v-btn @click="getItems">Get all items</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script setup lang="ts">
import axios from "axios";
import { ref, onMounted, computed } from "vue";
import type { Item } from "./Item";
import { VDataTable } from 'vuetify/labs/VDataTable';

const items = ref<Item[]>([])

const search = ref<string>("")

const headers = ref<any>([
    { title: 'Item', align: 'start', sortable: true, key: 'name', },
    { title: 'Purchase Date', align: 'start', key: 'purchaseDate' },
    { title: 'Best Before Date', align: 'start', key: 'bestBeforeDate' },
    { title: 'Opening Date', align: 'start', key: 'openingDate' },
    { title: 'Actions', align: 'start', sortable: false }
])

onMounted(() => {
    getItems()
})

async function getItems() {
    await axios.get("http://localhost:8080/items")
        .then(response => { items.value = response.data })
        .catch(error => { console.error("error fetching data: ", error) }
        )
}

function editItem(item: Item) {
    console.log("bearbeite Item: ", item)
}

function deleteItem(item: Item) {
    console.log("lösche Item: ", item)
}
</script>