<template>
    <v-dialog v-model="dialogIsOpen" persistent width="1024">
        <template v-slot:activator="{ props }">
            <v-btn color="green" v-bind="props" class="m-4">
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
                            <v-combobox v-model="newItem.name" label="Name*" :items="allItemNames"></v-combobox>
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
import { ref, computed, PropType, toRefs, defineEmits } from "vue";
import axios from "axios";
import type { Item } from "./Item"

const props = defineProps({
    items: Object as PropType<Item[]>
})

const { items } = toRefs(props);

const emit = defineEmits(["getItems"])

const dialogIsOpen = ref<boolean>(false)

const newItem = ref({ name: "", purchaseDate: new Date().toISOString().slice(0, 10), openingDate: null, bestBeforeDate: null })

//only save form when name and purchaseDate are set
const saveItemIsDisabled = computed(() => { return (!newItem.value.name || !newItem.value.purchaseDate) })

// get only the names for the recommendation's list
const allItemNames = computed(() => { return items.value.map((item: Item) => item.name) })

// call send new item to backend an reset form
function addItem() {
    sendItem()
    emit("getItems")
    newItem.value = { name: "", purchaseDate: new Date().toISOString().slice(0, 10), openingDate: null, bestBeforeDate: null }
    dialogIsOpen.value = false
}

//send item to backend
async function sendItem() {
    await axios.post("http://localhost:8080/items", newItem.value)
        .catch(error => { console.error("error fetching data: ", error) }
        )
}
</script>