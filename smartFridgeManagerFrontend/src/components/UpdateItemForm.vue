<template>
    <v-dialog v-model="dialogIsOpen" persistent width="1024">
        <!-- <template v-slot:activator="{ props }">
            <v-btn color="green" v-bind="props" class="m-4">
                Add a new Item
            </v-btn>
        </template> -->
        <v-card>
            <v-card-title class="pt-4">
                <span class="text-h5">Edit your item: </span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6">
                            <v-combobox v-model="currentItem.name" label="Name*" :items="allItemNames"></v-combobox>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field v-model="currentItem.purchaseDate" label="Purchase Date*"
                                type="date"></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field v-model="currentItem.bestBeforeDate" label="Best Before Date"
                                type="date"></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field v-model="currentItem.openingDate" label="Opening Date" type="date"></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="grey" variant="text" @click="emit('closeDialog')">
                    Close
                </v-btn>
                <v-btn color="primary" variant="text" @click="updateItem(currentItem)" :disabled="saveItemIsDisabled">
                    Save
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script setup lang="ts">
import { computed, PropType, toRefs, defineEmits } from "vue";
import axios from "axios";
import type { Item } from "./Item"

const props = defineProps({
    dialogIsOpen: Boolean,
    currentItem: Object as PropType<Item>,
    items: Object as PropType<Item[]>
})

const { items, dialogIsOpen, currentItem } = toRefs(props);

const emit = defineEmits(["getItems", "closeDialog"])

// only update item when name and purchase date are set
const saveItemIsDisabled = computed(() => { return (!currentItem.value.name || !currentItem.value.purchaseDate) })

// get names for recommendation's list
const allItemNames = computed(() => { return items.value.map((item: Item) => item.name) })

// call put method and send emits to get all updated items and close dialog
async function updateItem(item: Item) {
    await axios.put("http://localhost:8080/items/" + item.id, item)
        .catch(error => { console.error("error fetching data: ", error) }
        )
    emit("closeDialog")
    emit("getItems")
}
</script>