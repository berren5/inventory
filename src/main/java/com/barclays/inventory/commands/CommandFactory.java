package com.barclays.inventory.commands;

import com.barclays.inventory.commands.*;
import com.barclays.inventory.constants.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    @Autowired
    private Create create;

    @Autowired
    private Delete delete;

    @Autowired
    private UpdateBuy updateBuy;

    @Autowired
    private Report report;

    @Autowired
    UpdateSell updateSell;

    @Autowired
    UpdateSellPrice updateSellPrice;

    public CommandStrategy getCommand(String command){
        String[] commandArray = command.split(" ");
        Commands action = Commands.valueOf(commandArray[0].toUpperCase());
        CommandStrategy commandStrategy;
        switch (action){
            case CREATE:
                commandStrategy = create;
                break;
            case DELETE:
                commandStrategy = delete;
                break;
            case UPDATEBUY:
                commandStrategy = updateBuy;
                break;
            case UPDATESELL:
                commandStrategy = updateSell;
                break;
            case REPORT:
                commandStrategy = report;
                break;
            case UPDATESELLPRICE:
                commandStrategy = updateSellPrice;
                break;
            default:
                throw new IllegalArgumentException("Invalid State");
        }
        return commandStrategy.processCommand(commandArray);
    }
}
