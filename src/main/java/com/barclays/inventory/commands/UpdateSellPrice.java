package com.barclays.inventory.commands;

import com.barclays.inventory.service.InventoryService;
import com.barclays.inventory.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UpdateSellPrice extends AbstractCommand {

    private Item item;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public UpdateSellPrice processCommand(String[] command){
        Item updateSellPriceItem = new Item();
        updateSellPriceItem.setItemName(command[1]);
        updateSellPriceItem.setSellingPrice(new BigDecimal(command[2]));
        item = updateSellPriceItem;
        return this;
    }


    @Override
    public ResponseEntity execute() {
        this.success = inventoryService.updateSellPrice(this.item);
        return super.execute();
    }
}
