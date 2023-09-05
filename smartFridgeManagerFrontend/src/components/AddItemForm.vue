<template>
    <v-dialog v-model="dialogIsOpen" persistent width="1024">
        <template v-slot:activator="{ props }">
            <v-btn color="primary" v-bind="props">
                Add a new Item
            </v-btn>
        </template>
        <v-card>
            <v-card-title class="pt-4">
                <span class="text-h5">Add a new Item to your fridge:</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6">
                            <v-combobox v-model="newItem.name" label="Name*" :items="currentItems"></v-combobox>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field v-model="newItem.purchaseDate" label="Purchase Date*" type="date"></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field v-model="newItem.bestBeforeDate" label="Best Before Date"
                                type="date"></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field v-model="newItem.openingDate" label="Opening Date" type="date"></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="grey" variant="text" @click="dialogIsOpen = false">
                    Close
                </v-btn>
                <v-btn color="primary" variant="text" @click="addItem" :disabled="saveItemIsDisabled">
                    Save
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from "vue";
import axios from "axios";
import type { Item } from "./Item"

const dialogIsOpen = ref<boolean>(false)

const saveItemIsDisabled = computed(() => { return (!newItem.value.name || !newItem.value.purchaseDate) })

const newItem = ref({ name: "", purchaseDate: new Date().toISOString().slice(0, 10), openingDate: null, bestBeforeDate: null })

function addItem() {
    sendItem()
    newItem.value = { name: "", purchaseDate: new Date().toISOString().slice(0, 10), openingDate: null, bestBeforeDate: null }
    dialogIsOpen.value = false
    getItems()
}

const currentItems = ref([])

onMounted(() => {
    getItems()
})

async function getItems() {
    await axios.get("http://localhost:8080/items")
        .then(response => { currentItems.value = response.data.map((item: Item) => item.name) })
        .catch(error => { console.error("error fetching data: ", error) }
        )
}

async function sendItem() {
    await axios.post("http://localhost:8080/items", newItem.value)
        .catch(error => { console.error("error fetching data: ", error) }
        )
}
</script>