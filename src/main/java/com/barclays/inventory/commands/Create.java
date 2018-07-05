package com.barclays.inventory.commands;

import com.barclays.inventory.item.Item;
import com.barclays.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Create extends AbstractCommand {

    private Item item;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public Create processCommand(String[] command){
        Item createItem = new Item();
        createItem.setItemName(command[1]);
        createItem.setCostPrice(new BigDecimal(command[2]));
        createItem.setSellingPrice(new BigDecimal(command[3]));
        createItem.setQuantity(0);
        item = createItem;
        return this;
    }


    @Override
    public ResponseEntity execute() {
        this.success = inventoryService.create(this.item);
        return super.execute();
    }
}
