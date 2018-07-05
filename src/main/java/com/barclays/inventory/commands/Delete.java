package com.barclays.inventory.commands;

import com.barclays.inventory.service.InventoryService;
import com.barclays.inventory.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Delete extends AbstractCommand {

    private Item item;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public Delete processCommand(String[] command){
        Item deleteItem = new Item();
        deleteItem.setItemName(command[1]);
        item = deleteItem;
        return this;
    }


    @Override
    public ResponseEntity execute() {
        this.success = inventoryService.delete(this.item);
        return super.execute();
    }
}
