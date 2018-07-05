package com.barclays.inventory.commands;

import com.barclays.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractCommand implements CommandStrategy {

    protected boolean success = false;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public ResponseEntity execute() {
        if(success){
            return new ResponseEntity(inventoryService.getItemMap(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Override
    public CommandStrategy processCommand(String[] commandArray) {
        return null;
    }
}
