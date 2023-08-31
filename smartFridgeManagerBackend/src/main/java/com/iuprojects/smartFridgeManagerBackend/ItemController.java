package com.iuprojects.smartFridgeManagerBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    //create a new item
    @PostMapping
    public Item createItem(@RequestBody Item item){
        return itemService.createItem(item);
    }

    //get all items
    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    //get item by id
    @GetMapping("/{id}")
    public Optional<Item> getItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }

    //update user by id
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item){
        return itemService.updateItem(id, item);
    }

    //delete item by id
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
    }
}
