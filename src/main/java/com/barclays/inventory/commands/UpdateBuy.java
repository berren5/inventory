package com.barclays.inventory.commands;

import com.barclays.inventory.service.InventoryService;
import com.barclays.inventory.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateBuy extends AbstractCommand {

    private Item item = new Item();

    @Autowired
    private InventoryService inventoryService;

    @Override
    public UpdateBuy processCommand(String[] command){
        this.item.setItemName(command[1]);
        this.item.setQuantity(Integer.valueOf(command[2]));
        return this;
    }


    @Override
    public ResponseEntity execute() {
        this.success = inventoryService.updateBuy(this.item);
        return super.execute();

    }
}
