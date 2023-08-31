package com.iuprojects.smartFridgeManagerBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    //create a new item
    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    //get all items
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    //get item by id
    public Optional<Item> getItemById(Long id){
        return itemRepository.findById(id);
    }

    //update item
    public Item updateItem(Long id, Item itemNew){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()){
            Item itemPresent = item.get();
            itemPresent.setName(itemNew.getName());
            itemPresent.setPurchaseDate(itemNew.getPurchaseDate());
            itemPresent.setBestBeforeDate(itemNew.getBestBeforeDate());
            itemPresent.setOpeningDate(itemNew.getOpeningDate());
            return itemRepository.save(itemPresent);
        }
        return null;
    }

    //delete item
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
}
