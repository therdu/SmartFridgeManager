<template>
    <v-card>
        <v-card-title>
            <h2 class="m-2">Your current stock:</h2>
        </v-card-title>
        <v-card-content>
            <v-text-field v-model="search" label="Search Item" single-line hide-details class="mx-4"></v-text-field>
            <v-data-table items-per-page="10" :headers="headers" :items="items" item-value="name" class="elevation-0 p-2"
                :sort-by="[{ key: 'purchaseDate', order: 'asc' }]" :search="search">

                <template v-slot:item="row">
                    <tr>
                        <td>{{ row.item.raw.name }}</td>
                        <td class="text-right">{{ row.item.raw.purchaseDate }}</td>
                        <td class="text-right dueDate">{{ row.item.raw.bestBeforeDate }}</td>
                        <td class="text-right">{{ row.item.raw.openingDate }}</td>
                        <td class="text-right">
                            <v-icon size="small" class="me-2" @click="editItem(row.item.raw)">
                                mdi-pencil
                            </v-icon>
                            <v-icon size="small" color="red" @click="deleteItem(row.item.raw)">
                                mdi-delete
                            </v-icon>
                        </td>
                    </tr>
                </template>
            </v-data-table>
        </v-card-content>
    </v-card>
</template>

<script setup lang="ts">
import axios from "axios";
import { ref, onMounted } from "vue";
import type { Item } from "./Item";
import { VDataTable } from 'vuetify/labs/VDataTable';

const items = ref<Item[]>([])


const search = ref<string>("")

const headers = ref<any>([
    { title: 'Item', align: 'start', sortable: true, key: 'name', },
    { title: 'Purchase Date', align: 'end', key: 'purchaseDate' },
    { title: 'Best Before Date', align: 'end', key: 'bestBeforeDate' },
    { title: 'Opening Date', align: 'end', key: 'openingDate' },
    { title: 'Actions', align: 'end', sortable: false }
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

const updateDialogIsOpen = ref<boolean>(false)
const currentItem = ref<Item>()

async function editItem(item: Item) {
    updateDialogIsOpen.value = true;
    currentItem.value = item
}

async function updateItem(item: Item) {
    await axios.put("http://localhost:8080/items/" + item.id, item)
        .catch(error => { console.error("error fetching data: ", error) }
        )
    updateDialogIsOpen.value = false
}

async function deleteItem(item: Item) {
    await axios.delete("http://localhost:8080/items/" + item.id)
        .catch(error => { console.error("error fetching data: ", error) }
        )
}
</script>
<style scoped>
.dueDate {
    color: red
}
</style>