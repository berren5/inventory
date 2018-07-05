package com.barclays.inventory.commands;

import com.barclays.inventory.service.InventoryService;
import com.barclays.inventory.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateSell extends AbstractCommand {

    private Item item;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public UpdateSell processCommand(String[] command){
        Item updateSellItem = new Item();
        updateSellItem.setItemName(command[1]);
        updateSellItem.setQuantity(Integer.valueOf(command[2]));
        item = updateSellItem;
        return this;
    }


    @Override
    public ResponseEntity execute() {
        this.success = inventoryService.updateSell(this.item);
        return super.execute();
    }
}
