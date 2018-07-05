package com.barclays.inventory.commands;

import com.barclays.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Report extends AbstractCommand {

    @Autowired
    private InventoryService inventoryService;

    @Override
    public Report processCommand(String[] command){
        return this;
    }


    @Override
    public ResponseEntity execute() {
        return new ResponseEntity(inventoryService.inventoryReport(), HttpStatus.OK);
    }
}
