<template>
    <v-card>
        <v-card-title>
            <h2 class="m-2">Your current stock:</h2>
        </v-card-title>
        <v-text-field v-model="search" label="Search Item" single-line hide-details class="mx-4"></v-text-field>
        <v-data-table items-per-page="10" :headers="headers" :items="items" item-value="name" class="elevation-0 p-2"
            :sort-by="[{ key: 'purchaseDate', order: 'asc' }]" :search="search">

            <template v-slot:item="row">
                <tr>
                    <td>{{ row.item.raw.name }}</td>
                    <td class="text-right">{{ row.item.raw.purchaseDate == null ? '' : new
                        Date(row.item.raw.purchaseDate).toLocaleDateString() }}</td>
                    <td class="text-right" :class="{ expired: isExpired(row.item.raw.bestBeforeDate) }">{{
                        row.item.raw.bestBeforeDate == null ? '' : new
                            Date(row.item.raw.bestBeforeDate).toLocaleDateString() }}</td>
                    <td class="text-right">{{ row.item.raw.openingDate == null ? '' : new
                        Date(row.item.raw.openingDate).toLocaleDateString() }}</td>
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
    </v-card>
    <UpdateItemForm :dialogIsOpen="updateDialogIsOpen" :currentItem="currentItem" :items="items"
        @getItems="emit('getItems')" @closeDialog="closeUpdateDialog" />
</template>

<script setup lang="ts">
import axios from "axios";
import { ref, PropType, toRefs } from "vue";
import type { Item } from "./Item";
import { VDataTable } from 'vuetify/labs/VDataTable';
import UpdateItemForm from "./UpdateItemForm.vue"

const props = defineProps({
    items: Object as PropType<Item[]>
})

const { items } = toRefs(props);

const emit = defineEmits(["getItems"])

const search = ref<string>("")

const headers = ref<any>([
    { title: 'Item', align: 'start', sortable: true, key: 'name', },
    { title: 'Purchase Date', align: 'end', key: 'purchaseDate' },
    { title: 'Best Before Date', align: 'end', key: 'bestBeforeDate' },
    { title: 'Opening Date', align: 'end', key: 'openingDate' },
    { title: 'Actions', align: 'end', sortable: false, key: '' }
])

const updateDialogIsOpen = ref<boolean>(false)
const currentItem = ref<Item>()

function editItem(item: Item) {
    updateDialogIsOpen.value = true;
    currentItem.value = item
}

async function deleteItem(item: Item) {
    await axios.delete("http://localhost:8080/items/" + item.id)
        .catch(error => { console.error("error fetching data: ", error) }
        )
    emit("getItems")
}

function closeUpdateDialog() {
    updateDialogIsOpen.value = false
    emit("getItems")
}

function isExpired(date: string) {
    return new Date() > new Date(date)
}
</script>
<style scoped>
.expired {
    color: red
}
</style>